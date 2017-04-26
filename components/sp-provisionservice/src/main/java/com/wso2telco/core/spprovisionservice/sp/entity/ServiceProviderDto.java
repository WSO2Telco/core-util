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

public class ServiceProviderDto {

    private String applicationName = null;
    private String description = null;
    private int applicationId = 0;
    private boolean alwaysSendMappedLocalSubjectId = false;
    private boolean localClaimDialect = false;
    private String inboundAuthKey = null;
    private String inboundAuthType = null;
    private boolean confidential = false;
    private String defaultValue = null;
    private String propertyName = null;
    private boolean propertyRequired = false;
    private String propertyValue = null;
    private boolean provisioningEnabled = false;
    private String provisioningUserStore = null;
    private String[] idpRoles = null;
    private boolean saasApp = false;
    private String localAuthenticatorConfigsDisplayName = null;
    private boolean localAuthenticatorConfigsEnabled = false;
    private String localAuthenticatorConfigsName = null;
    private boolean localAuthenticatorConfigsValid = false;
    private String localAuthenticatorConfigsAuthenticationType = null;
    private ServiceProviderConfig serviceProviderConfig = null;
    private ProvisionType existance;
    private AdminServiceDto adminServiceDto;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public boolean isAlwaysSendMappedLocalSubjectId() {
        return alwaysSendMappedLocalSubjectId;
    }

    public void setAlwaysSendMappedLocalSubjectId(boolean alwaysSendMappedLocalSubjectId) {
        this.alwaysSendMappedLocalSubjectId = alwaysSendMappedLocalSubjectId;
    }

    public boolean isLocalClaimDialect() {
        return localClaimDialect;
    }

    public void setLocalClaimDialect(boolean localClaimDialect) {
        this.localClaimDialect = localClaimDialect;
    }

    public String getInboundAuthKey() {
        return inboundAuthKey;
    }

    public void setInboundAuthKey(String inboundAuthKey) {
        this.inboundAuthKey = inboundAuthKey;
    }

    public String getInboundAuthType() {
        return inboundAuthType;
    }

    public void setInboundAuthType(String inboundAuthType) {
        this.inboundAuthType = inboundAuthType;
    }

    public boolean isConfidential() {
        return confidential;
    }

    public void setConfidential(boolean confidential) {
        this.confidential = confidential;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String efaultValue) {
        this.defaultValue = efaultValue;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public boolean isPropertyRequired() {
        return propertyRequired;
    }

    public void setPropertyRequired(boolean propertyRequired) {
        this.propertyRequired = propertyRequired;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public boolean isProvisioningEnabled() {
        return provisioningEnabled;
    }

    public void setProvisioningEnabled(boolean provisioningEnabled) {
        this.provisioningEnabled = provisioningEnabled;
    }

    public String getProvisioningUserStore() {
        return provisioningUserStore;
    }

    public void setProvisioningUserStore(String provisioningUserStore) {
        this.provisioningUserStore = provisioningUserStore;
    }

    public String[] getIdpRoles() {
        return idpRoles;
    }

    public void setIdpRoles(String[] idpRoles) {
        this.idpRoles = idpRoles;
    }

    public boolean isSaasApp() {
        return saasApp;
    }

    public void setSaasApp(boolean saasApp) {
        this.saasApp = saasApp;
    }

    public ProvisionType getExistance() {
        return existance;
    }

    public void setExistance(ProvisionType existance) {
        this.existance = existance;
    }

    public String getLocalAuthenticatorConfigsDisplayName() {
        return localAuthenticatorConfigsDisplayName;
    }

    public void setLocalAuthenticatorConfigsDisplayName(String localAuthenticatorConfigsDisplayName) {
        this.localAuthenticatorConfigsDisplayName = localAuthenticatorConfigsDisplayName;
    }

    public boolean isLocalAuthenticatorConfigsEnabled() {
        return localAuthenticatorConfigsEnabled;
    }

    public void setLocalAuthenticatorConfigsEnabled(boolean localAuthenticatorConfigsEnabled) {
        this.localAuthenticatorConfigsEnabled = localAuthenticatorConfigsEnabled;
    }

    public String getLocalAuthenticatorConfigsName() {
        return localAuthenticatorConfigsName;
    }

    public void setLocalAuthenticatorConfigsName(String localAuthenticatorConfigsName) {
        this.localAuthenticatorConfigsName = localAuthenticatorConfigsName;
    }

    public boolean isLocalAuthenticatorConfigsValid() {
        return localAuthenticatorConfigsValid;
    }

    public void setLocalAuthenticatorConfigsValid(boolean localAuthenticatorConfigsValid) {
        this.localAuthenticatorConfigsValid = localAuthenticatorConfigsValid;
    }

    public String getLocalAuthenticatorConfigsAuthenticationType() {
        return localAuthenticatorConfigsAuthenticationType;
    }

    public void setLocalAuthenticatorConfigsAuthenticationType(String localAuthenticatorConfigsAuthenticationType) {
        this.localAuthenticatorConfigsAuthenticationType = localAuthenticatorConfigsAuthenticationType;
    }

    public ServiceProviderConfig getServiceProviderConfig() {
        return serviceProviderConfig;
    }

    public void setServiceProviderConfig(ServiceProviderConfig serviceProviderConfig) {
        this.serviceProviderConfig = serviceProviderConfig;
    }

    public AdminServiceDto getAdminServiceDto() {
        return adminServiceDto;
    }

    public void setAdminServiceDto(AdminServiceDto adminServiceDto) {
        this.adminServiceDto = adminServiceDto;
    }
}
