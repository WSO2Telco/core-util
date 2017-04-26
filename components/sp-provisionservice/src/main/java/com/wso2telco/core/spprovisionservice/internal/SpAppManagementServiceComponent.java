package com.wso2telco.core.spprovisionservice.internal;

import com.wso2telco.core.spprovisionservice.external.admin.service.OauthAdminService;
import com.wso2telco.core.spprovisionservice.external.admin.service.SpAppManagementService;
import com.wso2telco.core.spprovisionservice.external.admin.service.impl.SpAppManagementServiceImpl;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.apache.felix.scr.annotations.*;

@Component(name = "com.wso2telco.core.spprovisionservice.internal.SpAppManagementServiceComponent", immediate = true)
public class SpAppManagementServiceComponent {

    @Activate
    protected void activate(ComponentContext componentContext) {
        // do nothing
        SpAppManagementService appManagementService = new SpAppManagementServiceImpl();
        componentContext.getBundleContext().registerService(SpAppManagementService.class, appManagementService, null);
    }

    @Deactivate
    protected void deactivate(ComponentContext componentContext) {
        // do nothing
    }
}