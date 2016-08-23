/*******************************************************************************
 * Copyright  (c) 2015-2016, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 * 
 * WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.wso2telco.core.pcrservice.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

// TODO: Auto-generated Javadoc
/**
 * The Interface PersistableApplication.
 */
public interface PersistableApplication {

	/**
	 * Insert application.
	 *
	 * @param appid the appid
	 * @return the int
	 */
	@SqlUpdate("INSERT INTO pctapplication ( appid ) VALUES (:appid)")
	public int insertApplication(@Bind("appid") String appid);

	/**
	 * Select application.
	 *
	 * @param appid the appid
	 * @return true, if successful
	 */
	@SqlQuery("select 1 from pctapplication where appid = :appid")
	public boolean checkApplicationExists(@Bind("appid") String appid);
	
	/**
	 * Check application active.
	 *
	 * @param useruuid the useruuid
	 * @return true, if successful
	 */
	@SqlQuery("select isactive from pctapplication where appid = :appid")
	public boolean checkApplicationActive(@Bind("userid") String useruuid);
	
	/**
	 * Sets the application active.
	 *
	 * @param useruuid the new application active
	 */
	@SqlQuery("UPDATE pctapplication SET isactive = 1 WHERE appid = :appid")
	public void setApplicationActive(@Bind("userid") String useruuid);
	
	/**
	 * Sets the application inactive.
	 *
	 * @param useruuid the new application inactive
	 */
	@SqlQuery("UPDATE pctapplication SET isactive = 0 WHERE appid = :appid")
	public void setApplicationInactive(@Bind("userid") String useruuid);
}
