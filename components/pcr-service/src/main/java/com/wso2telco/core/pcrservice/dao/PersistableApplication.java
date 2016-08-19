package com.wso2telco.core.pcrservice.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface PersistableApplication {

	@SqlUpdate("INSERT INTO pctapplication ( appid ) VALUES (:appid)")
	public int insert(@Bind("appid") String appid);

	@SqlQuery("select 1 from pctapplication where appid = :appid")
	public boolean select(@Bind("appid") String appid);
}
