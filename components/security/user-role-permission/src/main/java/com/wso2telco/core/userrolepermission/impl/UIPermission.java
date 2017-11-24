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
package com.wso2telco.core.userrolepermission.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.user.mgt.stub.types.carbon.UIPermissionNode;
import com.wso2telco.core.userrolepermission.util.UserRolePermissionType;

public class UIPermission implements UserRolePermission {

	private final Log log = LogFactory.getLog(UIPermission.class);

	@Override
	public List<String> getUserRolePermissions(UIPermissionNode rootPermissionTree) {

		List<String> uiPermissionList = null;

		try {

			UIPermissionNode[] rootPermissionArray = rootPermissionTree.getNodeList();

			for (int i = 0; i < rootPermissionArray.length; i++) {

				String permissionName = rootPermissionArray[i].getDisplayName();

				if (permissionName.equals(UserRolePermissionType.UI_PERMISSION.getTObject())) {

					UIPermissionNode[] uiPermissionArray = rootPermissionArray[i].getNodeList();

					if (uiPermissionArray.length != 0) {

						uiPermissionList = new ArrayList<>();

						for (int j = 0; j < uiPermissionArray.length; j++) {

							if(uiPermissionArray[j].getSelected()){
								
								uiPermissionList.add(uiPermissionArray[j].getDisplayName());
							}						
						}
					}
				}
			}
		} catch (Exception e) {

			log.error("unable to retrieve ui permissions : ", e);
			Collections.emptyList();
		}

		return uiPermissionList;
	}
}
