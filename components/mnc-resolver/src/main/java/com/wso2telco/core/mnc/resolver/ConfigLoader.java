/*******************************************************************************
 * Copyright  (c) 2015-2016, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
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
package com.wso2telco.core.mnc.resolver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.utils.CarbonUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

// TODO: Auto-generated Javadoc

/**
 * The Class ConfigLoader.
 */
public class ConfigLoader {

    /**
     * The log.
     */
    private Log log = LogFactory.getLog(ConfigLoader.class);


    /**
     * The mcc config.
     */
    private MCCConfiguration mccConfig;

    /**
     * The loader.
     */
    private static ConfigLoader loader = new ConfigLoader();

    /**
     * Instantiates a new config loader.
     */
    private ConfigLoader() {
        try {
            this.mccConfig = initMccConfig();
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
     * Inits the mcc config.
     *
     * @return the MCC configuration
     * @throws JAXBException the JAXB exception
     */
    private MCCConfiguration initMccConfig() throws JAXBException {
        String configPath = CarbonUtils.getCarbonConfigDirPath() + File.separator + "MobileCountryConfig.xml";
        File file = new File(configPath);
        JAXBContext ctx = JAXBContext.newInstance(MCCConfiguration.class);
        Unmarshaller um = ctx.createUnmarshaller();
        return (MCCConfiguration) um.unmarshal(file);
    }

    /**
     * Gets the mobile country config.
     *
     * @return the mobile country config
     */
    public MCCConfiguration getMobileCountryConfig() {
        return mccConfig;
    }

}
