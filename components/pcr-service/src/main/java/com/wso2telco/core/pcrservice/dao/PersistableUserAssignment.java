package com.wso2telco.core.pcrservice.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface PersistableUserAssignment {

	
	@SqlUpdate("INSERT INTO pctuserassignment ( appdid, userdid, sectordid, pcr ) "
			+ "VALUES ( :appdid, :userdid, :sectordid, :pcr)")
	public int insertUserAssignment(@Bind("appdid") int appdid, @Bind("userdid") int userdid, @Bind("sectordid") int sectordid, @Bind("pcr") String pcr);
}
