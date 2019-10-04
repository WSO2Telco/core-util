package com.wso2telco.core.dbutils;

import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class OperatorendpointTest {
    private Operatorendpoint operatorendpoint;

    @BeforeTest
    public void initialtest() {
        operatorendpoint = new Operatorendpoint(2, "operatercode", "http//localhost", "/api");
    }

    @Test
    public void testgetOperatorid_whenoperateridcorrect() {
        Assertions.assertThat(operatorendpoint.getApi()).isEqualTo("http//localhost");
    }

    @Test
    public void testgetEndpoint() {
        Assertions.assertThat(operatorendpoint.getEndpoint()).isEqualTo("/api");
    }

    @Test
    public void testgetId() {
        Assertions.assertThat(operatorendpoint.getOperatorid()).isEqualTo(2);
    }

    @Test
    public void testsetoperatoecode() {
        Assertions.assertThat(operatorendpoint.getOperatorcode()).isEqualTo("operatercode");
    }
}
