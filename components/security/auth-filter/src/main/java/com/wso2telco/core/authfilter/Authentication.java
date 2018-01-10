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
package com.wso2telco.core.authfilter;

import javax.ws.rs.container.ContainerRequestFilter;
import java.lang.reflect.Method;
import java.util.List;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import com.wso2telco.core.authfilter.impl.AuthenticationFilter;
import com.wso2telco.core.authfilter.impl.AuthenticationProsser;
import com.wso2telco.core.authfilter.impl.authorization.AuthorizationFilterFactory;
import com.wso2telco.core.authfilter.impl.cookie.CookieFilterFactory;
import com.wso2telco.core.authfilter.util.AuthFilterParam;

@Provider
public class Authentication implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInformation;

	public void filter(ContainerRequestContext requestContext) {

		Response accessDenied = Response.status(Response.Status.UNAUTHORIZED).entity("You cannot access this resource")
				.build();
		Response accessForbidden = Response.status(Response.Status.FORBIDDEN).entity("Access blocked for all users !!")
				.build();

		Method method = resourceInformation.getResourceMethod();

		// will allowed access for all
		if (!method.isAnnotationPresent(PermitAll.class)) {

			// will denied access for all
			if (method.isAnnotationPresent(DenyAll.class)) {

				requestContext.abortWith(accessForbidden);
				return;
			}

			// get request headers
			final MultivaluedMap<String, String> headers = requestContext.getHeaders();

			// get authorization header
			final List<String> authorization = headers.get(AuthFilterParam.AUTHORIZATION_PROPERTY.getTObject());

			// deny access if there is no authorization information
			if (authorization == null || authorization.isEmpty()) {

				final List<String> cookie = headers.get(AuthFilterParam.COOKIE.getTObject());

				if (cookie == null || cookie.isEmpty()) {

					requestContext.abortWith(accessDenied);
					return;
				} else {

					// get cookie header
					String cookieHeader = cookie.get(0);

					// get relevant authentication filter based on cookie
					// type
					AuthenticationProsser authenticationProsser = new CookieFilterFactory();
					AuthenticationFilter authenticationFilter = authenticationProsser.verifyUser(requestContext, method,
							cookieHeader);

					if (authenticationFilter == null) {

						requestContext.abortWith(accessDenied);
						return;
					}
				}
			} else {

				// get authorization header
				String authorizationHeader = authorization.get(0);

				// get relevant authentication filter based on authorization
				// type
				AuthenticationProsser authenticationProsser = new AuthorizationFilterFactory();
				AuthenticationFilter authenticationFilter = authenticationProsser.verifyUser(requestContext, method,
						authorizationHeader);

				if (authenticationFilter == null) {

					requestContext.abortWith(accessDenied);
					return;
				}
			}
		}
	}
}
