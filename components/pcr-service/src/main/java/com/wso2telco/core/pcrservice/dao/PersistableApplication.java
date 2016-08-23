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
	 * Insert.
	 *
	 * @param appid the appid
	 * @return the int
	 */
	@SqlUpdate("INSERT INTO pctapplication ( appid ) VALUES (:appid)")
	public int insert(@Bind("appid") String appid);

	/**
	 * Select.
	 *
	 * @param appid the appid
	 * @return true, if successful
	 */
	@SqlQuery("select 1 from pctapplication where appid = :appid")
	public boolean select(@Bind("appid") String appid);
}
