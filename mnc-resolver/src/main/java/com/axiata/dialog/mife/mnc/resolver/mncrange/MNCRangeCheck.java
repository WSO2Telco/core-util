/*
 * MNCRangeCheck.java
 * Nov 17, 2014  11:09:13 AM
 * Roshan.Saputhanthri
 *
 * Copyright (C) Dialog Axiata PLC. All Rights Reserved.
 */
package com.axiata.dialog.mife.mnc.resolver.mncrange;

import com.axiata.dialog.mife.mnc.resolver.IProviderNetwork;
import com.axiata.dialog.mife.mnc.resolver.MCCConfiguration;
import com.axiata.dialog.mife.mnc.resolver.MobileNtException;
import com.axiata.dialog.mife.mnc.resolver.NumberRange;
import java.util.List;

/**
 * <TO-DO>
 * <code>MNCRangeCheck</code>
 *
 * @version $Id: MNCRangeCheck.java,v 1.00.000
 */
public class MNCRangeCheck implements IProviderNetwork {

    @Override
    public String queryNetwork(String countryCode, String endUser) throws MobileNtException {

        String fmtendUser = trimNumber(endUser);
        List<NumberRange> mccranges = McnRangeDbUtil.getMccNumberRanges(countryCode);
        
        String operatorNetwork = null;
        
        for (NumberRange m : mccranges) {
            if (isInRange(m.getRangefrom(), m.getRangeto(), Long.parseLong(fmtendUser)) ) {
                operatorNetwork = m.getBrand();
                break;                
            }
        }

        return operatorNetwork;
    }

    public static boolean isInRange(long lowerBound, long upperBound, long value) {
        return (lowerBound <= value && value <= upperBound);
    }

    public static String trimNumber(String mobile) {
        return mobile.replace("tel", "").replace(":", "").replace("+", "");
    }
}
