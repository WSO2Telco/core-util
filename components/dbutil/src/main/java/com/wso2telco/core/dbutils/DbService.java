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
 * The Class DbService.
 */
public class DbService {

	/** The log. */
	private static Log log = LogFactory.getLog(DbService.class);
    
    /** The Constant MEDIATOR_CACHE_MANAGER. */
    private static final String MEDIATOR_CACHE_MANAGER = "MediatorCacheManager";
    
	
    /**
     * Outbound subscription entry.
     *
     * @param notifyurl the notifyurl
     * @return the integer
     * @throws Exception the exception
     */
    public Integer outboundSubscriptionEntry(String notifyurl) throws Exception {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Integer newid = 0;

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "SELECT MAX(dn_subscription_did) maxid "
                    + "FROM outbound_subscriptions";

            rs = st.executeQuery(sql);
            if (rs.next()) {
                newid = rs.getInt("maxid") + 1;
            }

            sql = "INSERT INTO outbound_subscriptions (dn_subscription_did,notifyurl) "
                    + "VALUES (" + newid + ",'" + notifyurl + "')";
            st.executeUpdate(sql);

        } catch (Exception e) {
            DbUtils.handleException("Error while inserting in to subscriptions. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return newid;
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
            con = DbUtils.getDBConnection();
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
     * Application operators.
     *
     * @param appID the appID
     * @return the list
     * @throws Exception the exception
     */
    
    public List<Operator> applicationOperators(Integer appID) throws Exception {

        Connection con = DbUtils.getDBConnection();   
        Statement st = null;
        ResultSet rs = null;
        List<Operator> operators = new ArrayList<Operator>();

        try {

            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "SELECT oa.id id,oa.applicationid,oa.operatorid,o.operatorname,o.refreshtoken,o.tokenvalidity,o.tokentime,o.token, o.tokenurl, o.tokenauth "
                    + "FROM operatorapps oa, operators o "
                    + "WHERE oa.operatorid = o.id AND oa.isactive = 1  AND oa.applicationid = '" + appID + "'";
            
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

        Connection con = DbUtils.getDBConnection();
        Statement st = null;
        ResultSet rs = null;
        String notifyurls = "";
        try {

            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "SELECT notifyurl "
                    + "FROM outbound_subscriptions "
                    + "WHERE dn_subscription_did = " + subscriptionID + "";

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

    }
    
    /**
     * Subscription notifi.
     *
     * @param subscriptionID the subscriptionID
     * @return the string
     * @throws Exception the exception
     */
    public String subscriptionNotifi(Integer subscriptionID) throws Exception {

        Connection con = DbUtils.getDBConnection();
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
                    + "WHERE mo_subscription_did = " + subscriptionID + "";

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

    }
    
    /**
     * Operator endpoints.
     *
     * @param appID the appID
     * @return the list
     * @throws Exception the exception
     */

    public List<Operatorendpoint> operatorEndpoints(Integer appID) throws Exception {

        Connection con = DbUtils.getDBConnection();
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
                    + "WHERE applicationid = " + appID + " "
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

        Cache<Integer, List<Operatorendpoint>> cache = Caching.getCacheManager(MEDIATOR_CACHE_MANAGER).getCache("DbOperatorEndpoints");
        List<Operatorendpoint> endpoints = cache.get(opEndpointsID);
        
        if (endpoints == null) {
            Connection con = DbUtils.getDBConnection();
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
     * @param appID the appID
     * @param operatorid the operatorid
     * @param opstat the opstat
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean UpdateApplicationOp(int appID, int operatorid, boolean opstat) throws Exception {

        Connection con = null;
        Statement st = null;

        int opactive = (opstat ? 1 : 0);

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = "UPDATE operatorapps "
                    + "SET isactive=" + opactive + " "
                    + "WHERE applicationid =" + appID + " "
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
     * @param appID the appID
     * @param operators the operators
     * @return the integer
     * @throws Exception the exception
     */
    public Integer applicationEntry(int appID, Integer[] operators) throws Exception {

        Connection con = null;
        Statement st = null;
        Integer newid = 0;

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            String sql = null;
            for (Integer d : operators) {
                sql = "INSERT INTO operatorapps (applicationid,operatorid) "
                        + "VALUES (" + appID + "," + d + ")";
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
     * Gets the operators.
     *
     * @return the operators
     * @throws Exception the exception
     */

    public List<Operator> getOperators() throws Exception {

        Connection con = DbUtils.getDBConnection();
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
     * Insert operator app endpoints.
     *
     * @param appID the app id
     * @param opEndpointIDList the op endpoint id list
     * @throws Exception the exception
     */
    public void insertOperatorAppEndpoints(int appID, int[] opEndpointIDList) throws Exception {

        Connection con = null;
        Statement st = null;
        
        try {
            con = DbUtils.getDBConnection();
            String inputStr = "";

            log.debug("opEndpointIDList.length : " + opEndpointIDList.length);
            for (int i = 0; i < opEndpointIDList.length; i++) {
            	if(opEndpointIDList[i] > 0) {
            		
            		if (inputStr.length() > 0) {
            			inputStr = inputStr + ",";
            		}
            		inputStr = inputStr + "(" + opEndpointIDList[i] + "," + appID + ",0)";
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
     * Update operator app endpoint status.
     *
     * @param appID the app id
     * @param opEndpointID the op endpoint id
     * @param status the status
     * @throws Exception the exception
     */
    public void updateOperatorAppEndpointStatus(int appID, int opEndpointID, int status) throws Exception {

        Connection con = null;
        Statement st = null;
                
        try {
            con = DbUtils.getDBConnection();

            String sql = "UPDATE endpointapps SET isactive=" + status
                    + "blacklistedmerchant WHERE endpointid=" + opEndpointID
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
            con = DbUtils.getDBConnection();
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
     * @param appID the appID
     * @param operatorId the operator id
     * @param status the status
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean updateAppApprovalStatusOp(int appID, int operatorId, int status) throws Exception {

        Connection con = null;
        Statement st = null;

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found.");
            }

            st = con.createStatement();
            String sql = "UPDATE operatorapps "
                    + "SET isactive=" + status + " "
                    + "WHERE applicationid =" + appID + " "
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
     * Insert validator for subscription.
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
            con = DbUtils.getDBConnection();

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
     * Removes the merchant provision.
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
            con = DbUtils.getDBConnection();

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
	 * Gets the prefix from country code.
	 *
	 * @param countryCode the country code
	 * @return the prefix from country code
	 * @throws DBUtilException the db util exception
	 * @throws SQLException the SQL exception
	 */
	public String getPrefixFromCountryCode(String countryCode) throws DBUtilException, SQLException {

        Connection con = DbUtils.getDBConnection();
        Statement st = null;
        ResultSet rs = null;
        String prefix = "";
        try {
            if (con == null) {
                throw new Exception("Connection not found");
            }
            st = con.createStatement();
            String sql = "SELECT prefix FROM operatorcodes where countrycode='"+countryCode+"';";

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
     * Insert sms request ids.
     *
     * @param requestID the request id
     * @param senderAddress the sender address
     * @param pluginRequestIDs the plugin request i ds
     * @return true, if successful
     * @throws DBUtilException the db util exception
     */

    public boolean insertSmsRequestIds(String requestID, String senderAddress, Map<String, String> pluginRequestIDs)
            throws DBUtilException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DbUtils.getDBConnection();

            String sql = "INSERT INTO sendsms_reqid (hub_requestid,sender_address,delivery_address,plugin_requestid) " +
                    "VALUES (?,?,?,?)";
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
     * @throws DBUtilException the db util exception
     */

    public Map<String, String> getSmsRequestIds(String requestID, String senderAddress)
            throws DBUtilException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, String> pluginRequestIDs = new HashMap<String, String>();

        try {
            con = DbUtils.getDBConnection();

            String sql = "SELECT delivery_address, plugin_requestid from sendsms_reqid where hub_requestid=? AND " +
                    "sender_address=?";
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
	 * @throws DBUtilException the db util exception
	 */

	public List<Integer> activeApplicationOperators(Integer appId,String apitype) throws SQLException, DBUtilException {

        Connection con = null; 
        Statement st = null;
        ResultSet rs = null;
        List<Integer> operators = new ArrayList<Integer>();
        
        try {
        	con = DbUtils.getDBConnection();
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
     * Gets the SP token map.
     *
     * @return the SP token map
     * @throws SQLException the SQL exception
     * @throws DBUtilException the db util exception
     */
    public Map<String,String> getSPTokenMap() throws SQLException, DBUtilException {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Map<String, String> spToken = new HashMap<String,String>();

        try {
            con = DbUtils.getDBConnection();
            if (con == null) {
                throw new Exception("Connection not found.");
            }

            st = con.createStatement();
            String sql = "SELECT consumer_key, token FROM sp_token ";

            log.debug("[Dbutils] sql : " + sql);
            rs = st.executeQuery(sql);

            while (rs.next()) {
                String  consumerKey = (rs.getString("consumer_key"));
                String  token = (rs.getString("token"));
                spToken.put(consumerKey, token);
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while selecting from sp_token ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return spToken;
    }
}
