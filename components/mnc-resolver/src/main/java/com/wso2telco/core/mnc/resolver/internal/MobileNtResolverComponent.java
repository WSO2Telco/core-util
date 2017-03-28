
package com.wso2telco.core.mnc.resolver.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.osgi.service.component.ComponentContext;
import com.wso2telco.core.mnc.resolver.ConfigLoader;
import com.wso2telco.core.mnc.resolver.DataHolder;


@Component(name = "mnc.component")
public class MobileNtResolverComponent {

    private static final Log log = LogFactory.getLog(MobileNtResolverComponent.class);

    @Activate
    protected void activate(ComponentContext ctx) {
        try {
            DataHolder.getInstance().setMobileCountryConfig(ConfigLoader.getInstance().getMobileCountryConfig());
            log.debug("Mobile Network Resover configuration is activated ");
        } catch (Throwable e) {
            log.error("Mobile Network Resover configuration ", e);
        }
    }

    @Deactivate
    protected void deactivate(ComponentContext ctx) {
        if (log.isDebugEnabled()) {
            log.info("Mobile Network Resover configuration is deactivated");
        }
    }


}
