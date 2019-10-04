package com.wso2telco.core.pcrservice.util;

import org.assertj.core.api.Assertions;
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
public class SectorUtilTest {

    @Test
    public void testGetSectorIdFromUrl_shouldReturnHostName_forValidCallBackUrlsWithHttp() {
        Assertions.assertThat(SectorUtil.getSectorIdFromUrl("http://www.valid-url.com")).isEqualTo("www.valid-url.com");
        Assertions.assertThat(SectorUtil.getSectorIdFromUrl("http://www.valid-url.com/rest-of-the-url")).isEqualTo("www.valid-url.com");
    }

    @Test
    public void testGetSectorIdFromUrl_shouldReturnHostName_forValidCallBackUrlsWithoutHttp() {
        Assertions.assertThat(SectorUtil.getSectorIdFromUrl("www.valid-url.com")).isEqualTo("www.valid-url.com");
        Assertions.assertThat(SectorUtil.getSectorIdFromUrl("www.valid-url.com/rest-of-the-url")).isEqualTo("www.valid-url.com");
        Assertions.assertThat(SectorUtil.getSectorIdFromUrl("valid-url.com")).isEqualTo("valid-url.com");
    }

    @Test
    public void testGetSectorIdFromUrl_shouldReturnSameInputParameter_forInvalidCallBackUrls() {
        Assertions.assertThat(SectorUtil.getSectorIdFromUrl("http://")).isEqualTo("http://");
        Assertions.assertThat(SectorUtil.getSectorIdFromUrl("")).isEqualTo("");
    }
}