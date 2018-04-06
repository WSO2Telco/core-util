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

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wso2telco.core.dbutils.exception.BusinessException;
import com.wso2telco.core.userprofile.dto.UserClaimDTO;
import com.wso2telco.core.userprofile.dto.UserPermissionDTO;
import com.wso2telco.core.userprofile.dto.UserProfileDTO;
import com.wso2telco.core.userprofile.dto.UserRoleDTO;
import com.wso2telco.core.userprofile.permission.impl.UserRolePermission;
import com.wso2telco.core.userprofile.permission.impl.UserRolePermissionFactory;
import com.wso2telco.core.userprofile.prosser.UserClaimProsser;
import com.wso2telco.core.userprofile.prosser.UserRoleProsser;
import com.wso2telco.core.userprofile.util.UserRolePermissionType;

public class UserProfileRetriever {

	private final Log log = LogFactory.getLog(UserProfileRetriever.class);

	private UserRoleProsser userRoleRetriever = new UserRoleProsser();

	private static UserProfileRetriever instance;

	/*private UserProfileRetriever() {

	}*/

	public static UserProfileRetriever getInstance() {
		if (instance != null) {
			instance = new UserProfileRetriever();
		}
		return instance;
	}

	public UserProfileDTO getUserProfile(String userName) throws BusinessException {

		log.debug("retrieve user profile for user : " + userName);

		UserRoleDTO userRoleDTO = userRoleRetriever.getUserRoles(userName);
		UserRolePermission uiPermissionBuilder = UserRolePermissionFactory.getInstance()
				.getUserRolePermissionExecuter(UserRolePermissionType.UI_PERMISSION);
		Map<String, Object> uiPermissionTree = uiPermissionBuilder.build(userName);

		UserClaimProsser userClaimRetriever = new UserClaimProsser();
		UserClaimDTO userClaimDTO = userClaimRetriever.getUserClaims(userName);

		return fillUserProfileDTO(userName, userRoleDTO, uiPermissionTree, userClaimDTO);

	}

	private UserProfileDTO fillUserProfileDTO(String userName, UserRoleDTO userRoleDTO,
			Map<String, Object> uiPermissionTree, UserClaimDTO userClaimDTO) {

		UserProfileDTO userProfileDTO = new UserProfileDTO();

		userProfileDTO.setUserName(userName);

		if (userRoleDTO.getUserRoles() != null) {

			userProfileDTO.setUserRoles(userRoleDTO.getUserRoles());
		}

		if (uiPermissionTree != null) {

			userProfileDTO.setUiPermissions(uiPermissionTree);
		}

		for(String role: userRoleDTO.getUserRoles()){

			if(role.equalsIgnoreCase("admin")){
				userProfileDTO.setAdmin(true);
			}else if(role.equalsIgnoreCase("operator-admin")){
				userProfileDTO.setOperatorAdmin(true);
			}
		}
		log.info("is admin:" +userProfileDTO.isAdmin()+" is operator"+userProfileDTO.isOperatorAdmin());
		if (userClaimDTO != null) {

			userProfileDTO.setFirstName(userClaimDTO.getFirstName());
			userProfileDTO.setLastName(userClaimDTO.getLastName());
			userProfileDTO.setEmailAddress(userClaimDTO.getEmailAddress());
			userProfileDTO.setOrganization(userClaimDTO.getOrganization());
			userProfileDTO.setDepartment(userClaimDTO.getDepartment());
			if(userProfileDTO.isOperatorAdmin()){
				userProfileDTO.setOperatorName(userClaimDTO.getOperatorName());
			}
			log.info(userProfileDTO.getOperatorName());
		}
		return userProfileDTO;
	}
}
