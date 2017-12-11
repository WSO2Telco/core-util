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
package com.wso2telco.core.authfilter.impl.cookie;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.wso2telco.core.authfilter.authorization.UserAuthorizationValidator;
import com.wso2telco.core.authfilter.impl.AuthenticationFilter;
import com.wso2telco.core.authfilter.util.HeaderParam;
import com.wso2telco.core.dbutils.exception.BusinessException;
import com.wso2telco.core.userprofile.cache.CacheFactory;
import com.wso2telco.core.userprofile.cache.UserProfileCachable;
import com.wso2telco.core.userprofile.dto.UserProfileDTO;
import com.wso2telco.core.userprofile.util.CacheType;

public class JSessionAuthenticationFilter implements AuthenticationFilter {

	private final Log log = LogFactory.getLog(JSessionAuthenticationFilter.class);

	Response accessDenied = Response.status(Response.Status.UNAUTHORIZED).entity("You cannot access this resource")
			.build();
	private UserAuthorizationValidator userAuthorizationValidator = new UserAuthorizationValidator();

	private String userName = null;

	@Override
	public boolean isAuthenticated(ContainerRequestContext requestContext, Method method, String header) {

		boolean isExpired = false;

		try {

			UserProfileCachable cachable = CacheFactory.getInstance(CacheType.LOCAL).getService();
			String sessionId = header.replace("JSESSIONID=", "");
			isExpired = cachable.isExpired(sessionId);

			if (isExpired) {

				requestContext.abortWith(accessDenied);
				return false;
			}

			UserProfileDTO userProfileDTO = cachable.get(sessionId);
			userName = userProfileDTO.getUserName();
			log.debug("username : " + userName);
		} catch (BusinessException e) {

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
