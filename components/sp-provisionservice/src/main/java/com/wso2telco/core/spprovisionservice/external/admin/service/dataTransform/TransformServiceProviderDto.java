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
package com.wso2telco.core.spprovisionservice.external.admin.service.dataTransform;

import com.wso2telco.core.spprovisionservice.sp.entity.ServiceProviderDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.common.model.xsd.*;

public class TransformServiceProviderDto {

    private ServiceProvider serviceProvider = null;

    private static Log log = LogFactory.getLog(TransformServiceProviderDto.class);

    /*
    * Transform ServiceProviderDto to create the service Provider
    * */
    public ServiceProvider transformToServiceProviderToCreateApplication(ServiceProviderDto serviceProviderDto) {

        serviceProvider = new ServiceProvider();
        serviceProvider.setApplicationName(serviceProviderDto.getApplicationName());
        serviceProvider.setApplicationID(serviceProviderDto.getApplicationId());
        return serviceProvider;
    }

    /*
    * Transform ServiceProvider to update the existing service Provider
    * */
    public ServiceProvider transformToServiceProviderToUpdateApplication(ServiceProvider serviceProviderObject,
                                                                         ServiceProviderDto serviceProviderDto) {

        serviceProviderObject.setApplicationName(serviceProviderDto.getApplicationName());
        serviceProviderObject.setApplicationID(serviceProviderDto.getApplicationId());
        serviceProviderObject.setDescription(serviceProviderDto.getDescription());
        serviceProviderObject.setSaasApp(serviceProviderDto.isSaasApp());
        serviceProviderObject = setClaimConfigObject(serviceProviderObject, serviceProviderDto);
        serviceProviderObject = setInboundAuthenticationConfigObject(serviceProviderObject, serviceProviderDto);
        serviceProviderObject = setInboundProvisioningConfigObject(serviceProviderObject, serviceProviderDto);
        serviceProviderObject = setPermissionsAndRoleConfigObject(serviceProviderObject, serviceProviderDto);
        serviceProviderObject = setLocalAndOutboundAuthenticationConfigObject(serviceProviderObject,
                serviceProviderDto);

        return serviceProviderObject;
    }

    /*
    * Set ServiceProviderDto details to a ClaimConfig object
    * */
    private ServiceProvider setClaimConfigObject(ServiceProvider serviceProviderObject, ServiceProviderDto
            serviceProviderDto) {

        ClaimConfig claimConfig = new ClaimConfig();
        claimConfig.setAlwaysSendMappedLocalSubjectId(serviceProviderDto.isAlwaysSendMappedLocalSubjectId());
        claimConfig.setLocalClaimDialect(serviceProviderDto.isLocalClaimDialect());
        serviceProviderObject.setClaimConfig(claimConfig);
        return serviceProviderObject;
    }

    /*
    * Set ServiceProviderDto details to a InboundAuthenticationRequestConfig object
    * */
    private InboundAuthenticationRequestConfig[] setInboundAuthenticationRequestConfigObject(
            ServiceProviderDto serviceProviderDto) {

        InboundAuthenticationRequestConfig inboundAuthenticationRequestConfig1 = new
                InboundAuthenticationRequestConfig();
        InboundAuthenticationRequestConfig inboundAuthenticationRequestConfig2 = new
                InboundAuthenticationRequestConfig();
        inboundAuthenticationRequestConfig1.setInboundAuthKey(serviceProviderDto.getApplicationName());
        inboundAuthenticationRequestConfig1.setInboundAuthType("passivests");

        inboundAuthenticationRequestConfig2.setInboundAuthKey(serviceProviderDto.getInboundAuthKey());
        inboundAuthenticationRequestConfig2.setInboundAuthType(serviceProviderDto.getInboundAuthType());
        inboundAuthenticationRequestConfig2.setProperties(setPropertyObject(serviceProviderDto));
        InboundAuthenticationRequestConfig inboundAuthenticationRequestConfig[] = {inboundAuthenticationRequestConfig1,
                inboundAuthenticationRequestConfig2};

        return inboundAuthenticationRequestConfig;
    }

    /*
    * Set ServiceProviderDto details to a InboundAuthenticationConfig object
    * */
    private ServiceProvider setInboundAuthenticationConfigObject(ServiceProvider serviceProviderObject,
                                                                 ServiceProviderDto serviceProviderDto) {

        InboundAuthenticationRequestConfig[] inboundAuthenticationRequestConfig =
                setInboundAuthenticationRequestConfigObject(serviceProviderDto);
        InboundAuthenticationConfig inboundAuthenticationConfig1 = new InboundAuthenticationConfig();
        inboundAuthenticationConfig1.setInboundAuthenticationRequestConfigs(inboundAuthenticationRequestConfig);
        serviceProviderObject.setInboundAuthenticationConfig(inboundAuthenticationConfig1);
        return serviceProviderObject;
    }

