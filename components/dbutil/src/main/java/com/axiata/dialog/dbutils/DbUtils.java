package com.axiata.dialog.dbutils;

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

public class DbUtils {

    private static DbUtils resdb;

    String driverClass;
    String connectionUrl;
    String connectionUsername;
    String connectionPassword;

    private static volatile DataSource axiataDatasource = null;
    private static final String AXIATA_DATA_SOURCE = "jdbc/AXIATA_MIFE_DB";
    private static final Log log = LogFactory.getLog(DbUtils.class);

    public static void initializeDatasources() throws SQLException, AxataDBUtilException {
        if (axiataDatasource != null) {
            return;
        }

        try {
            log.info("Before DB Initialize");
            Context ctx = new InitialContext();
            axiataDatasource = (DataSource) ctx.lookup(AXIATA_DATA_SOURCE);
        } catch (NamingException e) {
            handleException("Error while looking up the data source: " + AXIATA_DATA_SOURCE, e);
        }
    }

    public static Connection getAxiataDBConnection() throws SQLException, AxataDBUtilException {
        initializeDatasources();

        if (axiataDatasource != null) {
            return axiataDatasource.getConnection();
        }
        throw new SQLException("Axiata Datasource not initialized properly");
    }

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

    public static String format(Integer integerData, int finalLen)
            throws Exception {
        int intData;
        String finalStr;

        intData = integerData.intValue();
        finalStr = format(intData, finalLen);

        return (finalStr);
    } // format(Integer, int)

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

    public static String format(Double doubleData, int precision, int scale)
            throws Exception {
        double doubData;
        String finalStr;

        doubData = doubleData.doubleValue();
        return (format(doubData, precision, scale));
    } // format(Double, int, int)

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

    public void disconnect(Connection con) throws Exception {
        System.out.println();
        //System.out.println("  Disconnect from database.");

        // makes all changes made since the previous commit/rollback permanent
        // and releases any database locks currrently held by the Connection.
        con.commit();

        // immediately disconnects from database and releases JDBC resources
        con.close();
    } // disconnect

    public static void handleException(String msg, Throwable t) throws AxataDBUtilException {
        log.error(msg, t);
        throw new AxataDBUtilException(msg, t);
    }

    public static void closeAllConnections(PreparedStatement preparedStatement,
            Connection connection, ResultSet resultSet) {

        closeConnection(connection);
        closeStatement(preparedStatement);
        closeResultSet(resultSet);
    }

    public static void closeAllConnections(Statement statement,
            Connection connection, ResultSet resultSet) {

        closeConnection(connection);
        closeStatement(statement);
        closeResultSet(resultSet);
    }

    /**
     * Close Connection
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
     * Close ResultSet
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
     * Close PreparedStatement
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
