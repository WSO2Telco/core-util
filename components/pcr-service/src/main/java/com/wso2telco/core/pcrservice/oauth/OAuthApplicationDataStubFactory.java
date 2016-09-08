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
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.ssl.KeyMaterial;
import org.apache.log4j.Logger;
import org.wso2.carbon.identity.oauth.stub.OAuthAdminServiceStub;

import com.wso2telco.core.pcrservice.dao.AdminServiceConfig;
import com.wso2telco.core.pcrservice.exception.PCRException;

public class OAuthApplicationDataStubFactory extends BasePoolableObjectFactory{
	
	private static final Logger log = Logger.getLogger(OAuthApplicationDataStubFactory.class);
	AdminServiceConfig config;
	private HttpClient httpClient;

	public OAuthApplicationDataStubFactory(){		
		this.config = AdminServiceConfig.getInstance();			
		this.httpClient = createHttpClient();
	}

	/**
	 * This creates a OAuth2TokenValidationServiceStub object to the pool.
	 * @return an OAuthValidationStub object
	 * @throws PCRException 
	 * @throws Exception thrown when creating the object.
	 */
	@Override
	public Object makeObject() throws PCRException{
		return this.generateStub();
	}

	/**
	 * This is used to clean up the OAuth validation stub and releases to the object pool.
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
	 * This is used to create a stub which will be triggered through object pool factory, which will create an instance of it.
	 * @return OAuth2TokenValidationServiceStub stub that is used to call an external service.
	 * @throws PCRException will be thrown when initialization failed.
	 */
	private OAuthAdminServiceStub generateStub() throws PCRException{
		OAuthAdminServiceStub stub;
		try {
			URL hostURL = new URL(config.getHostUrl());
			stub = new OAuthAdminServiceStub(hostURL.toString());
			ServiceClient client = stub._getServiceClient();
			client.getServiceContext().getConfigurationContext().setProperty(
					HTTPConstants.CACHED_HTTP_CLIENT, httpClient);

			HttpTransportProperties.Authenticator auth =
					new HttpTransportProperties.Authenticator();
			auth.setPreemptiveAuthentication(true);
			String username = config.getUsername();
			String password = config.getPassword();
			auth.setPassword(username);
			auth.setUsername(password);

			Options options = client.getOptions();
			options.setProperty(HTTPConstants.AUTHENTICATE, auth);
			options.setProperty(HTTPConstants.REUSE_HTTP_CLIENT, Constants.VALUE_TRUE);
			client.setOptions(options);
			if (hostURL.getProtocol().equals("https")) {
				// set up ssl factory since axis2 https transport is used.
				EasySSLProtocolSocketFactory sslProtocolSocketFactory =
						createProtocolSocketFactory();
				Protocol authhttps = new Protocol(hostURL.getProtocol(),
							(ProtocolSocketFactory) sslProtocolSocketFactory, hostURL.getPort());
				Protocol.registerProtocol(hostURL.getProtocol(), authhttps);
				options.setProperty(HTTPConstants.CUSTOM_PROTOCOL_HANDLER, authhttps);
			}
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
	 * This is required to create a trusted connection with the external entity.
	 * Have to manually configure it since we use CommonHTTPTransport(axis2 transport) in axis2.
	 * @return an EasySSLProtocolSocketFactory for SSL communication.
	 */
	private EasySSLProtocolSocketFactory createProtocolSocketFactory() throws PCRException {
		try {
			EasySSLProtocolSocketFactory easySSLPSFactory = new EasySSLProtocolSocketFactory();
			String keyStoreLocation = config.getJksKeyStorePath();
			char[] password = config.getJksKeyStorePassword().toCharArray();
			File keyStoreFile = new File(keyStoreLocation);
			if (keyStoreFile.exists()) {
				KeyMaterial km = new KeyMaterial(keyStoreLocation, password);
				easySSLPSFactory.setKeyMaterial(km);
				return easySSLPSFactory;
			} else {
				String errorMsg = "Unable to load Keystore from the following location: " + keyStoreLocation;
				throw new PCRException(errorMsg);
			}
		} catch (IOException e) {
			String errorMsg = "Failed to initiate EasySSLProtocolSocketFactory.";
			throw new PCRException(errorMsg, e);
		} catch (GeneralSecurityException e) {
			String errorMsg = "Failed to set the key material in easy ssl factory.";
			throw new PCRException(errorMsg, e);
		}
	}

	/**
	 * This created httpclient pool that can be used to connect to external entity. This connection can be configured
	 * via broker.xml by setting up the required http connection parameters.
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
