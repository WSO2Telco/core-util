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

import com.wso2telco.core.dbutils.exception.BusinessException;
import com.wso2telco.core.dbutils.exception.GenaralError;
import com.wso2telco.core.userprofile.prosser.UserRoleProsser;
import com.wso2telco.core.userprofile.util.AdminServicePath;
import com.wso2telco.core.userprofile.util.UserRolePermissionType;
import org.apache.axis2.AxisFault;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.apimgt.hostobjects.internal.HostObjectComponent;
import org.wso2.carbon.apimgt.impl.APIConstants;
import org.wso2.carbon.apimgt.impl.APIManagerConfiguration;
import org.wso2.carbon.user.mgt.stub.UserAdminStub;
import org.wso2.carbon.user.mgt.stub.UserAdminUserAdminException;
import org.wso2.carbon.user.mgt.stub.types.carbon.UIPermissionNode;
import org.wso2.carbon.utils.CarbonUtils;

import java.rmi.RemoteException;
import java.util.*;

class WSO2PermissionBuilder implements UserRolePermission {

    private final Log log = LogFactory.getLog(WSO2PermissionBuilder.class);
    private UserAdminStub userAdminStub;
    private static final Integer DEFAULT_SO_TIMEOUT = 1000 * 60 * 2;//2mins as default
    private static final Integer DEFAULT_CONNECTION_TIMEOUT = 1000 * 60 * 2;

    public WSO2PermissionBuilder() throws BusinessException {
        APIManagerConfiguration config = HostObjectComponent.getAPIManagerConfiguration();
        String userAdminServiceEndpoint = config.getFirstProperty(APIConstants.AUTH_MANAGER_URL)
                + AdminServicePath.USER_ADMIN.getTObject();
        String adminUsername = config.getFirstProperty(APIConstants.AUTH_MANAGER_USERNAME);
        String adminPassword = config.getFirstProperty(APIConstants.AUTH_MANAGER_PASSWORD);
        try {
            userAdminStub = new UserAdminStub(userAdminServiceEndpoint);
            userAdminStub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, DEFAULT_SO_TIMEOUT); //set so time out and connection timeout to 2min
            userAdminStub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT, DEFAULT_CONNECTION_TIMEOUT);
        } catch (AxisFault e) {
            log.error("", e);
            throw new BusinessException(GenaralError.INTERNAL_SERVER_ERROR);
        }
        CarbonUtils.setBasicAccessSecurityHeaders(adminUsername, adminPassword, userAdminStub._getServiceClient());
    }

    /**
     * This will build the permision tree using given users name
     */
    public Map<String, Object> build(final String userName) throws BusinessException {
        Map<String, Object> permisionTree = Collections.emptyMap();
        RetunEntitiy retunItem = new RetunEntitiy();
        try {
            UserRoleProsser userRoleRetriever = new UserRoleProsser();
            UIPermissionNode uiPermissionTree = null;


            List<String> currentUserRoleList = userRoleRetriever.getRolesByUserName(userName);
            /**
             * None of the roles are assign for the user
             */
            if (currentUserRoleList.isEmpty()) {
                throw new BusinessException("No roles assigned for user :" + userName);
            }

            for (Iterator<String> iterator = currentUserRoleList.iterator(); iterator.hasNext(); ) {

                String roleName = iterator.next();

                UIPermissionNode rolePermissions = userAdminStub.getRolePermissions(roleName);
                /**
                 * if the permission node is empty
                 */
                if (rolePermissions == null || rolePermissions.getNodeList() == null) {
                    continue;
                }

                /**
                 * filter out ui permission only
                 */
                Optional<UIPermissionNode> optNode = Arrays.stream(rolePermissions.getNodeList())
                        .filter(rowItem -> rowItem.getDisplayName()
                                .equalsIgnoreCase(UserRolePermissionType.UI_PERMISSION.getTObject()))
                        .findFirst();

                /**
                 * check for existence of node
                 */
                if (optNode.isPresent()) {
                    uiPermissionTree = optNode.get();

                    if (uiPermissionTree.getNodeList() != null && uiPermissionTree.getNodeList().length > 0) {
                        retunItem = popUserRolePermissions(uiPermissionTree.getNodeList());
                        if (retunItem.atLeastOneSelected) {
                            break;
                        }
                    } else {
                        /**
                         * if the current role does not contain Ui permission then continue
                         */
                        continue;
                    }
                }

            }

            if (retunItem.returnMap.isEmpty()) {
                throw new BusinessException(
                        UserRolePermissionType.UI_PERMISSION.getTObject() + " not assigned for the user :" + userName
                                + " , assigned roles :[ " + StringUtils.join(currentUserRoleList, ",") + "]");
            }

        } catch (RemoteException | UserAdminUserAdminException e) {
            log.error("UIPermission.build", e);
            throw new BusinessException(GenaralError.INTERNAL_SERVER_ERROR);
        }
        if (retunItem.returnMap.isEmpty()) {
            log.warn(" No ui permission tree found for " + userName);
            return Collections.emptyMap();
        } else {
            return retunItem.returnMap;
        }

    }

    /**
     * recuresvly build the permission tree and return as tree of maps
     *
     * @param rootPermissionTree
     * @return
     */

    private RetunEntitiy popUserRolePermissions(UIPermissionNode[] rootPermissionTree) {
        RetunEntitiy entity = new RetunEntitiy();
        Arrays.stream(rootPermissionTree).forEach(item -> {
            /**
             * if node has child elements
             */
            UIPermissionNode[] uiPermissionArray = item.getNodeList();
            if (uiPermissionArray != null && uiPermissionArray.length > 0) {

                RetunEntitiy temp = popUserRolePermissions(uiPermissionArray);
                entity.mergeMapEntry(item.getDisplayName(), temp);

            } else {
                /**
                 * node don't have children
                 */

                entity.mergeMapEntry(item);
            }

        });

        return entity;
    }

    class RetunEntitiy {
        private boolean atLeastOneSelected = false;
        private Map<String, Object> returnMap = new HashMap<>();

        public void setEntryName(String key, Object value) {
            returnMap.put(key, value);
        }

        public void mergeMapEntry(UIPermissionNode item) {
            this.returnMap.put(item.getDisplayName(), item.getSelected());
            if (item.getSelected()) {
                atLeastOneSelected = true;
            }
        }

        public void mergeMapEntry(String entryName, RetunEntitiy mergeEntry) {
            this.returnMap.put(entryName, mergeEntry.returnMap);
            if (mergeEntry.atLeastOneSelected) {
                this.atLeastOneSelected = mergeEntry.atLeastOneSelected;
            }
        }

    }
}
