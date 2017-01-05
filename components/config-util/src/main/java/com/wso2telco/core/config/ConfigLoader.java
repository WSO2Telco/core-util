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
package com.wso2telco.core.config;

import com.wso2telco.core.config.model.AuthenticationLevels;
import com.wso2telco.core.config.model.MobileConnectConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.utils.CarbonUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * The Class ConfigLoader.
 */
public class ConfigLoader {

    /** The log. */
    private Log log = LogFactory.getLog(ConfigLoader.class);

    /** The loa config. */
    private AuthenticationLevels authenticationLevels;
    
    /** The mobile connect config. */
    private MobileConnectConfig mobileConnectConfig;
    
    /** The loader. */
    private static ConfigLoader loader = new ConfigLoader();

    /**
     * Instantiates a new config loader.
     */
    private ConfigLoader() {
        try {
            if(this.authenticationLevels == null)
                this.authenticationLevels = initLoaConfig();
            if(this.mobileConnectConfig == null)
                this.mobileConnectConfig = initMConnectConfig();
        } catch (JAXBException e) {
            log.error("Error while initiating custom config files", e);
        }
    }

    /**
     * Gets the single instance of ConfigLoader.
     *
     * @return single instance of ConfigLoader
     */
    public static ConfigLoader getInstance() {
        return loader;
    }

    /**
     * Resets the singleton and re-initiate
     */
    public static void reset(){
        loader = new ConfigLoader();
    }

    /**
     * Inits the loa config.
     *
     * @return the LOA config
     * @throws JAXBException the JAXB exception
     */
    private AuthenticationLevels initLoaConfig() throws JAXBException {
        String configPath = CarbonUtils.getCarbonConfigDirPath() + File.separator + "LOA.xml";
        File file = new File(configPath);
        JAXBContext ctx = JAXBContext.newInstance(AuthenticationLevels.class);
        Unmarshaller um = ctx.createUnmarshaller();
        return  (AuthenticationLevels) um.unmarshal(file);
    }

    /**
     * Gets the loa config.
     *
     * @return the loa config
     */
    public AuthenticationLevels getAuthenticationLevels() {
        return this.authenticationLevels;
    }

    /**
     * Inits the m connect config.
     *
     * @return the mobile connect config
     * @throws JAXBException the JAXB exception
     */
    private MobileConnectConfig initMConnectConfig() throws JAXBException {
        String configPath = CarbonUtils.getCarbonConfigDirPath() + File.separator + "mobile-connect.xml";
        File file = new File(configPath);
        JAXBContext ctx = JAXBContext.newInstance(MobileConnectConfig.class);
        Unmarshaller um = ctx.createUnmarshaller();
        return (MobileConnectConfig) um.unmarshal(file);
    }

    /**
     * Gets the mobile connect config.
     *
     * @return the mobile connect config
     */
    public MobileConnectConfig getMobileConnectConfig(){
        return this.mobileConnectConfig;
    }

}
