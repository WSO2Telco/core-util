package com.wso2telco.core.pcrservice;

import com.wso2telco.core.pcrservice.persistable.PersistableUUIDGeneratorFactory;

public class PCRFactory {
	//TODO:This need to refactor so that the facatory need to taken from propaty file
	PersistableUUIDGeneratorFactory factory =new PersistableUUIDGeneratorFactory();
	
	public PCRGeneratable getPCRGenarator(){
		return factory.createGenarator();
	}
}
