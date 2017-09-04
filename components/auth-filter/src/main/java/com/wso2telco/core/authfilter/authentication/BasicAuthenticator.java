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
