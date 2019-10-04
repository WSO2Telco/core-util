package com.wso2telco.core.pcrservice.oauth;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.ssl.KeyMaterial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.identity.oauth.stub.OAuthAdminServiceStub;

import com.wso2telco.core.pcrservice.conf.AdminServiceConfig;
import com.wso2telco.core.pcrservice.exception.PCRException;

public class OAuthApplicationDataStubFactory extends BasePoolableObjectFactory {

    private static final Logger log = LoggerFactory.getLogger(OAuthApplicationDataStubFactory.class);
    AdminServiceConfig config;
    private HttpClient httpClient;

    /**
     * @deprecated use {@link #OAuthApplicationDataStubFactory(AdminServiceConfig adminServiceConfig)} instead
     */
    @Deprecated
    public OAuthApplicationDataStubFactory() {
        this.config = AdminServiceConfig.getInstance();
        this.httpClient = createHttpClient();
    }

    public OAuthApplicationDataStubFactory(AdminServiceConfig adminServiceConfig) {
        this.config = adminServiceConfig;
        this.httpClient = createHttpClient();
    }

    /**
     * This creates a OAuth2TokenValidationServiceStub object to the pool.
     *
     * @return an OAuthValidationStub object
     * @throws PCRException pcr
     */
    @Override
    public Object makeObject() throws PCRException {
        return this.generateStub();
    }

    /**
     * This is used to clean up the OAuth validation stub and releases to the object pool.
     *
     * @param o object that needs to be released.
     * @throws Exception throws when failed to release to the pool
     */
    @Override
    public void passivateObject(Object o) throws Exception {
        if (o instanceof OAuthAdminServiceStub) {
            OAuthAdminServiceStub stub = (OAuthAdminServiceStub) o;
            stub._getServiceClient().cleanupTransport();
        }
    }

    /**
     * This is used to create a stub which will be triggered through object pool factory, which will create an
     * instance of it.
     *
     * @return OAuth2TokenValidationServiceStub stub that is used to call an external service.
     * @throws PCRException will be thrown when initialization failed.
     */
    private OAuthAdminServiceStub generateStub() throws PCRException {
        OAuthAdminServiceStub stub;
        try {
            URL hostURL = new URL(config.getHostUrl());
//			ConfigurationContext myConfigContext =
//					ConfigurationContextFactory.createConfigurationContextFromFileSystem(
//					"repo", CarbonUtils.getCarbonConfigDirPath() + File.separator + "axis2" + File.separator +
// "axis2.xml");
            stub = new OAuthAdminServiceStub(null, hostURL.toString());
            ServiceClient client = stub._getServiceClient();
            client.getServiceContext().getConfigurationContext().setProperty(
                    HTTPConstants.CACHED_HTTP_CLIENT, httpClient);

            HttpTransportProperties.Authenticator auth =
                    new HttpTransportProperties.Authenticator();
            auth.setPreemptiveAuthentication(true);
            String username = config.getUsername();
            String password = config.getPassword();
            auth.setUsername(username);
            auth.setPassword(password);

            Options options = client.getOptions();
            options.setProperty(HTTPConstants.AUTHENTICATE, auth);
            options.setProperty(HTTPConstants.REUSE_HTTP_CLIENT, Constants.VALUE_TRUE);
            client.setOptions(options);

        } catch (AxisFault axisFault) {
            log.error("Error occurred while creating the OAuth2TokenValidationServiceStub.");
            throw new PCRException(
                    "Error occurred while creating the OAuth2TokenValidationServiceStub.", axisFault);
        } catch (MalformedURLException e) {
            log.error("Malformed URL error");
            throw new PCRException(
                    "Malformed URL error", e);
        }

        return stub;
    }

    /**
     * This created httpclient pool that can be used to connect to external entity. This connection can be configured
     * via broker.xml by setting up the required http connection parameters.
     *
     * @return an instance of HttpClient that is configured with MultiThreadedHttpConnectionManager
     */
    private HttpClient createHttpClient() {
        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setDefaultMaxConnectionsPerHost(config.getMaximumHttpConnectionPerHost());
        params.setMaxTotalConnections(config.getMaximumTotalHttpConnection());
        HttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.setParams(params);
        return new HttpClient(connectionManager);
    }
}
