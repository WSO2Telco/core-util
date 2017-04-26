/*******************************************************************************
 * Copyright  (c) 2015-2016, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 *
 * WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.wso2telco.core.mnc.resolver.mncrange;

import java.util.List;

import com.wso2telco.core.mnc.resolver.IProviderNetwork;
import com.wso2telco.core.mnc.resolver.MobileNtException;
import com.wso2telco.core.mnc.resolver.NumberRange;


// TODO: Auto-generated Javadoc

/**
 * The Class MNCRangeCheck.
 */
public class MNCRangeCheck implements IProviderNetwork {

    @Override
    public String queryNetwork(String countryCode, String endUser) throws MobileNtException {

        String fmtendUser = trimNumber(endUser);
        List<NumberRange> mccranges = McnRangeDbUtil.getMccNumberRanges(countryCode);

        String operatorNetwork = null;

        for (NumberRange m : mccranges) {
            if (isInRange(m.getRangefrom(), m.getRangeto(), Long.parseLong(fmtendUser))) {
                operatorNetwork = m.getBrand();
                break;
            }
        }

        return operatorNetwork;
    }

    /**
     * Checks if is in range.
     *
     * @param lowerBound the lower bound
     * @param upperBound the upper bound
     * @param value      the value
     * @return true, if is in range
     */
    public static boolean isInRange(long lowerBound, long upperBound, long value) {
        return (lowerBound <= value && value <= upperBound);
    }

    /**
     * Trim number.
     *
     * @param mobile the mobile
     * @return the string
     */
    public static String trimNumber(String mobile) {
        return mobile.replace("tel", "").replace(":", "").replace("+", "");
    }
}
