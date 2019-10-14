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
package com.wso2telco.core.dbutils;

import com.wso2telco.core.dbutils.dao.SpendLimitDAO;
import com.wso2telco.core.dbutils.model.FederatedIdpMappingDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

// TODO: Auto-generated Javadoc

/**
 * The Class DbService.
 */
public class DbService {

    /**
     * The log.
     */
    private static Log log = LogFactory.getLog(DbService.class);

    /**
     * Outbound subscription entry.
     *
     * @param notifyurl the notifyurl
     * @return the integer
     * @throws Exception the exception
     */

    public Integer outboundSubscriptionEntry(String notifyurl) throws Exception {

        Connection con = null;
        PreparedStatement selectStatement = null;
        PreparedStatement insertStatement = null;
        ResultSet rs = null;
        Integer newid = 0;

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            StringBuilder queryString = new StringBuilder("SELECT ");
            queryString.append("MAX(dn_subscription_did) maxid ");
            queryString.append("FROM ");
            queryString.append("outbound_subscriptions ");

            selectStatement = con.prepareStatement(queryString.toString());

            log.debug("sql query in getValidPayCategories : " + selectStatement);

            rs = selectStatement.executeQuery();

            if (rs.next()) {
                newid = rs.getInt("maxid") + 1;
            }

            StringBuilder insertQueryString = new StringBuilder("INSERT INTO ");
            insertQueryString.append("outbound_subscriptions ");
            insertQueryString.append(" (dn_subscription_did,notifyurl) ");
            insertQueryString.append("VALUES (?, ?) ");

            insertStatement = con.prepareStatement(insertQueryString.toString());

            insertStatement.setInt(1, newid);
            insertStatement.setString(2, notifyurl);

            insertStatement.executeUpdate();

        } catch (PersistenceException e) {
            log.error("database operation error in outbound subscriptions entry", e);
        } catch (Exception e) {
            log.error("database operation error in outbound subscriptions entry", e);
        } finally {
            DbUtils.closeAllConnections(selectStatement, con, rs);
            DbUtils.closeAllConnections(insertStatement, null, null);
        }

