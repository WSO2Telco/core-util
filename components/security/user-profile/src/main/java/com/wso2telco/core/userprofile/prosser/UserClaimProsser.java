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
package com.wso2telco.core.userprofile.prosser;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.apimgt.hostobjects.internal.HostObjectComponent;
import org.wso2.carbon.apimgt.impl.APIConstants;
import org.wso2.carbon.apimgt.impl.APIManagerConfiguration;
import org.wso2.carbon.um.ws.api.stub.RemoteUserStoreManagerServiceStub;
import org.wso2.carbon.um.ws.api.stub.RemoteUserStoreManagerServiceUserStoreExceptionException;
import org.wso2.carbon.user.core.claim.Claim;
import org.wso2.carbon.utils.CarbonUtils;

import com.wso2telco.core.userprofile.dto.UserClaimDTO;
import com.wso2telco.core.userprofile.util.AdminServicePath;
import com.wso2telco.core.userprofile.util.ClaimName;
import com.wso2telco.core.userprofile.util.ClaimUtil;
import com.wso2telco.core.userprofile.util.UserProfileType;

public class UserClaimProsser {

	private final Log log = LogFactory.getLog(UserClaimProsser.class);

	private EnumMap<ClaimName, String> userClaimDetails = new EnumMap<>(ClaimName.class);

	public Map<ClaimName, String> getUserClaimsByUserName(String userName) {

		try {

			APIManagerConfiguration config = HostObjectComponent.getAPIManagerConfiguration();
			String remoteUserStoreManagerServiceEndpoint = config.getFirstProperty(APIConstants.AUTH_MANAGER_URL)
					+ AdminServicePath.REMOTE_USER_STORE_MANAGER_SERVICE.getTObject();
			String adminUsername = config.getFirstProperty(APIConstants.AUTH_MANAGER_USERNAME);
			String adminPassword = config.getFirstProperty(APIConstants.AUTH_MANAGER_PASSWORD);

			RemoteUserStoreManagerServiceStub userStoreManagerStub = new RemoteUserStoreManagerServiceStub(
					remoteUserStoreManagerServiceEndpoint);
			CarbonUtils.setBasicAccessSecurityHeaders(adminUsername, adminPassword,
					userStoreManagerStub._getServiceClient());

			Claim[] claims = ClaimUtil.convertToClaims(
					userStoreManagerStub.getUserClaimValues(userName, UserProfileType.DEFAULT.getTObject()));

			List<ClaimName> somethingList = Arrays.asList(ClaimName.values());

			for (Iterator<ClaimName> iterator = somethingList.iterator(); iterator.hasNext();) {

				ClaimName claimName = iterator.next();
				getClaimValue(claims, claimName);
			}
		} catch (RemoteException | RemoteUserStoreManagerServiceUserStoreExceptionException e) {

			log.error("unable to retrieve claims for user " + userName + " : ", e);

			return Collections.emptyMap();
		}

		return userClaimDetails;
	}

	public UserClaimDTO getUserClaims(String userName) {

		UserClaimDTO userClaimDTO = null;

		Map<ClaimName, String> userClaims = getUserClaimsByUserName(userName);

		if (!userClaims.isEmpty()) {

			userClaimDTO = new UserClaimDTO();

			userClaimDTO = fillUserClaimDTO(userClaims, userClaimDTO);
		}

		return userClaimDTO;
	}

	private void getClaimValue(Claim[] claims, ClaimName claimName) {

		for (int i = 0; i < claims.length; i++) {

			Claim claim = claims[i];

			if (claim.getClaimUri().equalsIgnoreCase(claimName.getClaimURL())) {

				String claimValue = claim.getValue();

				userClaimDetails.put(claimName, claimValue);
			}
		}
	}

	private UserClaimDTO fillUserClaimDTO(Map<ClaimName, String> userClaims, UserClaimDTO userClaimDTO) {

		userClaimDTO.setFirstName(userClaims.get(ClaimName.FIRST_NAME));
		userClaimDTO.setLastName(userClaims.get(ClaimName.LAST_NAME));
		userClaimDTO.setEmailAddress(userClaims.get(ClaimName.EMAIL_ADDRESS));
		userClaimDTO.setOrganization(userClaims.get(ClaimName.ORGANIZATION));
		userClaimDTO.setDepartment(userClaims.get(ClaimName.DEPARTMENT));

		return userClaimDTO;
	}
}
