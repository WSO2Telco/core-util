/*******************************************************************************
 * Copyright (c) 2015-2016, WSO2.Telco Inc. (http://www.wso2telco.com)
 *
 * All Rights Reserved. WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
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

package com.wso2telco.core.config.service;

import com.wso2telco.core.config.*;
import com.wso2telco.core.config.model.*;
import com.wso2telco.core.dbutils.DbService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

/**
 * Configuration service is OSGi service exposing DataHolder object which contains configuration data loaded from XML
 * files.
 */
public class ConfigurationServiceImpl implements ConfigurationService {

    private static Log log = LogFactory.getLog(ConfigurationServiceImpl.class);
    /**
     * The ConfigurationServiceImpl constructor. Loads data to data holder if they are not initialized
     */
    public ConfigurationServiceImpl() {
        if (DataHolder.getInstance().getMobileConnectConfig() == null)
            DataHolder.getInstance().setMobileConnectConfig(ConfigLoader.getInstance().getMobileConnectConfig());

        if (DataHolder.getInstance().getAuthenticationLevels() == null)
            DataHolder.getInstance().setAuthenticationLevels(ConfigLoader.getInstance().getAuthenticationLevels());

        if (DataHolder.getInstance().getAuthenticationLevelMap() == null) {
            Map<String, MIFEAuthentication> authenticationMap = loadMIFEAuthenticatorMap(ConfigLoader.getInstance()
                    .getAuthenticationLevels());
            DataHolder.getInstance().setAuthenticationLevelMap(authenticationMap);
        }

        if (DataHolder.getInstance().getAuthenticatorMNOMap() == null) {
            Map<String, Set<String>> authenticatorMNOMap = loadauthenticatorMNOMap();
            DataHolder.getInstance().setAuthenticatorMNOMap(authenticatorMNOMap);
        }
    }

    /**
     * Gets the single instance of DataHolder object.
     *
     * @return DataHolder
     */
    @Override
    public DataHolder getDataHolder() {
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
        DataHolder.getInstance().setAuthenticatorMNOMap(null);
    }

    /**
     * Loads MIFE Authenticators into a Map
     *
     * @param authenticationLevels
     * @return MIFE Authenticator Map
     */
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
        return authenticatorMap;
    }

    /**
     * Loads MIFE Authenticators into a Map
     *
     * @return MIFE Authenticator Map
     */
    private Map<String, Set<String>> loadauthenticatorMNOMap() {
        Map<String, Set<String>> authenticatorMNOMap = null;
        try {
            authenticatorMNOMap=new DbService().getAllowedAuthenticatorSetForMNO();
        } catch (Exception e) {
            log.error("Error while fetching authenticator MNO Map",e);
        }

        return authenticatorMNOMap;
    }
}
