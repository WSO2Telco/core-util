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
package com.wso2telco.mnc.resolver;

import java.util.List;

import com.wso2telco.mnc.resolver.MCCConfiguration.MobileCountryCodes.Mcc;
import com.wso2telco.mnc.resolver.dnsssl.DNSSSLQueryClient;
import com.wso2telco.mnc.resolver.mncrange.MNCRangeCheck;

 
// TODO: Auto-generated Javadoc
/**
 * The Class MNCQueryClient.
 */
public class MNCQueryClient {

    /**
     * Query network.
     *
     * @param countryCode the country code
     * @param endUser the end user
     * @return the string
     * @throws MobileNtException the mobile nt exception
     */
    public String QueryNetwork(String countryCode, String endUser) throws MobileNtException {

        MCCConfiguration MCCconfig = DataHolder.getInstance().getMobileCountryConfig();
        String MobileNetwork = null;

        List<Mcc> lstMcc = MCCconfig.getMobileCountryCodes().getMcc();
        Mcc mcc = null;

        if ((countryCode == null) || (countryCode.isEmpty())) {
            for (Mcc m : lstMcc) {
                String mprefix = m.getCallingCode();
                if (endUser.startsWith(mprefix) ) {
                    mcc = m;
                    break;
                }
            }            
            
        } else {
            for (Mcc m : lstMcc) {
                String mcountry = String.valueOf(m.getCode());
                if (mcountry.equalsIgnoreCase(countryCode)) {
                    mcc = m;
                    break;
                }
            }
        }
        
        if (mcc.getStage() == 0) {
            //only one operator for the country
            MobileNetwork = mcc.getDefaultMnc();
        } else if (mcc.getStage() == 1) {
            //1 range check only 
            IProviderNetwork networkprovider = new MNCRangeCheck();
            MobileNetwork = networkprovider.queryNetwork(String.valueOf(mcc.getCode()), endUser);
        } else if (mcc.getStage() == 2) {
            //path finder
            IProviderNetwork networkprovider = new DNSSSLQueryClient();
            MobileNetwork = networkprovider.queryNetwork(String.valueOf(mcc.getCode()), endUser);

        } else if (mcc.getStage() == 3) {
            //range checker with fallback
            IProviderNetwork networkprovider = new MNCRangeCheck();
            MobileNetwork = networkprovider.queryNetwork(String.valueOf(mcc.getCode()), endUser);

            if (MobileNetwork == null) {
                MobileNetwork = networkprovider.queryNetwork(String.valueOf(mcc.getCode()), endUser);
            }
        }

        return MobileNetwork;
    }
}
