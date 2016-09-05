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

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wso2telco.core.pcrservice.exception.PCRException;
import com.wso2telco.core.pcrservice.model.RequestDTO;
import com.wso2telco.core.pcrservice.model.UserAssignmentDTO;
import com.wso2telco.core.pcrservice.util.Tables;

// TODO: Auto-generated Javadoc
/**
 * The Class RelationBasedPcrDAOImpl.
 */
public class RelationBasedPcrDAOImpl implements PersistablePcr {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(RelationBasedPcrDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.wso2telco.core.pcrservice.dao.PersistablePcr#createNewPcrEntry(com.wso2telco.core.pcrservice.model.UserAssignmentDTO)
	 */
	public void createNewPcrEntry(UserAssignmentDTO userAssignmentDTO) throws PCRException {
		DBI dbi = JDBIUtil.getInstance();
		UserAssignmentHandler userAssignmentHandler = dbi.onDemand(UserAssignmentHandler.class);
		try {
			userAssignmentHandler.createNewUserAssignment(userAssignmentDTO);
		} catch (SQLException e) {
			log.error("Could not create new PCR for given userdid : " + userAssignmentDTO.getUserdid() + ",appdid : "
					+ userAssignmentDTO.getAppdid());
			throw new PCRException(e.toString());
		}
	}

	/* (non-Javadoc)
	 * @see com.wso2telco.core.pcrservice.dao.PersistablePcr#getExistingPCR(com.wso2telco.core.pcrservice.model.UserAssignmentDTO)
	 */
	public String getExistingPCR(UserAssignmentDTO userAssignmentDTO) throws PCRException {
		DBI dbi = JDBIUtil.getInstance();
		Handle h = dbi.open();
		String pcr = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT pcr ");
			sb.append("from ").append(Tables.TABLE_PCTAPPLICATION.toString()).append(" A ");
			sb.append("INNER JOIN ").append(Tables.TABLE_PCTUSERASSIGNMENT.toString()).append(" T ");
			sb.append("ON A.appdid = T.appdid ");
			sb.append("INNER JOIN ").append(Tables.TABLE_PCTUSER.toString()).append(" U ");
			sb.append("ON T.userdid = U.userdid ");
			sb.append("WHERE A.appdid = :appdid ");
			sb.append("AND U.userdid = :userdid ");

			List<Map<String, Object>> resultSet = h.createQuery(sb.toString())
					.bind("appdid", userAssignmentDTO.getAppdid()).bind("userdid", userAssignmentDTO.getUserdid())
					.list();

			if (resultSet.size() == 0) {
				return null;
			}
			pcr = (String) resultSet.get(0).get("pcr");
		} catch (Exception e) {
			log.error("Could not get PCR for given userdid : " + userAssignmentDTO.getUserdid() + ",appdid : "
					+ userAssignmentDTO.getAppdid());
			throw new PCRException(e.toString());
		} finally {
			h.close();
		}
		return pcr;
	}

	
	/* (non-Javadoc)
	 * @see com.wso2telco.core.pcrservice.dao.PersistablePcr#getApplicationIds(java.lang.String)
	 */
	@Override
	public List<String> getRelatedApplicationIdList(String sectorId) throws PCRException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wso2telco.core.pcrservice.dao.PersistablePcr#checkIsRelated(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean checkIsRelated(String sectorId, String appId) throws PCRException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wso2telco.core.pcrservice.dao.PersistablePcr#createNewSPEntry(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public void createNewSPEntry(String sectorId, String appId, boolean isRelated) throws PCRException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createNewPcrEntry(RequestDTO requestDTO, String pcr) throws PCRException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getExistingPCR(RequestDTO requestDTO) throws PCRException {
		// TODO Auto-generated method stub
		return null;
	}
}
