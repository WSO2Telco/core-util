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
package com.wso2telco.core.authfilter.impl.authorization;

import com.wso2telco.core.authfilter.authentication.BearerAuthenticator;
import com.wso2telco.core.authfilter.authorization.UserAuthorizationValidator;
import com.wso2telco.core.authfilter.impl.AuthenticationFilter;
import com.wso2telco.core.authfilter.util.AuthFilterParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.api.model.AccessTokenInfo;
import org.wso2.carbon.apimgt.impl.factory.KeyManagerHolder;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class BearerAuthenticationFilter implements AuthenticationFilter {

    private static final Log log = LogFactory.getLog(BearerAuthenticationFilter.class);
    private static final String REGEX_BEARER_PATTERN = "Bearer\\s";
    private static final Pattern PATTERN = Pattern.compile(REGEX_BEARER_PATTERN);
    Response accessDenied = Response.status(Response.Status.UNAUTHORIZED).entity("You cannot access this resource")
            .build();
    private UserAuthorizationValidator userAuthorizationValidator = new UserAuthorizationValidator();
    private BearerAuthenticator bearerAuthenticator = new BearerAuthenticator();
    private String authorizationHeader = null;

    @Override
    public boolean isAuthenticated(ContainerRequestContext requestContext, Method method, String authorizationHeader) {
        Boolean isAuthenticated = false;
        this.authorizationHeader = authorizationHeader;

        isAuthenticated = bearerAuthenticator.isAuthenticatedUser(requestContext, method, authorizationHeader);

        if (!isAuthenticated) {
            requestContext.abortWith(accessDenied);
            return false;
        }

        return true;
    }

    @Override
    public boolean isAuthorized(ContainerRequestContext requestContext, Method method) {
        Boolean isAuthorized = false;
        if (authorizationHeader != null && method.isAnnotationPresent(Scope.class)) {

            String accessToken = authorizationHeader
                    .replaceFirst(AuthFilterParam.AUTHENTICATION_SCHEME_BEARER.getTObject() + " ", "");
            AccessTokenInfo tokenInfo = null;
            try {
                tokenInfo = KeyManagerHolder.getKeyManagerInstance().getTokenMetaData(accessToken);
            } catch (APIManagementException e) {
                log.error("Error while retrieving token information for token: " + accessToken, e);
            }
            if (tokenInfo.getScopes() != null) {
                Scope scopeAnnotation = method.getAnnotation(Scope.class);
                Set<String> allowedScopeSet = new HashSet<>(Arrays.asList(scopeAnnotation.value().split(" ")));
                if (log.isDebugEnabled()) {
                    log.debug("Access Token: " + tokenInfo.getAccessToken());
                }
                isAuthorized = userAuthorizationValidator.isAuthorizedScope(tokenInfo.getScopes(), allowedScopeSet);

                if (isAuthorized) {
                    return true;
                }
            }
        }
        log.error("Authorization failed : Invalid OAuth token scope");

        requestContext.abortWith(accessDenied);
        return false;
    }
}