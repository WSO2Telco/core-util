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

import com.wso2telco.dbutils.enums.DBTableNames;
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
 * The Class AxiataDbService.
 */
public class AxiataDbService {

	/** The log. */
	private static Log log = LogFactory.getLog(AxiataDbService.class);
    
    /** The Constant AXIATA_MEDIATOR_CACHE_MANAGER. */
    private static final String AXIATA_MEDIATOR_CACHE_MANAGER = "AxiataMediatorCacheManager";

    private static final String TABLE_INSERT_LOG = "Inserting into table ";
    private static final String TABLE_UPDATE_LOG = "Updating table ";
    private static final String TABLE_RETRIEVE_LOG = "Retrieving from table ";
    private static final String TABLE_DELETE_LOG = "Deleting from table ";
    private static final String TABLE_INSERT_ERROR_LOG = "Error while inserting into table ";
    private static final String TABLE_UPDATE_ERROR_LOG = "Error while updating table ";
    private static final String TABLE_RETRIEVE_ERROR_LOG = "Error while retrieving from table ";
    private static final String TABLE_DELETE_ERROR_LOG = "Error while deleting from table ";
    private static final String COLAN = " : ";
    
    /** The Constant MSISDN_SPEND_LIMIT_TABLE. */
//    private static final String MSISDN_SPEND_LIMIT_TABLE = "spendlimitexceeded_msisdn";
    
    /** The Constant APPLICATION_SPEND_LIMIT_TABLE. */
//    private static final String APPLICATION_SPEND_LIMIT_TABLE = "spendlimitexceeded_application";
    
