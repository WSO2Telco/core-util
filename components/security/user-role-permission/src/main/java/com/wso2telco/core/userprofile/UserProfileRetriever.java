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
package com.wso2telco.core.userprofile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wso2telco.core.userprofile.dto.UserClaimDTO;
import com.wso2telco.core.userprofile.dto.UserPermissionDTO;
import com.wso2telco.core.userprofile.dto.UserProfileDTO;
import com.wso2telco.core.userprofile.dto.UserRoleDTO;
import com.wso2telco.core.userprofile.prosser.UserClaimProsser;
import com.wso2telco.core.userprofile.prosser.UserPermissionProsser;
import com.wso2telco.core.userprofile.prosser.UserRoleProsser;
import com.wso2telco.core.userprofile.util.UserRolePermissionType;

public class UserProfileRetriever {

	private final Log log = LogFactory.getLog(UserProfileRetriever.class);

	private UserProfileDTO userProfileDTO = new UserProfileDTO();

	public UserProfileDTO getUserProfile(String userName) {

		log.debug("retrieve user profile for user : " + userName);

		UserRoleProsser userRoleRetriever = new UserRoleProsser();
		UserRoleDTO userRoleDTO = userRoleRetriever.getUserRoles(userName);

		UserPermissionProsser userPermissionRetriever = new UserPermissionProsser();
		UserPermissionDTO userUIPermissionDTO = userPermissionRetriever.getUserPermissions(userName,
				UserRolePermissionType.UI_PERMISSION);

		UserClaimProsser userClaimRetriever = new UserClaimProsser();
		UserClaimDTO userClaimDTO = userClaimRetriever.getUserClaims(userName);

		fillUserProfileDTO(userName, userRoleDTO, userUIPermissionDTO, userClaimDTO);

		return userProfileDTO;
	}

	private void fillUserProfileDTO(String userName, UserRoleDTO userRoleDTO, UserPermissionDTO userUIPermissionDTO,
			UserClaimDTO userClaimDTO) {

		userProfileDTO.setUserName(userName);

		if (userRoleDTO != null) {

			userProfileDTO.setUserRoles(userRoleDTO.getUserRoles());
		}

		if (userUIPermissionDTO != null) {

			userProfileDTO.setUiPermissions(userUIPermissionDTO.getUserPermissions());
		}

		if (userClaimDTO != null) {

			userProfileDTO.setFirstName(userClaimDTO.getFirstName());
			userProfileDTO.setLastName(userClaimDTO.getLastName());
			userProfileDTO.setEmailAddress(userClaimDTO.getEmailAddress());
			userProfileDTO.setOrganization(userClaimDTO.getOrganization());
			userProfileDTO.setDepartment(userClaimDTO.getDepartment());
		}
	}
}
