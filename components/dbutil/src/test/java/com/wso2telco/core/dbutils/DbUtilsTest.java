package com.wso2telco.core.dbutils;

import com.wso2telco.core.dbutils.util.DataSourceNames;
import org.assertj.core.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class DbUtilsTest {
    DbUtils dbUtils;

    @Mock
    DataSourceNames dataSourceNames;

    @Mock
    Class cls;




    @BeforeTest
    public void setUp() {
        dbUtils = DbUtils.getInstance();
    }

    @Test
    public void testInitializeConnectDatasource_whenRightName() throws SQLException, DBUtilException, NamingException {
        DataSource dataSourceMock = Mockito.mock(DataSource.class);
        Context ctxMock = Mockito.mock(Context.class);
        when(ctxMock.lookup(anyString())).thenReturn(dataSourceMock);
        DbUtils.getInstance().initializeConnectDatasource(ctxMock);
        verify(ctxMock, times(1)).lookup("jdbc/CONNECT_DB");

    }

    @Test
    public void testInitializeConnectDatasource_whenWrongName() throws SQLException, DBUtilException, NamingException {

        DataSource dataSourceMock = Mockito.mock(DataSource.class);
        Context ctxMock = Mockito.mock(Context.class);
        when(ctxMock.lookup(anyString())).thenReturn(dataSourceMock);
        DbUtils.getInstance().initializeConnectDatasource(ctxMock);
        verify(ctxMock, never()).lookup("wrongdatabase");

    }

    @Test
    public void testGetDBConnection_whenDataSourceName_WSO2AM_DB() throws Exception {
        DataSource dataSourceMock = Mockito.mock(DataSource.class);
        Context ctxMock = Mockito.mock(Context.class);
        Connection conMock =Mockito.mock(Connection.class);
        when(ctxMock.lookup(anyString())).thenReturn(dataSourceMock);
        when(dataSourceMock.getConnection()).thenReturn(conMock);
        DbUtils.getDbConnection(DataSourceNames.WSO2AM_DB,ctxMock);
        verify(dataSourceMock,times(1)).getConnection();

    }
    @Test
    public void testGetDBConnection_whenDataSourceName_WSO2AM_STATS_DB() throws Exception {
        DataSource dataSourceMock = Mockito.mock(DataSource.class);
        Context ctxMock = Mockito.mock(Context.class);
        Connection conMock =Mockito.mock(Connection.class);
        when(ctxMock.lookup(anyString())).thenReturn(dataSourceMock);
        when(dataSourceMock.getConnection()).thenReturn(conMock);
        DbUtils.getDbConnection(DataSourceNames.WSO2AM_STATS_DB,ctxMock);
        verify(dataSourceMock,times(1)).getConnection();

    }

    @Test
    public void testGetDBConnection_whenDataSourceName_WSO2UM_DB() throws Exception {
        DataSource dataSourceMock = Mockito.mock(DataSource.class);
        Context ctxMock = Mockito.mock(Context.class);
        Connection conMock =Mockito.mock(Connection.class);
        when(ctxMock.lookup(anyString())).thenReturn(dataSourceMock);
        when(dataSourceMock.getConnection()).thenReturn(conMock);
        DbUtils.getDbConnection(DataSourceNames.WSO2UM_DB,ctxMock);
        verify(dataSourceMock,times(1)).getConnection();

    }

    @Test
    public void testGetDBConnection_whenDataSourceName_WSO2REG_DB() throws Exception {
        DataSource dataSourceMock = Mockito.mock(DataSource.class);
        Context ctxMock = Mockito.mock(Context.class);
        Connection conMock =Mockito.mock(Connection.class);
        when(ctxMock.lookup(anyString())).thenReturn(dataSourceMock);
        when(dataSourceMock.getConnection()).thenReturn(conMock);
        DbUtils.getDbConnection(DataSourceNames.WSO2REG_DB,ctxMock);
        verify(dataSourceMock,times(1)).getConnection();

    }
    @Test
    public void testGetDBConnection_whenDataSourceName_WSO2TELCO_DEP_DB() throws Exception {
        DataSource dataSourceMock = Mockito.mock(DataSource.class);
        Context ctxMock = Mockito.mock(Context.class);
        Connection conMock =Mockito.mock(Connection.class);
        when(ctxMock.lookup(anyString())).thenReturn(dataSourceMock);
        when(dataSourceMock.getConnection()).thenReturn(conMock);
        DbUtils.getDbConnection(DataSourceNames.WSO2TELCO_DEP_DB,ctxMock);
        verify(dataSourceMock,times(1)).getConnection();

    }

    @Test
    public void testGetDBConnection_whenDataSourceName_WSO2TELCO_RATE_DB() throws Exception {
        DataSource dataSourceMock = Mockito.mock(DataSource.class);
        Context ctxMock = Mockito.mock(Context.class);
        Connection conMock =Mockito.mock(Connection.class);
        when(ctxMock.lookup(anyString())).thenReturn(dataSourceMock);
        when(dataSourceMock.getConnection()).thenReturn(conMock);
        DbUtils.getDbConnection(DataSourceNames.WSO2TELCO_RATE_DB,ctxMock);
        verify(dataSourceMock,times(1)).getConnection();

    }

    @Test
    public void testGetDBConnection_whenDataSourceName_WSO2TELCO_MANDATE_DB() throws Exception {
        DataSource dataSourceMock = Mockito.mock(DataSource.class);
        Context ctxMock = Mockito.mock(Context.class);
        Connection conMock =Mockito.mock(Connection.class);
        when(ctxMock.lookup(anyString())).thenReturn(dataSourceMock);
        when(dataSourceMock.getConnection()).thenReturn(conMock);
        DbUtils.getDbConnection(DataSourceNames.WSO2TELCO_MANDATE_DB,ctxMock);
        verify(dataSourceMock,times(1)).getConnection();

    }


    @Test(expectedExceptions = Exception.class)
    public void testGetDBConnection_whenDataSourceName_Null() throws Exception {
        DataSource dataSourceMock = Mockito.mock(DataSource.class);
        Context ctxMock = Mockito.mock(Context.class);
        Connection conMock =Mockito.mock(Connection.class);
        when(ctxMock.lookup(anyString())).thenReturn(dataSourceMock);
        when(dataSourceMock.getConnection()).thenReturn(conMock);
        DbUtils.getDbConnection(null,ctxMock);
        // verify(dataSourceMock,times(1)).getConnection();

    }



    @Test
    public void testFormat_whenFinalLengthLargerThanStringLength() throws Exception {
        int finalLength =20;
        String strData = "chamara";
        Assertions.assertThat(DbUtils.format(strData,finalLength).length()).isEqualTo(finalLength);
    }

    @Test
    public void testFormat_whenStringLengthLargerThanFinalLength() throws Exception {
        int finalLength =2;
        String strData = "chamara";
        Assertions.assertThat(DbUtils.format(strData,finalLength).length()).isEqualTo(finalLength);
    }
    @Test
    public void testFormat_whenIntDataLengthLargerThanFinalLength() throws Exception {
        int intData =100;
        int finalLength = 40;
        Assertions.assertThat(DbUtils.format(intData,finalLength).length()).isEqualTo(finalLength);
    }
    @Test
    public void testFormat_whenIntDataLengthSmallerThanFinalLength() throws Exception {
        int intData =1;
        int finalLength = 40;
        Assertions.assertThat(DbUtils.format(intData,finalLength).length()).isEqualTo(finalLength);
    }

    @Test
    public void testFormat_shouldReturnToPrecisionLength() throws Exception {
        double doubleData =100.999999999;
        int precision = 10;
        int scale =4;
        Assertions.assertThat(DbUtils.format(doubleData,precision,scale).length()).isEqualTo(precision+1);
    }

    @Test
    public void testFormat_shouldReturnCorrectScale() throws Exception {
        double doubleData =100.999999999;
        int precision = 10;
        int scale =4;

        String doubleToString = DbUtils.format(doubleData, precision, scale);
        String[] stringArray =doubleToString.split("\\.");
        Assertions.assertThat(stringArray[1].length()).isEqualTo(scale);

    }
    @Test
    public void testFormat_whenBigDecimalShouldReturnToPrecisionLength() throws Exception {
        BigDecimal decData = new BigDecimal(232424234);
        int precision = 10;
        int scale =4;
        Assertions.assertThat(DbUtils.format(decData,precision,scale).length()).isEqualTo(precision+1);
    }
    @Test
    public void testFormat_whenBigDecimalShouldReturnCorrectScale() throws Exception {
        BigDecimal decData = new BigDecimal(23242.234);
        int precision = 10;
        int scale =5;
        System.out.println(DbUtils.format(decData, precision, scale));
        String doubleToString = DbUtils.format(decData, precision, scale);
        String[] stringArray =doubleToString.split("\\.");
        Assertions.assertThat(stringArray[1].length()).isEqualTo(scale);
    }

    @Test
    public void testDisconnect() throws Exception {
        Connection moccon = Mockito.mock(Connection.class);

        DbUtils.getInstance().disconnect(moccon);
        verify(moccon,times(1)).commit();
        verify(moccon,times(1)).close();
    }


    @Test
    public void testGetDbNames() throws NamingException {
        Context ctxMock = Mockito.mock(Context.class);
        DbUtils dbutil =DbUtils.getInstance();
        dbutil.getDbNames(ctxMock);
    }


}

