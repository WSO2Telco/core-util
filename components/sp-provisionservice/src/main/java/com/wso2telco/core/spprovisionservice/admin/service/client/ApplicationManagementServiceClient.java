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
package com.wso2telco.core.spprovisionservice.admin.service.client;

import com.wso2telco.core.spprovisionservice.external.admin.service.dataTransform.TransformServiceProviderDto;
import com.wso2telco.core.spprovisionservice.sp.entity.ServiceProviderDto;
import com.wso2telco.core.spprovisionservice.sp.exception.SpProvisionServiceException;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.identity.application.common.model.xsd.ServiceProvider;
import org.wso2.carbon.identity.application.mgt.stub
        .IdentityApplicationManagementServiceIdentityApplicationManagementException;
import org.wso2.carbon.identity.application.mgt.stub.IdentityApplicationManagementServiceStub;

import java.rmi.RemoteException;

public class ApplicationManagementServiceClient {

    private static final Logger log = LoggerFactory.getLogger(ApplicationManagementServiceClient.class);
    private static String cookie;
    private GenericObjectPool stubs;
    private static boolean DEBUG = log.isDebugEnabled();
    private TransformServiceProviderDto transformServiceProviderDto = null;
    private IdentityApplicationManagementServiceStub identityApplicationManagementServiceStub;

    public ApplicationManagementServiceClient() {
        stubs = new GenericObjectPool(new ApplicationManagementServiceClientStubFactory());
        generateAndAuthenticateStub();
    }

    public void generateAndAuthenticateStub() {

        Object stub = null;
        try {
            stub = this.stubs.borrowObject();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (stub != null) {
            identityApplicationManagementServiceStub = (IdentityApplicationManagementServiceStub) stub;

            if (cookie != null) {
                identityApplicationManagementServiceStub._getServiceClient().getOptions().setProperty(HTTPConstants
                                .COOKIE_STRING,
                        cookie);
            }

            ServiceContext serviceContext = identityApplicationManagementServiceStub._getServiceClient()
                    .getLastOperationContext().getServiceContext();
            cookie = (String) serviceContext.getProperty(HTTPConstants.COOKIE_STRING);
        }
    }

    /*
    * Get Application Data
    * */
    public ServiceProvider getSpApplicationData(String applicationName) throws SpProvisionServiceException {

        ServiceProvider serviceProvider = null;
        try {
            serviceProvider = identityApplicationManagementServiceStub.getApplication(applicationName);
        } catch (RemoteException e) {
            throw new SpProvisionServiceException(e.getMessage());
        } catch (IdentityApplicationManagementServiceIdentityApplicationManagementException e) {
            throw new SpProvisionServiceException(e.getMessage());
        }
        return serviceProvider;
    }

    /*
    * Create Service Provider
    * */
    public void createSpApplication(ServiceProviderDto serviceProviderDto) throws SpProvisionServiceException {

        transformServiceProviderDto = new TransformServiceProviderDto();
        ServiceProvider application = transformServiceProviderDto.transformToServiceProviderToCreateApplication
                (serviceProviderDto);
        try {
            identityApplicationManagementServiceStub.createApplication(application);
        } catch (RemoteException e) {
            throw new SpProvisionServiceException(e.getMessage());
        } catch (IdentityApplicationManagementServiceIdentityApplicationManagementException e) {
            throw new SpProvisionServiceException(e.getMessage());
        }
    }

    /*
    * Update Service Provider Application
    * */
    public void updateSpApplication(ServiceProviderDto serviceProviderDto) throws SpProvisionServiceException {

        transformServiceProviderDto = new TransformServiceProviderDto();
        ServiceProvider serviceProvider = transformServiceProviderDto.transformToServiceProviderToUpdateApplication
                (getSpApplicationData(serviceProviderDto.getApplicationName()), serviceProviderDto);
        try {
            identityApplicationManagementServiceStub.updateApplication(serviceProvider);
        } catch (RemoteException e) {
            throw new SpProvisionServiceException(e.getMessage());
        } catch (IdentityApplicationManagementServiceIdentityApplicationManagementException e) {
            throw new SpProvisionServiceException(e.getMessage());
        } catch (Exception e) {
            throw new SpProvisionServiceException(e.getMessage());
        }
    }

    /*
    * Delete Service Provider deleteApplication(String applicationName71)
    * */
    public void deleteSpApplication(String applicationName) throws SpProvisionServiceException {

        try {
            identityApplicationManagementServiceStub.deleteApplication(applicationName);
        } catch (RemoteException e) {
            throw new SpProvisionServiceException(e.getMessage());
        } catch (IdentityApplicationManagementServiceIdentityApplicationManagementException e) {
            throw new SpProvisionServiceException(e.getMessage());
        }
    }
}

