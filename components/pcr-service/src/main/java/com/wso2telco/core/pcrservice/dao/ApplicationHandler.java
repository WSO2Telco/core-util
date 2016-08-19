package com.wso2telco.core.pcrservice.dao;

import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import com.wso2telco.core.pcrservice.model.ApplicationDTO;

public abstract class ApplicationHandler {

	@CreateSqlObject
	abstract PersistableApplication  applicationPersister();
	
	public int createNewApplication(ApplicationDTO applicationDTO){		
		int newApplicationDid=0;		
		newApplicationDid = applicationPersister().insert(applicationDTO.getAppId());
		return newApplicationDid;		
	}
	
	public boolean checkApplicationExists(ApplicationDTO applicationDTO){
		boolean exists = false;
		exists = applicationPersister().select(applicationDTO.getAppId());
		return exists;
	}
}
