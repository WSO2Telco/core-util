package com.wso2telco.utils.exception;

import com.wso2telco.core.dbutils.exception.GenaralError;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GenaralErrorTest {


    @Test
    public void testMessage_whenCorrectValues() {
        Assertions.assertThat(com.wso2telco.utils.exception.GenaralError.UNDEFINED.getMessage()).isEqualTo("Undefined Error");
        Assertions.assertThat(com.wso2telco.utils.exception.GenaralError.INTERNAL_SERVER_ERROR.getMessage()).isEqualTo("Internal Server Error");

    }

    @Test
    public void testMessage_whenWrongValues() {
        Assertions.assertThat(com.wso2telco.utils.exception.GenaralError.UNDEFINED.getMessage()).isNotEqualTo("Undefined Error_wrong");
        Assertions.assertThat(com.wso2telco.utils.exception.GenaralError.INTERNAL_SERVER_ERROR.getMessage()).isNotEqualTo("Internal Server Error_wrong");

    }

    @Test
    public void testGetCode_whenCorrectValues() {
        Assertions.assertThat(com.wso2telco.utils.exception.GenaralError.UNDEFINED.getCode()).isEqualTo("CORE0001");
        Assertions.assertThat(com.wso2telco.utils.exception.GenaralError.INTERNAL_SERVER_ERROR.getCode()).isEqualTo("CORE0299");

    }


    @Test
    public void testGetCode_whenWongValues() {
        Assertions.assertThat(com.wso2telco.utils.exception.GenaralError.UNDEFINED.getCode()).isNotEqualTo("CORE0001_wrong");
        Assertions.assertThat(com.wso2telco.utils.exception.GenaralError.INTERNAL_SERVER_ERROR.getCode()).isNotEqualTo("CORE0299_wrong");

    }
}