/*******************************************************************************
 * Copyright  (c) 2015-2017, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
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
package com.wso2telco.core.spprovisionservice.dao.impl;

import com.wso2telco.core.spprovisionservice.dao.SpConfigurationDao;

import javax.naming.ConfigurationException;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpConfigurationDaoImpl extends BaseDao implements SpConfigurationDao {

    public void insertSpToSpConfiguration(String clientId, String configKey, String configValue, String operator)
            throws SQLException, ConfigurationException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryToInsertSp = "INSERT INTO sp_configuration(client_id,config_key,config_value,operator) VALUES(?," +
                "?,?,?)";
        try {
            connection = getConnectDBConnection();
            preparedStatement = connection.prepareStatement(queryToInsertSp);
            preparedStatement.setString(1, clientId);
            preparedStatement.setString(2, configKey);
            preparedStatement.setString(3, configValue);
            preparedStatement.setString(4, operator);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new SQLException("Error occurred while inserting values to SP_Configuration table", e);
        } catch (NamingException e) {
            throw new ConfigurationException("DataSource could not be found in mobile-connect.xml");
        } finally {
            closeAllConnections(preparedStatement, connection, resultSet);
        }
    }

    @Override
    public boolean getSpConfigDetails(String authKey) throws SQLException, ConfigurationException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryToInsertSp = "SELECT * FROM sp_configuration";
        boolean available = false;
        String authorizationKey = null;
        try {
            connection = getConnectDBConnection();
            preparedStatement = connection.prepareStatement(queryToInsertSp);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                authorizationKey = resultSet.getString("clientId");
                if(authorizationKey.equals(authKey)){
                    available = true;
                    break;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error occurred while retrieving SP_Configuration properties.", e);
        } catch (NamingException e) {
            throw new ConfigurationException("DataSource could not be found in mobile-connect.xml");
        } finally {
            closeAllConnections(preparedStatement, connection, resultSet);
        }
        return available;
    }
}
