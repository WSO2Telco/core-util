package com.wso2telco.core.pcrservice.dao;

import java.sql.SQLException;

import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import com.wso2telco.core.pcrservice.model.UserAssignmentDTO;

public abstract class UserAssignmentHandler {

	@CreateSqlObject
	abstract PersistableUserAssignment  UserAssignmentPersister();
	
	/**
	 * Creates the new user.
	 *
	 * @param userAssignmentDTO the user DTO
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	public int createNewUserAssignment(UserAssignmentDTO userAssignmentDTO) throws SQLException{		
		int newUserAssignmentDid=0;		
		newUserAssignmentDid = UserAssignmentPersister().insertUserAssignment(userAssignmentDTO.getAppdid(),userAssignmentDTO.getUserdid(),userAssignmentDTO.getSectordid(),userAssignmentDTO.getPcr());
		return newUserAssignmentDid;		
	}
	
}
