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
/*
 * IProviderNetwork.java
 * Nov 17, 2014  11:00:37 AM
 * Roshan.Saputhanthri
 *
 * Copyright (C) Dialog Axiata PLC. All Rights Reserved.
 */

package com.wso2telco.mnc.resolver;

// TODO: Auto-generated Javadoc
/**
 * <TO-DO> <code>IProviderNetwork</code>.
 *
 * @version $Id: IProviderNetwork.java,v 1.00.000
 */
public interface IProviderNetwork {

    /**
     * Query network.
     *
     * @param countryCode the country code
     * @param endUser the end user
     * @return the string
     * @throws MobileNtException the mobile nt exception
     */
    public String queryNetwork(String countryCode, String endUser) throws MobileNtException;
    
}
