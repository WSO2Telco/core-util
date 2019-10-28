package com.wso2telco.core.dbutils.exception;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ServiceErrorTest {


    @Test
    public void testGetMessage_whenCorrectValues() {
        Assertions.assertThat(ServiceError.SERVICE_ERROR_OCCURED.getMessage()).isEqualTo("A service error occurred. Error code is %1");
        Assertions.assertThat(ServiceError.INVALID_INPUT_VALUE.getMessage()).isEqualTo("Invalid input value for message part %1");
        Assertions.assertThat(ServiceError.INVALID_ADDRESS.getMessage()).isEqualTo("No valid addresses provided in message part %1");
        Assertions.assertThat(ServiceError.NO_RESOURCES.getMessage()).isEqualTo("No resources");
    }

    @Test
    public void testGetMessage_whenWrongValues() {
        Assertions.assertThat(ServiceError.SERVICE_ERROR_OCCURED.getMessage()).isNotEqualTo("A service error occurred. Error code is %1 _positive");
        Assertions.assertThat(ServiceError.INVALID_INPUT_VALUE.getMessage()).isNotEqualTo("Invalid input value for message part %1 _positive");
        Assertions.assertThat(ServiceError.INVALID_ADDRESS.getMessage()).isNotEqualTo("No valid addresses provided in message part %1 _positive");
        Assertions.assertThat(ServiceError.NO_RESOURCES.getMessage()).isNotEqualTo("No resources _postie");
    }

    @Test
    public void testGetCode_whenCorrectValues() {
        Assertions.assertThat(ServiceError.SERVICE_ERROR_OCCURED.getCode()).isEqualTo("SVC0001");
        Assertions.assertThat(ServiceError.INVALID_INPUT_VALUE.getCode()).isEqualTo("SVC0002");
        Assertions.assertThat(ServiceError.INVALID_ADDRESS.getCode()).isEqualTo("SVC0004");
        Assertions.assertThat(ServiceError.NO_RESOURCES.getCode()).isEqualTo("SVC1000");

    }
    @Test
    public void testGetCode_whenWrongValues() {
        Assertions.assertThat(ServiceError.SERVICE_ERROR_OCCURED.getCode()).isNotEqualTo("SVC0001 _postive");
        Assertions.assertThat(ServiceError.INVALID_INPUT_VALUE.getCode()).isNotEqualTo("SVC0002 _postive");
        Assertions.assertThat(ServiceError.INVALID_ADDRESS.getCode()).isNotEqualTo("SVC0004 _postive");
        Assertions.assertThat(ServiceError.NO_RESOURCES.getCode()).isNotEqualTo("SVC1000 _positive");

    }
}