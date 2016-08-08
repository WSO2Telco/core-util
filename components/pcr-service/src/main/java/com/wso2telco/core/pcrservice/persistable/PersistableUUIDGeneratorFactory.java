package com.wso2telco.core.pcrservice.persistable;

import com.wso2telco.core.pcrservice.PCRGeneratable;

public class PersistableUUIDGeneratorFactory {
public PCRGeneratable createGenarator(){
	return new UUIDPCRGenarator();
}
}
