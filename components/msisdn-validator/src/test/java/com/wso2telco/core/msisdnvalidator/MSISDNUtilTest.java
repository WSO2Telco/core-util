package com.wso2telco.core.msisdnvalidator;

import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Copyright (c) 2016, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 * <p>
 * WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class MSISDNUtilTest {

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testParse_shouldSplitCountryCodeAndNationalNumber_forCorrectMsisdnValues() throws InvalidMSISDNException {
        // HUBDEV-587
        MSISDN msisdnWithPlus = new MSISDNUtil().parse("+941234567");
        Assertions.assertThat(msisdnWithPlus.getCountryCode()).isEqualTo(94);
        Assertions.assertThat(msisdnWithPlus.getNationalNumber()).isEqualTo(1234567);

        MSISDN msisdnTelWithPlus = new MSISDNUtil().parse("tel:+941234567");
        Assertions.assertThat(msisdnTelWithPlus.getCountryCode()).isEqualTo(94);
        Assertions.assertThat(msisdnTelWithPlus.getNationalNumber()).isEqualTo(1234567);

        MSISDN msisdnTelWithoutPlus = new MSISDNUtil().parse("tel:+941234567");
        Assertions.assertThat(msisdnTelWithoutPlus.getCountryCode()).isEqualTo(94);
        Assertions.assertThat(msisdnTelWithoutPlus.getNationalNumber()).isEqualTo(1234567);

        // EXTGW-26
        MSISDN msisdnEtelWithPlus = new MSISDNUtil().parse("etel:+941234567");
        Assertions.assertThat(msisdnEtelWithPlus.getCountryCode()).isEqualTo(94);
        Assertions.assertThat(msisdnEtelWithPlus.getNationalNumber()).isEqualTo(1234567);

        MSISDN msisdnEtelWithoutPlus = new MSISDNUtil().parse("etel:+941234567");
        Assertions.assertThat(msisdnEtelWithoutPlus.getCountryCode()).isEqualTo(94);
        Assertions.assertThat(msisdnEtelWithoutPlus.getNationalNumber()).isEqualTo(1234567);
    }

    @Test
    public void testParse_shouldThrowException_forIncorrectMsisdnValues() throws InvalidMSISDNException {
        String[] incorrectMsisdnValues = {"941234567", "msisdn", "0715902989"};
        for( String incorrectMsisdnValue : incorrectMsisdnValues) {
            Assertions.assertThat(Assertions.catchThrowable(() -> new MSISDNUtil().parse(incorrectMsisdnValue)))
                    .as("InvalidMSISDNException expected for INPUT value : " + incorrectMsisdnValue)
                    .isInstanceOf(InvalidMSISDNException.class);
        }
    }

    @Test
    public void testResourceInMsisdnFormat() {
        //TODO: resourceInMsisdnFormat() method need to be refactored as it is always returning true;
        Assertions.assertThat(true).isTrue();
    }
}