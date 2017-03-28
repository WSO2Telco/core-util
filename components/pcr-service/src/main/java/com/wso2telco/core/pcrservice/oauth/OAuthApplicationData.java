package com.wso2telco.core.pcrservice.oauth;

import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wso2telco.core.pcrservice.exception.PCRException;
import org.wso2.carbon.identity.oauth.stub.OAuthAdminServiceStub;
import org.wso2.carbon.identity.oauth.stub.dto.OAuthConsumerAppDTO;

public class OAuthApplicationData {

    private static final Logger log = LoggerFactory.getLogger(OAuthApplicationData.class);
    private static String cookie;
    private GenericObjectPool stubs;
    private static boolean DEBUG = log.isDebugEnabled();

    public OAuthApplicationData() {
        stubs = new GenericObjectPool(new OAuthApplicationDataStubFactory());
    }

    public OAuthConsumerAppDTO getApplicationData(String appId) throws PCRException {

        OAuthConsumerAppDTO apps = null;
        OAuthAdminServiceStub oAuthAdminServiceStub;
        try {
            if (DEBUG) log.debug("initializing the o Auth Admin Service stub");
            Object stub = this.stubs.borrowObject();
            if (stub != null) {
                oAuthAdminServiceStub = (OAuthAdminServiceStub) stub;

                if (cookie != null) {
                    oAuthAdminServiceStub._getServiceClient().getOptions().setProperty(HTTPConstants.COOKIE_STRING,
                            cookie);
                }

                apps = oAuthAdminServiceStub.getOAuthApplicationData(appId);
                //apps = oAuthAdminServiceStub.getAllOAuthApplicationData();
                ServiceContext serviceContext = oAuthAdminServiceStub._getServiceClient()
                        .getLastOperationContext().getServiceContext();
                cookie = (String) serviceContext.getProperty(HTTPConstants.COOKIE_STRING);
            } else {
                log.warn("Stub initialization failed.");
            }

        } catch (Exception e) {
            log.error("error initializing the stub", e);
            throw new PCRException("error initializing the stub");
        }
        return apps;
    }
}
