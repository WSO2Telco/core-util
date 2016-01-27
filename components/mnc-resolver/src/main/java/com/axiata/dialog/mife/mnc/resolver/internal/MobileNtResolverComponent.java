package com.axiata.dialog.mife.mnc.resolver.internal;

import com.axiata.dialog.mife.mnc.resolver.ConfigLoader;
import com.axiata.dialog.mife.mnc.resolver.DataHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;


/**
 * @scr.component name="com.axiata.dialog.mnc.component" immediate="true"
 */

public class MobileNtResolverComponent {

    private static final Log log = LogFactory.getLog(MobileNtResolverComponent.class);

    protected void activate(ComponentContext ctx) {
        try {
            DataHolder.getInstance().setMobileCountryConfig(ConfigLoader.getInstance().getMobileCountryConfig());
            log.debug("Mobile Network Resover configuration is activated ");
        } catch (Throwable e) {
            log.error("Mobile Network Resover configuration ", e);
        }
    }

    protected void deactivate(ComponentContext ctx) {
        if (log.isDebugEnabled()) {
            log.info("Mobile Network Resover configuration is deactivated");
        }        
    }


}
