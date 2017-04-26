package com.wso2telco.core.pcrservice.internal;

import com.wso2telco.core.pcrservice.PCRGeneratable;
import com.wso2telco.core.pcrservice.persistable.UUIDPCRGenarator;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component(name = "com.wso2telco.core.pcrservice.internal.PCRServiceComponen", immediate = true)
public class PCRServiceComponent {

	 @Activate
    protected void activate(ComponentContext componentContext) {
        //do nothing
        PCRGeneratable pcrGeneratable = new UUIDPCRGenarator();
        componentContext.getBundleContext().registerService(PCRGeneratable.class, pcrGeneratable, null);
    }

	@Deactivate
    protected void deactivate(ComponentContext componentContext) {
        //do nothing
    }
}
