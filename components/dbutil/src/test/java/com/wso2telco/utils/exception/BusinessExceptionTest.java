package com.wso2telco.utils.exception;

import com.wso2telco.core.dbutils.exception.BusinessException;
import org.testng.Assert;
import org.testng.annotations.Test;


public class BusinessExceptionTest {
    private BusinessException businessException;
    @Test
    public void testGetErrorType_whenCorrectValues() {
        businessException=new BusinessException("Text format error");
        Assert.assertEquals(businessException.getErrorType().getMessage(), "Text format error");
    }
    @Test
    public void testGetErrorType_whenWrongValues() {
        businessException=new BusinessException("Text format error wrong message");
        Assert.assertNotEquals(businessException.getErrorType().getMessage(), "Text format error");
    }
}