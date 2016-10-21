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

import com.wso2telco.core.dbutils.DbUtils;
import com.wso2telco.core.dbutils.Operatorendpoint;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

// TODO: Auto-generated Javadoc
/**
 * The Class DbService.
 */
public class DbService {

	/** The log. */
	private static Log log = LogFactory.getLog(DbService.class);
	

	/**
	 * Outbound subscription entry.
	 *
	 * @param notifyurl
	 *            the notifyurl
	 * @return the integer
	 * @throws PersistenceException
	 * @throws Exception
	 *             the exception
	 */

	public Integer outboundSubscriptionEntry(String notifyurl) throws SQLException, PersistenceException {

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

		} catch (SQLException e) {

			log.error("database operation error in outbound subscriptions entry: ", e);
			throw e;

		} catch (Exception e) {
			throw new PersistenceException("Error while inserting in to subscriptions. ", e);
		} finally {
			DbUtils.closeAllConnections(selectStatement, con, rs);
			DbUtils.closeAllConnections(insertStatement, null, null);
		}

		return newid;
	}

	/**
	 * Application operators.
	 *
	 * @param appID
	 *            the appID
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */

	public List<Operator> applicationOperators(Integer appID) throws SQLException, PersistenceException {

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
			queryString.append("oa.id id,oa.applicationid,oa.operatorid,o.operatorname,o.refreshtoken,o.tokenvalidity,o.tokentime,o.token, o.tokenurl, o.tokenauth ");
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

		} catch (SQLException e) {

			/**
			 * rollback if Exception occurs
			 */
			con.rollback();

			log.error("database operation error in operator entry : ", e);
			throw e;
		} catch (Exception e) {
			throw new PersistenceException("Error while selecting from operators ", e);
		} finally {
			DbUtils.closeAllConnections(statement, con, rs);
		}

		return operators;

	}

	/**
	 * Subscription dn notifi.
	 *
	 * @param subscriptionID
	 *            the subscriptionID
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */

	public String subscriptionDNNotifi(Integer subscriptionID) throws SQLException, PersistenceException {

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

		} catch (SQLException e) {

			log.error("database operation error in subscription entry : ", e);
			throw e;
		} catch (Exception e) {
			throw new PersistenceException("Error while selecting from subscriptions. ", e);
		} finally {
			DbUtils.closeAllConnections(statement, con, rs);
		}

		return notifyurls;
	}

	/**
	 * Subscription notifi.
	 *
	 * @param subscriptionID
	 *            the subscriptionID
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String subscriptionNotifi(Integer subscriptionID) throws SQLException, PersistenceException {

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

		} catch (SQLException e) {

			log.error("database operation error in subscription entry : ", e);
			throw e;
		} catch (Exception e) {
			throw new PersistenceException("Error while selecting from subscriptions. ", e);
		} finally {
			DbUtils.closeAllConnections(statement, con, rs);
		}

		return notifyurls;

	}

	/**
	 * Operator endpoints.
	 *
	 * @param appID
	 *            the appID
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */

	public List<Operatorendpoint> operatorEndpoints(Integer appID) throws SQLException, PersistenceException {

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
				Operatorendpoint endpoint = new Operatorendpoint(rs.getInt("operatorid"), rs.getString("operatorname"), rs.getString("api"), rs.getString("endpoint"));
				endpoint.setId(rs.getInt("ID"));
				endpoints.add(endpoint);
			}

		} catch (SQLException e) {

			log.error("database operation error in operator endpoints entry : ", e);
			throw e;
		} catch (Exception e) {
			throw new PersistenceException("Error while retrieving operator endpoints. ", e);
		} finally {
			DbUtils.closeAllConnections(statement, con, rs);
		}

		return endpoints;
	}
	
	

	/**
	 * Update application op.
	 *
	 * @param appID
	 *            the appID
	 * @param operatorid
	 *            the operatorid
	 * @param opstat
	 *            the opstat
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	public boolean UpdateApplicationOp(int appID, int operatorid, boolean opstat) throws SQLException, PersistenceException {

		Connection con = null;
		PreparedStatement statement = null;

		int opactive = (opstat ? 1 : 0);

		try {
			con = DbUtils.getDBConnection();
			if (con == null) {
				throw new Exception("Connection not found");
			}

			con.setAutoCommit(false);

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

			con.commit();

		} catch (SQLException e) {

			/**
			 * rollback if Exception occurs
			 */
			con.rollback();

			log.error("database operation error in updating operatorapps ", e);
			throw e;
		} catch (Exception e) {
			throw new PersistenceException("Error while updating operatorapps. ", e);
		} finally {
			DbUtils.closeAllConnections(statement, con, null);
		}

		return true;
	}

	/**
	 * Application entry.
	 *
	 * @param appID
	 *            the appID
	 * @param operators
	 *            the operators
	 * @return the integer
	 * @throws Exception
	 *             the exception
	 */
	public Integer applicationEntry(int appID, Integer[] operators) throws SQLException, PersistenceException {

		Connection con = null;
		PreparedStatement statement = null;
		Integer newid = 0;

		try {
			con = DbUtils.getDBConnection();
			if (con == null) {
				throw new Exception("Connection not found");
			}

			con.setAutoCommit(false);

			for (Integer d : operators) {
				StringBuilder queryString = new StringBuilder("INSERT INTO ");
				queryString.append("operatorapps ");
				queryString.append("(applicationid,operatorid) ");
				queryString.append("VALUES (?, ? ) ");

				statement = con.prepareStatement(queryString.toString());

				statement.setInt(1, appID);
				statement.setInt(2, d);

				statement.executeUpdate();

				con.commit();
			}

		} catch (SQLException e) {

			/**
			 * rollback if Exception occurs
			 */
			con.rollback();

			log.error("database operation error in inserting operatorapps ", e);
			throw e;
		} catch (Exception e) {
			throw new PersistenceException("Error while inserting in to operatorapps. ", e);
		} finally {
			DbUtils.closeAllConnections(statement, con, null);
		}

		return newid;
	}

	/**
	 * Gets the operators.
	 *
	 * @return the operators
	 * @throws Exception
	 *             the exception
	 */

	public List<Operator> getOperators() throws SQLException, PersistenceException {

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

		} catch (SQLException e) {

			log.error("database operation error in retrieving operators ", e);
			throw e;
		} catch (Exception e) {
			throw new PersistenceException("Error while retrieving operators. ", e);
		} finally {
			DbUtils.closeAllConnections(statement, con, rs);
		}
		return operators;
	}

	/**
	 * Insert operator app endpoints.
	 *
	 * @param appID
	 *            the app id
	 * @param opEndpointIDList
	 *            the op endpoint id list
	 * @throws Exception
	 *             the exception
	 */
	public void insertOperatorAppEndpoints(int appID, int[] opEndpointIDList) throws SQLException, PersistenceException {

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

			con.setAutoCommit(false);

			StringBuilder queryString = new StringBuilder("INSERT INTO ");
			queryString.append("endpointapps ");
			queryString.append("(endpointid, applicationid, isactive) ");
			queryString.append("VALUES ");
			queryString.append("inputStr ");

			log.debug("sql : " + queryString);

			statement = con.prepareStatement(queryString.toString());

			statement.executeUpdate();

			con.commit();

		} catch (SQLException e) {

			/**
			 * rollback if Exception occurs
			 */
			con.rollback();

			log.error("database operation error in inserting endpointapps ", e);
			throw e;
		} catch (Exception e) {
			throw new PersistenceException("Error while inserting in to endpointapps. ", e);
		} finally {
			DbUtils.closeAllConnections(statement, con, null);
		}
	}

	/**
	 * Update operator app endpoint status.
	 *
	 * @param appID
	 *            the app id
	 * @param opEndpointID
	 *            the op endpoint id
	 * @param status
	 *            the status
	 * @throws Exception
	 *             the exception
	 */
	public void updateOperatorAppEndpointStatus(int appID, int opEndpointID, int status) throws SQLException, PersistenceException {

		Connection con = null;
		PreparedStatement statement = null;

		try {
			con = DbUtils.getDBConnection();

			con.setAutoCommit(false);

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

			con.commit();

		} catch (SQLException e) {

			/**
			 * rollback if Exception occurs
			 */
			con.rollback();

			log.error("database operation error in updating endpointapps ", e);
			throw e;
		} catch (Exception e) {
			throw new PersistenceException("Error while updating endpointapps. ", e);
		} finally {
			DbUtils.closeAllConnections(statement, con, null);
		}
	}

	/**
	 * Gets the operator endpoints.
	 *
	 * @return the operator endpoints
	 * @throws Exception
	 *             the exception
	 */

	public List<Operatorendpoint> getOperatorEndpoints() throws SQLException, PersistenceException {

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
				Operatorendpoint endpoint = new Operatorendpoint(rs.getInt("operatorid"), null, rs.getString("api"), null);
				endpoint.setId(rs.getInt("ID"));
				operatorEndpoints.add(endpoint);
			}

		} catch (SQLException e) {
			log.error("database operation error in selecting from operatorendpoints ", e);
			throw e;
		} catch (Exception e) {
			throw new PersistenceException("Error while selecting from operatorendpoints. ", e);
		} finally {
			DbUtils.closeAllConnections(statement, con, rs);
		}

		return operatorEndpoints;
	}

	/**
	 * Update app approval status op.
	 *
	 * @param appID
	 *            the appID
	 * @param operatorId
	 *            the operator id
	 * @param status
	 *            the status
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	public boolean updateAppApprovalStatusOp(int appID, int operatorId, int status) throws SQLException, PersistenceException {

		Connection con = null;
		PreparedStatement statement = null;

		try {
			con = DbUtils.getDBConnection();
			if (con == null) {
				throw new Exception("Connection not found.");
			}

			con.setAutoCommit(false);

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

			con.commit();

		} catch (SQLException e) {

			/**
			 * rollback if Exception occurs
			 */
			con.rollback();

			log.error("database operation error in updating operatorapps ", e);
			throw e;
		} catch (Exception e) {
			throw new PersistenceException("Error while updating operatorapps. ", e);
		} finally {
			DbUtils.closeAllConnections(statement, con, null);
		}

		return true;
	}

	/**
	 * Insert validator for subscription.
	 *
	 * @param appID
	 *            the app id
	 * @param apiID
	 *            the api id
	 * @param validatorID
	 *            the validator id
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	public boolean insertValidatorForSubscription(int appID, int apiID, int validatorID) throws SQLException, PersistenceException {
		Connection con = null;
		PreparedStatement statement = null;

		try {
			con = DbUtils.getDBConnection();
			if (con == null) {
				throw new Exception("Connection not found.");
			}

			con.setAutoCommit(false);

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

			con.commit();

		} catch (SQLException e) {

			/**
			 * rollback if Exception occurs
			 */
			con.rollback();

			log.error("database operation error in inserting in to subscription_validator ", e);
			throw e;
		} catch (Exception e) {
			throw new PersistenceException("Error while inserting in to subscription_validator. ", e);
		} finally {
			DbUtils.closeAllConnections(statement, con, null);
		}
		return true;
	}

	/**
	 * Removes the merchant provision.
	 *
	 * @param appID
	 *            the app id
	 * @param subscriber
	 *            the subscriber
	 * @param operator
	 *            the operator
	 * @param merchants
	 *            the merchants
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	public boolean removeMerchantProvision(Integer appID, String subscriber, String operator, String[] merchants) throws SQLException, PersistenceException {

		Connection con = null;
		PreparedStatement selectStatement = null;
		PreparedStatement deleteStatement = null;
		ResultSet rs = null;

		try {
			con = DbUtils.getDBConnection();
			if (con == null) {
				throw new Exception("Connection not found.");
			}

			StringBuilder selectQueryString = new StringBuilder("SELECT ");
			selectQueryString.append("id ");
			selectQueryString.append("FROM ");
			selectQueryString.append("operators ");
			selectQueryString.append("WHERE ");
			selectQueryString.append("operatorname = ? ");

			selectStatement = con.prepareStatement(selectQueryString.toString());

			selectStatement.setString(1, operator);
			rs = selectStatement.executeQuery();

			int operatorid = 0;
			if (rs.next()) {
				operatorid = rs.getInt("id");
			} else {
				throw new Exception("Operator Not Found");
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

					deleteStatement = con.prepareStatement(deleteQueryString.toString());

					deleteStatement.setInt(1, operatorid);
					deleteStatement.setString(2, subscriber);
					deleteStatement.setString(3, merchants[i]);
					deleteStatement.executeUpdate();

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

					deleteStatement = con.prepareStatement(queryString.toString());

					deleteStatement.setInt(1, appID);
					deleteStatement.setInt(2, operatorid);
					deleteStatement.setString(3, subscriber);
					deleteStatement.setString(4, merchants[i]);
					deleteStatement.executeUpdate();

				}
			}

		} catch (SQLException e) {

			/**
			 * rollback if Exception occurs
			 */
			con.rollback();

			log.error("database operation error while Provisioning Merchant ", e);
			throw e;
		} catch (Exception e) {
			throw new PersistenceException("Error while Provisioning Merchant. ", e);
		} finally {
			DbUtils.closeAllConnections(selectStatement, con, rs);
			DbUtils.closeAllConnections(deleteStatement, null, null);
		}
		return true;
	}

	/**
	 * Gets the prefix from country code.
	 *
	 * @param countryCode
	 *            the country code
	 * @return the prefix from country code
	 * @throws Persistence
	 *             Exception the persistenceException exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public String getPrefixFromCountryCode(String countryCode) throws SQLException, PersistenceException {

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

		} catch (SQLException e) {

			/**
			 * rollback if Exception occurs
			 */
			con.rollback();

			log.error("database operation error while selecting from subscriptions ", e);
			throw e;
		} catch (Exception e) {
			throw new PersistenceException("Error while selecting from subscriptions. ", e);

		} finally {
			DbUtils.closeAllConnections(statement, con, rs);
		}

		return prefix;

	}

	/**
	 * Insert sms request ids.
	 *
	 * @param requestID
	 *            the request id
	 * @param senderAddress
	 *            the sender address
	 * @param pluginRequestIDs
	 *            the plugin request i ds
	 * @return true, if successful
	 * @throws PersistenceException
	 *             the persistence exception
	 */

	public boolean insertSmsRequestIds(String requestID, String senderAddress, Map<String, String> pluginRequestIDs) throws SQLException, PersistenceException {
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

		} catch (SQLException e) {

			log.error("database operation error while inserting in to sendsms_reqid ", e);
			throw e;
		} catch (Exception e) {
			throw new PersistenceException("Error while inserting in to sendsms_reqid. ", e);
		} finally {
			DbUtils.closeAllConnections(ps, con, null);
		}
		return true;
	}

	/**
	 * Gets the sms request ids.
	 *
	 * @param requestID
	 *            the request id
	 * @param senderAddress
	 *            the sender address
	 * @return the sms request ids
	 * @throws PersistenceException
	 *             the Persistence exception
	 */

	public Map<String, String> getSmsRequestIds(String requestID, String senderAddress) throws SQLException, PersistenceException {
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
		} catch (SQLException e) {

			log.error("database operation error while inserting in to sendsms_reqid ", e);
			throw e;
		} catch (Exception e) {
			throw new PersistenceException("Error while inserting in to sendsms_reqid. ", e);
		} finally {
			DbUtils.closeAllConnections(ps, con, rs);
		}
		return pluginRequestIDs;
	}

	/**
	 * Gets the SP token map.
	 *
	 * @return the SP token map
	 * @throws SQLException
	 *             the SQL exception
	 * @throws PersistenceException
	 *             the persistenceException exception
	 */
	public Map<String, String> getSPTokenMap() throws SQLException, PersistenceException {

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

		} catch (SQLException e) {

			log.error("database operation error while selecting from sp_token ", e);
			throw e;
		} catch (Exception e) {
			throw new PersistenceException("Error while selecting from sp_token ", e);
		} finally {
			DbUtils.closeAllConnections(statement, con, rs);
		}

		return spToken;
	}
}
