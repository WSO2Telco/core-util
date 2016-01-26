/*
 * IProviderNetwork.java
 * Nov 17, 2014  11:00:37 AM
 * Roshan.Saputhanthri
 *
 * Copyright (C) Dialog Axiata PLC. All Rights Reserved.
 */

package com.axiata.dialog.mife.mnc.resolver;

/**
 * <TO-DO> <code>IProviderNetwork</code>
 * @version $Id: IProviderNetwork.java,v 1.00.000
 */
public interface IProviderNetwork {

    public String queryNetwork(String countryCode, String endUser) throws MobileNtException;
    
}
