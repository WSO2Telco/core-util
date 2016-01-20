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
package com.wso2telco.dbutils;

import java.lang.*;
import java.util.*;
import java.sql.*;
import java.math.BigDecimal;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class DbUtils.
 */
public class DbUtils {

    /** The resdb. */
    private static DbUtils resdb;

    /** The driver class. */
    String driverClass;
    
    /** The connection url. */
    String connectionUrl;
    
    /** The connection username. */
    String connectionUsername;
    
    /** The connection password. */
    String connectionPassword;

    /** The axiata datasource. */
    private static volatile DataSource axiataDatasource = null;
    
    /** The Constant AXIATA_DATA_SOURCE. */
    private static final String AXIATA_DATA_SOURCE = "jdbc/AXIATA_MIFE_DB";
    
    /** The Constant log. */
    private static final Log log = LogFactory.getLog(DbUtils.class);

    /**
     * Initialize datasources.
     *
     * @throws SQLException the SQL exception
     * @throws AxataDBUtilException the axata db util exception
     */
    public static void initializeDatasources() throws SQLException, AxataDBUtilException {
        if (axiataDatasource != null) {
            return;
        }

        try {
            Context ctx = new InitialContext();
            axiataDatasource = (DataSource) ctx.lookup(AXIATA_DATA_SOURCE);
        } catch (NamingException e) {
            handleException("Error while looking up the data source: " + AXIATA_DATA_SOURCE, e);
        }
    }

    /**
     * Gets the axiata db connection.
     *
     * @return the axiata db connection
     * @throws SQLException the SQL exception
     * @throws AxataDBUtilException the axata db util exception
     */
    public static Connection getAxiataDBConnection() throws SQLException, AxataDBUtilException {
        initializeDatasources();

        if (axiataDatasource != null) {
            return axiataDatasource.getConnection();
        }
        throw new SQLException("Axiata Datasource not initialized properly");
    }

    /**
     * Format.
     *
     * @param strData the str data
     * @param finalLen the final len
     * @return the string
     * @throws Exception the exception
     */
    public static String format(String strData, int finalLen) throws Exception {
        String finalStr;
        if (finalLen <= strData.length()) {
            finalStr = strData.substring(0, finalLen);
        } else {
            finalStr = strData;
            for (int i = strData.length(); i < finalLen; i++) {
                finalStr = finalStr + " ";
            }
        }
        return (finalStr);
    } // format(String, int)

    /**
     * Format.
     *
     * @param intData the int data
     * @param finalLen the final len
     * @return the string
     * @throws Exception the exception
     */
    public static String format(int intData, int finalLen) throws Exception {
        String strData = String.valueOf(intData);
        String finalStr;
        if (finalLen <= strData.length()) {
            finalStr = strData.substring(0, finalLen);
        } else {
            finalStr = "";
            for (int i = 0; i < finalLen - strData.length(); i++) {
                finalStr = finalStr + " ";
            }
            finalStr = finalStr + strData;
        }
        return (finalStr);
    } // format(int, int)

    /**
     * Format.
     *
     * @param integerData the integer data
     * @param finalLen the final len
     * @return the string
     * @throws Exception the exception
     */
    public static String format(Integer integerData, int finalLen)
            throws Exception {
        int intData;
        String finalStr;

        intData = integerData.intValue();
        finalStr = format(intData, finalLen);

        return (finalStr);
    } // format(Integer, int)

    /**
     * Format.
     *
     * @param doubData the doub data
     * @param precision the precision
     * @param scale the scale
     * @return the string
     * @throws Exception the exception
     */
    public static String format(double doubData, int precision, int scale)
            throws Exception {
        BigDecimal decData = new BigDecimal(doubData);
        decData = decData.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
        String strData = decData.toString();

        // prepare the final string
        int finalLen = precision + 1;
        String finalStr;
        if (finalLen <= strData.length()) {
            finalStr = strData.substring(0, finalLen);
        } else {
            finalStr = "";
            for (int i = 0; i < finalLen - strData.length(); i++) {
                finalStr = finalStr + " ";
            }
            finalStr = finalStr + strData;
        }

        return (finalStr);
    } // format(double, int, int)

