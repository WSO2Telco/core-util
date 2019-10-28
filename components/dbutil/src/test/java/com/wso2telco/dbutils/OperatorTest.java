package com.wso2telco.dbutils;


import com.wso2telco.core.dbutils.dao.SpendLimitDAO;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.assertj.core.api.Assertions;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class OperatorTest {

    private Operator operator = null;
    @BeforeTest
    public  void init(){
        operator=Mockito.mock(Operator.class);
        operator.setId(1);
        operator.setOperatorname("Operator");
        operator.setApplicationid(1);
        operator.setOperatorid(1);
        operator.setIsactive(1);
        operator.setNote("Note");
        operator.setCreated("2019/09/24");
        operator.setCreated_date("2019/09/24");
        operator.setLastupdated( "Last Updated");
        operator.setLastupdated_date("2019/09/26");
        operator.setRefreshtoken("aed3ffbfrheftwerfghyt");
        operator.setTokenvalidity(123234);
        operator.setTokentime(2);
        operator.setToken("token");
        operator.setTokenurl("http://www.url.com");
        operator.setTokenauth("ebs3452239023241223w1s001");

        System.out.println(operator.getTokenurl());
    }


    @Test
    public void testId_whenCorrectValues() {

        int  expected = 1;

        Mockito.when(operator.getId()).thenReturn(expected);
        Assert.assertEquals(operator.getId(), 1);
        verify(operator, times(1)).getId();


    }

    @Test
    public void testId_whenWrongValues() {

        int  expected = 156;
        Assert.assertNotEquals(operator.getId(),expected);
    }


    @Test
    public void testApplicationid_whenCorrectValues() {
        int  expected = 1;
        operator.setApplicationid(1);
        Mockito.when(operator.getApplicationid()).thenReturn(expected);
        Assert.assertEquals(operator.getApplicationid(), 1);
        verify(operator, times(1)).getApplicationid();
    }

    @Test
    public void testApplicationid_whenWrongValues() {
        int  expected = 145;
        Assert.assertNotEquals(operator.getApplicationid(),expected);

    }

    @Test
    public void testOperatorname_whenCorrectValues() {
        com.wso2telco.core.dbutils.Operator operators=new com.wso2telco.core.dbutils.Operator();
        String  expected = "Operator";

        operators.setOperatorname(expected);

    Assert.assertEquals(operators.getOperatorname(), expected);

    }

    @Test
    public void testOperatorname_whenWrongValues() {
        String  expected = "Op";
        Assert.assertNotEquals(operator.getOperatorname(),expected);
    }

    @Test
    public void testSetApplicationid_whenCorrectValues() {
        int  expected = 1;
        Assert.assertEquals(operator.getApplicationid(), expected);
    }

    @Test
    public void testSetApplicationid_whenWrongValues() {
        int  expected = 2;
        Assert.assertNotEquals(operator.getApplicationid(), expected);
    }

    @Test
    public void testOperatorid_whenCorrectValues() {
        int  expected = 1;
        Mockito.when(operator.getOperatorid()).thenReturn(expected);
        Assert.assertEquals(operator.getOperatorid(), expected);
        verify(operator,times(1)).getOperatorid();
    }

    @Test
    public void testOperatorid_whenWrongValues() {
        int  expected = 0;
        Assert.assertNotEquals(operator.getOperatorid(), expected);
    }

    @Test
    public void testGetIsactive_whenCorrectValues() {
        int  expected = 1;
        Mockito.when(operator.getIsactive()).thenReturn(expected);
        Assert.assertEquals(operator.getIsactive(), expected);
        verify(operator,times(1)).getIsactive();
    }

    @Test
    public void testGetIsactive_whenWrongValues() {
        int  expected = 0;
        Assert.assertNotEquals(operator.getIsactive(),expected);
    }

    @Test
    public void testGetNote_whenCorrectValues() {
        String  expected = "Note";
        Mockito.when(operator.getNote()).thenReturn(expected);
        Assert.assertEquals(operator.getNote(), expected);
        verify(operator,times(1)).getNote();
    }

    @Test
    public void testGetNote_whenWrongValues() {
        String  expected = "N";
        Assert.assertNotEquals(operator.getNote(),expected);
    }

    @Test
    public void testGetCreated_whenCorrectValues() {
        String  expected = "2019/09/24";
        Mockito.when(operator.getCreated()).thenReturn(expected);
        Assert.assertEquals(operator.getCreated(), expected);
        verify(operator,times(1)).getCreated();
    }

    @Test
    public void testGetCreated_whenWrongValues() {
        String  expected = "2019";
        Assert.assertNotEquals(operator.getCreated(),expected);
    }

    @Test
    public void testCreated_date_whenCorrectValues() {
        String  expected = "2019/09/24";
        Mockito.when(operator.getCreated_date()).thenReturn(expected);
        Assert.assertEquals(operator.getCreated_date(), expected);
        verify(operator,times(1)).getCreated_date();
    }

    @Test
    public void testCreated_date_whenWrongValues() {

        String  expected = "2019/09/18";
        Mockito.when(operator.getCreated_date()).thenReturn(expected);
        Assert.assertNotEquals(operator.getLastupdated(),expected);
    }

    @Test
    public void testLastupdated_whenCorrectValues() {
        String  expected = "Last Updated";
        Mockito.when(operator.getLastupdated()).thenReturn(expected);
        Assert.assertEquals(operator.getLastupdated(), expected);

    }

    @Test
    public void testLastupdated_whenWrongValues() {
        String  expected = "NOT Last Updated";
        Assert.assertNotEquals(operator.getLastupdated(), expected);
    }

    @Test
    public void testLastupdated_date_whenCorrectValues() {
        String  expected = "2019/09/26";
        Mockito.when(operator.getLastupdated_date()).thenReturn(expected);
        Assert.assertEquals(operator.getLastupdated_date(), expected);
        verify(operator,times(1)).getLastupdated_date();
    }
    @Test
    public void testLastupdated_date_whenWrongValues() {
        String  expected = "2019/09/21";
        Assert.assertNotEquals(operator.getLastupdated_date(), expected);
    }



    @Test
    public void testRefreshtoken_whenCorrectValues() {
        String  expected = "aed3ffbfrheftwerfghyt";
        Mockito.when(operator.getRefreshtoken()).thenReturn(expected);
        Assert.assertEquals(operator.getRefreshtoken(), expected);
        verify(operator, times(1)).getRefreshtoken();

    }

    @Test
    public void testRefreshtoken_whenWrongValues() {
        String  expected = "aed";
        Assert.assertNotEquals(operator.getRefreshtoken(), expected);

    }

    @Test
    public void testTokenvalidity_whenCorrectValues() {
        double  expected = 1234;
        com.wso2telco.core.dbutils.Operator operators=new com.wso2telco.core.dbutils.Operator();
        operators.setTokenvalidity(1234);
        Assert.assertEquals(operators.getTokenvalidity(), expected);

    }



    @Test
    public void testTokenvalidity_whenWrongValues() {
        double  expected = 012323401111;
        Assert.assertNotEquals(operator.getTokenvalidity(), expected);

    }

    @Test
    public void testTokentime_whenCorrectValues() {
        double  expected = 2;
        Mockito.when(operator.getTokentime()).thenReturn((double)expected);
        Assert.assertEquals(operator.getTokentime(), expected);
        verify(operator, times(1)).getTokentime();
    }

    @Test
    public void testTokentime_whenWrongValues() {
        double  expected = 20;
        Assert.assertNotEquals(operator.getToken(), expected);
    }

    @Test
    public void testToken_whenCorrectValues() {
        String  expected = "token";
        Mockito.when(operator.getToken()).thenReturn(expected);
        Assert.assertEquals(operator.getToken(), expected);
        verify(operator, times(1)).getToken();
    }


    @Test
    public void testToken_whenWrongValues() {
        String  expected = "wrongtoken";
        Assert.assertNotEquals(operator.getToken(), expected);
    }
    @Test
    public void testTokenurl_whenCorrectValues() {
        String  expected = "http://www.url.com";
        System.out.println(operator.getTokenurl());
//        Assert.assertEquals(operator.getTokenurl(), expected);

    }

    @Test
    public void testTokenurl_whenWrongValues() {
        String  expected = "http://www.fakeurl.com";
        Assert.assertNotEquals(operator.getTokenurl(), expected);

    }

    @Test
    public void testTokenauth_whenCorrectValue() {
        String  expected = "ebs3452239023241223w1s001";
        Mockito.when(operator.getTokenauth()).thenReturn(expected);
        Assert.assertEquals(operator.getTokenauth(), expected);
        verify(operator, times(1)).getTokenauth();
    }

    @Test
    public void testTokenauth_whenWrongValue() {
        String  expected = "e";
        Assert.assertNotEquals(operator.getTokenauth(), expected);
    }
}