    /*
    * Set ServiceProviderDto details to a Property object
    * */
    private Property[] setPropertyObject(ServiceProviderDto serviceProviderDto) {

        Property property1 = new Property();
        property1.setConfidential(serviceProviderDto.isConfidential());
        property1.setDefaultValue(serviceProviderDto.getDefaultValue());
        property1.setName(serviceProviderDto.getPropertyName());
        property1.setValue(serviceProviderDto.getPropertyValue());
        property1.setRequired(serviceProviderDto.isPropertyRequired());
        Property[] property = {property1};

        return property;
    }

    /*
    * Set ServiceProviderDto details to a PermissionsAndRoleConfig object
    * */
    private ServiceProvider setPermissionsAndRoleConfigObject(ServiceProvider serviceProviderObject,
                                                              ServiceProviderDto serviceProviderDto) {
        PermissionsAndRoleConfig permissionsAndRoleConfig1 = new PermissionsAndRoleConfig();
        permissionsAndRoleConfig1.setIdpRoles(serviceProviderDto.getIdpRoles());
        serviceProviderObject.setPermissionAndRoleConfig(permissionsAndRoleConfig1);
        return serviceProviderObject;
    }

    /*
    * Set ServiceProviderDto details to a LocalAndOutboundAuthenticationConfig object
    * */
    private ServiceProvider setLocalAndOutboundAuthenticationConfigObject(ServiceProvider serviceProviderObject,
                                                                          ServiceProviderDto serviceProviderDto) {
        AuthenticationStep[] authenticationStep = setAuthenticationStepObject(serviceProviderDto);

        LocalAndOutboundAuthenticationConfig localAndOutboundAuthenticationConfig1 = new
                LocalAndOutboundAuthenticationConfig();
        localAndOutboundAuthenticationConfig1.setAuthenticationType(serviceProviderDto
                .getLocalAuthenticatorConfigsAuthenticationType());
        localAndOutboundAuthenticationConfig1.setAuthenticationSteps(authenticationStep);
        serviceProviderObject.setLocalAndOutBoundAuthenticationConfig(localAndOutboundAuthenticationConfig1);
        return serviceProviderObject;
    }

    /*
    * Set ServiceProviderDto details to a LocalAuthenticatorConfig object
    * */
    private LocalAuthenticatorConfig[] setLocalAuthenticatorConfigObject(ServiceProviderDto serviceProviderDto) {
        LocalAuthenticatorConfig localAuthenticatorConfig1 = new LocalAuthenticatorConfig();
        localAuthenticatorConfig1.setDisplayName(serviceProviderDto.getLocalAuthenticatorConfigsDisplayName());
        localAuthenticatorConfig1.setEnabled(serviceProviderDto.isLocalAuthenticatorConfigsEnabled());
        localAuthenticatorConfig1.setName(serviceProviderDto.getLocalAuthenticatorConfigsName());
        localAuthenticatorConfig1.setValid(serviceProviderDto.isLocalAuthenticatorConfigsValid());
        LocalAuthenticatorConfig localAuthenticatorConfig[] = {localAuthenticatorConfig1};

        return localAuthenticatorConfig;
    }

    /*
    * Set ServiceProviderDto details to a AuthenticationStep object
    * */
    private AuthenticationStep[] setAuthenticationStepObject(ServiceProviderDto serviceProviderDto) {
        AuthenticationStep authenticationStep1 = new AuthenticationStep();
        LocalAuthenticatorConfig[] localAuthenticatorConfig = setLocalAuthenticatorConfigObject(serviceProviderDto);
        authenticationStep1.setLocalAuthenticatorConfigs(localAuthenticatorConfig);
        AuthenticationStep authenticationStep[] = {authenticationStep1};
        return authenticationStep;
    }

    /*
    * Set ServiceProviderDto details to a InboundProvisioningConfig object
    * */
    private ServiceProvider setInboundProvisioningConfigObject(ServiceProvider serviceProviderObject,
                                                               ServiceProviderDto serviceProviderDto) {

        InboundProvisioningConfig inboundProvisioningConfig1 = new InboundProvisioningConfig();
        inboundProvisioningConfig1.setProvisioningEnabled(serviceProviderDto.isProvisioningEnabled());
        inboundProvisioningConfig1.setProvisioningUserStore(serviceProviderDto.getProvisioningUserStore());
        serviceProviderObject.setInboundProvisioningConfig(inboundProvisioningConfig1);
        return serviceProviderObject;
    }

    /*
    * Transform ServiceProvider data to ServiceProviderDTO
    * */
    public ServiceProviderDto transformToServiceProviderDto(ServiceProvider serviceProvider) {

        ServiceProviderDto serviceProviderDto = new ServiceProviderDto();
        serviceProviderDto.setApplicationName(serviceProvider.getApplicationName());
        serviceProviderDto.setApplicationId(serviceProvider.getApplicationID());
        serviceProviderDto.setDescription(serviceProvider.getDescription());
        serviceProviderDto.setSaasApp(serviceProvider.getSaasApp());
        serviceProviderDto = mapClaimConfigObject(serviceProviderDto, serviceProvider);
        serviceProviderDto = mapInboundAuthenticationRequestConfigObject(serviceProviderDto, serviceProvider);
        serviceProviderDto = mapPropertyObject(serviceProviderDto, serviceProvider);
        serviceProviderDto = mapInboundProvisioningConfigObject(serviceProviderDto, serviceProvider);
        String[] idpRoles = {serviceProvider.getApplicationName()};
        serviceProviderDto.setIdpRoles(idpRoles);
        serviceProviderDto = mapLocalAndOutBoundAuthenticationConfigObject(serviceProviderDto, serviceProvider);

        return serviceProviderDto;
    }

