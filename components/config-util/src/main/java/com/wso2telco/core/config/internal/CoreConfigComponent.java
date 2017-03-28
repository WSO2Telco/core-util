/*******************************************************************************
 * Copyright (c) 2015-2016, WSO2.Telco Inc. (http://www.wso2telco.com)
 *
 * All Rights Reserved. WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
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

package com.wso2telco.core.config.internal;

import com.wso2telco.core.config.service.ConfigurationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component(name = "configuration.component", immediate = true)
public class CoreConfigComponent {
    private static final Log log = LogFactory.getLog(CoreConfigComponent.class);
    private ServiceRegistration serviceRegistration;
    private ConfigurationService configurationService;

    @Activate
    protected void activate(ComponentContext context) throws Exception {

        try {
            //configurationService = new ConfigurationServiceImpl();
            //serviceRegistration = context.getBundleContext().registerService(ConfigurationService.class.getName(),
            // configurationService, null);
        } catch (Throwable t) {
            String errorMsg = "Error in registering ConfigurationService";
            log.error(errorMsg, t);
            throw new Exception(errorMsg, t);
        }
    }

    @Deactivate
    protected void deactivate(ComponentContext ctx) {

        //configurationService.unregisterDataHolder();
        //serviceRegistration.unregister();

        if (log.isDebugEnabled()) {
            log.info("Core Config Component is deactivated");
        }
    }
}
