package com.wso2telco.core.pcrservice.persistable;

import com.wso2telco.core.pcrservice.PCRGeneratable;
import com.wso2telco.core.pcrservice.Returnable;
import com.wso2telco.core.pcrservice.exception.PCRException;
import com.wso2telco.core.pcrservice.model.RequestDTO;

class UUIDPCRGenarator implements PCRGeneratable{

	String uuid;
	
	@Override
	public Returnable getPCR(RequestDTO dto) throws PCRException {
		// TODO Auto-generated method stub
		return new Returnable(){

			@Override
			public String getID() {
				// TODO Auto-generated method stub
				return uuid;
			}
			
		};
	}

}
