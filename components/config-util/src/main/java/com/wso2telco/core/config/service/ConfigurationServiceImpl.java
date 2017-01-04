package com.wso2telco.core.config.service;

import com.wso2telco.core.config.*;
import com.wso2telco.core.config.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Configuration service is OSGi service exposing DataHolder object which contains configuration data loaded from XML files.
 */
public class ConfigurationServiceImpl implements ConfigurationService{


    /**
     * The ConfigurationServiceImpl constructor. Loads data to data holder if they are not initialized
     */
    public ConfigurationServiceImpl() {
        if(DataHolder.getInstance().getMobileConnectConfig() ==null)
            DataHolder.getInstance().setMobileConnectConfig(ConfigLoader.getInstance().getMobileConnectConfig());

        if(DataHolder.getInstance().getAuthenticationLevels() ==null)
            DataHolder.getInstance().setAuthenticationLevels(ConfigLoader.getInstance().getAuthenticationLevels());

        if(DataHolder.getInstance().getAuthenticationLevelMap() ==null) {
            Map<String, MIFEAuthentication> authenticationMap = loadMIFEAuthenticatorMap(ConfigLoader.getInstance().getAuthenticationLevels());
            DataHolder.getInstance().setAuthenticationLevelMap(authenticationMap);
        }
    }

    /**
     * Gets the single instance of DataHolder object.
     *
     * @return DataHolder
     */
    @Override
    public DataHolder getDataHolder(){
        return DataHolder.getInstance();
    }

    /**
     * Unset the configurations from the service
     */
    @Override
    public void unregisterDataHolder() {
        ConfigLoader.reset();
        DataHolder.getInstance().setMobileConnectConfig(null);
        DataHolder.getInstance().setAuthenticationLevels(null);
        DataHolder.getInstance().setAuthenticationLevelMap(null);
    }

    private Map<String, MIFEAuthentication> loadMIFEAuthenticatorMap(AuthenticationLevels authenticationLevels) {
        Map<String, MIFEAuthentication> authenticatorMap = new HashMap<>();
        List<AuthenticationLevel> authenticationLevelList = authenticationLevels.getAuthenticationLevelList();
        for (AuthenticationLevel authenticationLevel : authenticationLevelList) {
            MIFEAuthentication mifeAuthentication = new MIFEAuthentication();
            String authenticationLevelValue = authenticationLevel.getLevel();
            Authentication authentication = authenticationLevel.getAuthentication();
            Authenticators authenticators = authentication.getAuthenticators();
            String levelToFallBack = authentication.getLevelToFallback();
            List<Authenticator> authenticatorList = authenticators.getAuthenticators();
            List<MIFEAuthentication.MIFEAbstractAuthenticator> mifeAuthenticationList = new ArrayList<>();
            for (Authenticator authenticator : authenticatorList) {
                MIFEAuthentication.MIFEAbstractAuthenticator mifeAuthenticator = new MIFEAuthentication
                        .MIFEAbstractAuthenticator();
                mifeAuthenticator.setAuthenticator(authenticator.getAuthenticatorName());
                mifeAuthenticator.setOnFailAction(authenticator.getOnfail());
                mifeAuthenticator.setSupportFlow(authenticator.getSupportiveFlow());
                mifeAuthenticationList.add(mifeAuthenticator);
            }
            mifeAuthentication.setLevelToFail(levelToFallBack);
            mifeAuthentication.setAuthenticatorList(mifeAuthenticationList);
            authenticatorMap.put(authenticationLevelValue, mifeAuthentication);
        }
        return  authenticatorMap;
    }
}
