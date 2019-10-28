package com.wso2telco.core.dbutils.exception;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PolicyErrorTest {

    @Test
    public void testMessage_whenCorrectValues() {
        Assertions.assertThat(PolicyError.TOO_MANY_ADDRESSES_SPECIFIED.getMessage()).isEqualTo("Too many addresses specified in message part %1");
        Assertions.assertThat(PolicyError.DUPLICATED_ADDRESS.getMessage()).isEqualTo("Duplicated addresses");
        Assertions.assertThat(PolicyError.NO_VALID_SERVICES_AVAILABLE.getMessage()).isEqualTo("No Valid Services Available");

    }

    @Test
    public void testMessage_whenWrongValues() {
        Assertions.assertThat(PolicyError.TOO_MANY_ADDRESSES_SPECIFIED.getMessage()).isNotEqualTo("Too many addresses specified in message part %1 _wrong");
        Assertions.assertThat(PolicyError.DUPLICATED_ADDRESS.getMessage()).isNotEqualTo("Duplicated addresses _wrong");
        Assertions.assertThat(PolicyError.NO_VALID_SERVICES_AVAILABLE.getMessage()).isNotEqualTo("No Valid Services Available _wrong");

    }

    @Test
    public void testGetCode_whenCorrectValues() {
        Assertions.assertThat(PolicyError.TOO_MANY_ADDRESSES_SPECIFIED.getCode()).isEqualTo("POL0003");
        Assertions.assertThat(PolicyError.DUPLICATED_ADDRESS.getCode()).isEqualTo("POL0013");
        Assertions.assertThat(PolicyError.NO_VALID_SERVICES_AVAILABLE.getCode()).isEqualTo("POL0014");

    }

    @Test
    public void testGetCode_whenWrongValues() {
        Assertions.assertThat(PolicyError.TOO_MANY_ADDRESSES_SPECIFIED.getCode()).isNotEqualTo("POL0003 _wrong");
        Assertions.assertThat(PolicyError.DUPLICATED_ADDRESS.getCode()).isNotEqualTo("POL0013 _wrong");
        Assertions.assertThat(PolicyError.NO_VALID_SERVICES_AVAILABLE.getCode()).isNotEqualTo("POL0014 _wrong");

    }
}