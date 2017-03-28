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
package com.wso2telco.core.pcrservice.conf;

import java.net.MalformedURLException;
import java.util.Map;

import com.wso2telco.core.pcrservice.util.YamlReader;

// TODO: Auto-generated Javadoc

/**
 * The Class AdminServiceConfig.
 */
public class AdminServiceConfig {

    /**
     * The config.
     */
    private static AdminServiceConfig config = null;

    /**
     * The host url.
     */
    private String hostUrl;

    /**
     * The username.
     */
    private String username;

    /**
     * The password.
     */
    private String password;

    /**
     * The jks key store path.
     */
    private String jksKeyStorePath;

    /**
     * The jks key store password.
     */
    private String jksKeyStorePassword;

    /**
     * The jks trust store.
     */
    private String jksTrustStore;

    /**
     * The maximum total http connection.
     */
    private int maximumTotalHttpConnection;

    /**
     * The maximum http connection per host.
     */
    private int maximumHttpConnectionPerHost;

    /**
     * Instantiates a new admin service config.
     *
     * @throws MalformedURLException
     */
    public AdminServiceConfig() {

        Map<Object, Object> adminConfig = (Map<Object, Object>) YamlReader.getConfiguration().getAdmin();
        hostUrl = (String) adminConfig.get("hostUrl");
        username = (String) adminConfig.get("username");
        password = (String) adminConfig.get("password");
        jksKeyStorePath = (String) adminConfig.get("jksKeyStorePath");
        jksKeyStorePassword = (String) adminConfig.get("jksKeyStorePassword");
        jksTrustStore = (String) adminConfig.get("jksTrustStore");
        maximumTotalHttpConnection = (int) adminConfig.get("maximumTotalHttpConnection");
        maximumHttpConnectionPerHost = (int) adminConfig.get("maximumHttpConnectionPerHost");
    }

    /**
     * Gets the single instance of AdminServiceConfig.
     *
     * @return single instance of AdminServiceConfig
     * @throws MalformedURLException
     */
    public static AdminServiceConfig getInstance() {
        if (config == null) {
            config = new AdminServiceConfig();
        }
        return config;
    }

    /**
     * Gets the host url.
     *
     * @return the host url
     */
    public String getHostUrl() {
        return hostUrl;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the jks key store path.
     *
     * @return the jks key store path
     */
    public String getJksKeyStorePath() {
        return jksKeyStorePath;
    }

    /**
     * Gets the jks key store password.
     *
     * @return the jks key store password
     */
    public String getJksKeyStorePassword() {
        return jksKeyStorePassword;
    }

    /**
     * Gets the maximum total http connection.
     *
     * @return the maximum total http connection
     */
    public int getMaximumTotalHttpConnection() {
        return maximumTotalHttpConnection;
    }

    /**
     * Gets the maximum http connection per host.
     *
     * @return the maximum http connection per host
     */
    public int getMaximumHttpConnectionPerHost() {
        return maximumHttpConnectionPerHost;
    }

    /**
     * Gets the jks trust store.
     *
     * @return the jks trust store
     */
    public String getJksTrustStore() {
        return jksTrustStore;
    }
}
