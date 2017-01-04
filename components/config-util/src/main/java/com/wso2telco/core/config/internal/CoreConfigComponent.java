package com.wso2telco.core.config.internal;

import com.wso2telco.core.config.service.ConfigurationService;
import com.wso2telco.core.config.service.ConfigurationServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * Created by madusha on 1/2/17.
 */

@Component(name="configuration.service")
public class CoreConfigComponent {
    private static final Log log = LogFactory.getLog(CoreConfigComponent.class);
    private ServiceRegistration serviceRegistration;
    private ConfigurationService configurationService;

    @Activate
    protected void activate(ComponentContext context) throws Exception {

        try {
            configurationService = new ConfigurationServiceImpl();
            serviceRegistration = context.getBundleContext().registerService(ConfigurationService.class.getName(), configurationService, null);
        } catch (Throwable t) {
            String errorMsg = "Error in registering ConfigurationService";
            log.error(errorMsg, t);
            throw new Exception(errorMsg, t);
        }
    }

    @Deactivate
    protected void deactivate(ComponentContext ctx) {

        configurationService.unregisterDataHolder();
        serviceRegistration.unregister();

        if (log.isDebugEnabled()) {
            log.info("Core Config Component is deactivated");
        }
    }
}
