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
import com.axiata.dialog.mife.mnc.resolver.mncrange.McnRangeDbUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <TO-DO>
 * <code>MNCQueryClient</code>
 *
 * @version $Id: MNCQueryClient.java,v 1.00.000
 */
public class MNCQueryClient {

    private static final Log log = LogFactory.getLog(MNCQueryClient.class);
    
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
