package com.axiata.dialog.mife.mnc.resolver.mncrange;
/*
 * McnRangeDbUtil.java
 * Nov 19, 2014  12:01:53 PM
 * Roshan.Saputhanthri
 *
 * Copyright (C) Dialog Axiata PLC. All Rights Reserved.
 */

import com.axiata.dialog.mife.mnc.resolver.MobileNtException;
import com.axiata.dialog.mife.mnc.resolver.NumberRange;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.apimgt.impl.utils.APIMgtDBUtil;



/**
 * <TO-DO>
 * <code>McnRangeDbUtil</code>
 *
 * @version $Id: McnRangeDbUtil.java,v 1.00.000
 */
public class McnRangeDbUtil {

    private static volatile DataSource axiataDatasource = null;
    private static final String AXIATA_DATA_SOURCE = "jdbc/AXIATA_MIFE_DB";
    private static final Log log = LogFactory.getLog(McnRangeDbUtil.class);

    public static void initializeDatasources() throws MobileNtException {
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

    public static Connection getAxiataDBConnection() throws SQLException, MobileNtException {
        initializeDatasources();

        if (axiataDatasource != null) {
            return axiataDatasource.getConnection();
        }
        throw new SQLException("Axiata Datasource not initialized properly");
    }

    private static void handleException(String msg, Throwable t) throws MobileNtException {
        log.error(msg, t);
        throw new MobileNtException(msg, t);
    }

    public static List<NumberRange> getMccNumberRanges(String mcc) throws MobileNtException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT mnccode,brand,rangefrom,rangeto "
                + "FROM mcc_number_ranges "
                + "WHERE mcccode = ?";

        List<NumberRange> lstranges = new ArrayList();

        try {
            conn = getAxiataDBConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, mcc);
            rs = ps.executeQuery();
            while (rs.next()) {
                lstranges.add(new NumberRange(rs.getLong("rangefrom"), rs.getLong("rangeto"), rs.getString("mnccode"), rs.getString("brand")));
            }
        } catch (SQLException e) {
            handleException("Error occured while getting Number ranges for mcc: " + mcc + " from the database", e);
        } finally {
            APIMgtDBUtil.closeAllConnections(ps, conn, rs);
        }
        return lstranges;
    }
}
