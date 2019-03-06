/**
 * Copyright (c) 2016, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 * <p>
 * WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wso2telco.core.authfilter.authentication;

import com.wso2telco.core.authfilter.util.AuthFilterParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.api.model.AccessTokenInfo;
import org.wso2.carbon.apimgt.impl.APIConstants;
import org.wso2.carbon.apimgt.impl.factory.KeyManagerHolder;
import org.wso2.carbon.apimgt.impl.utils.APIUtil;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.user.core.service.RealmService;
import org.wso2.carbon.utils.multitenancy.MultitenantConstants;
import org.wso2.carbon.utils.multitenancy.MultitenantUtils;

import javax.ws.rs.container.ContainerRequestContext;
import java.lang.reflect.Method;

public class BearerAuthenticator {
    private static final String SUPER_TENANT_SUFFIX =
            APIConstants.EMAIL_DOMAIN_SEPARATOR + MultitenantConstants.SUPER_TENANT_DOMAIN_NAME;
    private static final Log log = LogFactory.getLog(BearerAuthenticator.class);

    /**
     * @param authorizationHeader Authorization Header value
     * @return boolean
     */
    public boolean isAuthenticatedUser(ContainerRequestContext requestContext, Method method, String
            authorizationHeader) {
        String accessToken = authorizationHeader
                .replaceFirst(AuthFilterParam.AUTHENTICATION_SCHEME_BEARER.getTObject() + " ", "");
        AccessTokenInfo tokenInfo = null;
        try {
            tokenInfo = KeyManagerHolder.getKeyManagerInstance().getTokenMetaData(accessToken);
        } catch (APIManagementException e) {
            log.error("Error while retrieving token information for token: " + accessToken, e);
        }
        // if we got valid access token we will proceed with next
        if (tokenInfo != null && tokenInfo.isTokenValid()) {

            //If scope validation successful then set tenant name and user name to current context
            String tenantDomain = MultitenantUtils.getTenantDomain(tokenInfo.getEndUserName());
            int tenantId;
            PrivilegedCarbonContext carbonContext = PrivilegedCarbonContext.getThreadLocalCarbonContext();
            RealmService realmService = (RealmService) carbonContext.getOSGiService(RealmService.class, null);
            try {
                String username = tokenInfo.getEndUserName();
                if (MultitenantConstants.SUPER_TENANT_DOMAIN_NAME.equals(tenantDomain)) {
                    if (username.endsWith(SUPER_TENANT_SUFFIX)) {
                        username = username.substring(0, username.length() - SUPER_TENANT_SUFFIX.length());
                    }
                }
                tenantId = realmService.getTenantManager().getTenantId(tenantDomain);
                carbonContext.setTenantDomain(tenantDomain);
                carbonContext.setTenantId(tenantId);
                carbonContext.setUsername(username);
                if (!tenantDomain.equals(MultitenantConstants.SUPER_TENANT_DOMAIN_NAME)) {
                    APIUtil.loadTenantConfigBlockingMode(tenantDomain);
                }
                return true;
            } catch (org.wso2.carbon.user.api.UserStoreException e) {
                log.error("Error while retrieving tenant id for tenant domain: " + tenantDomain, e);
            }
        } else {
            log.error("Authentication failed. Please check your token");
        }
        return false;
    }
}
