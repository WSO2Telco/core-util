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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.cache.Cache;
import javax.cache.Caching;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * Hello world!.
 */
public class AxiataDbService {

    /** The log. */
    private static Log log = LogFactory.getLog(AxiataDbService.class);
    
    /** The Constant AXIATA_MEDIATOR_CACHE_MANAGER. */
    private static final String AXIATA_MEDIATOR_CACHE_MANAGER = "AxiataMediatorCacheManager";
    
    /** The Constant MSISDN_SPEND_LIMIT_TABLE. */
    private static final String MSISDN_SPEND_LIMIT_TABLE = "spendlimitexceeded_msisdn";
    
    /** The Constant APPLICATION_SPEND_LIMIT_TABLE. */
    private static final String APPLICATION_SPEND_LIMIT_TABLE = "spendlimitexceeded_application";
    
    /** The Constant OPERATOR_SPEND_LIMIT_TABLE. */
    private static final String OPERATOR_SPEND_LIMIT_TABLE = "spendlimitexceeded_operator";

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        try {
            /* Connection con = DbUtils.getInstance().connect();

             if (con == null) {
             throw new Exception("Connection not found");
             }

             Statement st = con.createStatement();
             String sql = "SELECT ID, axiataid, domainurl "
             + "FROM operatorsubs";

             ResultSet rs = st.executeQuery(sql);

             while (rs.next()) {
             System.out.println(rs.getString("ID"));
             System.out.println(rs.getString("axiataid"));
             System.out.println(rs.getString("domainurl"));
             }
             */
            new AxiataDbService().insertMerchantProvision(9, "admin", "DIALOG", new String[]{"mahesh", "roshan"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ussd request entry.
     *
     * @param notifyurl the notifyurl
     * @return the integer
     * @throws Exception the exception
     */
    public Integer ussdRequestEntry(String notifyurl) throws Exception {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Integer newid = 0;

        try {
            con = DbUtils.getAxiataDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "SELECT MAX(axiataid) maxid "
                    + "FROM ussd_request_entry";

            rs = st.executeQuery(sql);
            if (rs.next()) {
                newid = rs.getInt("maxid") + 1;
            }

            sql = "INSERT INTO ussd_request_entry (axiataid,notifyurl) "
                    + "VALUES (" + newid + ",'" + notifyurl + "')";
            st.executeUpdate(sql);

        } catch (Exception e) {
            DbUtils.handleException("Error while inserting in to ussd_request_entry. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return newid;

    }

    /**
     * Ussd entry delete.
     *
     * @param axiataid the axiataid
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean ussdEntryDelete(Integer axiataid) throws Exception {

        Connection con = DbUtils.getAxiataDBConnection();
        Statement st = null;

        try {

            if (con == null) {
                throw new Exception("Connection not found");
            }

            Integer newid = 0;

            st = con.createStatement();
            String sql = "DELETE FROM ussd_request_entry "
                    + "WHERE axiataid = " + axiataid + "";

            st.executeUpdate(sql);

        } catch (Exception e) {
            DbUtils.handleException("Error while deleting ussd_request_entry. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, null);
        }

        return true;
    }

    /**
     * Gets the USSD notify.
     *
     * @param axiataid the axiataid
     * @return the USSD notify
     * @throws Exception the exception
     */
    public String getUSSDNotify(Integer axiataid) throws Exception {

        Connection con = DbUtils.getAxiataDBConnection();
        Statement st = null;
        ResultSet rs = null;
        String notifyurls = "";
        try {

            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "SELECT notifyurl "
                    + "FROM ussd_request_entry "
                    + "WHERE axiataid = " + axiataid + "";

            rs = st.executeQuery(sql);

            if (rs.next()) {
                notifyurls = rs.getString("notifyurl");
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while selecting from ussd_request_entry. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return notifyurls;

    }

    /**
     * Operatorsubs entry.
     *
     * @param domainsubs the domainsubs
     * @param axiataid the axiataid
     * @return true, if successful
     * @throws Exception the exception
     */
    /*public Integer subscriptionEntry(String notifyurl) throws Exception {

     Connection con = null;
     Statement st = null;
     ResultSet rs = null;
     Integer newid = 0;

     try {
     con = DbUtils.getAxiataDBConnection();
     if (con == null) {
     throw new Exception("Connection not found");
     }

     st = con.createStatement();
     String sql = "SELECT MAX(axiataid) maxid "
     + "FROM subscriptions";

     rs = st.executeQuery(sql);
     if (rs.next()) {
     newid = rs.getInt("maxid") + 1;
     }

     sql = "INSERT INTO subscriptions (axiataid,notifyurl) "
     + "VALUES (" + newid + ",'" + notifyurl + "')";
     st.executeUpdate(sql);

     } catch (Exception e) {
     DbUtils.handleException("Error while inserting in to subscriptions. ", e);
     } finally {
     DbUtils.closeAllConnections(st, con, rs);
     }

     return newid;

     }*/
    public boolean operatorsubsEntry(List<Operatorsubs> domainsubs, Integer axiataid) throws Exception {

        Connection con = null;
        Statement st = null;

        try {
            con = DbUtils.getAxiataDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = null;

            for (Operatorsubs d : domainsubs) {
                sql = "INSERT INTO operatorsubs (axiataid,domainurl,operator) "
                        + "VALUES (" + axiataid + "," + "'" + d.getDomain() + "','" + d.getOperator() + "')";
                st.executeUpdate(sql);
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while inserting in to operatorsubs. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, null);
        }

        return true;

    }

    /**
     * Token update.
     *
     * @param id the id
     * @param refreshtoken the refreshtoken
     * @param tokenvalidity the tokenvalidity
     * @param tokentime the tokentime
     * @param token the token
     * @return the integer
     * @throws Exception the exception
     */
    public Integer tokenUpdate(int id, String refreshtoken, long tokenvalidity, long tokentime, String token) throws Exception {

        Connection con = null;
        Statement st = null;
        Integer newid = 0;

        try {
            con = DbUtils.getAxiataDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "UPDATE operators "
                    + "SET refreshtoken='" + refreshtoken + "',tokenvalidity=" + tokenvalidity + ",tokentime=" + tokentime + ",token='" + token + "' "
                    + "WHERE id =" + id;

            st.executeUpdate(sql);

        } catch (Exception e) {
            DbUtils.handleException("Error while updating operators. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, null);
        }

        return newid;
    }

    /**
     * Subscription query.
     *
     * @param axiataid the axiataid
     * @return the list
     * @throws Exception the exception
     */
    public List<Operatorsubs> subscriptionQuery(Integer axiataid) throws Exception {

        Connection con = DbUtils.getAxiataDBConnection();
        Statement st = null;
        ResultSet rs = null;
        List<Operatorsubs> domainsubs = new ArrayList();
        try {

            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "SELECT domainurl,operator "
                    + "FROM operatorsubs "
                    + "WHERE axiataid = " + axiataid + "";

            rs = st.executeQuery(sql);
            //boolean first = true;
            while (rs.next()) {
                domainsubs.add(new Operatorsubs(rs.getString("operator"), rs.getString("domainurl")));
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while selecting selecting from operatorsubs. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return domainsubs;

    }

    /**
     * Application operators.
     *
     * @param axiataid the axiataid
     * @return the list
     * @throws Exception the exception
     */
    public List<Operator> applicationOperators(Integer axiataid) throws Exception {

        Connection con = DbUtils.getAxiataDBConnection();   //.getInstance().connect();
        Statement st = null;
        ResultSet rs = null;
        String domainurls = "";
        List<Operator> operators = new ArrayList<Operator>();

        try {

            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "SELECT oa.id id,oa.applicationid,oa.operatorid,o.operatorname,o.refreshtoken,o.tokenvalidity,o.tokentime,o.token, o.tokenurl, o.tokenauth "
                    + "FROM operatorapps oa, operators o "
                    + "WHERE oa.operatorid = o.id AND oa.isactive = 1  AND oa.applicationid = '" + axiataid + "'";

            System.out.println(sql);
            rs = st.executeQuery(sql);

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

        } catch (Exception e) {
            DbUtils.handleException("Error while selecting from operatorapps, operators. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        //DbUtils.getInstance().disconnect(con);
        return operators;
    }

    /*public String subscriptionNotifi(Integer axiataid) throws Exception {

        Connection con = DbUtils.getAxiataDBConnection();
        Statement st = null;
        ResultSet rs = null;
        String notifyurls = "";
        try {

            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "SELECT notifyurl "
                    + "FROM subscriptions "
                    + "WHERE axiataid = " + axiataid + "";

            rs = st.executeQuery(sql);

            if (rs.next()) {
                notifyurls = rs.getString("notifyurl");
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while selecting from subscriptions. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return notifyurls;

    }*/

    /**
     * Subscription delete.
     *
     * @param axiataid the axiataid
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean subscriptionDelete(Integer axiataid) throws Exception {

        Connection con = DbUtils.getAxiataDBConnection();
        Statement st = null;

        try {

            if (con == null) {
                throw new Exception("Connection not found");
            }

            Integer newid = 0;

            st = con.createStatement();
            String sql = "DELETE FROM subscriptions "
                    + "WHERE axiataid = " + axiataid + "";

            st.executeUpdate(sql);

            sql = "DELETE FROM operatorsubs "
                    + "WHERE axiataid = " + axiataid + "";

            st.executeUpdate(sql);

        } catch (Exception e) {
            DbUtils.handleException("Error while deleting subscriptions. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, null);
        }

        return true;
    }

    /**
     * Operator endpoints.
     *
     * @param appid the appid
     * @return the list
     * @throws Exception the exception
     */
    public List<Operatorendpoint> operatorEndpoints(Integer appid) throws Exception {

        Connection con = DbUtils.getAxiataDBConnection();
        Statement st = null;
        ResultSet rs = null;
        List<Operatorendpoint> endpoints = new ArrayList();
        try {

            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "SELECT operatorendpoints.ID as ID, operatorid,operatorname,api,endpoint "
                    + "FROM operatorendpoints, operators "
                    + "WHERE operatorendpoints.operatorid = operators.id "
                    + "AND operatorendpoints.id in ("
                    + "SELECT endpointid FROM endpointapps "
                    + "WHERE applicationid = " + appid + " "
                    + "AND isactive = 1"
                    + ")";

            rs = st.executeQuery(sql);

            while (rs.next()) {
                Operatorendpoint endpoint = new Operatorendpoint(rs.getInt("operatorid"), rs.getString("operatorname"), rs.getString("api"), rs.getString("endpoint"));
                endpoint.setId(rs.getInt("ID"));
                endpoints.add(endpoint);
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while retrieving operator endpoints. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return endpoints;
    }

    /**
     * Operator endpoints.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Operatorendpoint> operatorEndpoints() throws Exception {
        final int opEndpointsID = 0;

        Cache<Integer, List<Operatorendpoint>> cache = Caching.getCacheManager(AXIATA_MEDIATOR_CACHE_MANAGER).getCache("axiataDbOperatorEndpoints");
        List<Operatorendpoint> endpoints = cache.get(opEndpointsID);
        System.out.println((endpoints == null ? "== cache miss ==" : "== cache hit ==") + " --- operatorEndpoints");

        if (endpoints == null) {
            Connection con = DbUtils.getAxiataDBConnection();
            Statement st = null;
            ResultSet rs = null;
            endpoints = new ArrayList<Operatorendpoint>();
            try {

                if (con == null) {
                    throw new Exception("Connection not found");
                }

                st = con.createStatement();
                String sql = "SELECT operatorid,operatorname,api,endpoint "
                        + "FROM operatorendpoints, operators "
                        + "WHERE operatorendpoints.operatorid = operators.id";

                rs = st.executeQuery(sql);

                while (rs.next()) {
                    endpoints.add(new Operatorendpoint(rs.getInt("operatorid"), rs.getString("operatorname"), rs.getString("api"), rs.getString("endpoint")));
                }

            } catch (Exception e) {
                DbUtils.handleException("Error while retrieving operator endpoint. ", e);
            } finally {
                DbUtils.closeAllConnections(st, con, rs);
            }
            if (!endpoints.isEmpty()) {
                cache.put(opEndpointsID, endpoints);
            }
        }

        return endpoints;
    }

    /**
     * Update application op.
     *
     * @param axiataid the axiataid
     * @param operatorid the operatorid
     * @param opstat the opstat
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean UpdateApplicationOp(int axiataid, int operatorid, boolean opstat) throws Exception {

        Connection con = null;
        Statement st = null;

        int opactive = (opstat ? 1 : 0);

        try {
            con = DbUtils.getAxiataDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "UPDATE operatorapps "
                    + "SET isactive=" + opactive + " "
                    + "WHERE applicationid =" + axiataid + " "
                    + "AND operatorid = " + operatorid + "";

            st.executeUpdate(sql);

        } catch (Exception e) {
            DbUtils.handleException("Error while updating operatorapps. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, null);
        }

        return true;
    }

    /**
     * Application entry.
     *
     * @param applicationid the applicationid
     * @param operators the operators
     * @return the integer
     * @throws Exception the exception
     */
    public Integer applicationEntry(int applicationid, Integer[] operators) throws Exception {

        Connection con = null;
        Statement st = null;
        Integer newid = 0;

        try {
            con = DbUtils.getAxiataDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = null;
            for (Integer d : operators) {
                sql = "INSERT INTO operatorapps (applicationid,operatorid) "
                        + "VALUES (" + applicationid + "," + d + ")";
                st.executeUpdate(sql);
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while inserting in to operatorapps. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, null);
        }

        return newid;

    }

    /**
     * Retrieves the list of operators available.
     *
     * @return List<Operator> List of Operator entries available.
     * @throws Exception the exception
     */
    public List<Operator> getOperators() throws Exception {

        Connection con = DbUtils.getAxiataDBConnection();
        Statement st = null;
        ResultSet rs = null;
        List<Operator> operators = new ArrayList<Operator>();

        try {
            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "SELECT ID, operatorname "
                    + "FROM operators";

            rs = st.executeQuery(sql);

            while (rs.next()) {
                Operator operator = new Operator();
                operator.setOperatorid(rs.getInt("ID"));
                operator.setOperatorname(rs.getString("operatorname"));
                operators.add(operator);
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while retrieving operators. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }
        return operators;
    }

    /**
     * Insert the records with operatorEndpointID, applicationID and active
     * state mappings.
     *
     * @param appID Application ID.
     * @param opEndpointIDList List of operatorEndpointIDs.
     * @throws Exception the exception
     */
    public void insertOperatorAppEndpoints(int appID, int[] opEndpointIDList) throws Exception {

        Connection con = null;
        Statement st = null;

        try {
            con = DbUtils.getAxiataDBConnection();
            String inputStr = "";

            log.debug("opEndpointIDList.length : " + opEndpointIDList.length);
            for (int i = 0; i < opEndpointIDList.length; i++) {
                if (opEndpointIDList[i] > 0) {

                    if (inputStr.length() > 0) {
                        inputStr = inputStr + ",";
                    }
                    inputStr = inputStr + "(" + opEndpointIDList[i] + "," + appID + ",0)";

//                    if (!(i == opEndpointIDList.length - 1)) {
//                        inputStr = inputStr + ",";
//                    }
                    log.debug("inputStr : " + inputStr);
                }
            }

            log.debug("Final inputStr : " + inputStr);

            String sql = "INSERT INTO endpointapps (endpointid, applicationid, isactive) VALUES " + inputStr;
            log.debug("sql : " + sql);

            st = con.createStatement();
            st.executeUpdate(sql);

        } catch (Exception e) {
            DbUtils.handleException("Error while inserting in to endpointapps. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, null);
        }
    }

    /**
     * Update the active status of an operatorEndpoint for a given application.
     *
     * @param appID Application ID.
     * @param opEndpointID Operator endpoint ID.
     * @param status Active status.
     * @throws Exception the exception
     */
    public void updateOperatorAppEndpointStatus(int appID, int opEndpointID, int status) throws Exception {

        Connection con = null;
        Statement st = null;

        try {
            con = DbUtils.getAxiataDBConnection();

            String sql = "UPDATE endpointapps SET isactive=" + status
                    + " WHERE endpointid=" + opEndpointID
                    + " AND applicationid=" + appID;

            st = con.createStatement();
            st.executeUpdate(sql);

        } catch (Exception e) {
            DbUtils.handleException("Error while updating endpointapps. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, null);
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
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DbUtils.getAxiataDBConnection();
            if (con == null) {
                throw new Exception("Connection not found.");
            }

            st = con.createStatement();
            String sql = "SELECT ID,operatorid,api FROM operatorendpoints";

            rs = st.executeQuery(sql);

            while (rs.next()) {
                Operatorendpoint endpoint = new Operatorendpoint(rs.getInt("operatorid"), null, rs.getString("api"), null);
                endpoint.setId(rs.getInt("ID"));
                operatorEndpoints.add(endpoint);
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while selecting from operatorendpoints. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return operatorEndpoints;
    }

    /**
     * Update app approval status op.
     *
     * @param axiataId the axiata id
     * @param operatorId the operator id
     * @param status the status
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean updateAppApprovalStatusOp(int axiataId, int operatorId, int status) throws Exception {

        Connection con = null;
        Statement st = null;

        try {
            con = DbUtils.getAxiataDBConnection();
            if (con == null) {
                throw new Exception("Connection not found.");
            }

            st = con.createStatement();
            String sql = "UPDATE operatorapps "
                    + "SET isactive=" + status + " "
                    + "WHERE applicationid =" + axiataId + " "
                    + "AND operatorid = " + operatorId + "";

            st.executeUpdate(sql);

        } catch (Exception e) {
            DbUtils.handleException("Error while updating operatorapps. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, null);
        }

        return true;
    }

    /**
     * Insert the validator used for the specific subscription.
     *
     * @param appID the app id
     * @param apiID the api id
     * @param validatorID the validator id
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean insertValidatorForSubscription(int appID, int apiID, int validatorID) throws Exception {
        Connection con = null;
        Statement st = null;
        try {
            con = DbUtils.getAxiataDBConnection();

            String sql = "INSERT INTO subscription_validator (application_id, api_id, validator_id) VALUES "
                    + "(" + appID + "," + apiID + "," + validatorID + ")";
            st = con.createStatement();
            st.executeUpdate(sql);

        } catch (Exception e) {
            DbUtils.handleException("Error while inserting in to subscription_validator. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, null);
        }
        return true;
    }

    /**
     * Retrieves the list of operators available.
     *
     * @param appid the appid
     * @param operatorid the operatorid
     * @param subscriber the subscriber
     * @param merchant the merchant
     * @return List<Operator> List of Operator entries available.
     * @throws Exception the exception
     */
    public String blacklistedmerchant(int appid, String operatorid, String subscriber, String merchant) throws Exception {

        String resultcode = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        if (merchant == null || merchant.isEmpty()) {
            return resultcode;
        }

        try {
            con = DbUtils.getAxiataDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            //is aggrigator
            st = con.createStatement();
            String sql = "SELECT merchantopco_blacklist.id id "
                    + "FROM merchantopco_blacklist, operators "
                    + "WHERE merchantopco_blacklist.operator_id = operators.id "
                    + "AND application_id = " + appid + " "
                    + "AND operatorname = '" + operatorid + "' "
                    + "AND subscriber = '" + subscriber + "' "
                    + "AND lower(merchant) = '" + merchant.toLowerCase() + "'";

            rs = st.executeQuery(sql);
            if (rs.next()) {
                resultcode = String.valueOf(rs.getInt("id"));
            } else {
                sql = "SELECT merchantopco_blacklist.id id "
                        + "FROM merchantopco_blacklist, operators "
                        + "WHERE merchantopco_blacklist.operator_id = operators.id "
                        + "AND application_id is null "
                        + "AND subscriber = '" + subscriber + "' "
                        + "AND operatorname = '" + operatorid + "' "
                        + "AND lower(merchant) = '" + merchant.toLowerCase() + "'";

                rs = st.executeQuery(sql);
                if (rs.next()) {
                    resultcode = String.valueOf(rs.getInt("id"));
                }
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while selecting black listed merchant. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }
        return resultcode;
    }

    /**
     * Insert the validator used for the specific subscription.
     *
     * @param appID the app id
     * @param subscriber the subscriber
     * @param operator the operator
     * @param merchants the merchants
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean insertMerchantProvision(Integer appID, String subscriber, String operator,
            String[] merchants) throws Exception {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try {
            con = DbUtils.getAxiataDBConnection();

            st = con.createStatement();
            String sql = "SELECT id "
                    + "FROM operators "
                    + "WHERE operatorname = '" + operator + "'";

            rs = st.executeQuery(sql);

            int operatorid = 0;
            if (rs.next()) {
                operatorid = rs.getInt("id");
            } else {
                throw new Exception("Operator Not Found");
            }

            pst = null;
            for (int i = 0; i < merchants.length; i++) {
                sql = "INSERT INTO merchantopco_blacklist (application_id, operator_id, subscriber, merchant) VALUES "
                        + "(?, ?, ?, ?)";

                pst = con.prepareStatement(sql);
                if (appID == null) {
                    pst.setNull(1, Types.INTEGER);
                } else {
                    pst.setInt(1, appID);
                }
                pst.setInt(2, operatorid);
                pst.setString(3, subscriber);
                pst.setString(4, merchants[i]);
                pst.executeUpdate();
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while Provisioning Merchant", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
            DbUtils.closeAllConnections(pst, null, null);
        }
        return true;
    }

    /**
     * Insert the validator used for the specific subscription.
     *
     * @param appID the app id
     * @param subscriber the subscriber
     * @param operator the operator
     * @param merchants the merchants
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean removeMerchantProvision(Integer appID, String subscriber, String operator,
            String[] merchants) throws Exception {

        Connection con = null;
        Statement st = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DbUtils.getAxiataDBConnection();

            st = con.createStatement();
            String sql = "SELECT id "
                    + "FROM operators "
                    + "WHERE operatorname = '" + operator + "'";

            rs = st.executeQuery(sql);

            int operatorid = 0;
            if (rs.next()) {
                operatorid = rs.getInt("id");
            } else {
                throw new Exception("Operator Not Found");
            }

            pst = null;

            for (int i = 0; i < merchants.length; i++) {

                if (appID == null) {
                    sql = "DELETE FROM merchantopco_blacklist "
                            + "WHERE application_id is null AND operator_id = ? AND subscriber = ? AND merchant = ?";

                    pst = con.prepareStatement(sql);

                    pst.setInt(1, operatorid);
                    pst.setString(2, subscriber);
                    pst.setString(3, merchants[i]);
                    pst.executeUpdate();

                } else {
                    sql = "DELETE FROM merchantopco_blacklist "
                            + "WHERE application_id = ? AND operator_id = ? AND subscriber = ? AND merchant = ?";

                    pst = con.prepareStatement(sql);

                    pst.setInt(1, appID);
                    pst.setInt(2, operatorid);
                    pst.setString(3, subscriber);
                    pst.setString(4, merchants[i]);
                    pst.executeUpdate();

                }
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while Provisioning Merchant. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
            DbUtils.closeAllConnections(pst, null, null);
        }
        return true;
    }

    /**
     * Retrieves the list of operators available.
     *
     * @return List<Operator> List of Operator entries available.
     * @throws Exception the exception
     */
    public List<String> getValidPayCategories() throws Exception {

        Connection con = DbUtils.getAxiataDBConnection();
        Statement st = null;
        ResultSet rs = null;
        List<String> categories = new ArrayList<String>();

        try {
            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "SELECT id, category "
                    + "FROM valid_payment_categories";

            rs = st.executeQuery(sql);

            while (rs.next()) {
                categories.add(rs.getString("category"));
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while retrieving valid payment categories. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }
        return categories;
    }

    /**
     * Gets the prefix from country code.
     *
     * @param countryCode the country code
     * @return the prefix from country code
     * @throws AxataDBUtilException the axata db util exception
     * @throws SQLException the SQL exception
     */
    public String getPrefixFromCountryCode(String countryCode) throws AxataDBUtilException, SQLException {

        Connection con = DbUtils.getAxiataDBConnection();
        Statement st = null;
        ResultSet rs = null;
        String prefix = "";
        try {
            if (con == null) {
                throw new Exception("Connection not found");
            }
            st = con.createStatement();
            String sql = "SELECT prefix FROM operatorcodes where countrycode='" + countryCode + "';";

            rs = st.executeQuery(sql);

            if (rs.next()) {
                prefix = rs.getString("prefix");
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while selecting from subscriptions. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return prefix;

    }

    /**
     * Check msisdn spend limit.
     *
     * @param msisdn the msisdn
     * @return true, if successful
     * @throws AxataDBUtilException the axata db util exception
     */
    public boolean checkMSISDNSpendLimit(String msisdn) throws AxataDBUtilException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isExceeded = false;
        try {
            con = DbUtils.getAxiataDBConnection();

            String sql = "SELECT exists (SELECT 1 FROM " + MSISDN_SPEND_LIMIT_TABLE + " where msisdn=? LIMIT 1)";
            ps = con.prepareStatement(sql);
            ps.setString(1, msisdn);
            rs = ps.executeQuery();

            if (rs.next()) {
                isExceeded = rs.getBoolean(1);
            }
        } catch (Exception e) {
            DbUtils.handleException("Error while checking MSISDN Spend Limit. ", e);
        } finally {
            DbUtils.closeAllConnections(ps, con, rs);
        }
        return isExceeded;
    }

    /**
     * Check application spend limit.
     *
     * @param consumerKey the consumer key
     * @return true, if successful
     * @throws AxataDBUtilException the axata db util exception
     */
    public boolean checkApplicationSpendLimit(String consumerKey) throws AxataDBUtilException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isExceeded = false;
        try {
            con = DbUtils.getAxiataDBConnection();

            String sql = "SELECT exists (SELECT 1 FROM " + APPLICATION_SPEND_LIMIT_TABLE + " where consumerKey=? LIMIT 1)";
            ps = con.prepareStatement(sql);
            ps.setString(1, consumerKey);
            rs = ps.executeQuery();

            if (rs.next()) {
                isExceeded = rs.getBoolean(1);
            }
        } catch (Exception e) {
            DbUtils.handleException("Error while checking Application Spend Limit. ", e);
        } finally {
            DbUtils.closeAllConnections(ps, con, rs);
        }
        return isExceeded;
    }

    /**
     * Check operator spend limit.
     *
     * @param operatorId the operator id
     * @return true, if successful
     * @throws AxataDBUtilException the axata db util exception
     */
    public boolean checkOperatorSpendLimit(String operatorId) throws AxataDBUtilException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isExceeded = false;
        try {
            con = DbUtils.getAxiataDBConnection();

            String sql = "SELECT exists (SELECT 1 FROM " + OPERATOR_SPEND_LIMIT_TABLE + " where operatorId=? LIMIT 1)";
            ps = con.prepareStatement(sql);
            ps.setString(1, operatorId);
            rs = ps.executeQuery();

            if (rs.next()) {
                isExceeded = rs.getBoolean(1);
            }
        } catch (Exception e) {
            DbUtils.handleException("Error while checking Operator Spend Limit. ", e);
        } finally {
            DbUtils.closeAllConnections(ps, con, rs);
        }
        return isExceeded;
    }

    /**
     * Insert sms request ids.
     *
     * @param requestID the request id
     * @param senderAddress the sender address
     * @param pluginRequestIDs the plugin request i ds
     * @return true, if successful
     * @throws AxataDBUtilException the axata db util exception
     */
    public boolean insertSmsRequestIds(String requestID, String senderAddress, Map<String, String> pluginRequestIDs)
            throws AxataDBUtilException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DbUtils.getAxiataDBConnection();

            String sql = "INSERT INTO sendsms_reqid (hub_requestid,sender_address,delivery_address,plugin_requestid) "
                    + "VALUES (?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, requestID);
            ps.setString(2, senderAddress);
            for (Map.Entry<String, String> entry : pluginRequestIDs.entrySet()) {
                ps.setString(3, entry.getKey());
                ps.setString(4, entry.getValue());
                ps.executeUpdate();
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while inserting in to sendsms_reqid. ", e);
        } finally {
            DbUtils.closeAllConnections(ps, con, null);
        }
        return true;
    }

    /**
     * Gets the sms request ids.
     *
     * @param requestID the request id
     * @param senderAddress the sender address
     * @return the sms request ids
     * @throws AxataDBUtilException the axata db util exception
     */
    public Map<String, String> getSmsRequestIds(String requestID, String senderAddress)
            throws AxataDBUtilException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, String> pluginRequestIDs = new HashMap<String, String>();

        try {
            con = DbUtils.getAxiataDBConnection();

            String sql = "SELECT delivery_address, plugin_requestid from sendsms_reqid where hub_requestid=? AND "
                    + "sender_address=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, requestID);
            ps.setString(2, senderAddress);
            rs = ps.executeQuery();

            while (rs.next()) {
                pluginRequestIDs.put(rs.getString("delivery_address"), rs.getString("plugin_requestid"));
            }
        } catch (Exception e) {
            DbUtils.handleException("Error while inserting in to sendsms_reqid. ", e);
        } finally {
            DbUtils.closeAllConnections(ps, con, rs);
        }
        return pluginRequestIDs;
    }

    /**
     * Active application operators.
     *
     * @param appId the app id
     * @param apitype the apitype
     * @return the list
     * @throws SQLException the SQL exception
     * @throws AxataDBUtilException the axata db util exception
     */
    public List<Integer> activeApplicationOperators(Integer appId, String apitype) throws SQLException, AxataDBUtilException {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        List<Integer> operators = new ArrayList<Integer>();

        try {
            con = DbUtils.getAxiataDBConnection();
            if (con == null) {
                throw new Exception("Connection not found.");
            }

            st = con.createStatement();
            String sql = "SELECT o.operatorid FROM endpointapps e,operatorendpoints o "
                    + " where o.id = e.endpointid AND e.applicationid = " + appId + " AND e.isactive = 1 AND o.api='" + apitype + "'";

            log.debug("[Dbutils] sql : " + sql);
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Integer operatorid = (rs.getInt("operatorid"));
                log.debug("[Dbutils] operatorid : " + operatorid);
                operators.add(operatorid);
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while selecting from endpointapps, operatorendpoints ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return operators;
    }

    /**
     * Subscription entry.
     *
     * @param notifyurl the notifyurl
     * @param serviceProvider the service provider
     * @return the integer
     * @throws Exception the exception
     */
    public Integer subscriptionEntry(String notifyurl, String serviceProvider) throws Exception {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Integer newid = 0;

        try {
            con = DbUtils.getAxiataDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "SELECT MAX(axiataid) maxid "
                    + "FROM subscriptions";

            rs = st.executeQuery(sql);
            if (rs.next()) {
                newid = rs.getInt("maxid") + 1;
            }

            sql = "INSERT INTO subscriptions (axiataid,notifyurl,service_provider) "
                    + "VALUES (" + newid + ",'" + notifyurl + "', '" + serviceProvider + "')";

            st.executeUpdate(sql);

        } catch (Exception e) {
            DbUtils.handleException("Error while inserting in to subscriptions. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return newid;
    }

    /**
     * Outbound operatorsubs entry.
     *
     * @param domainsubs the domainsubs
     * @param axiataid the axiataid
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean outboundOperatorsubsEntry(List<Operatorsubs> domainsubs, Integer axiataid) throws Exception {

        Connection con = null;
        Statement st = null;

        try {
            con = DbUtils.getAxiataDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = null;

            for (Operatorsubs d : domainsubs) {
                sql = "INSERT INTO outbound_operatorsubs (axiataid,domainurl,operator) "
                        + "VALUES (" + axiataid + "," + "'" + d.getDomain() + "','" + d.getOperator() + "')";
                st.executeUpdate(sql);
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while inserting in to operatorsubs. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, null);
        }

        return true;
    }

    /**
     * Outbound subscription entry.
     *
     * @param notifyurl the notifyurl
     * @param serviceProvider the service provider
     * @return the integer
     * @throws Exception the exception
     */
    public Integer outboundSubscriptionEntry(String notifyurl, String serviceProvider) throws Exception {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Integer newid = 0;

        try {
            con = DbUtils.getAxiataDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "SELECT MAX(axiataid) maxid "
                    + "FROM outbound_subscriptions";

            rs = st.executeQuery(sql);
            if (rs.next()) {
                newid = rs.getInt("maxid") + 1;
            }

            sql = "INSERT INTO outbound_subscriptions (axiataid,notifyurl,service_provider) "
                    + "VALUES (" + newid + ",'" + notifyurl + "', '" + serviceProvider + "')";
            st.executeUpdate(sql);

        } catch (Exception e) {
            DbUtils.handleException("Error while inserting in to subscriptions. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return newid;
    }

    /**
     * Outbound subscription delete.
     *
     * @param axiataid the axiataid
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean outboundSubscriptionDelete(Integer axiataid) throws Exception {

        Connection con = DbUtils.getAxiataDBConnection();
        Statement st = null;

        try {

            if (con == null) {
                throw new Exception("Connection not found");
            }

            Integer newid = 0;

            st = con.createStatement();
            String sql = "DELETE FROM outbound_subscriptions "
                    + "WHERE axiataid = " + axiataid + "";

            st.executeUpdate(sql);

            sql = "DELETE FROM outbound_operatorsubs "
                    + "WHERE axiataid = " + axiataid + "";

            st.executeUpdate(sql);

        } catch (Exception e) {
            DbUtils.handleException("Error while deleting subscriptions. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, null);
        }

        return true;
    }

    /**
     * Outboud subscription query.
     *
     * @param axiataid the axiataid
     * @return the list
     * @throws Exception the exception
     */
    public List<Operatorsubs> outboudSubscriptionQuery(Integer axiataid) throws Exception {

        Connection con = DbUtils.getAxiataDBConnection();
        Statement st = null;
        ResultSet rs = null;
        List<Operatorsubs> domainsubs = new ArrayList();
        try {

            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "SELECT domainurl,operator "
                    + "FROM outbound_operatorsubs "
                    + "WHERE axiataid = " + axiataid + "";

            rs = st.executeQuery(sql);
            //boolean first = true;
            while (rs.next()) {
                domainsubs.add(new Operatorsubs(rs.getString("operator"), rs.getString("domainurl")));
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while selecting selecting from operatorsubs. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return domainsubs;
    }
    
    /**
     * Subscription dn notifi.
     *
     * @param axiataid the axiataid
     * @return the hash map
     * @throws Exception the exception
     */
    public HashMap<String, String> subscriptionDNNotifi(Integer axiataid) throws Exception {

        HashMap<String, String> dnSubscriptionDetails = new HashMap<String, String>();
        Connection con = DbUtils.getAxiataDBConnection();
        Statement st = null;
        ResultSet rs = null;
        //String notifyurls = "";
        try {

            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "SELECT notifyurl, service_provider "
                    + "FROM outbound_subscriptions "
                    + "WHERE axiataid = " + axiataid + "";
            log.debug("subscriptionDNNotifi --> sql query : " + sql);
            
            rs = st.executeQuery(sql);

            if (rs.next()) {
                //notifyurls = rs.getString("notifyurl");
                dnSubscriptionDetails.put("notifyurl", rs.getString("notifyurl"));
                dnSubscriptionDetails.put("serviceProvider", rs.getString("service_provider"));
            }

        } catch (Exception e) {

            DbUtils.handleException("Error while selecting from subscriptions. ", e);
        } finally {

            DbUtils.closeAllConnections(st, con, rs);
        }

        return dnSubscriptionDetails;
    }
    
    /**
     * Subscription notifi.
     *
     * @param axiataid the axiataid
     * @return the hash map
     * @throws Exception the exception
     */
    public HashMap<String, String> subscriptionNotifi(Integer axiataid) throws Exception {

        HashMap<String, String> subscriptionDetails = new HashMap<String, String>();
        Connection con = DbUtils.getAxiataDBConnection();
        Statement st = null;
        ResultSet rs = null;
        //String notifyurls = "";
        try {

            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "SELECT notifyurl, service_provider "
                    + "FROM subscriptions "
                    + "WHERE axiataid = " + axiataid + "";
            log.debug("subscriptionNotifi --> sql query : " + sql);

            rs = st.executeQuery(sql);

            if (rs.next()) {
                //notifyurls = rs.getString("notifyurl");
                subscriptionDetails.put("notifyurl", rs.getString("notifyurl"));
                subscriptionDetails.put("serviceProvider", rs.getString("service_provider"));
            }
        } catch (Exception e) {

            DbUtils.handleException("Error while selecting from subscriptions. ", e);
        } finally {

            DbUtils.closeAllConnections(st, con, rs);
        }

        return subscriptionDetails;
    }
}
