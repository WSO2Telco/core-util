package com.wso2telco.core.util;

import com.wso2telco.core.entity.PinConfig;
import org.wso2.carbon.identity.application.authentication.framework.context.AuthenticationContext;

/**
 * Created by isuru on 1/2/17.
 */
public class PinConfigUtil {

    public static void savePinConfigToContext(AuthenticationContext authenticationContext, String msisdn) {
        PinConfig pinConfig = new PinConfig();
        pinConfig.setCurrentStep(PinConfig.CurrentStep.REGISTRATION);
        pinConfig.setInvalidFormatAttempts(0);
        pinConfig.setMsisdn(msisdn);
        pinConfig.setPinMismatchAttempts(0);
        pinConfig.setSessionId(authenticationContext.getContextIdentifier());
        pinConfig.setTotalAttempts(0);

        authenticationContext.setProperty(Constants.PIN_CONFIG_OBJECT, pinConfig);
    }

    public static void savePinConfigToContext(PinConfig pinConfig, AuthenticationContext authenticationContext) {

        authenticationContext.setProperty(Constants.PIN_CONFIG_OBJECT, pinConfig);
    }

    public static PinConfig getPinConfig(AuthenticationContext authenticationContext){
        return (PinConfig) authenticationContext.getProperty(Constants.PIN_CONFIG_OBJECT);
    }

}
