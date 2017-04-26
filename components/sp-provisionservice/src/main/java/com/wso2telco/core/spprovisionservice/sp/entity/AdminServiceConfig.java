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
package com.wso2telco.core.spprovisionservice.sp.entity;

public class AdminServiceConfig {

    private String adminServiceUrl;
    private String ApplicationManagementHostUrl;
    private String stubAccessUserName;
    private String stubAccessPassword;
    private int maximumTotalHttpConnections;
    private int maximumHttpConnectionsPerHost;
    private String userName = null;
    private String password = null;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminServiceUrl() {
        return adminServiceUrl;
    }

    public void setAdminServiceUrl(String adminServiceUrl) {
        this.adminServiceUrl = adminServiceUrl;
    }

    public String getApplicationManagementHostUrl() {
        return ApplicationManagementHostUrl;
    }

    public void setApplicationManagementHostUrl(String applicationManagementHostUrl) {
        ApplicationManagementHostUrl = applicationManagementHostUrl;
    }

    public String getStubAccessUserName() {
        return stubAccessUserName;
    }

    public void setStubAccessUserName(String stubAccessUserName) {
        this.stubAccessUserName = stubAccessUserName;
    }

    public String getStubAccessPassword() {
        return stubAccessPassword;
    }

    public void setStubAccessPassword(String stubAccessPassword) {
        this.stubAccessPassword = stubAccessPassword;
    }

    public int getMaximumTotalHttpConnections() {
        return maximumTotalHttpConnections;
    }

    public void setMaximumTotalHttpConnections(int maximumTotalHttpConnections) {
        this.maximumTotalHttpConnections = maximumTotalHttpConnections;
    }

    public int getMaximumHttpConnectionsPerHost() {
        return maximumHttpConnectionsPerHost;
    }

    public void setMaximumHttpConnectionsPerHost(int maximumHttpConnectionsPerHost) {
        this.maximumHttpConnectionsPerHost = maximumHttpConnectionsPerHost;
    }

}
