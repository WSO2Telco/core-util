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
}
