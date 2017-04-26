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

public class SpProvisionDto {

    private ServiceProviderDto serviceProviderDto = null;
    private SpProvisionConfig spProvisionConfig = null;
    private ProvisionType provisionType = null;
    private DiscoveryServiceDto discoveryServiceDto = null;

    public ServiceProviderDto getServiceProviderDto() {
        return serviceProviderDto;
    }

    public void setServiceProviderDto(ServiceProviderDto serviceProviderDto) {
        this.serviceProviderDto = serviceProviderDto;
    }

    public SpProvisionConfig getSpProvisionConfig() {
        return spProvisionConfig;
    }

    public void setSpProvisionConfig(SpProvisionConfig spProvisionConfig) {
        this.spProvisionConfig = spProvisionConfig;
    }

    public ProvisionType getProvisionType() {
        return provisionType;
    }

    public void setProvisionType(ProvisionType provisionType) {
        this.provisionType = provisionType;
    }

    public DiscoveryServiceDto getDiscoveryServiceDto() {
        return discoveryServiceDto;
    }

    public void setDiscoveryServiceDto(DiscoveryServiceDto discoveryServiceDto) {
        this.discoveryServiceDto = discoveryServiceDto;
    }
}
