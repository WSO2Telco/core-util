package com.wso2telco.dbutils;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OperatorsubsTest {

        private Operatorsubs operator = null;
        @BeforeTest
        public  void init(){
            operator= Mockito.mock(Operatorsubs.class);
        }

        @Test
        public void testOperator_whenCorrectValues() {
            String  expected = "Operator";
            Mockito.when(operator.getOperator()).thenReturn(expected);
            Assert.assertEquals(operator.getOperator(), expected);

        }

    @Test
    public void testOperator_whenWrongValues() {
        String  expected = "Operator";
        Mockito.when(operator.getOperator()).thenReturn("WrongOperator");
        Assert.assertNotEquals(operator.getOperator(), expected);

    }

        @Test
        public void testDomain_whenCorrectValues() {
            String  expected = "domain";
            Mockito.when(operator.getDomain()).thenReturn(expected);
            Assert.assertEquals(operator.getDomain(), expected);
        }
    @Test
    public void testDomain_whenWrongValues() {
        String  expected = "domain";
        Mockito.when(operator.getDomain()).thenReturn("Wrong values");
        Assert.assertNotEquals(operator.getDomain(), expected);
    }
    }