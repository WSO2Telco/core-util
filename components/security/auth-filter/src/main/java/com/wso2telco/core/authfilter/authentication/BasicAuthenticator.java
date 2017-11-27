/*******************************************************************************
 * Copyright  (c) 2015-2016, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
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
 ******************************************************************************/
package com.wso2telco.core.authfilter.authentication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.CarbonException;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.core.util.AnonymousSessionUtil;
import org.wso2.carbon.registry.core.service.RegistryService;
import org.wso2.carbon.user.core.UserRealm;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.carbon.user.core.service.RealmService;
import org.wso2.carbon.utils.multitenancy.MultitenantUtils;

public class BasicAuthenticator {

	private final Log log = LogFactory.getLog(BasicAuthenticator.class);

	public boolean isAuthenticatedUser(String userName, String password) {

		PrivilegedCarbonContext carbonContext = PrivilegedCarbonContext.getThreadLocalCarbonContext();
		RealmService realmService = (RealmService) carbonContext.getOSGiService(RealmService.class, null);
		RegistryService registryService = (RegistryService) carbonContext.getOSGiService(RegistryService.class, null);
		String tenantDomain = MultitenantUtils.getTenantDomain(userName);

		try {

			UserRealm userRealm = null;

			userRealm = AnonymousSessionUtil.getRealmByTenantDomain(registryService, realmService, tenantDomain);

			if (userRealm == null) {

				log.error("invalid domain or unactivated tenant login");
				return false;
			}

			String tenantAwareUsername = MultitenantUtils.getTenantAwareUsername(userName);

			if (userRealm.getUserStoreManager().authenticate(tenantAwareUsername, password)) {

				return true;
			} else {

				log.error("authentication failed. please check your username/password");
				return false;
			}
		} catch (CarbonException | UserStoreException e) {

			log.error("authentication failed for user : " + userName, e);
			return false;
		}
	}
}