    /**
     * Format.
     *
     * @param decData the dec data
     * @param precision the precision
     * @param scale the scale
     * @return the string
     * @throws Exception the exception
     */
    public static String format(BigDecimal decData, int precision, int scale)
            throws Exception {
        decData = decData.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
        String strData = decData.toString();

        // prepare the final string
        int finalLen = precision + 1;
        String finalStr;
        if (finalLen <= strData.length()) {
            finalStr = strData.substring(0, finalLen);
        } else {
            finalStr = "";
            for (int i = 0; i < finalLen - strData.length(); i++) {
                finalStr = finalStr + " ";
            }
            finalStr = finalStr + strData;
        }

        return (finalStr);
    } // format(BigDecimal, int, int)

    /**
     * Format.
     *
     * @param doubleData the double data
     * @param precision the precision
     * @param scale the scale
     * @return the string
     * @throws Exception the exception
     */
    public static String format(Double doubleData, int precision, int scale)
            throws Exception {
        double doubData;
        String finalStr;

        doubData = doubleData.doubleValue();
        return (format(doubData, precision, scale));
    } // format(Double, int, int)

    /**
     * Connect.
     *
     * @return the connection
     * @throws Exception the exception
     */
    public Connection connect() throws Exception {
        System.out.println("-------- JDBC Connection Init ------------");
        Connection connection = null;

        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(connectionUrl, connectionUsername, connectionPassword);

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver Error");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }
        connection.setAutoCommit(false);

        return connection;
    } // connect

    /**
     * Disconnect.
     *
     * @param con the con
     * @throws Exception the exception
     */
    public void disconnect(Connection con) throws Exception {
        System.out.println();
        //System.out.println("  Disconnect from database.");

        // makes all changes made since the previous commit/rollback permanent
        // and releases any database locks currrently held by the Connection.
        con.commit();

        // immediately disconnects from database and releases JDBC resources
        con.close();
    } // disconnect

    /**
     * Handle exception.
     *
     * @param msg the msg
     * @param t the t
     * @throws AxataDBUtilException the axata db util exception
     */
    public static void handleException(String msg, Throwable t) throws AxataDBUtilException {
        log.error(msg, t);
        throw new AxataDBUtilException(msg, t);
    }

    /**
     * Close all connections.
     *
     * @param preparedStatement the prepared statement
     * @param connection the connection
     * @param resultSet the result set
     */
    public static void closeAllConnections(PreparedStatement preparedStatement,
            Connection connection, ResultSet resultSet) {

        closeConnection(connection);
        closeStatement(preparedStatement);
        closeResultSet(resultSet);
    }

    /**
     * Close all connections.
     *
     * @param statement the statement
     * @param connection the connection
     * @param resultSet the result set
     */
    public static void closeAllConnections(Statement statement,
            Connection connection, ResultSet resultSet) {

        closeConnection(connection);
        closeStatement(statement);
        closeResultSet(resultSet);
    }

    /**
     * Close Connection.
     *
     * @param dbConnection Connection
     */
    private static void closeConnection(Connection dbConnection) {
        if (dbConnection != null) {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                log.warn("Database error. Could not close database connection. Continuing with "
                        + "others. - " + e.getMessage(), e);
            }
        }
    }

    /**
     * Close ResultSet.
     *
     * @param resultSet ResultSet
     */
    private static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.warn("Database error. Could not close ResultSet  - " + e.getMessage(), e);
            }
        }

    }

    /**
     * Close PreparedStatement.
     *
     * @param preparedStatement PreparedStatement
     */
    private static void closeStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.warn("Database error. Could not close PreparedStatement. Continuing with"
                        + " others. - " + e.getMessage(), e);
            }
        }
    }

    /**
     * Close statement.
     *
     * @param statement the statement
     */
    private static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.warn("Database error. Could not close Statement. Continuing with"
                        + " others. - " + e.getMessage(), e);
            }
        }
    }
}
