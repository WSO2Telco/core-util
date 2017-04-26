/*******************************************************************************
 * Copyright  (c) 2015-2017, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 *
 * WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.wso2telco.core.spprovisionservice.admin.config;

import com.wso2telco.core.config.model.MobileConnectConfig;
import com.wso2telco.core.config.service.ConfigurationService;
import com.wso2telco.core.config.service.ConfigurationServiceImpl;

public class AdministrationServiceConfig {

    private static AdministrationServiceConfig config = null;
    private String username;
    private String password;
    private String adminServicesHostUrl;
    private String applicationManagementHostUrl;
    private MobileConnectConfig mobileConnectConfig;
    private ConfigurationService configurationService = new ConfigurationServiceImpl();

    /**
     * The maximum total http connection.
     */
    private int maximumTotalHttpConnection;

    /**
     * The maximum http connection per host.
     */
    private int maximumHttpConnectionPerHost;

    /**
     * Instantiates a new admin service config.
     */
    public AdministrationServiceConfig() {

        mobileConnectConfig = configurationService.getDataHolder().getMobileConnectConfig();
        adminServicesHostUrl = mobileConnectConfig.getSpProvisionConfig().getAdminServiceUrl();
        applicationManagementHostUrl = mobileConnectConfig.getSpProvisionConfig().getApplicationManagementHostUrl();
        username = mobileConnectConfig.getSpProvisionConfig().getStubAccessUserName();
        password = mobileConnectConfig.getSpProvisionConfig().getStubAccessPassword();
        maximumTotalHttpConnection = mobileConnectConfig.getSpProvisionConfig().getMaximumTotalHttpConections();
        maximumHttpConnectionPerHost = mobileConnectConfig.getSpProvisionConfig().getMaximumHttpConnectionsPerHost();

    }

    /**
     * Gets the single instance of AdministrationServiceConfig.
     *
     * @return single instance of AdministrationServiceConfig
     */
    public static AdministrationServiceConfig getInstance() {
        if (config == null) {
            config = new AdministrationServiceConfig();
        }
        return config;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }


    /**
     * Gets the maximum http connection per host.
     *
     * @return the maximum http connection per host
     */
    public int getMaximumHttpConnectionPerHost() {
        return maximumHttpConnectionPerHost;
    }

    public String getAdminServicesHostUrl() {
        return adminServicesHostUrl;
    }

    public String getApplicationManagementHostUrl() {
        return applicationManagementHostUrl;
    }

    public int getMaximumTotalHttpConnection() {
        return maximumTotalHttpConnection;
    }
}
