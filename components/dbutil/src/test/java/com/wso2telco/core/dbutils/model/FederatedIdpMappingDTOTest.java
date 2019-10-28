package com.wso2telco.core.dbutils.model;


import com.wso2telco.dbutils.Operator;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FederatedIdpMappingDTOTest {

    private FederatedIdpMappingDTO federatedIdpMappingDTO = null;
    @BeforeTest
    public  void init(){
        federatedIdpMappingDTO= Mockito.mock(FederatedIdpMappingDTO.class);
    }

    @Test
    public void testOperator_whenCorrectValues() {
        String  expected = "Operator";
        federatedIdpMappingDTO.setOperator(expected);
        Mockito.when(federatedIdpMappingDTO.getOperator()).thenReturn(expected);
        Assert.assertEquals(federatedIdpMappingDTO.getOperator(), expected);
    }

    @Test
    public void testOperator_whenWrongValues() {
        String  expected = "Operator";
        Mockito.when(federatedIdpMappingDTO.getOperator()).thenReturn("WrongOperator");
        Assert.assertNotEquals(federatedIdpMappingDTO.getOperator(), expected);
    }



    @Test
    public void testFidpAuthCode_whenCorrectValues() {
        String  expected = "edcxwrtuhvkfpo56jfk";
        federatedIdpMappingDTO.setFidpAuthCode(expected);
        Mockito.when(federatedIdpMappingDTO.getFidpAuthCode()).thenReturn(expected);
        Assert.assertEquals(federatedIdpMappingDTO.getFidpAuthCode(), expected);
    }


    @Test
    public void testFidpAuthCode_whenWrongValues() {
        String  expected = "edcxwrtuhvkfpo56jfk";
        federatedIdpMappingDTO.setFidpAuthCode(expected);
        Mockito.when(federatedIdpMappingDTO.getFidpAuthCode()).thenReturn("WrongValues");
        Assert.assertNotEquals(federatedIdpMappingDTO.getFidpAuthCode(), expected);
    }




    @Test
    public void testFidpAccessToken_whenCorrectValues() {
        String  expected = "edcxwrtuhvkfpo56jfk";
        federatedIdpMappingDTO.setFidpAccessToken(expected);
        Mockito.when(federatedIdpMappingDTO.getFidpAccessToken()).thenReturn(expected);
        Assert.assertEquals(federatedIdpMappingDTO.getFidpAccessToken(), expected);
    }

    @Test
    public void testFidpAccessToken_whenWrongValues() {
        String  expected = "edcxwrtuhvkfpo56jfk";
        federatedIdpMappingDTO.setFidpAccessToken(expected);
        Mockito.when(federatedIdpMappingDTO.getFidpAccessToken()).thenReturn("WrongToken");
        Assert.assertNotEquals(federatedIdpMappingDTO.getFidpAccessToken(), expected);
    }

    @Test
    public void testAccessToken_whenCorrectValues() {
        String  expected = "edcxwrtuhvkfpo56jfk";
        federatedIdpMappingDTO.setAccessToken(expected);
        Mockito.when(federatedIdpMappingDTO.getAccessToken()).thenReturn(expected);
        Assert.assertEquals(federatedIdpMappingDTO.getAccessToken(), expected);
    }

    @Test
    public void testAccessToken_whenWrongValues() {
        String  expected = "edcxwrtuhvkfpo56jfk";
        federatedIdpMappingDTO.setAccessToken(expected);
        Mockito.when(federatedIdpMappingDTO.getAccessToken()).thenReturn("WrongValues");
        Assert.assertNotEquals(federatedIdpMappingDTO.getAccessToken(), expected);
    }
}