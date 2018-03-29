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
package com.wso2telco.core.mnc.resolver.mncrange;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
// TODO: Auto-generated Javadoc
//import org.wso2.carbon.apimgt.impl.utils.APIMgtDBUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wso2telco.core.dbutils.DbUtils;
import com.wso2telco.core.dbutils.util.DataSourceNames;
import com.wso2telco.core.mnc.resolver.MobileNtException;
import com.wso2telco.core.mnc.resolver.NumberRange;


/**
 * The Class McnRangeDbUtil.
 */
public class McnRangeDbUtil {

    /**
     * The Constant log.
     */
    private static final Log log = LogFactory.getLog(McnRangeDbUtil.class);


    /**
     * Gets the axiata db connection.
     *
     * @return the axiata db connection
     * @throws Exception
     */
    public static Connection getAxiataDBConnection() throws Exception {
        return DbUtils.getDbConnection(DataSourceNames.WSO2TELCO_DEP_DB);
    }

    /**
     * Handle exception.
     *
     * @param msg the msg
     * @param t   the t
     * @throws MobileNtException the mobile nt exception
     */
    private static void handleException(String msg, Throwable t) throws MobileNtException {
        log.error(msg, t);
        throw new MobileNtException(msg, t);
    }

    /**
     * Gets the mcc number ranges.
     *
     * @param mcc the mcc
     * @return the mcc number ranges
     * @throws MobileNtException the mobile nt exception
     */
    public static List<NumberRange> getMccNumberRanges(String mcc) throws MobileNtException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT mnccode,brand,rangefrom,rangeto "
                + "FROM mcc_number_ranges "
                + "WHERE mcccode = ?";

        List<NumberRange> lstranges = new ArrayList();

        try {
            conn = DbUtils.getDbConnection(DataSourceNames.WSO2TELCO_DEP_DB);
            ps = conn.prepareStatement(sql);
            ps.setString(1, mcc);
            rs = ps.executeQuery();
            while (rs.next()) {
                lstranges.add(new NumberRange(rs.getLong("rangefrom"), rs.getLong("rangeto"), rs.getString("mnccode")
                        , rs.getString("brand")));
            }
        } catch (Exception e) {
            handleException("Error occured while getting Number ranges for mcc: " + mcc + " from the database", e);
        } finally {
            DbUtils.closeAllConnections(ps, conn, rs);
        }
        return lstranges;
    }

    public static String getMncBrand(String mcc, String mnc) throws MobileNtException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT operatorname "
                + "FROM operators "
                + "WHERE mcc = ? AND mnc = ?";


        String mncBrand = null;

        try {
            conn = DbUtils.getDbConnection(DataSourceNames.WSO2TELCO_DEP_DB);
            ps = conn.prepareStatement(sql);
            ps.setString(1, mcc);
            ps.setString(2, mnc);
            rs = ps.executeQuery();
            if (rs.next()) {
                mncBrand = rs.getString("operatorname");
            }
        } catch (SQLException e) {
            handleException("Error occured while getting Brand for for mcc: and mnc: " + mcc + ":" + mnc + " from the" +
                    " database", e);
        } catch (Exception e) {
            handleException("Error occured while getting Brand for for mcc: and mnc: " + mcc + ":" + mnc + " from the" +
                    " database", e);
        } finally {
            DbUtils.closeAllConnections(ps, conn, rs);
        }
        return mncBrand;
    }
    
    public static String getMncBrand(String mncCode) throws MobileNtException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT brand "
                + "FROM operator_brands "
                + "WHERE operatorcode = ?";


        String mncBrand = null;

        try {
            conn = DbUtils.getDbConnection(DataSourceNames.WSO2TELCO_DEP_DB);
            ps = conn.prepareStatement(sql);
            ps.setString(1, mncCode);
            
            rs = ps.executeQuery();
            if (rs.next()) {
                mncBrand = rs.getString("brand");
            }
        } catch (SQLException e) {
            handleException("Error occured while getting Brand for for mncCode :" +mncCode + ""
                    + " database", e);
        } catch (Exception e) {
            handleException("Error occured while getting Brand for for mncCode :" +mncCode + ""+
                    " database", e);
        } finally {
            DbUtils.closeAllConnections(ps, conn, rs);
        }
        return mncBrand;
    }


    /**
     * Close statement.
     *
     * @param preparedStatement the prepared statement
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
}
