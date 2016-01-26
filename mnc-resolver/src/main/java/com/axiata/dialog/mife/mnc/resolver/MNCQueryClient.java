/*
 * MNCQueryClient.java
 * Nov 17, 2014  10:48:39 AM
 * Roshan.Saputhanthri
 *
 * Copyright (C) Dialog Axiata PLC. All Rights Reserved.
 */
package com.axiata.dialog.mife.mnc.resolver;

import com.axiata.dialog.mife.mnc.resolver.MCCConfiguration.MobileCountryCodes.Mcc;
import com.axiata.dialog.mife.mnc.resolver.dnsssl.DNSSSLQueryClient;
import com.axiata.dialog.mife.mnc.resolver.mncrange.MNCRangeCheck;
import java.util.List;

/**
 * <TO-DO>
 * <code>MNCQueryClient</code>
 *
 * @version $Id: MNCQueryClient.java,v 1.00.000
 */
public class MNCQueryClient {

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
