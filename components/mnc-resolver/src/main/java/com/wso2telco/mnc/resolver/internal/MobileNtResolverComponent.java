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
package com.wso2telco.mnc.resolver.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;

import com.wso2telco.mnc.resolver.ConfigLoader;
import com.wso2telco.mnc.resolver.DataHolder;


 

// TODO: Auto-generated Javadoc
/**
 * The Class MobileNtResolverComponent.
 */
public class MobileNtResolverComponent {

    /** The Constant log. */
    private static final Log log = LogFactory.getLog(MobileNtResolverComponent.class);

    /**
     * Activate.
     *
     * @param ctx the ctx
     */
    protected void activate(ComponentContext ctx) {
        try {
            DataHolder.getInstance().setMobileCountryConfig(ConfigLoader.getInstance().getMobileCountryConfig());
            log.debug("Mobile Network Resover configuration is activated ");
        } catch (Throwable e) {
            log.error("Mobile Network Resover configuration ", e);
        }
    }

    /**
     * Deactivate.
     *
     * @param ctx the ctx
     */
    protected void deactivate(ComponentContext ctx) {
        if (log.isDebugEnabled()) {
            log.info("Mobile Network Resover configuration is deactivated");
        }        
    }


}
