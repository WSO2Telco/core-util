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

import com.wso2telco.core.spprovisionservice.dao.SpConfigurationDao;
import com.wso2telco.core.spprovisionservice.dao.impl.SpConfigurationDaoImpl;
import com.wso2telco.core.spprovisionservice.external.admin.service.dataTransform.TransformServiceProviderDto;
import com.wso2telco.core.spprovisionservice.sp.entity.ServiceProviderDto;
import com.wso2telco.core.spprovisionservice.sp.entity.SpProvisionConfig;
import com.wso2telco.core.spprovisionservice.sp.exception.SpProvisionServiceException;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.common.model.xsd.ApplicationBasicInfo;
import org.wso2.carbon.identity.application.common.model.xsd.ServiceProvider;
import org.wso2.carbon.identity.application.mgt.stub
        .IdentityApplicationManagementServiceIdentityApplicationManagementException;
import org.wso2.carbon.identity.application.mgt.stub.IdentityApplicationManagementServiceStub;

import javax.naming.ConfigurationException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class ApplicationManagementClient {

    private IdentityApplicationManagementServiceStub stub = null;
    private ServiceClient client = null;
    private TransformServiceProviderDto transformServiceProviderDto = null;
    private static Log log = LogFactory.getLog(ApplicationManagementClient.class);
    private SpProvisionConfig spProvisionConfig = null;
    private SpConfigurationDao spConfigurationDao = null;

    public ApplicationManagementClient(SpProvisionConfig spProvisionConfig) {
        this.spProvisionConfig = spProvisionConfig;
        this.spConfigurationDao = new SpConfigurationDaoImpl();
        createAndAuthenticateStub();
    }

    private void createAndAuthenticateStub() {
        try {
            stub = new IdentityApplicationManagementServiceStub(null,
                    this.spProvisionConfig.getAdminServiceConfig().getApplicationManagementHostUrl());

            client = stub._getServiceClient();
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }
    }

    public ServiceProvider getSpApplicationData(String applicationName) throws SpProvisionServiceException {

        ServiceProvider serviceProvider = null;
        authenticate(client);
        ApplicationBasicInfo[] applicationBasicInfo;

        applicationBasicInfo = getAllApplicationBasicInfo();
        if (applicationBasicInfo != null) {
            for (ApplicationBasicInfo appInfo : applicationBasicInfo) {
                if (appInfo.getApplicationName().equals(applicationName)) {
                    try {
                        serviceProvider = stub.getApplication(applicationName);
                    } catch (RemoteException e) {
                        log.error("RemoteException occurred when getting Sp Application data for application :" +
                                applicationName, e);
                        throw new SpProvisionServiceException(e.getMessage());
                    } catch (IdentityApplicationManagementServiceIdentityApplicationManagementException e) {
                        log.error("IdentityApplicationManagementServiceIdentityApplicationManagementException " +
                                "occurred when getting Sp Application data for application :" +
                                applicationName, e);
                        throw new SpProvisionServiceException(e.getMessage());
                    }
                    break;
                }
            }
        }

        return serviceProvider;
    }

    private ApplicationBasicInfo[] getAllApplicationBasicInfo() throws SpProvisionServiceException {

        ApplicationBasicInfo[] applicationBasicInfo = null;
        authenticate(client);
        try {
            applicationBasicInfo = stub.getAllApplicationBasicInfo();
        } catch (RemoteException e) {
            log.error("RemoteException occurred when getting all Sp Application data", e);
            throw new SpProvisionServiceException(e.getMessage());
        } catch (IdentityApplicationManagementServiceIdentityApplicationManagementException e) {
            log.error("IdentityApplicationManagementServiceIdentityApplicationManagementException occurred when " +
                    "getting all Sp Application data", e);
            throw new SpProvisionServiceException(e.getMessage());
        }

        return applicationBasicInfo;

    }

    public void createSpApplication(ServiceProviderDto serviceProviderDto) throws SpProvisionServiceException {

        authenticate(client);

        if (serviceProviderDto != null) {
            transformServiceProviderDto = new TransformServiceProviderDto();
            ServiceProvider serviceProvider = transformServiceProviderDto
                    .transformToServiceProviderToCreateApplication(serviceProviderDto);
            try {
                stub.createApplication(serviceProvider);
                addSpConfigurationTable(serviceProviderDto.getInboundAuthKey());

            } catch (RemoteException e) {
                throw new SpProvisionServiceException(e.getMessage());
            } catch (IdentityApplicationManagementServiceIdentityApplicationManagementException e) {
                throw new SpProvisionServiceException(e.getMessage());
            } catch (Exception e) {
                throw new SpProvisionServiceException(e.getMessage());
            }
        } else {
            log.error("Service provider details are null");
        }
    }

    private void addSpConfigurationTable(String authKey) {
        try {
            boolean availability = spConfigurationDao.getSpConfigDetails(authKey);

            if (availability == false) {
                spConfigurationDao.insertSpToSpConfiguration(authKey, "scope", "openid",
                        "ALL");
            } else {
                log.info("Service Provider already available in the Sp Configurations table ");
            }
        } catch (SQLException e) {
            log.error("SQLException occurred in adding SP s to the SPConfiguration table ", e);
        } catch (ConfigurationException e) {
            log.error("ConfigurationException occurred in adding SP s to the SPConfiguration table", e);
        }
    }

    public void updateSpApplication(ServiceProviderDto serviceProviderDto) throws SpProvisionServiceException {

        authenticate(client);

        if (serviceProviderDto != null) {
            transformServiceProviderDto = new TransformServiceProviderDto();

            ServiceProvider serviceProvider = transformServiceProviderDto.transformToServiceProviderToUpdateApplication(
                    getSpApplicationData(serviceProviderDto.getApplicationName()), serviceProviderDto);
            try {
                stub.updateApplication(serviceProvider);
            } catch (RemoteException e) {
                log.error("RemoteException occurred when updating Sp Application:" + serviceProviderDto
                        .getApplicationName(), e);
                throw new SpProvisionServiceException(e.getMessage());
            } catch (IdentityApplicationManagementServiceIdentityApplicationManagementException e) {
                log.error("IdentityApplicationManagementServiceIdentityApplicationManagementException occurred when " +
                        "updating Sp Application:" + serviceProviderDto
                        .getApplicationName(), e);
                throw new SpProvisionServiceException(e.getMessage());
            } catch (Exception e) {
                log.error("Exception occurred when updating Sp Application:" + serviceProviderDto
                        .getApplicationName(), e);
                throw new SpProvisionServiceException(e.getMessage());
            }
        } else {
            log.error("Service provider details are null");
        }
    }

    public void deleteSpApplication(String applicationName) throws SpProvisionServiceException {

        authenticate(client);
        try {
            stub.deleteApplication(applicationName);
        } catch (RemoteException e) {
            log.error("RemoteException occurred when deleting SP application" + applicationName, e);
            throw new SpProvisionServiceException(e.getMessage());
        } catch (IdentityApplicationManagementServiceIdentityApplicationManagementException e) {
            log.error("IdentityApplicationManagementServiceIdentityApplicationManagementException occurred when " +
                    "deleting SP application" + applicationName, e);
            throw new SpProvisionServiceException(e.getMessage());
        } catch (Exception e) {
            log.error("Exception occurred when deleting SP application" + applicationName, e);
            throw new SpProvisionServiceException(e.getMessage());
        }
    }

    public void authenticate(ServiceClient client) {
        Options option = client.getOptions();
        HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
        auth.setUsername(this.spProvisionConfig.getAdminServiceConfig().getStubAccessUserName());
        auth.setPassword(this.spProvisionConfig.getAdminServiceConfig().getStubAccessPassword());
        auth.setPreemptiveAuthentication(true);
        option.setProperty(org.apache.axis2.transport.http.HTTPConstants.AUTHENTICATE, auth);
        option.setManageSession(true);
    }
}
