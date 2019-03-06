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
package com.wso2telco.core.authfilter.authorization;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.user.api.RealmConfiguration;
import org.wso2.carbon.user.core.service.RealmService;

public class UserAuthorizationValidator {

	private final Log log = LogFactory.getLog(UserAuthorizationValidator.class);

	public boolean isAuthorizedRole(String userName, Set<String> allowedRolesSet) {

		PrivilegedCarbonContext carbonContext = PrivilegedCarbonContext.getThreadLocalCarbonContext();
		RealmService realmService = (RealmService) carbonContext.getOSGiService(RealmService.class, null);

		try {

			RealmConfiguration realmConfiguration = new RealmConfiguration();
			String[] currentUserRoles = realmService.getUserRealm(realmConfiguration).getUserStoreManager()
					.getRoleListOfUser(userName);

			List<String> currentUserRolesList = Arrays.asList(currentUserRoles);

			Iterator<String> iterator = allowedRolesSet.iterator();
			while (iterator.hasNext()) {

				String allowedRole = iterator.next();
				if (currentUserRolesList.contains(allowedRole)) {

					return true;
				}
			}
		} catch (org.wso2.carbon.user.api.UserStoreException e) {

			log.error("authorization failed for user : " + userName, e);
			return false;
		}

		log.error("authorization failed for user : " + userName);
		return false;
	}

	/**
	 * Checks the list of allowed scopes for a api resource against the scopes
	 * granted to a token
	 *
	 * @param currentTokenScopes Scopes granted to the token being evaluated
	 * @param allowedScopeSet Scopes allowed to access the APIs resource
	 * @return Boolean
	 *
	 */
	public Boolean isAuthorizedScope(String[] currentTokenScopes, Set<String> allowedScopeSet){

		List<String> currentTokenScopesList = Arrays.asList(currentTokenScopes);

		Iterator<String> iterator = allowedScopeSet.iterator();
		while (iterator.hasNext()) {

			String allowedScope = iterator.next();
			if (currentTokenScopesList.contains(allowedScope)) {

				return true;
			}
		}

		if(log.isDebugEnabled()){
			log.debug("Required OAuth Scopes: " + allowedScopeSet);
			log.debug("Available OAuth Scopes: " + currentTokenScopesList);
		}

	return false;
	}
}
