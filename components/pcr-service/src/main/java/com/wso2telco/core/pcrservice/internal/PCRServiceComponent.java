package com.wso2telco.core.pcrservice.internal;

import com.wso2telco.core.pcrservice.PCRGeneratable;
import com.wso2telco.core.pcrservice.persistable.UUIDPCRGenarator;
import org.osgi.service.component.ComponentContext;

/**
 * @scr.component name="com.wso2telco.core.pcrservice.internal.PCRServiceComponent" immediate="true"
 */
public class PCRServiceComponent {

    protected void activate(ComponentContext componentContext) {
        //do nothing
        PCRGeneratable pcrGeneratable = new UUIDPCRGenarator();
        componentContext.getBundleContext().registerService(PCRGeneratable.class, pcrGeneratable, null);
    }

    protected void deactivate(ComponentContext componentContext) {
        //do nothing
    }
}
