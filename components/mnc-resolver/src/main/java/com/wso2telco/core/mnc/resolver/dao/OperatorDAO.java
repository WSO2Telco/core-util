/*******************************************************************************
 * Copyright  (c) 2015-2016, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 * 
 * WSO2.Telco Inc. licences this file to you under  the Apache License, Version 2.0 (the "License");
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
package com.wso2telco.core.mnc.resolver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wso2telco.core.dbutils.DbUtils;
import com.wso2telco.core.dbutils.util.DataSourceNames;
import com.wso2telco.core.mnc.resolver.MobileNtException;

public class OperatorDAO {
	private static DataSource datasource = null;
	
	private static final Log log = LogFactory.getLog(OperatorDAO.class);
	
	public static void initializeDatasources() throws MobileNtException {
		if (datasource != null) {
			return;
		}

		try {
			Context ctx = new InitialContext();
			datasource = (DataSource) ctx.lookup(DataSourceNames.WSO2TELCO_DEP_DB.jndiName());
		} catch (NamingException e) {
			handleException("Error while looking up the data source: " + DataSourceNames.WSO2TELCO_DEP_DB, e);
		}
	}
	
	private static void handleException(String msg, Throwable t) throws MobileNtException {
		log.error(msg, t);
		throw new MobileNtException(msg, t);
	}
	
	public static Connection getDBConnection() throws SQLException, MobileNtException {
		initializeDatasources();

		if (datasource != null) {
			return datasource.getConnection();
		}
		throw new SQLException("Datasource not initialized properly");
	}
	
	public static String getOperatorByMCCMNC(String mcc, String mnc) throws MobileNtException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String operator = null;

		try {

			String sql = "SELECT operatorname FROM operators WHERE mcc = ? AND mnc = ?";

			conn = getDBConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, mcc);
			ps.setString(2, mnc);
			rs = ps.executeQuery();

			while (rs.next()) {

				operator = rs.getString("operatorname");
				log.debug("operator in getOperatorByMCCMNC: " + operator);
			}
		} catch (SQLException e) {

			log.error("database operation error in getOperatorByMCCMNC : ", e);
			handleException(
					"Error occured while getting operator for mcc : " + mcc + " and mnc : " + mnc + "from the database",
					e);
		} catch (Exception e) {

			log.error("error in getOperatorByMCCMNC : ", e);
			handleException(
					"Error occured while getting operator for mcc : " + mcc + " and mnc : " + mnc + "from the database",
					e);
		} finally {
			DbUtils.closeAllConnections(ps, conn, rs);
		}

		return operator;
	}

}
