package com.wso2telco.core.authfilter;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Arrays;
import java.util.HashSet;
import com.wso2telco.core.authfilter.authentication.BasicAuthenticator;
import com.wso2telco.core.authfilter.authorization.UserAuthorizationValidator;
import org.apache.commons.codec.binary.Base64;
import javax.ws.rs.container.ContainerRequestFilter;

@Provider
public class BasicAuthenticationFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInformation;

	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic";

	private final Log log = LogFactory.getLog(BasicAuthenticationFilter.class);
	private BasicAuthenticator userAuthentication = new BasicAuthenticator();
	private UserAuthorizationValidator userAuthorizationValidator = new UserAuthorizationValidator();

	String userName = null;
	String password = null;

	public void filter(ContainerRequestContext requestContext) {

		Response accessDenied = Response.status(Response.Status.UNAUTHORIZED).entity("You cannot access this resource")
				.build();
		Response accessForbidden = Response.status(Response.Status.FORBIDDEN).entity("Access blocked for all users !!")
				.build();

		boolean isAuthenticated = false;
		boolean isAuthorized = false;

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
			final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

			// deny access if there is no authorization information
			if (authorization == null || authorization.isEmpty()) {

				requestContext.abortWith(accessDenied);
				return;
			}

			// get base 64 encoded username and password
			final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

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
						return;
					}

					// validate user authorization by using user roles
					if (method.isAnnotationPresent(RolesAllowed.class)) {

						RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
						Set<String> allowedRolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));

						isAuthorized = userAuthorizationValidator.isAuthorizedRole(userName, allowedRolesSet);

						if (!isAuthorized) {

							requestContext.abortWith(accessDenied);
							return;
						}
					}
				} else {

					requestContext.abortWith(accessDenied);
					return;
				}
			} else {

				requestContext.abortWith(accessDenied);
				return;
			}
		}
	}
}
