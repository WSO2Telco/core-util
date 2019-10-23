package com.wso2telco.core.dbutils.dao;


import com.wso2telco.core.dbutils.Operator;
import com.wso2telco.core.dbutils.dao.SpendLimitDAO;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpendLimitDAOTest {

    private SpendLimitDAO spendlimit = null;

    @BeforeClass
    public void init() {
        spendlimit=new SpendLimitDAO();
        spendlimit.setGroupName("group");
        spendlimit.setConsumerKey("consumerkey");
        spendlimit.setOperatorId("OperatorId");
        spendlimit.setMsisdn("134-678");
        spendlimit.setAmount(1000.0);
        spendlimit.setCurrentDateTime("2019/09/20");
        spendlimit.setResponseTime(12);
    }

    @Test(groups = "spendlimit")
    public void testGroupName_whenCorrectValues() {
        String expected = "group";
        String actual = spendlimit.getGroupName();
        Assert.assertEquals(actual, expected);
    }


    @Test(groups = "spendlimit")
    public void testGroupName_whenWrongValues() {
        String expected = "groupWrong";
        String actual = spendlimit.getGroupName();
        Assert.assertNotEquals(actual, expected);
    }


    @Test(groups = "spendlimit")
    public void testConsumerKey_whenCorrectValues() {
        String expected = "consumerkey";
        String actual = spendlimit.getConsumerKey();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "spendlimit")
    public void testConsumerKey_whenWrongValues() {
        String expected = "consumerkeyWrong";
        String actual = spendlimit.getConsumerKey();
        Assert.assertNotEquals(actual, expected);
    }

    @Test(groups = "spendlimit")
    public void testOperator_whenCorrectValues() {
        String expected = "OperatorId";
        String actual = spendlimit.getOperatorId();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "spendlimit")
    public void testOperator_whenWrongValues() {
        String expected = "OperatorIdWrong";
        String actual = spendlimit.getOperatorId();
        Assert.assertNotEquals(actual, expected);
    }


    @Test(groups = "spendlimit")
    public void testMsisdn_whenCorrectValues() {
        String expected = "134-678";
        String actual = spendlimit.getMsisdn();
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = "spendlimit")
    public void testMsisdn_whenWrongValues() {
        String expected = "134-678-00-wrong";
        String actual = spendlimit.getMsisdn();
        Assert.assertNotEquals(actual, expected);
    }

    @Test(groups = "spendlimit")
    public void testAmount_whenCorrectValues() {
        double expected = 1000.0;
        double actual = spendlimit.getAmount();
        Assert.assertEquals(actual, expected);
    }
    @Test(groups = "spendlimit")
    public void testAmount_whenWrongValues() {
        double expected = 0000.0;
        double actual = spendlimit.getAmount();
        Assert.assertNotEquals(actual, expected);
    }

    @Test(groups = "spendlimit")
    public void testCurrentDateTime_whenCorrectValues() {
        String expected = "2019/09/20";
        String actual = spendlimit.getCurrentDateTime();
        Assert.assertEquals(actual, expected);
    }


    @Test(groups = "spendlimit")
    public void testCurrentDateTime_whenWrongValues() {
        String expected = "2019/00/00";
        String actual = spendlimit.getCurrentDateTime();
        Assert.assertNotEquals(actual, expected);
    }

    @Test(groups = "spendlimit")
    public void testResponses_whenCorrectValues() {
        long  expected = 12;
        long actual = spendlimit.getResponseTime();
        Assert.assertEquals(actual, expected);
    }
    @Test(groups = "spendlimit")
    public void testResponses_whenWrongValues() {
        long  expected = 00;
        long actual = spendlimit.getResponseTime();
        Assert.assertNotEquals(actual, expected);
    }


}
