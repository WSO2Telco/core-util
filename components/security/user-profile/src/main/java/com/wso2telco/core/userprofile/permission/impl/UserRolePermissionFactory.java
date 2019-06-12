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
package com.wso2telco.core.userprofile.permission.impl;

import java.util.HashMap;
import java.util.Map;

import com.wso2telco.core.dbutils.exception.BusinessException;
import com.wso2telco.core.userprofile.util.UserRolePermissionType;

public class UserRolePermissionFactory {
    private static UserRolePermissionFactory instance;

    private Map<UserRolePermissionType, UserRolePermission> permissionBuilderMap;

    private UserRolePermissionFactory() {
        permissionBuilderMap = new HashMap<UserRolePermissionType, UserRolePermission>();
    }

    public static UserRolePermissionFactory getInstance() {
        if (instance == null) {
            instance = new UserRolePermissionFactory();
        }
        return instance;
    }


	public UserRolePermission getUserRolePermissionExecuter(UserRolePermissionType userRolePermissionType) throws BusinessException  {

		UserRolePermission userRolePermission = null;

        switch (userRolePermissionType) {
            case UI_PERMISSION: {
                if (permissionBuilderMap.containsKey(userRolePermissionType)) {
                    userRolePermission = permissionBuilderMap.get(userRolePermissionType);
                } else {
                    userRolePermission = new WSO2PermissionBuilder();
                    permissionBuilderMap.put(userRolePermissionType, userRolePermission);
                }
            }
            break;
            default:
                break;
        }

		return userRolePermission;
	}
}