        return newid;
    }

    /**
     * Application operators.
     *
     * @param appID the appID
     * @return the list
     * @throws Exception the exception
     */

    public List<Operator> applicationOperators(Integer appID) throws Exception {

        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Operator> operators = new ArrayList<Operator>();

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            StringBuilder queryString = new StringBuilder("SELECT ");
            queryString.append("oa.id id,oa.applicationid,oa.operatorid,o.operatorname,o.refreshtoken,o" +
                    ".tokenvalidity,o.tokentime,o.token, o.tokenurl, o.tokenauth ");
            queryString.append("FROM ");
            queryString.append("operatorapps oa, operators o ");
            queryString.append("WHERE ");
            queryString.append("oa.operatorid = o.id ");
            queryString.append("AND oa.isactive = 1 ");
            queryString.append("AND oa.applicationid = ? ");

            statement = con.prepareStatement(queryString.toString());

            statement.setInt(1, appID);
            rs = statement.executeQuery();
            while (rs.next()) {
                Operator oper = new Operator();
                oper.setId(rs.getInt("id"));
                oper.setApplicationid(rs.getInt("applicationid"));
                oper.setOperatorid(rs.getInt("operatorid"));
                oper.setOperatorname(rs.getString("operatorname"));
                oper.setRefreshtoken(rs.getString("refreshtoken"));
                oper.setTokenvalidity(rs.getLong("tokenvalidity"));
                oper.setTokentime(rs.getLong("tokentime"));
                oper.setToken(rs.getString("token"));
                oper.setTokenurl(rs.getString("tokenurl"));
                oper.setTokenauth(rs.getString("tokenauth"));
                operators.add(oper);
            }

        } catch (PersistenceException e) {
            log.error("database operation error in operator entry", e);
        } catch (Exception e) {
            log.error("database operation error in operator entry", e);
        } finally {
            DbUtils.closeAllConnections(statement, con, rs);
        }

        return operators;

    }

    /**
     * Subscription dn notifi.
     *
     * @param subscriptionID the subscriptionID
     * @return the string
     * @throws Exception the exception
     */

    public String subscriptionDNNotifi(Integer subscriptionID) throws Exception {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        String notifyurls = "";

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            StringBuilder queryString = new StringBuilder("SELECT ");
            queryString.append("notifyurl ");
            queryString.append("FROM ");
            queryString.append("outbound_subscriptions ");
            queryString.append("WHERE ");
            queryString.append("dn_subscription_did = ? ");

            statement = con.prepareStatement(queryString.toString());

            statement.setInt(1, subscriptionID);
            rs = statement.executeQuery();

            if (rs.next()) {
                notifyurls = rs.getString("notifyurl");
            }

        } catch (PersistenceException e) {
            log.error("database operation error in subscription entry", e);
        } catch (Exception e) {
            log.error("database operation error in subscription entry", e);
        } finally {
            DbUtils.closeAllConnections(statement, con, rs);
        }

        return notifyurls;
    }

    /**
     * Subscription notifi.
     *
     * @param subscriptionID the subscriptionID
     * @return the string
     * @throws Exception the exception
     */
    public String subscriptionNotifi(Integer subscriptionID) throws Exception {

        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String notifyurls = "";

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            StringBuilder queryString = new StringBuilder("SELECT ");
            queryString.append("notifyurl ");
            queryString.append("FROM ");
            queryString.append("subscriptions ");
            queryString.append("WHERE ");
            queryString.append("mo_subscription_did = ? ");

            statement = con.prepareStatement(queryString.toString());

            statement.setInt(1, subscriptionID);
            rs = statement.executeQuery();

            if (rs.next()) {
                notifyurls = rs.getString("notifyurl");
            }

        } catch (PersistenceException e) {
            log.error("database operation error in subscription entry", e);
        } catch (Exception e) {
            log.error("database operation error in subscription entry", e);
        } finally {
            DbUtils.closeAllConnections(statement, con, rs);
        }

        return notifyurls;

    }

    /**
     * Operator endpoints.
     *
     * @param appID the appID
     * @return the list
     * @throws Exception the exception
     */

    public List<Operatorendpoint> operatorEndpoints(Integer appID) throws Exception {

        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Operatorendpoint> endpoints = new ArrayList();

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            StringBuilder queryString = new StringBuilder("SELECT ");
            queryString.append("operatorendpoints.ID as ID, operatorid,operatorname,api,endpoint ");
            queryString.append("FROM ");
            queryString.append("operatorendpoints, operators ");
            queryString.append("WHERE ");
            queryString.append("operatorendpoints.operatorid = operators.id ");
            queryString.append("AND ");
            queryString.append("operatorendpoints.id IN ( ");
            queryString.append("SELECT ");
            queryString.append("endpointid ");
            queryString.append("FROM ");
            queryString.append("endpointapps ");
            queryString.append("WHERE ");
            queryString.append("applicationid = ? ");
            queryString.append("AND ");
            queryString.append("isactive = 1) ");

            statement = con.prepareStatement(queryString.toString());

            statement.setInt(1, appID);
            rs = statement.executeQuery();

            while (rs.next()) {
                Operatorendpoint endpoint = new Operatorendpoint(rs.getInt("operatorid"), rs.getString
                        ("operatorname"), rs.getString("api"), rs.getString("endpoint"));
                endpoint.setId(rs.getInt("ID"));
                endpoints.add(endpoint);
            }

        } catch (PersistenceException e) {
            log.error("database operation error in operator endpoints entry", e);
        } catch (Exception e) {
            log.error("database operation error in operator endpoints entry", e);
        } finally {
            DbUtils.closeAllConnections(statement, con, rs);
        }

        return endpoints;
    }

    /**
     * Update application op.
     *
     * @param appID      the appID
     * @param operatorid the operatorid
     * @param opstat     the opstat
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean UpdateApplicationOp(int appID, int operatorid, boolean opstat) throws Exception {

        Connection con = null;
        PreparedStatement statement = null;

        int opactive = (opstat ? 1 : 0);

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            StringBuilder queryString = new StringBuilder("UPDATE ");
            queryString.append("operatorapps ");
            queryString.append("SET ");
            queryString.append("isactive = ? ");
            queryString.append("WHERE ");
            queryString.append("applicationid = ? ");
            queryString.append("AND ");
            queryString.append("operatorid = ? ");

            statement = con.prepareStatement(queryString.toString());

            statement.setInt(1, opactive);
            statement.setInt(2, appID);
            statement.setInt(3, operatorid);

            statement.executeUpdate();

        } catch (PersistenceException e) {
            log.error("database operation error in updating operatorapps", e);
        } catch (Exception e) {
            log.error("database operation error in updating operatorapps", e);
        } finally {
            DbUtils.closeAllConnections(statement, con, null);
        }

        return true;
    }

    /**
     * Application entry.
     *
     * @param appID     the appID
     * @param operators the operators
     * @return the integer
     * @throws Exception the exception
     */
    public Integer applicationEntry(int appID, Integer[] operators) throws Exception {

        Integer newid = 0;

        StringBuilder queryString = new StringBuilder("INSERT INTO ");
        queryString.append("operatorapps ");
        queryString.append("(applicationid,operatorid) ");
        queryString.append("VALUES (?, ? ) ");

        try (Connection con = DbUtils.getDBConnection();
             PreparedStatement statement = con.prepareStatement(queryString.toString())) {
            for (Integer d : operators) {
                statement.setInt(1, appID);
                statement.setInt(2, d);
                statement.executeUpdate();
            }
        } catch (PersistenceException e) {
            log.error("database operation error in inserting operatorapps", e);
        } catch (Exception e) {
            log.error("database operation error in inserting operatorapps", e);
        }

        return newid;
    }

    /**
     * Gets the operators.
     *
     * @return the operators
     * @throws Exception the exception
     */

    public List<Operator> getOperators() throws Exception {

        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Operator> operators = new ArrayList<Operator>();

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            StringBuilder queryString = new StringBuilder("SELECT ");
            queryString.append("ID, operatorname ");
            queryString.append("SET ");
            queryString.append("FROM ");
            queryString.append("operators ");

            statement = con.prepareStatement(queryString.toString());

            rs = statement.executeQuery();

            while (rs.next()) {
                Operator operator = new Operator();
                operator.setOperatorid(rs.getInt("ID"));
                operator.setOperatorname(rs.getString("operatorname"));
                operators.add(operator);
            }

        } catch (PersistenceException e) {
            log.error("database operation error in retrieving operators", e);
        } catch (Exception e) {
            log.error("database operation error in retrieving operators", e);
        } finally {
            DbUtils.closeAllConnections(statement, con, rs);
        }
        return operators;
    }

    /**
     * Insert operator app endpoints.
     *
     * @param appID            the app id
     * @param opEndpointIDList the op endpoint id list
     * @throws Exception the exception
     */
    public void insertOperatorAppEndpoints(int appID, int[] opEndpointIDList) throws Exception {

        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = DbUtils.getDBConnection();
            String inputStr = "";

            log.debug("opEndpointIDList.length : " + opEndpointIDList.length);
            for (int i = 0; i < opEndpointIDList.length; i++) {
                if (opEndpointIDList[i] > 0) {

                    if (inputStr.length() > 0) {
                        inputStr = inputStr + ",";
                    }
                    inputStr = inputStr + "(" + opEndpointIDList[i] + ","
                            + appID + ",0)";
                    log.debug("inputStr : " + inputStr);
                }
            }

            log.debug("Final inputStr : " + inputStr);

            StringBuilder queryString = new StringBuilder("INSERT INTO ");
            queryString.append("endpointapps ");
            queryString.append("(endpointid, applicationid, isactive) ");
            queryString.append("VALUES ");
            queryString.append("inputStr ");

            log.debug("sql : " + queryString);

            statement = con.prepareStatement(queryString.toString());

            statement.executeUpdate();

        } catch (PersistenceException e) {
            log.error("database operation error in inserting endpointapps", e);
        } catch (Exception e) {
            log.error("database operation error in inserting endpointapps", e);
        } finally {
            DbUtils.closeAllConnections(statement, con, null);
        }
    }

    /**
     * Update operator app endpoint status.
     *
     * @param appID        the app id
     * @param opEndpointID the op endpoint id
     * @param status       the status
     * @throws Exception the exception
     */
    public void updateOperatorAppEndpointStatus(int appID, int opEndpointID, int status) throws Exception {

        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = DbUtils.getDBConnection();

            StringBuilder queryString = new StringBuilder("UPDATE");
            queryString.append("endpointapps ");
            queryString.append("SET ");
            queryString.append("isactive = ? ");
            queryString.append("WHERE ");
            queryString.append("endpointid = ? ");
            queryString.append("AND ");
            queryString.append("applicationid = ? ");

            statement = con.prepareStatement(queryString.toString());

            statement.setInt(1, status);
            statement.setInt(2, opEndpointID);
            statement.setInt(3, appID);

            statement.executeQuery();

        } catch (PersistenceException e) {
            log.error("database operation error in updating endpointapps", e);
        } catch (Exception e) {
            log.error("database operation error in updating endpointapps", e);
        } finally {
            DbUtils.closeAllConnections(statement, con, null);
        }
    }

    /**
     * Gets the operator endpoints.
     *
     * @return the operator endpoints
     * @throws Exception the exception
     */

    public List<Operatorendpoint> getOperatorEndpoints() throws Exception {

        List<Operatorendpoint> operatorEndpoints = new ArrayList();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found.");
            }

            StringBuilder queryString = new StringBuilder("SELECT ");
            queryString.append("ID,operatorid,api ");
            queryString.append("FROM ");
            queryString.append("operatorendpoints ");

            statement = con.prepareStatement(queryString.toString());

            rs = statement.executeQuery();

            while (rs.next()) {
                Operatorendpoint endpoint = new Operatorendpoint(rs.getInt("operatorid"), null, rs.getString("api"),
                        null);
                endpoint.setId(rs.getInt("ID"));
                operatorEndpoints.add(endpoint);
            }

        } catch (PersistenceException e) {
            log.error("database operation error in selecting from operatorendpoints", e);
        } catch (Exception e) {
            log.error("database operation error in selecting from operatorendpoints", e);
        } finally {
            DbUtils.closeAllConnections(statement, con, rs);
        }

        return operatorEndpoints;
    }

    /**
     * Update app approval status op.
     *
     * @param appID      the appID
     * @param operatorId the operator id
     * @param status     the status
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean updateAppApprovalStatusOp(int appID, int operatorId, int status) throws Exception {

        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found.");
            }

            StringBuilder queryString = new StringBuilder("UPDATE ");
            queryString.append("operatorapps ");
            queryString.append("SET ");
            queryString.append("isactive = ? ");
            queryString.append("WHERE ");
            queryString.append("applicationid = ? ");
            queryString.append("AND ");
            queryString.append("operatorid = ? ");

            statement = con.prepareStatement(queryString.toString());

            statement.setInt(1, status);
            statement.setInt(2, appID);
            statement.setInt(3, operatorId);

            statement.executeUpdate();

        } catch (PersistenceException e) {
            log.error("database operation error in updating operatorapps", e);
        } catch (Exception e) {
            log.error("database operation error in updating operatorapps", e);
        } finally {
            DbUtils.closeAllConnections(statement, con, null);
        }

        return true;
    }

    /**
     * Insert validator for subscription.
     *
     * @param appID       the app id
     * @param apiID       the api id
     * @param validatorID the validator id
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean insertValidatorForSubscription(int appID, int apiID, int validatorID) throws Exception {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found.");
            }

            StringBuilder queryString = new StringBuilder("INSERT INTO ");
            queryString.append("subscription_validator ");
            queryString.append("(application_id, api_id, validator_id) ");
            queryString.append("VALUES ");
            queryString.append("(?, ?, ?) ");

            statement = con.prepareStatement(queryString.toString());

            statement.setInt(1, appID);
            statement.setInt(2, apiID);
            statement.setInt(3, validatorID);

            statement.executeUpdate();

        } catch (PersistenceException e) {
            log.error("database operation error in inserting in to subscription_validator", e);
        } catch (Exception e) {
            log.error("database operation error in inserting in to subscription_validator", e);
        } finally {
            DbUtils.closeAllConnections(statement, con, null);
        }
        return true;
    }

    /**
     * Removes the merchant provision.
     *
     * @param appID      the app id
     * @param subscriber the subscriber
     * @param operator   the operator
     * @param merchants  the merchants
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean removeMerchantProvision(Integer appID, String subscriber, String operator, String[] merchants)
            throws Exception {

        int operatorid = 0;

        StringBuilder selectQueryString = new StringBuilder("SELECT ");
        selectQueryString.append("id ");
        selectQueryString.append("FROM ");
        selectQueryString.append("operators ");
        selectQueryString.append("WHERE ");
        selectQueryString.append("operatorname = ? ");

        try (final Connection con = DbUtils.getDBConnection();
             final PreparedStatement selectStatement = con.prepareStatement(selectQueryString.toString())) {

            selectStatement.setString(1, operator);
            try (ResultSet rs = selectStatement.executeQuery()) {
                if (rs.next()) {
                    operatorid = rs.getInt("id");
                } else {
                    throw new Exception("Operator Not Found");
                }
            }

            for (int i = 0; i < merchants.length; i++) {

                if (appID == null) {

                    StringBuilder deleteQueryString = new StringBuilder("DELETE ");
                    deleteQueryString.append("FROM ");
                    deleteQueryString.append("merchantopco_blacklist ");
                    deleteQueryString.append("WHERE ");
                    deleteQueryString.append("application_id = null ");
                    deleteQueryString.append("AND ");
                    deleteQueryString.append("operator_id = ? ");
                    deleteQueryString.append("AND ");
                    deleteQueryString.append("subscriber = ? ");
                    deleteQueryString.append("AND ");
                    deleteQueryString.append("merchant = ? ");

                    try (final PreparedStatement deleteStatement = con.prepareStatement(deleteQueryString.toString())) {
                        deleteStatement.setInt(1, operatorid);
                        deleteStatement.setString(2, subscriber);
                        deleteStatement.setString(3, merchants[i]);
                        deleteStatement.executeUpdate();
                    }

                } else {

                    StringBuilder queryString = new StringBuilder("DELETE ");
                    queryString.append("FROM ");
                    queryString.append("merchantopco_blacklist ");
                    queryString.append("WHERE ");
                    queryString.append("application_id = ? ");
                    queryString.append("AND ");
                    queryString.append("operator_id = ? ");
                    queryString.append("AND ");
                    queryString.append("subscriber = ? ");
                    queryString.append("AND ");
                    queryString.append("merchant = ? ");

                    try (final PreparedStatement deleteStatement = con.prepareStatement(queryString.toString())) {
                        deleteStatement.setInt(1, appID);
                        deleteStatement.setInt(2, operatorid);
                        deleteStatement.setString(3, subscriber);
                        deleteStatement.setString(4, merchants[i]);
                        deleteStatement.executeUpdate();
                    }
                }
            }
        } catch (PersistenceException e) {
            log.error("database operation error while Provisioning Merchant", e);
        } catch (Exception e) {
            log.error("database operation error while Provisioning Merchant", e);
        }
        return true;
    }

    /**
     * Gets the prefix from country code.
     *
     * @param countryCode the country code
     * @return the prefix from country code
     * @throws Exception the exception
     */
    public String getPrefixFromCountryCode(String countryCode) throws Exception {

        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String prefix = "";

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found.");
            }

            StringBuilder selectQueryString = new StringBuilder("SELECT");
            selectQueryString.append("prefix ");
            selectQueryString.append("FROM ");
            selectQueryString.append("operatorcodes ");
            selectQueryString.append("WHERE ");
            selectQueryString.append("countrycode = ? ");

            statement = con.prepareStatement(selectQueryString.toString());

            statement.setString(1, countryCode);

            rs = statement.executeQuery();

            if (rs.next()) {
                prefix = rs.getString("prefix");
            }

        } catch (PersistenceException e) {
            log.error("database operation error while selecting from subscriptions", e);
        } catch (Exception e) {
            log.error("database operation error while selecting from subscriptions", e);
        } finally {
            DbUtils.closeAllConnections(statement, con, rs);
        }

        return prefix;

    }

    /**
     * Insert sms request ids.
     *
     * @param requestID        the request id
     * @param senderAddress    the sender address
     * @param pluginRequestIDs the plugin request i ds
     * @return true, if successful
     * @throws Exception the exception
     */

    public boolean insertSmsRequestIds(String requestID, String senderAddress, Map<String, String> pluginRequestIDs)
            throws Exception {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found.");
            }

            StringBuilder queryString = new StringBuilder("INSERT INTO ");
            queryString.append("sendsms_reqid ");
            queryString.append("(hub_requestid,sender_address,delivery_address,plugin_requestid) ");
            queryString.append("VALUES ");
            queryString.append("(?, ?, ?, ?) ");

            ps = con.prepareStatement(queryString.toString());

            ps.setString(1, requestID);
            ps.setString(2, senderAddress);
            for (Map.Entry<String, String> entry : pluginRequestIDs.entrySet()) {
                ps.setString(3, entry.getKey());
                ps.setString(4, entry.getValue());
                ps.executeUpdate();
            }

        } catch (PersistenceException e) {
            log.error("database operation error while inserting in to sendsms_reqid ", e);
        } catch (Exception e) {
            log.error("database operation error while inserting in to sendsms_reqid ", e);
        } finally {
            DbUtils.closeAllConnections(ps, con, null);
        }
        return true;
    }

    /**
     * Gets the sms request ids.
     *
     * @param requestID     the request id
     * @param senderAddress the sender address
     * @return the sms request ids
     * @throws Exception the exception
     */

    public Map<String, String> getSmsRequestIds(String requestID, String senderAddress) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, String> pluginRequestIDs = new HashMap<String, String>();

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found.");
            }

            StringBuilder queryString = new StringBuilder("SELECT ");
            queryString.append("delivery_address, plugin_requestid ");
            queryString.append("FROM ");
            queryString.append("sendsms_reqid ");
            queryString.append("WHERE ");
            queryString.append("hub_requestid = ? ");
            queryString.append("AND ");
            queryString.append("sender_address = ? ");

            ps = con.prepareStatement(queryString.toString());
            ps.setString(1, requestID);
            ps.setString(2, senderAddress);
            rs = ps.executeQuery();

            while (rs.next()) {
                pluginRequestIDs.put(rs.getString("delivery_address"), rs.getString("plugin_requestid"));
            }
        } catch (PersistenceException e) {
            log.error("database operation error while inserting in to sendsms_reqid", e);
        } catch (Exception e) {
            log.error("Error while inserting in to sendsms_reqid. ", e);
        } finally {
            DbUtils.closeAllConnections(ps, con, rs);
        }
        return pluginRequestIDs;
    }

    /**
     * Gets the SP token map.
     *
     * @return the SP token map
     * @throws Exception
     */
    public Map<String, String> getSPTokenMap() throws Exception {

        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Map<String, String> spToken = new HashMap<String, String>();

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found.");
            }

            StringBuilder queryString = new StringBuilder("SELECT ");
            queryString.append("consumer_key, token ");
            queryString.append("FROM ");
            queryString.append("sp_token ");

            statement = con.prepareStatement(queryString.toString());

            rs = statement.executeQuery();

            while (rs.next()) {
                String consumerKey = (rs.getString("consumer_key"));
                String token = (rs.getString("token"));
                spToken.put(consumerKey, token);
            }

        } catch (PersistenceException e) {
            log.error("database operation error while selecting from sp_token", e);
        } catch (Exception e) {
            log.error("database operation error while selecting from sp_token", e);
        } finally {
            DbUtils.closeAllConnections(statement, con, rs);
        }

        return spToken;
    }

    public SpendLimitDAO getGroupTotalDayAmount(String groupName, String operator, String msisdn)
            throws DBUtilException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SpendLimitDAO spendLimitDAO = null;

        try {
            con = DbUtils.getDBConnection();

            Calendar calendarFrom = Calendar.getInstance();
            calendarFrom.set(Calendar.AM_PM, Calendar.AM);
            calendarFrom.set(Calendar.HOUR, 00);
            calendarFrom.set(Calendar.MINUTE, 00);
            calendarFrom.set(Calendar.SECOND, 00);
            calendarFrom.set(Calendar.MILLISECOND, 00);

            Calendar calendarTo = Calendar.getInstance();
            calendarTo.set(Calendar.AM_PM, Calendar.PM);
            calendarTo.set(Calendar.HOUR, 11);
            calendarTo.set(Calendar.MINUTE, 59);
            calendarTo.set(Calendar.SECOND, 59);
            calendarTo.set(Calendar.MILLISECOND, 999);

            String sql = "SELECT SUM(amount) AS amount "
                    + "FROM spendlimitdata  "
                    + "where effectiveTime between ? and ? "
                    + "and groupName=? "
                    + "and operatorId=? "
                    + "and msisdn=? "
                    + "group by groupName, operatorId, msisdn";

            ps = con.prepareStatement(sql);

            ps.setLong(1, calendarFrom.getTimeInMillis());
            ps.setLong(2, calendarTo.getTimeInMillis());
            ps.setString(3, groupName);
            ps.setString(4, operator);
            ps.setString(5, msisdn);

            rs = ps.executeQuery();

            if (rs.next()) {
                spendLimitDAO = new SpendLimitDAO();
                spendLimitDAO.setAmount(rs.getDouble("amount"));
            }
        } catch (Exception e) {
            DbUtils.handleException("Error while checking Operator Spend Limit. ", e);
        } finally {
            DbUtils.closeAllConnections(ps, con, rs);
        }
        return spendLimitDAO;
    }

    public SpendLimitDAO getGroupTotalMonthAmount(String groupName, String operator, String msisdn)
            throws DBUtilException {

        SpendLimitDAO spendLimitDAO = null;

        Calendar calendarFrom = GregorianCalendar.getInstance();
        calendarFrom.set(Calendar.DAY_OF_MONTH, calendarFrom.getActualMinimum(Calendar.DAY_OF_MONTH));

        calendarFrom.set(Calendar.HOUR_OF_DAY, 0);
        calendarFrom.set(Calendar.MINUTE, 0);
        calendarFrom.set(Calendar.SECOND, 0);
        calendarFrom.set(Calendar.MILLISECOND, 0);

        Calendar calendarTo = GregorianCalendar.getInstance();
        calendarTo.setTime(new Date());
        calendarTo.set(Calendar.DAY_OF_MONTH,
                calendarTo.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendarTo.set(Calendar.HOUR_OF_DAY, 23);
        calendarTo.set(Calendar.MINUTE, 59);
        calendarTo.set(Calendar.SECOND, 59);
        calendarTo.set(Calendar.MILLISECOND, 999);

        String sql = "SELECT SUM(amount) AS amount "
                + "FROM spendlimitdata  "
                + "where effectiveTime between ? and ? "
                + "and groupName=? "
                + "and operatorId=? "
                + "and msisdn=? "
                + "group by groupName,  operatorId, msisdn";

        try (final Connection con = DbUtils.getDBConnection();
             final PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, calendarFrom.getTimeInMillis());
            ps.setLong(2, calendarTo.getTimeInMillis());
            ps.setString(3, groupName);
            ps.setString(4, operator);
            ps.setString(5, msisdn);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    spendLimitDAO = new SpendLimitDAO();
                    spendLimitDAO.setAmount(rs.getDouble("amount"));
                }
            }

            } catch (Exception e) {
                DbUtils.handleException("Error while checking Operator Spend Limit. ", e);
            }
            return spendLimitDAO;
        }

    /**
     * Get payment time
     *
     * @param messageDid                 Id of the message
     * @param orginalServerReferanceCode server reference code
     * @return time in long
     * @throws Exception If an error occurs during this operation
     */
    public long getPaymentTime(int messageDid, String orginalServerReferanceCode)
            throws Exception {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet results = null;
        long paidTime = 0L;

        try {
            connection = DbUtils.getDBConnection();
            if (connection == null) {
                throw new Exception("Connection not found");
            }

            String sql = "select MIN(reportedtime) AS reportedtime "
                    + "from mdtrequestmessage "
                    + "where clientrefval=? "
                    + "and msgtypeId=? ";

            ps = connection.prepareStatement(sql);
            ps.setString(1, orginalServerReferanceCode);
            ps.setInt(2, messageDid);

            results = ps.executeQuery();

            while (results.next()) {
                paidTime = results.getLong("reportedtime");
            }

        } catch (SQLException e) {
            DbUtils.handleException("Error occurred while getting payment Details", e);

        } finally {
            DbUtils.closeAllConnections(ps, connection, results);
        }
        return paidTime;
    }

    /**
     * Insert authorization code mappings between local & external
     * 
     * @param operator is the external IDP
     * @param fidpAuthCode FIDP generated code
     */

    public void insertFederatedAuthCodeMappings(String operator, String fidpAuthCode) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DbUtils.getConnectDbConnection();
            if (con == null) {
                throw new Exception("Connection not found.");
            }

            String query = "INSERT INTO federated_idp_mappings (operator,federated_authcode) VALUES (?,?) ";

            ps = con.prepareStatement(query.toString());
            ps.setString(1, operator);
            ps.setString(2, fidpAuthCode);
            ps.executeUpdate();

        } catch (Exception e) {
            log.error("Database operation error while inserting auth code in to federated_idp_mappings ", e);
            DbUtils.handleException("Error in inserting federated auth code mappings : " + e.getMessage(), e);
        } finally {
            DbUtils.closeAllConnections(ps, con, null);
        }
    }

    /**
     * Read authorization code mappings between local & external
     * 
     * @param fidpAuthCode locally generated code
     * @return the object for persisted authcode mapping
     * @throws Exception if an error occurs during this operation
     */
    public FederatedIdpMappingDTO retrieveFederatedAuthCodeMappings(String fidpAuthCode) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        FederatedIdpMappingDTO fidpMapping = new FederatedIdpMappingDTO();

        try {
            con = DbUtils.getConnectDbConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            String sql = "select operator,federated_authcode from federated_idp_mappings where federated_authcode=? ";

            ps = con.prepareStatement(sql);
            ps.setString(1, fidpAuthCode);
            rs = ps.executeQuery();

            while (rs.next()) {
                fidpMapping.setOperator(rs.getString("operator"));
                fidpMapping.setFidpAuthCode(rs.getString("federated_authcode"));
            }

        } catch (SQLException e) {
            log.error("Database operation error while retrieving federated auth code from federated_idp_mappings ", e);
            DbUtils.handleException("Error in retrieving federated auth code mappings : " + e.getMessage(), e);

        } finally {
            DbUtils.closeAllConnections(ps, con, rs);
        }
        return fidpMapping;

    }

    /**
     * Insert authorization token mappings between local & external
     * 
     * @param migAuthToken locally generated token
     * @param fidpAuthCode locally generated code
     * @param fidpAuthToken FIDP generated token
     * @throws Exception if an error occurs during this operation
     */
    public void insertFederatedTokenMappings(String migAuthToken, String fidpAuthCode, String fidpAuthToken)
            throws Exception {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DbUtils.getConnectDbConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            String query = "UPDATE federated_idp_mappings SET accesstoken = ?, federated_accesstoken = ? WHERE federated_authcode = ? ";

            ps = con.prepareStatement(query.toString());
            ps.setString(1, migAuthToken);
            ps.setString(2, fidpAuthToken);
            ps.setString(3, fidpAuthCode);
            ps.executeUpdate();

        } catch (SQLException e) {
            log.error("Database operation error in updating federated access token to federated_idp_mappings", e);
            DbUtils.handleException("Error in inserting federated access token mappings : " + e.getMessage(), e);
        } finally {
            DbUtils.closeAllConnections(ps, con, null);
        }

    }

    /**
     * Read authorization token mappings between local & external
     * 
     * @param migAuthToken FIDP generated token
     * @return the object for persisted access token mapping
     * @throws Exception
     */
    public FederatedIdpMappingDTO retrieveFederatedTokenMappings(String migAuthToken) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        FederatedIdpMappingDTO fidpMapping = new FederatedIdpMappingDTO();

        try {
            con = DbUtils.getConnectDbConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            String sql = "select operator,federated_accesstoken from federated_idp_mappings "
                    + "where accesstoken=? order by updated_time desc limit 1";
            ps = con.prepareStatement(sql);
            ps.setString(1, migAuthToken);

            rs = ps.executeQuery();

            while (rs.next()) {
                fidpMapping.setOperator(rs.getString("operator"));
                fidpMapping.setFidpAccessToken(rs.getString("federated_accesstoken"));
            }

        } catch (SQLException e) {
            log.error("Database operation error while retrieving federated access token from federated_idp_mappings ",
                    e);
            DbUtils.handleException("Error in retrieving federated access token mappings : " + e.getMessage(), e);
        } finally {
            DbUtils.closeAllConnections(ps, con, rs);
        }
        return fidpMapping;

    }

    public FederatedIdpMappingDTO checkIfExistingFederatedToken(String fidpAuthToken) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        FederatedIdpMappingDTO fidpMapping = new FederatedIdpMappingDTO();

        try {
            con = DbUtils.getConnectDbConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            String sql = "select operator,accesstoken from federated_idp_mappings "
                    + "where federated_accesstoken =? order by updated_time desc limit 1";
            ps = con.prepareStatement(sql);
            ps.setString(1, fidpAuthToken);

            rs = ps.executeQuery();

            while (rs.next()) {
                fidpMapping.setOperator(rs.getString("operator"));
                fidpMapping.setAccessToken(rs.getString("accesstoken"));
            }

        } catch (SQLException e) {
            log.error("Database operation error while retrieving federated access token from federated_idp_mappings ",
                    e);
            DbUtils.handleException("Error in retrieving federated access token mappings : " + e.getMessage(), e);
        } finally {
            DbUtils.closeAllConnections(ps, con, rs);
        }
        return fidpMapping;

    }

    public static  Map<String, Set<String>> getAllowedAuthenticatorSetForMNO()
            throws Exception {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * from allowed_authenticators_mno";
        Map<String, Set<String>> authenticatorSet = new HashMap<String, Set<String>>();
        try {
            connection = DbUtils.getConnectDbConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String mobile_network_operator=rs.getString("mobile_network_operator");
                String allowed_authenticator=rs.getString("allowed_authenticator");
                Set<String> MNOAuth = new HashSet<>();
                if(authenticatorSet.containsKey(mobile_network_operator)){
                    MNOAuth=authenticatorSet.remove(mobile_network_operator);
                    MNOAuth.add(allowed_authenticator);
                }else{
                    MNOAuth.add(allowed_authenticator);
                }
                authenticatorSet.put(mobile_network_operator,MNOAuth);
            }
        } catch (SQLException e) {
            log.error("Database operation error while retrieving allowed authenticators for MNOs ",
                    e);
            DbUtils.handleException("Error in retrieving allowed authenticators for MNOs mappings : " + e.getMessage(), e);
        } finally {
            DbUtils.closeAllConnections(ps, connection, rs);
        }
        return authenticatorSet;
    }

}
