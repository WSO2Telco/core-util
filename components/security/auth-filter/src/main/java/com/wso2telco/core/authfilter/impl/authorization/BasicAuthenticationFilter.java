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
package com.wso2telco.core.authfilter.impl.authorization;

import com.wso2telco.core.authfilter.authentication.BasicAuthenticator;
import com.wso2telco.core.authfilter.authorization.UserAuthorizationValidator;
import com.wso2telco.core.authfilter.impl.AuthenticationFilter;
import com.wso2telco.core.authfilter.util.AuthFilterParam;
import com.wso2telco.core.authfilter.util.HeaderParam;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BasicAuthenticationFilter implements AuthenticationFilter {

    private final Log log = LogFactory.getLog(BasicAuthenticationFilter.class);

    Response accessDenied = Response.status(Response.Status.UNAUTHORIZED).entity("You cannot access this resource")
            .build();
    private BasicAuthenticator userAuthentication = new BasicAuthenticator();
    private UserAuthorizationValidator userAuthorizationValidator = new UserAuthorizationValidator();

    private String userName = null;

    @Override
    public boolean isAuthenticated(ContainerRequestContext requestContext, Method method, String authorizationHeader) {

        String password = null;
        boolean isAuthenticated = false;

        // get base 64 encoded username and password
        final String encodedUserPassword = authorizationHeader
                .replaceFirst(AuthFilterParam.AUTHENTICATION_SCHEME_BASIC.getTObject() + " ", "");

        log.debug("base64 encoded username and password : " + encodedUserPassword);

        if (encodedUserPassword != null && encodedUserPassword.trim().length() > 0) {

            // decode username and password
            String usernameAndPassword = new String(Base64.decodeBase64(encodedUserPassword.getBytes()));

            // split username and password by :
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");

            if (tokenizer.countTokens() > 1) {

                userName = tokenizer.nextToken();
                password = tokenizer.nextToken();

                log.debug("username : " + userName);
                log.debug("password : " + password);

                // validate user authentication
                isAuthenticated = userAuthentication.isAuthenticatedUser(userName, password);

                if (!isAuthenticated) {

                    requestContext.abortWith(accessDenied);
                    return false;
                }
            } else {

                requestContext.abortWith(accessDenied);
                return false;
            }
        } else {

            requestContext.abortWith(accessDenied);
            return false;
        }

        return true;
    }

    @Override
    public boolean isAuthorized(ContainerRequestContext requestContext, Method method) {

        boolean isAuthorized = false;

        requestContext.getHeaders().add(HeaderParam.USER_NAME.getTObject(), userName);

        // validate user authorization by using user roles
        if (method.isAnnotationPresent(RolesAllowed.class)) {

            RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
            Set<String> allowedRolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));

            isAuthorized = userAuthorizationValidator.isAuthorizedRole(userName, allowedRolesSet);

            if (!isAuthorized) {

                requestContext.abortWith(accessDenied);
                return false;
            }
        }

        return true;
    }
}
