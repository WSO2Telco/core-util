package com.wso2telco.dbutils;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class JdbcExceptionTest {

    @Test
    public void testHandle_whenCorrectValues() {

        JdbcException jdbcex=Mockito.mock(JdbcException.class);
        Mockito.when(jdbcex.getMessage()).thenReturn("Error Message");
        Assert.assertEquals("Error Message", jdbcex.getMessage());
    }

    @Test
    public void testHandle_whenWrongValues() {

        JdbcException jdbcex=Mockito.mock(JdbcException.class);
        Mockito.when(jdbcex.getMessage()).thenReturn("Wrong Message");
        Assert.assertNotEquals("Error Message", jdbcex.getMessage());
    }

    @Test
    public void testHandleExpectedErr() {

        Exception e=new RuntimeException("Hi Error");
        JdbcException jdbcex=new JdbcException(e);
        Assert.assertEquals("Hi Error", jdbcex.getMessage());
    }
}