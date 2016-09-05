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
package com.wso2telco.core.mnc.resolver;

import java.util.List;
import com.wso2telco.core.mnc.resolver.MCCConfiguration.MobileCountryCodes.Mcc;
import com.wso2telco.core.mnc.resolver.dnsssl.DNSSSLQueryClient;
import com.wso2telco.core.mnc.resolver.mncrange.MNCRangeCheck;
import com.wso2telco.core.mnc.resolver.mncrange.McnRangeDbUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

 
// TODO: Auto-generated Javadoc
/**
 * The Class MNCQueryClient.
 */
public class MNCQueryClient {

    private static final Log log = LogFactory.getLog(MNCQueryClient.class);

    /**
     * Query network.
     *
     * @param countryCode the country code
     * @param endUser the end user
     * @return the string
     * @throws MobileNtException the mobile nt exception
     */
    public String QueryNetwork(String countryCode, String endUser) throws MobileNtException {

	log.debug("MNCQueryClient : QueryNetwork : " + countryCode+","+endUser);

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
        
	log.debug("MNCQueryClient : QueryNetwork : getStage :"+mcc.getStage());


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
            String pfapiMnc = networkprovider.queryNetwork(String.valueOf(mcc.getCallingCode()), endUser.substring(mcc.getCallingCode().length()) );
            log.debug("MNCQueryClient : QueryNetwork : pfapiMnc :"+pfapiMnc);
            
            if (pfapiMnc != null) {
                MobileNetwork = McnRangeDbUtil.getMncBrand(String.valueOf(mcc.getCode()), pfapiMnc);
            }


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

    public static void main(String arg[]) {
        
        //IProviderNetwork networkprovider = new DNSSSLQueryClient();
        try {
            String pfapiMnc = new DNSSSLQueryClient().queryNetworkStandalone(String.valueOf(arg[0]), arg[1].substring(arg[0].length() ) );
            System.out.println("Mnc:"+pfapiMnc);
        } catch (Exception ex) {
            Logger.getLogger(MNCQueryClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
