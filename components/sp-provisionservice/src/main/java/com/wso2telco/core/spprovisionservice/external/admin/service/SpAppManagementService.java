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
package com.wso2telco.core.spprovisionservice.external.admin.service;

import com.wso2telco.core.spprovisionservice.sp.entity.ServiceProviderDto;
import com.wso2telco.core.spprovisionservice.sp.entity.SpProvisionConfig;
import com.wso2telco.core.spprovisionservice.sp.exception.SpProvisionServiceException;
import org.wso2.carbon.identity.application.common.model.xsd.ServiceProvider;

public interface SpAppManagementService {

    void initialize(SpProvisionConfig spProvisionConfig);

    boolean createSpApplication(ServiceProviderDto serviceProviderDto) throws SpProvisionServiceException;

    void updateSpApplication(ServiceProviderDto serviceProviderDto) throws SpProvisionServiceException;

    ServiceProvider getSpApplicationData(String applicationName) throws SpProvisionServiceException;

    void deleteSpApplication(String applicationName) throws SpProvisionServiceException;

    ServiceProviderDto getServiceProviderDetails(String applicationName) throws SpProvisionServiceException;

}