    /*
    * Map ClaimConfig object value of ServiceProvider to ServiceProviderDto
    * */
    private ServiceProviderDto mapClaimConfigObject(ServiceProviderDto serviceProviderDto, ServiceProvider
            serviceProvider) {
        ClaimConfig claimConfig = serviceProvider.getClaimConfig();
        serviceProviderDto.setAlwaysSendMappedLocalSubjectId(claimConfig.isAlwaysSendMappedLocalSubjectIdSpecified());
        serviceProviderDto.setLocalClaimDialect(claimConfig.getLocalClaimDialect());
        return serviceProviderDto;
    }

    /*
    * Map InboundAuthenticationRequestConfig object value of ServiceProvider to ServiceProviderDto
    * */
    private ServiceProviderDto mapInboundAuthenticationRequestConfigObject(ServiceProviderDto serviceProviderDto,
                                                                           ServiceProvider serviceProvider) {
        InboundAuthenticationConfig inboundAuthenticationConfig = serviceProvider.getInboundAuthenticationConfig();
        InboundAuthenticationRequestConfig inboundAuthenticationRequestConfig[];
        inboundAuthenticationRequestConfig = inboundAuthenticationConfig.getInboundAuthenticationRequestConfigs();
        serviceProviderDto.setInboundAuthKey(inboundAuthenticationRequestConfig[0].getInboundAuthKey());
        serviceProviderDto.setInboundAuthType(inboundAuthenticationRequestConfig[0].getInboundAuthType());
        return serviceProviderDto;
    }

    /*
    * Map Property object value of ServiceProvider to ServiceProviderDto
    * */
    private ServiceProviderDto mapPropertyObject(ServiceProviderDto serviceProviderDto, ServiceProvider
            serviceProvider) {
        InboundAuthenticationRequestConfig inboundAuthenticationRequestConfig[];
        Property[] property;


        InboundAuthenticationConfig inboundAuthenticationConfig = serviceProvider.getInboundAuthenticationConfig();
        inboundAuthenticationRequestConfig = inboundAuthenticationConfig.getInboundAuthenticationRequestConfigs();
        property = inboundAuthenticationRequestConfig[0].getProperties();
        serviceProviderDto.setConfidential(property[0].getConfidential());
        serviceProviderDto.setDefaultValue(property[0].getDefaultValue());
        serviceProviderDto.setPropertyName(property[0].getName());
        serviceProviderDto.setPropertyValue(property[0].getValue());
        serviceProviderDto.setPropertyRequired(property[0].getRequired());
        return serviceProviderDto;

    }

    /*
    * Map InboundProvisioningConfig object value of ServiceProvider to ServiceProviderDto
    * */
    private ServiceProviderDto mapInboundProvisioningConfigObject(ServiceProviderDto serviceProviderDto,
                                                                  ServiceProvider serviceProvider) {
        InboundProvisioningConfig inboundProvisioningConfig = serviceProvider.getInboundProvisioningConfig();
        serviceProviderDto.setProvisioningEnabled(inboundProvisioningConfig.getProvisioningEnabled());
        serviceProviderDto.setProvisioningUserStore(inboundProvisioningConfig.getProvisioningUserStore());
        return serviceProviderDto;
    }

    /*
    * Map LocalAndOutBoundAuthenticationConfig object value of ServiceProvider to ServiceProviderDto
    * */
    private ServiceProviderDto mapLocalAndOutBoundAuthenticationConfigObject(ServiceProviderDto serviceProviderDto,
                                                                             ServiceProvider serviceProvider) {
        AuthenticationStep authenticationStep[];
        LocalAuthenticatorConfig localAuthenticatorConfig[];
        LocalAndOutboundAuthenticationConfig localAndOutBoundAuthenticationConfig = serviceProvider
                .getLocalAndOutBoundAuthenticationConfig();
        authenticationStep = localAndOutBoundAuthenticationConfig.getAuthenticationSteps();
        localAuthenticatorConfig = authenticationStep[0].getLocalAuthenticatorConfigs();
        serviceProviderDto.setLocalAuthenticatorConfigsDisplayName(localAuthenticatorConfig[0].getDisplayName());
        serviceProviderDto.setLocalAuthenticatorConfigsEnabled(localAuthenticatorConfig[0].getEnabled());
        serviceProviderDto.setLocalAuthenticatorConfigsName(localAuthenticatorConfig[0].getName());
        serviceProviderDto.setLocalAuthenticatorConfigsValid(localAuthenticatorConfig[0].getValid());
        serviceProviderDto.setLocalAuthenticatorConfigsAuthenticationType(
                localAndOutBoundAuthenticationConfig.getAuthenticationType());
        return serviceProviderDto;
    }
}
