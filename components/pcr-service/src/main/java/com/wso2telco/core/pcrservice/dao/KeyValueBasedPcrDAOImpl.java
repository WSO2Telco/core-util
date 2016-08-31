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

import java.util.ArrayList;
import java.util.List;

import com.wso2telco.core.pcrservice.exception.PCRException;
import com.wso2telco.core.pcrservice.model.RequestDTO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

// TODO: Auto-generated Javadoc
/**
 * The Class KeyValueBasedPcrDAOImpl.
 */
public class KeyValueBasedPcrDAOImpl implements PersistablePcr {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wso2telco.core.pcrservice.dao.PersistablePcr#createNewPcrEntry(com.
	 * wso2telco.core.pcrservice.model.UserAssignmentDTO)
	 */
	@Override
	public void createNewPcrEntry(RequestDTO requestDTO, String pcr) throws PCRException {
		Jedis jedis = RedisUtil.getInstance();
		jedis.set(requestDTO.getUserId() + ":" + requestDTO.getSectorId() + ":" + requestDTO.getAppId(), pcr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wso2telco.core.pcrservice.dao.PersistablePcr#getExistingPCR(com.
	 * wso2telco.core.pcrservice.model.UserAssignmentDTO)
	 */
	@Override
	public String getExistingPCR(RequestDTO requestDTO) throws PCRException {
		Jedis jedis = RedisUtil.getInstance();
		String pcr = null;
		pcr = jedis.get(requestDTO.getUserId() + ":" + requestDTO.getSectorId() + ":" + requestDTO.getAppId());
		return pcr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wso2telco.core.pcrservice.dao.PersistablePcr#getApplicationIds(java.
	 * lang.String)
	 */
	@Override
	public List<String> getRelatedApplicationIdList(String sectorId) throws PCRException {

		List<String> applicationIds = new ArrayList<String>();
		Jedis jedis = RedisUtil.getInstance();
		ScanParams params = new ScanParams();
		params.match(sectorId + "*");
		ScanResult<String> scanResult = jedis.scan("0", params);
		List<String> keys = scanResult.getResult();
		String nextCursor = scanResult.getStringCursor();

		while (true) {
			for (String key : keys) {
				String[] keyAry = key.split(":");
				applicationIds.add(keyAry[1]);
			}

			if (nextCursor.equals("0")) {
				break;
			}

			scanResult = jedis.scan(nextCursor, params);
			nextCursor = scanResult.getStringCursor();
			keys = scanResult.getResult();
		}
		return applicationIds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wso2telco.core.pcrservice.dao.PersistablePcr#checkIsRelated(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public boolean checkIsRelated(String sectorId, String appId) throws PCRException {
		Jedis jedis = RedisUtil.getInstance();
		boolean related = jedis.get(sectorId + ":" + appId).equals("true");
		return related;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wso2telco.core.pcrservice.dao.PersistablePcr#createNewSPEntry(java.
	 * lang.String, java.lang.String, boolean)
	 */
	@Override
	public void createNewSPEntry(String sectorId, String appId, boolean isRelated) throws PCRException {
		Jedis jedis = RedisUtil.getInstance();
		jedis.set(sectorId + ":" + appId, String.valueOf(isRelated));
	}

}
