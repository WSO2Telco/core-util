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

package com.wso2telco.core.sp.config.utils.dao.impl;

import com.wso2telco.core.dbutils.DbUtils;
import com.wso2telco.core.sp.config.utils.dao.SpConfigDAO;
import com.wso2telco.core.sp.config.utils.domain.Config;
import com.wso2telco.core.sp.config.utils.util.ConfigKey;
import org.apache.log4j.Logger;
import org.wso2.carbon.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SpConfigDAOImpl implements SpConfigDAO {

    private static Logger logger = Logger.getLogger(SpConfigDAOImpl.class);

    public void save(Config config) throws Exception {

        Connection connectDBConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            connectDBConnection = DbUtils.getConnectDbConnection();
            String query = "INSERT INTO sp_configuration VALUES(?,?,?)";

            preparedStatement = connectDBConnection.prepareStatement(query);
            preparedStatement.setString(1, config.getClientId());
            preparedStatement.setString(2, config.getConfigKey());
            preparedStatement.setString(3, config.getConfigValue());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            logger.error("Error occurred while Getting the database connection");
            throw new Exception(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connectDBConnection != null) {
                connectDBConnection.close();
            }
        }
    }

    @Override
    public void delete(Config config) throws Exception {

        Connection connectDBConnection = null;
        PreparedStatement preparedStatement = null;

        logger.debug("Deleting config [" + config + " ] ");

        try {
            connectDBConnection = DbUtils.getConnectDbConnection();
            String query = "DELETE FROM sp_configuration WHERE client_id = ? AND config_key = ? AND config_value = ?";

            preparedStatement = connectDBConnection.prepareStatement(query);
            preparedStatement.setString(1, config.getClientId());
            preparedStatement.setString(2, config.getConfigKey());
            preparedStatement.setString(3, config.getConfigValue());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            logger.error("Error occurred while Getting the database connection");
            throw new Exception(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connectDBConnection != null) {
                connectDBConnection.close();
            }
        }
    }

    public List<String> getScopes(String clientId) throws Exception {
        logger.debug("Getting scopes for client id [ " + clientId + " ] ");

        List<String> config = getConfig(clientId, ConfigKey.SCOPE);

        logger.debug("Defined scopes " + config + " for client id [ " + clientId + " ]");

        return config;
    }

    public List<String> getMessages(String clientId) throws Exception {
        logger.debug("Getting messages for client id [ " + clientId + " ] ");

        List<String> config = getConfig(clientId, ConfigKey.MSG);

        logger.debug("Defined messages " + config + " for client id [ " + clientId + " ]");

        return config;

    }

    @Override
    public String getSaaMessage(String clientId) throws Exception {
        logger.debug("Getting saa message for client id [ " + clientId + " ] ");

        List<String> saaMessages = getConfig(clientId, ConfigKey.SAA_MSG);

        if (saaMessages.size() == 0) {
            throw new Exception("No saa message found for client id [ " + clientId + "] ");
        }
        if (saaMessages.size() > 1) {
            throw new Exception("More than one saa message found for client id [ " + clientId + "] ");
        }
        return saaMessages.get(0);
    }

    @Override
    public String getSaaImageUrl(String clientId) throws Exception {
        logger.debug("Getting saa i formage url client id [ " + clientId + " ] ");

        List<String> saaImageUrls = getConfig(clientId, ConfigKey.SAA_IMG_URL);

        if (saaImageUrls.size() == 0) {
            throw new Exception("No saa image url found for client id [ " + clientId + "] ");
        }
        if (saaImageUrls.size() > 1) {
            throw new Exception("More than one saa image url found for client id [ " + clientId + "] ");
        }
        return saaImageUrls.get(0);
    }

    @Override
    public String getUSSDLoginMessage(String clientId) {
        try {
            List<String> spUssdLoginMessages = getConfig(clientId, ConfigKey.SP_USSD_LOGIN_MESSAGE);
            return spUssdLoginMessages.get(0);
        } catch (Exception e) {
            // return null if no configuration value found for sp
            return null;
        }
    }

    @Override
    public String getUSSDRegistrationMessage(String clientId) {
        try {
            List<String> spUssdRegistrationMessages = getConfig(clientId, ConfigKey.SP_USSD_REGISTRATION_MESSAGE);
            return spUssdRegistrationMessages.get(0);
        } catch (Exception e) {
            // return null if no configuration value found for sp
            return null;
        }
    }

    @Override
    public String getUSSDPinLoginMessage(String clientId) {
        try {
            List<String> spUssdPinLoginMessages = getConfig(clientId, ConfigKey.SP_USSD_PIN_LOGIN_MESSAGE);
            return spUssdPinLoginMessages.get(0);
        } catch (Exception e) {
            // return null if no configuration value found for sp
            return null;
        }
    }

    @Override
    public String getUSSDPinRegistrationMessage(String clientId) {
        try {
            List<String> spUssdPinRegistrationMessages = getConfig(clientId, ConfigKey
                    .SP_USSD_PIN_REGISTRATION_MESSAGE);
            return spUssdPinRegistrationMessages.get(0);
        } catch (Exception e) {
            // return null if no configuration value found for sp
            return null;
        }
    }

    private List<String> getConfig(String clientId, String configKey) throws Exception {
        Connection connectDBConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<String> configList = new ArrayList<String>();

        try {
            connectDBConnection = DbUtils.getConnectDbConnection();
            String query = "SELECT config_value FROM sp_configuration WHERE client_id = ? AND config_key = ?";

            preparedStatement = connectDBConnection.prepareStatement(query);
            preparedStatement.setString(1, clientId);
            preparedStatement.setString(2, configKey);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                configList.add(resultSet.getString("config_value"));
            }
        } catch (Exception e) {
            logger.error("Error occurred while Getting the database connection");
            throw new Exception(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }

            if (connectDBConnection != null) {
                connectDBConnection.close();
            }
        }

        if (configList.isEmpty()) {
            String msg = "No " + configKey + " configuration found for the client id [ " + clientId + " ] ";
            logger.info(msg);
            throw new Exception(msg);
        }
        return configList;
    }
}