    /** The Constant OPERATOR_SPEND_LIMIT_TABLE. */
//    private static final String OPERATOR_SPEND_LIMIT_TABLE = "spendlimitexceeded_operator";


    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        try {
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
            StringBuffer sql = new StringBuffer("SELECT MAX(axiataid) maxid ")
                                .append("FROM ")
                                .append(DBTableNames.USSD_REQUEST_ENTRY.getTableName());

            rs = st.executeQuery(sql.toString());
            if (rs.next()) {
                newid = rs.getInt("maxid") + 1;
            }

            sql = new StringBuffer("INSERT INTO ")
                    .append(DBTableNames.USSD_REQUEST_ENTRY.getTableName())
                    .append(" (axiataid,notifyurl) ")
                    .append("VALUES (")
                    .append(newid)
                    .append(",'")
                    .append(notifyurl)
                    .append("')");

            log.debug(TABLE_INSERT_LOG + DBTableNames.USSD_REQUEST_ENTRY.getTableName() + COLAN + sql);
            st.executeUpdate(sql.toString());

        } catch (Exception e) {
            DbUtils.handleException(TABLE_INSERT_ERROR_LOG + DBTableNames.USSD_REQUEST_ENTRY.getTableName() , e);
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
            StringBuffer sql = new StringBuffer("DELETE FROM ")
                    .append(DBTableNames.USSD_REQUEST_ENTRY.getTableName())
                    .append(" WHERE axiataid = ")
                    .append(axiataid);

            log.debug(TABLE_DELETE_LOG + DBTableNames.USSD_REQUEST_ENTRY.getTableName() + COLAN + sql);
            st.executeUpdate(sql.toString());

        } catch (Exception e) {
            DbUtils.handleException(TABLE_DELETE_ERROR_LOG + DBTableNames.USSD_REQUEST_ENTRY.getTableName() , e);
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
            StringBuffer sql = new StringBuffer("SELECT notifyurl ")
                    .append("FROM ")
                    .append(DBTableNames.USSD_REQUEST_ENTRY.getTableName())
                    .append(" WHERE axiataid = ")
                    .append(axiataid);

            log.debug(TABLE_RETRIEVE_LOG + DBTableNames.USSD_REQUEST_ENTRY.getTableName() + COLAN + sql);

            rs = st.executeQuery(sql.toString());

            if (rs.next()) {
                notifyurls = rs.getString("notifyurl");
            }

        } catch (Exception e) {
            DbUtils.handleException(TABLE_RETRIEVE_ERROR_LOG + DBTableNames.USSD_REQUEST_ENTRY.getTableName() , e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return notifyurls;

    }


    /**
     * Subscription entry.
     *
     * @param notifyurl the notifyurl
     * @return the integer
     * @throws Exception the exception
     */
    public Integer subscriptionEntry(String notifyurl) throws Exception {

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
            StringBuffer sql = new StringBuffer("SELECT MAX(axiataid) maxid FROM ")
                    .append(DBTableNames.SUBSCRIPTIONS.getTableName());

            rs = st.executeQuery(sql.toString());
            if (rs.next()) {
                newid = rs.getInt("maxid") + 1;
            }

            sql = new StringBuffer("INSERT INTO ")
                    .append(DBTableNames.SUBSCRIPTIONS.getTableName())
                    .append(" (axiataid,notifyurl) ")
                    .append("VALUES (")
                    .append(newid)
                    .append(",'")
                    .append(notifyurl)
                    .append("')");

            log.debug(TABLE_INSERT_LOG +  DBTableNames.SUBSCRIPTIONS.getTableName() + COLAN + sql);
            st.executeUpdate(sql.toString());

        } catch (Exception e) {
            DbUtils.handleException(TABLE_INSERT_ERROR_LOG + DBTableNames.SUBSCRIPTIONS.getTableName() , e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return newid;


    }

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
            con = DbUtils.getAxiataDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            StringBuffer sql = new StringBuffer("SELECT MAX(axiataid) maxid FROM ")
                    .append(DBTableNames.OUTBOUND_SUBSCRIPTIONS.getTableName());

            rs = st.executeQuery(sql.toString());
            if (rs.next()) {
                newid = rs.getInt("maxid") + 1;
            }

            sql = new StringBuffer("INSERT INTO ")
                    .append(DBTableNames.OUTBOUND_SUBSCRIPTIONS.getTableName())
                    .append(" (axiataid,notifyurl) ")
                    .append("VALUES (")
                    .append(newid)
                    .append(",'")
                    .append(notifyurl)
                    .append("')");

            log.debug(TABLE_INSERT_LOG + DBTableNames.OUTBOUND_SUBSCRIPTIONS.getTableName() + COLAN + sql);
            st.executeUpdate(sql.toString());

        } catch (Exception e) {
            DbUtils.handleException(TABLE_INSERT_ERROR_LOG + DBTableNames.OUTBOUND_SUBSCRIPTIONS.getTableName() , e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return newid;


    }
    
    /**
     * Operatorsubs entry.
     *
     * @param domainsubs the domainsubs
     * @param axiataid the axiataid
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean operatorsubsEntry(List<Operatorsubs> domainsubs, Integer axiataid) throws Exception {

        Connection con = null;
        Statement st = null;

        try {
            con = DbUtils.getAxiataDBConnection();
            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            StringBuffer sql = null;

            for (Operatorsubs d : domainsubs) {
                sql = new StringBuffer("INSERT INTO ")
                        .append(DBTableNames.OPERATORSUBS.getTableName())
                        .append(" (axiataid,domainurl,operator) ")
                        .append("VALUES (")
                        .append(axiataid)
                        .append(",")
                        .append("'")
                        .append(d.getDomain())
                        .append("','")
                        .append(d.getOperator())
                        .append("')");

                log.debug(TABLE_INSERT_LOG + DBTableNames.OPERATORSUBS.getTableName() + COLAN + sql);
                st.executeUpdate(sql.toString());
            }

        } catch (Exception e) {
            DbUtils.handleException(TABLE_INSERT_ERROR_LOG + DBTableNames.OPERATORSUBS.getTableName() , e);
        } finally {
            DbUtils.closeAllConnections(st, con, null);
        }

        return true;

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
            StringBuffer sql = null;

            for (Operatorsubs d : domainsubs) {
                sql = new StringBuffer("INSERT INTO ")
                        .append(DBTableNames.OUTBOUND_OPERATORSUBS.getTableName())
                        .append(" (axiataid,domainurl,operator) ")
                        .append("VALUES (")
                        .append(axiataid)
                        .append(",")
                        .append("'")
                        .append(d.getDomain())
                        .append("','")
                        .append(d.getOperator())
                        .append("')");
                st.executeUpdate(sql.toString());
            }

        } catch (Exception e) {
            DbUtils.handleException(TABLE_INSERT_ERROR_LOG + DBTableNames.OUTBOUND_OPERATORSUBS.getTableName() , e);
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
            StringBuffer sql = new StringBuffer("UPDATE ")
                    .append(DBTableNames.OPERATORS.getTableName())
                    .append(" SET refreshtoken='")
                    .append(refreshtoken)
                    .append("',tokenvalidity=")
                    .append(tokenvalidity)
                    .append(",tokentime=")
                    .append(tokentime)
                    .append(",token='")
                    .append(token)
                    .append("' ")
                    .append("WHERE id =")
                    .append(id);

            log.debug(TABLE_UPDATE_LOG + DBTableNames.OPERATORS.getTableName() + COLAN + sql);
            st.executeUpdate(sql.toString());

        } catch (Exception e) {
            DbUtils.handleException(TABLE_UPDATE_ERROR_LOG + DBTableNames.OPERATORS.getTableName() , e);
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
            StringBuffer sql = new StringBuffer("SELECT domainurl,operator ")
                    .append("FROM ")
                    .append(DBTableNames.OPERATORSUBS.getTableName())
                    .append(" WHERE axiataid = ")
                    .append(axiataid);

            log.debug(TABLE_RETRIEVE_LOG + DBTableNames.OPERATORSUBS.getTableName() + COLAN + sql);
            rs = st.executeQuery(sql.toString());
            //boolean first = true;
            while (rs.next()) {
                domainsubs.add(new Operatorsubs(rs.getString("operator"), rs.getString("domainurl")));
            }

        } catch (Exception e) {
            DbUtils.handleException(TABLE_RETRIEVE_ERROR_LOG + DBTableNames.OPERATORSUBS.getTableName() , e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return domainsubs;

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
            StringBuffer sql = new StringBuffer("SELECT domainurl,operator ")
                    .append("FROM ")
                    .append(DBTableNames.OUTBOUND_OPERATORSUBS.getTableName())
                    .append(" WHERE axiataid = ")
                    .append(axiataid);

            log.debug(TABLE_RETRIEVE_LOG + DBTableNames.OUTBOUND_OPERATORSUBS.getTableName() + COLAN + sql);
            rs = st.executeQuery(sql.toString());
            //boolean first = true;
            while (rs.next()) {
                domainsubs.add(new Operatorsubs(rs.getString("operator"), rs.getString("domainurl")));
            }

        } catch (Exception e) {
            DbUtils.handleException(TABLE_RETRIEVE_ERROR_LOG + DBTableNames.OUTBOUND_OPERATORSUBS.getTableName() , e);
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
            StringBuffer sql = new StringBuffer("SELECT oa.id id,oa.applicationid,oa.operatorid,o.operatorname,o.refreshtoken,o.tokenvalidity,o.tokentime,o.token, o.tokenurl, o.tokenauth ")
                    .append("FROM ")
                    .append(DBTableNames.OPERATOR_APPS.getTableName())
                    .append(" oa, ")
                    .append(DBTableNames.OPERATORS.getTableName())
                    .append(" o ")
                    .append("WHERE oa.operatorid = o.id AND oa.isactive = 1  AND oa.applicationid = '")
                    .append(axiataid)
                    .append("'");

            log.debug(TABLE_RETRIEVE_LOG + DBTableNames.OPERATOR_APPS.getTableName() + ", " + DBTableNames.OPERATORS.getTableName() + COLAN + sql);
//            System.out.println(sql);
            rs = st.executeQuery(sql.toString());

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

    /**
     * Subscription dn notifi.
     *
     * @param axiataid the axiataid
     * @return the string
     * @throws Exception the exception
     */
    public String subscriptionDNNotifi(Integer axiataid) throws Exception {

        Connection con = DbUtils.getAxiataDBConnection();
        Statement st = null;
        ResultSet rs = null;
        String notifyurls = "";
        try {

            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            StringBuffer sql = new StringBuffer("SELECT notifyurl ")
                    .append("FROM ")
                    .append(DBTableNames.OUTBOUND_SUBSCRIPTIONS.getTableName())
                    .append(" WHERE axiataid = ")
                    .append(axiataid);
            log.debug(TABLE_RETRIEVE_LOG + DBTableNames.OUTBOUND_SUBSCRIPTIONS.getTableName()+ COLAN + sql);

            rs = st.executeQuery(sql.toString());

            if (rs.next()) {
                notifyurls = rs.getString("notifyurl");
            }

        } catch (Exception e) {
            DbUtils.handleException("Error while selecting from outbound_subscriptions. ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return notifyurls;

    }
    
    /**
     * Subscription notifi.
     *
     * @param axiataid the axiataid
     * @return the string
     * @throws Exception the exception
     */
    public String subscriptionNotifi(Integer axiataid) throws Exception {

        Connection con = DbUtils.getAxiataDBConnection();
        Statement st = null;
        ResultSet rs = null;
        String notifyurls = "";
        try {

            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            StringBuffer sql = new StringBuffer("SELECT notifyurl ")
                    .append("FROM ")
                    .append(DBTableNames.SUBSCRIPTIONS.getTableName())
                    .append(" WHERE axiataid = ")
                    .append(axiataid);

            log.debug(TABLE_RETRIEVE_LOG + DBTableNames.SUBSCRIPTIONS.getTableName() + COLAN + sql);
            rs = st.executeQuery(sql.toString());

            if (rs.next()) {
                notifyurls = rs.getString("notifyurl");
            }

        } catch (Exception e) {
            DbUtils.handleException(TABLE_RETRIEVE_ERROR_LOG + DBTableNames.SUBSCRIPTIONS.getTableName() , e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return notifyurls;

    }

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
            StringBuffer sql = new StringBuffer("DELETE FROM ")
                    .append(DBTableNames.SUBSCRIPTIONS.getTableName())
                    .append(" WHERE axiataid = ")
                    .append(axiataid);

            log.debug(TABLE_DELETE_LOG + DBTableNames.SUBSCRIPTIONS.getTableName() + COLAN + sql);
            st.executeUpdate(sql.toString());

            sql = new StringBuffer("DELETE FROM ")
                    .append(DBTableNames.OPERATORSUBS.getTableName())
                    .append(" WHERE axiataid = ")
                    .append(axiataid);

            log.debug(TABLE_DELETE_LOG +  DBTableNames.SUBSCRIPTIONS.getTableName() + COLAN + sql);
            st.executeUpdate(sql.toString());

        } catch (Exception e) {
            DbUtils.handleException(TABLE_DELETE_ERROR_LOG + DBTableNames.OPERATORSUBS.getTableName() , e);
        } finally {
            DbUtils.closeAllConnections(st, con, null);
        }

        return true;
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
            StringBuffer sql = new StringBuffer("DELETE FROM ")
                    .append(DBTableNames.OUTBOUND_SUBSCRIPTIONS.getTableName())
                    .append(" WHERE axiataid = ")
                    .append(axiataid);

            log.debug(TABLE_DELETE_LOG + DBTableNames.OUTBOUND_SUBSCRIPTIONS.getTableName() + COLAN + sql);
            st.executeUpdate(sql.toString());

            sql = new StringBuffer("DELETE FROM ")
                    .append(DBTableNames.OUTBOUND_OPERATORSUBS.getTableName())
                    .append(" WHERE axiataid = ")
                    .append(axiataid);

            log.debug(TABLE_RETRIEVE_LOG + DBTableNames.OUTBOUND_OPERATORSUBS.getTableName() + COLAN + sql);
            st.executeUpdate(sql.toString());

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
            StringBuffer sql = new StringBuffer("SELECT operatorendpoints.ID as ID, operatorid,operatorname,api,endpoint ")
                    .append("FROM operatorendpoints, operators ")
                    .append("WHERE operatorendpoints.operatorid = operators.id ")
                    .append("AND operatorendpoints.id in (")
                    .append("SELECT endpointid FROM endpointapps ")
                    .append("WHERE applicationid = ")
                    .append(appid)
                    .append(" AND isactive = 1")
                    .append(")");

            log.debug(TABLE_RETRIEVE_LOG + DBTableNames.OPERATOR_ENDPOINTS.getTableName() +", " + DBTableNames.OPERATORS.getTableName() + COLAN + sql);
            rs = st.executeQuery(sql.toString());

            while (rs.next()) {
                Operatorendpoint endpoint = new Operatorendpoint(rs.getInt("operatorid"), rs.getString("operatorname"), rs.getString("api"), rs.getString("endpoint"));
                endpoint.setId(rs.getInt("ID"));
                endpoints.add(endpoint);
            }

        } catch (Exception e) {
            DbUtils.handleException(TABLE_RETRIEVE_ERROR_LOG + DBTableNames.OPERATOR_ENDPOINTS.getTableName() , e);
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
                StringBuffer sql = new StringBuffer("SELECT operatorid,operatorname,api,endpoint ")
                        .append("FROM ")
                        .append(DBTableNames.OPERATOR_ENDPOINTS.getTableName())
                        .append(", ")
                        .append(DBTableNames.OPERATORS.getTableName())
                        .append(" WHERE operatorendpoints.operatorid = operators.id");

                log.debug(TABLE_RETRIEVE_LOG + DBTableNames.OPERATOR_ENDPOINTS.getTableName() + ", " + DBTableNames.OPERATORS.getTableName() + " : " + sql);
                rs = st.executeQuery(sql.toString());

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
            StringBuffer sql = new StringBuffer("UPDATE ")
                    .append(DBTableNames.OPERATOR_APPS.getTableName())
                    .append(" SET isactive=")
                    .append(opactive)
                    .append(" ")
                    .append("WHERE applicationid =")
                    .append(axiataid)
                    .append(" ")
                    .append("AND operatorid = ")
                    .append(operatorid)
                    .append("");
            log.debug(TABLE_UPDATE_LOG + DBTableNames.OPERATOR_APPS.getTableName() + COLAN + sql);

            st.executeUpdate(sql.toString());

        } catch (Exception e) {
            DbUtils.handleException(TABLE_UPDATE_ERROR_LOG + DBTableNames.OPERATOR_APPS.getTableName() , e);
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
            StringBuffer sql = null;
            for (Integer d : operators) {
                sql = new StringBuffer("INSERT INTO ")
                        .append(DBTableNames.OPERATOR_APPS.getTableName())
                        .append(" (applicationid,operatorid) ")
                        .append("VALUES (")
                        .append(applicationid)
                        .append(",")
                        .append(d)
                        .append(")");
                st.executeUpdate(sql.toString());
            }

        } catch (Exception e) {
            DbUtils.handleException(TABLE_INSERT_ERROR_LOG + DBTableNames.OPERATOR_APPS.getTableName() , e);
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

        Connection con = DbUtils.getAxiataDBConnection();
        Statement st = null;
        ResultSet rs = null;
        List<Operator> operators = new ArrayList<Operator>();

        try {
            if (con == null) {
                throw new Exception("Connection not found");
            }

            st = con.createStatement();
            StringBuffer sql = new StringBuffer("SELECT ID, operatorname ")
                    .append("FROM ")
                    .append(DBTableNames.OPERATORS.getTableName());
            log.debug(TABLE_RETRIEVE_LOG + DBTableNames.OPERATORS.getTableName() + COLAN + sql);

            rs = st.executeQuery(sql.toString());

            while (rs.next()) {
                Operator operator = new Operator();
                operator.setOperatorid(rs.getInt("ID"));
                operator.setOperatorname(rs.getString("operatorname"));
                operators.add(operator);
            }

        } catch (Exception e) {
            DbUtils.handleException(TABLE_RETRIEVE_ERROR_LOG + DBTableNames.OPERATORS.getTableName() , e);
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
            con = DbUtils.getAxiataDBConnection();
            String inputStr = "";

            log.debug("opEndpointIDList.length : " + opEndpointIDList.length);
            for (int i = 0; i < opEndpointIDList.length; i++) {
            	if(opEndpointIDList[i] > 0) {
            		
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
            
            StringBuffer sql = new StringBuffer("INSERT INTO ")
                    .append(DBTableNames.ENDPOINT_APPS.getTableName())
                    .append(" (endpointid, applicationid, isactive) VALUES ")
                    .append(inputStr);
            log.debug(TABLE_INSERT_LOG + DBTableNames.ENDPOINT_APPS.getTableName() + COLAN + sql);
//            log.debug("sql : " + sql);
            
            st = con.createStatement();
            st.executeUpdate(sql.toString());

        } catch (Exception e) {
            DbUtils.handleException(TABLE_INSERT_ERROR_LOG + DBTableNames.ENDPOINT_APPS.getTableName() , e);
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
            con = DbUtils.getAxiataDBConnection();

            StringBuffer sql = new StringBuffer("UPDATE ")
                    .append(DBTableNames.ENDPOINT_APPS.getTableName())
                    .append(" SET isactive=")
                    .append(status)
                    .append(" WHERE endpointid=")
                    .append(opEndpointID)
                    .append(" AND applicationid=")
                    .append(appID);
            log.debug(TABLE_UPDATE_LOG + DBTableNames.ENDPOINT_APPS.getTableName() + COLAN + sql);

            st = con.createStatement();
            st.executeUpdate(sql.toString());

        } catch (Exception e) {
            DbUtils.handleException(TABLE_UPDATE_ERROR_LOG + DBTableNames.ENDPOINT_APPS.getTableName() , e);
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
            StringBuffer sql = new StringBuffer("SELECT ID,operatorid,api FROM ")
                    .append(DBTableNames.OPERATOR_ENDPOINTS.getTableName());
            log.debug(TABLE_RETRIEVE_LOG + DBTableNames.OPERATOR_ENDPOINTS.getTableName() + COLAN + sql);

            rs = st.executeQuery(sql.toString());

            while (rs.next()) {
                Operatorendpoint endpoint = new Operatorendpoint(rs.getInt("operatorid"), null, rs.getString("api"), null);
                endpoint.setId(rs.getInt("ID"));
                operatorEndpoints.add(endpoint);
            }

        } catch (Exception e) {
            DbUtils.handleException(TABLE_RETRIEVE_ERROR_LOG + DBTableNames.OPERATOR_ENDPOINTS.getTableName() , e);
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
            StringBuffer sql = new StringBuffer("UPDATE ")
                    .append(DBTableNames.OPERATOR_APPS.getTableName())
                    .append(" SET isactive=")
                    .append(status)
                    .append(" ")
                    .append("WHERE applicationid =")
                    .append(axiataId)
                    .append(" ")
                    .append("AND operatorid = ")
                    .append(operatorId);
            log.debug(TABLE_RETRIEVE_LOG + DBTableNames.OPERATOR_APPS.getTableName() + COLAN + sql);

            st.executeUpdate(sql.toString());

        } catch (Exception e) {
            DbUtils.handleException(TABLE_UPDATE_ERROR_LOG + DBTableNames.OPERATOR_APPS.getTableName() , e);
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
            con = DbUtils.getAxiataDBConnection();

            StringBuffer sql = new StringBuffer("INSERT INTO ")
                    .append(DBTableNames.SUBSCRIPTION_VALIDATOR.getTableName())
                    .append(" (application_id, api_id, validator_id) VALUES ")
                    .append("(")
                    .append(appID)
                    .append(",")
                    .append(apiID)
                    .append(",")
                    .append(validatorID)
                    .append(")");
            log.debug(TABLE_INSERT_LOG + DBTableNames.SUBSCRIPTION_VALIDATOR.getTableName() + COLAN + sql);
            st = con.createStatement();
            st.executeUpdate(sql.toString());

        } catch (Exception e) {
            DbUtils.handleException(TABLE_INSERT_ERROR_LOG + DBTableNames.SUBSCRIPTION_VALIDATOR.getTableName() , e);
        } finally {
            DbUtils.closeAllConnections(st, con, null);
        }
        return true;
    }

     
    /**
     * Blacklistedmerchant.
     *
     * @param appid the appid
     * @param operatorid the operatorid
     * @param subscriber the subscriber
     * @param merchant the merchant
     * @return the string
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
            StringBuffer sql = new StringBuffer("SELECT merchantopco_blacklist.id id ")
                    .append("FROM ")
                    .append(DBTableNames.MERCHANTOPCO_BLACKLIST.getTableName())
                    .append(", ")
                    .append(DBTableNames.OPERATORS.getTableName())
                    .append(" WHERE merchantopco_blacklist.operator_id = operators.id ")
                    .append("AND application_id = ").append(appid).append(" ")
                    .append("AND operatorname = '").append(operatorid).append("' ")
                    .append("AND subscriber = '").append(subscriber).append("' ")
                    .append("AND lower(merchant) = '").append(merchant.toLowerCase()).append("'");
            log.debug(TABLE_RETRIEVE_LOG + DBTableNames.MERCHANTOPCO_BLACKLIST.getTableName() + ", " + DBTableNames.OPERATORS.getTableName() + COLAN + sql);

            rs = st.executeQuery(sql.toString());
            if (rs.next()) {
                resultcode = String.valueOf(rs.getInt("id"));
            } else {
                sql = new StringBuffer("SELECT merchantopco_blacklist.id id ")
                        .append("FROM ")
                        .append(DBTableNames.MERCHANTOPCO_BLACKLIST.getTableName())
                        .append(", ")
                        .append(DBTableNames.OPERATORS.getTableName())
                        .append(" WHERE merchantopco_blacklist.operator_id = operators.id ")
                        .append("AND application_id is null ")
                        .append("AND subscriber = '").append(subscriber).append("' ")
                        .append("AND operatorname = '").append(operatorid).append("' ")
                        .append("AND lower(merchant) = '").append( merchant.toLowerCase()).append("'");
                log.debug(TABLE_RETRIEVE_LOG + DBTableNames.MERCHANTOPCO_BLACKLIST.getTableName() + ", " + DBTableNames.OPERATORS.getTableName() + COLAN + sql);

                rs = st.executeQuery(sql.toString());
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
     * Insert merchant provision.
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
            StringBuffer sql = new StringBuffer("SELECT id ")
                    .append("FROM ")
                    .append(DBTableNames.OPERATORS.getTableName())
                    .append(" WHERE operatorname = '")
                    .append(operator)
                    .append("'");

            rs = st.executeQuery(sql.toString());

            int operatorid = 0;
            if (rs.next()) {
                operatorid = rs.getInt("id");
            } else {
                throw new Exception("Operator Not Found");
            }

            pst = null;
            for (int i = 0; i < merchants.length; i++) {
                sql = new StringBuffer("INSERT INTO ")
                        .append(DBTableNames.MERCHANTOPCO_BLACKLIST.getTableName())
                        .append(" (application_id, operator_id, subscriber, merchant) VALUES ")
                        .append("(?, ?, ?, ?)");

                pst = con.prepareStatement(sql.toString());
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
            con = DbUtils.getAxiataDBConnection();

            st = con.createStatement();
            StringBuffer sql = new StringBuffer("SELECT id ")
                    .append("FROM ")
                    .append(DBTableNames.OPERATORS.getTableName())
                    .append(" WHERE operatorname = '")
                    .append(operator)
                    .append("'");

            rs = st.executeQuery(sql.toString());

            int operatorid = 0;
            if (rs.next()) {
                operatorid = rs.getInt("id");
            } else {
                throw new Exception("Operator Not Found");
            }

            pst = null;

            for (int i = 0; i < merchants.length; i++) {

                if (appID == null) {
                    sql = new StringBuffer("DELETE FROM ")
                            .append(DBTableNames.MERCHANTOPCO_BLACKLIST.getTableName())
                            .append(" WHERE application_id is null AND operator_id = ? AND subscriber = ? AND merchant = ?");

                    pst = con.prepareStatement(sql.toString());

                    pst.setInt(1, operatorid);
                    pst.setString(2, subscriber);
                    pst.setString(3, merchants[i]);
                    pst.executeUpdate();

                } else {
                    sql = new StringBuffer("DELETE FROM ")
                            .append(DBTableNames.MERCHANTOPCO_BLACKLIST.getTableName())
                            .append(" WHERE application_id = ? AND operator_id = ? AND subscriber = ? AND merchant = ?");

                    pst = con.prepareStatement(sql.toString());

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
     * Gets the valid pay categories.
     *
     * @return the valid pay categories
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
            StringBuffer sql = new StringBuffer("SELECT id, category ")
                    .append("FROM ")
                    .append(DBTableNames.VALID_PAYMENT_CATEGORIES.getTableName());
            log.debug(TABLE_RETRIEVE_LOG + DBTableNames.VALID_PAYMENT_CATEGORIES.getTableName() + COLAN + sql);

            rs = st.executeQuery(sql.toString());

            while (rs.next()) {                
                categories.add(rs.getString("category"));
            }

        } catch (Exception e) {
            DbUtils.handleException(TABLE_RETRIEVE_ERROR_LOG + DBTableNames.VALID_PAYMENT_CATEGORIES.getTableName() , e);
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
            StringBuffer sql = new StringBuffer("SELECT prefix FROM ")
                    .append(DBTableNames.OPERATOR_CODES.getTableName())
                    .append(" where countrycode='")
                    .append(countryCode)
                    .append("';");
            log.debug(TABLE_RETRIEVE_LOG + DBTableNames.OPERATOR_CODES.getTableName() + COLAN + sql);

            rs = st.executeQuery(sql.toString());

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

            StringBuffer sql = new StringBuffer("SELECT exists (SELECT 1 FROM ")
                    .append(DBTableNames.MSISDN_SPEND_LIMIT.getTableName())
                    .append(" where msisdn=? LIMIT 1)");
            ps = con.prepareStatement(sql.toString());
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

            StringBuffer sql = new StringBuffer("SELECT exists (SELECT 1 FROM ")
                    .append(DBTableNames.APPLICATION_SPEND_LIMIT.getTableName())
                    .append(" where consumerKey=? LIMIT 1)");
            ps = con.prepareStatement(sql.toString());
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

            StringBuffer sql = new StringBuffer("SELECT exists (SELECT 1 FROM ")
                    .append(DBTableNames.OPERATOR_SPEND_LIMIT.getTableName())
                    .append(" where operatorId=? LIMIT 1)");
            ps = con.prepareStatement(sql.toString());
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

            StringBuffer sql = new StringBuffer("INSERT INTO ")
                    .append(DBTableNames.SENDSMS_REQID.getTableName())
                    .append(" (hub_requestid,sender_address,delivery_address,plugin_requestid) ")
                    .append("VALUES (?,?,?,?)");
            ps = con.prepareStatement(sql.toString());
            ps.setString(1, requestID);
            ps.setString(2, senderAddress);
            for (Map.Entry<String, String> entry : pluginRequestIDs.entrySet()) {
                ps.setString(3, entry.getKey());
                ps.setString(4, entry.getValue());
                ps.executeUpdate();
            }

        } catch (Exception e) {
            DbUtils.handleException(TABLE_INSERT_ERROR_LOG + DBTableNames.SENDSMS_REQID.getTableName() , e);
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

            StringBuffer sql = new StringBuffer("SELECT delivery_address, plugin_requestid from ")
                    .append(DBTableNames.SENDSMS_REQID.getTableName())
                    .append(" where hub_requestid=? AND ")
                    .append("sender_address=?");
            ps = con.prepareStatement(sql.toString());
            ps.setString(1, requestID);
            ps.setString(2, senderAddress);
            rs = ps.executeQuery();

            while (rs.next()) {
                pluginRequestIDs.put(rs.getString("delivery_address"), rs.getString("plugin_requestid"));
            }
        } catch (Exception e) {
            DbUtils.handleException(TABLE_INSERT_ERROR_LOG + DBTableNames.SENDSMS_REQID.getTableName() , e);
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
	public List<Integer> activeApplicationOperators(Integer appId,String apitype) throws SQLException, AxataDBUtilException {

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
            StringBuffer sql = new StringBuffer("SELECT o.operatorid FROM ")
                    .append(DBTableNames.ENDPOINT_APPS.getTableName()).append(" e, ")
                    .append(DBTableNames.OPERATOR_ENDPOINTS.getTableName()).append(" o ")
                    .append(" where o.id = e.endpointid AND e.applicationid = ")
                    .append(appId)
                    .append(" AND e.isactive = 1 AND o.api='")
                    .append(apitype)
                    .append("'");
            
            log.debug(TABLE_RETRIEVE_LOG + DBTableNames.ENDPOINT_APPS.getTableName() + ", " + DBTableNames.OPERATOR_ENDPOINTS.getTableName() + COLAN + sql);
            rs = st.executeQuery(sql.toString());

            while (rs.next()) {
                Integer operatorid = (rs.getInt("operatorid"));
                log.debug("[Dbutils] operatorid : " + operatorid);
                operators.add(operatorid);
            }

        } catch (Exception e) {
            DbUtils.handleException(TABLE_RETRIEVE_ERROR_LOG + DBTableNames.ENDPOINT_APPS.getTableName() + ", " + DBTableNames.OPERATOR_ENDPOINTS.getTableName() + " ", e);
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
     * @throws AxataDBUtilException the axata db util exception
     */
    public Map<String,String> getSPTokenMap() throws SQLException, AxataDBUtilException {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Map<String, String> spToken = new HashMap<String,String>();

        try {
            con = DbUtils.getAxiataDBConnection();
            if (con == null) {
                throw new Exception("Connection not found.");
            }

            st = con.createStatement();
            StringBuffer sql = new StringBuffer("SELECT consumer_key, token FROM ")
                    .append(DBTableNames.SP_TOKEN.getTableName())
                    .append(" ");

            log.debug(TABLE_RETRIEVE_LOG + DBTableNames.SP_TOKEN.getTableName() + COLAN + sql);
            rs = st.executeQuery(sql.toString());

            while (rs.next()) {
                String  consumerKey = (rs.getString("consumer_key"));
                String  token = (rs.getString("token"));
                spToken.put(consumerKey, token);
            }

        } catch (Exception e) {
            DbUtils.handleException(TABLE_RETRIEVE_ERROR_LOG + DBTableNames.SP_TOKEN.getTableName() + " ", e);
        } finally {
            DbUtils.closeAllConnections(st, con, rs);
        }

        return spToken;
    }
}
