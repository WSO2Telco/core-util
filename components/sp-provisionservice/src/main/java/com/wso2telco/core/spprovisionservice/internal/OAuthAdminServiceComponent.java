package com.wso2telco.core.spprovisionservice.internal;

import com.wso2telco.core.spprovisionservice.external.admin.service.OauthAdminService;
import com.wso2telco.core.spprovisionservice.external.admin.service.impl.OauthAdminServiceImpl;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.apache.felix.scr.annotations.*;

@Component(name = "com.wso2telco.core.spprovisionservice.internal.OAuthAdminServiceComponent", immediate = true)
public class OAuthAdminServiceComponent {

    @Activate
    protected void activate(ComponentContext componentContext) {
        // do nothing
        OauthAdminService adminService = new OauthAdminServiceImpl();
        componentContext.getBundleContext().registerService(OauthAdminService.class, adminService, null);
    }

    @Deactivate
    protected void deactivate(ComponentContext componentContext) {
        // do nothing
    }
}