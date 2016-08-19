package com.wso2telco.core.pcrservice.dao;

import java.sql.SQLException;

import org.skife.jdbi.v2.DBI;

import com.wso2telco.core.pcrservice.model.ApplicationDTO;

public class ApplicationDAO {

	public void saveNewApplication(ApplicationDTO applicationDTO) throws SQLException{
		DBI dbi = JDBIUtil.getInstance();
		ApplicationHandler ApplicationHandler = dbi.onDemand(ApplicationHandler.class);
		
		ApplicationHandler.createNewApplication(applicationDTO);
	}
}
