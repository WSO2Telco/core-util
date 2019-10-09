package com.wso2telco.core.dbutils.util;

import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CallbackTest {

    private Callback callback;

    @BeforeTest
    public void initialtest(){
        callback =new Callback();
        callback.setMessage("message");
        callback.setPayload("payload");
        callback.setSuccess(true);
    }
    @Test
    public void testgetMessage(){
        Assertions.assertThat(callback.getMessage()).isEqualTo("message");
    }

    @Test
    public void testgetSuccess(){
        Assertions.assertThat(callback.getSuccess()).isEqualTo(true);
    }

    @Test
    public void testgetPayload(){
        Assertions.assertThat(callback.getPayload()).isEqualTo("payload");
    }

}