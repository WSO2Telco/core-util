package com.wso2telco.core.dbutils.exception;

import com.wso2telco.dbutils.Operator;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import static org.testng.Assert.*;

public class GenaralErrorTest {



    @Test
    public void testMessage_whenCorrectValues() {
       Assertions.assertThat(GenaralError.UNDEFINED.getMessage()).isEqualTo("Undefined Error");
        Assertions.assertThat(GenaralError.AUTH_HEADER_NULL.getMessage()).isEqualTo("Auth Header is null");
        Assertions.assertThat(GenaralError.INTERNAL_SERVER_ERROR.getMessage()).isEqualTo("Internal Server Error");
        Assertions.assertThat(GenaralError.INVALID_AUTH_HEADER.getMessage()).isEqualTo("Auth Header needs to be Basic encodeBase64(username:password)");

    }

    @Test
    public void testMessage_whenWrongValues() {
        Assertions.assertThat(GenaralError.UNDEFINED.getMessage()).isNotEqualTo("Undefined Error_wrong");
        Assertions.assertThat(GenaralError.AUTH_HEADER_NULL.getMessage()).isNotEqualTo("Auth Header is null_wrong");
        Assertions.assertThat(GenaralError.INTERNAL_SERVER_ERROR.getMessage()).isNotEqualTo("Internal Server Error_wrong");
        Assertions.assertThat(GenaralError.INVALID_AUTH_HEADER.getMessage()).isNotEqualTo("Auth Header needs to be Basic encodeBase64(username:password)_wrong");

    }

    @Test
    public void testCode_whenCorrectValues() {
        Assertions.assertThat(GenaralError.UNDEFINED.getCode()).isEqualTo("CORE0001");
        Assertions.assertThat(GenaralError.AUTH_HEADER_NULL.getCode()).isEqualTo("CORE0300");
        Assertions.assertThat(GenaralError.INTERNAL_SERVER_ERROR.getCode()).isEqualTo("CORE0299");
        Assertions.assertThat(GenaralError.INVALID_AUTH_HEADER.getCode()).isEqualTo("CORE0301");

    }
    @Test
    public void testCode_whenWrongValues() {
        Assertions.assertThat(GenaralError.UNDEFINED.getCode()).isNotEqualTo("CORE0001 _wrong");
        Assertions.assertThat(GenaralError.AUTH_HEADER_NULL.getCode()).isNotEqualTo("CORE0300 _wrong");
        Assertions.assertThat(GenaralError.INTERNAL_SERVER_ERROR.getCode()).isNotEqualTo("CORE0299 _wrong");
        Assertions.assertThat(GenaralError.INVALID_AUTH_HEADER.getCode()).isNotEqualTo("CORE0301 _wrong");

    }
}