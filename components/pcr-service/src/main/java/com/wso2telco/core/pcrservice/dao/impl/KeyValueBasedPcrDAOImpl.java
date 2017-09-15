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
package com.wso2telco.core.pcrservice.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.wso2telco.core.pcrservice.dao.PersistablePcr;
import com.wso2telco.core.pcrservice.exception.PCRException;
import com.wso2telco.core.pcrservice.model.RequestDTO;
import com.wso2telco.core.pcrservice.util.RedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
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
        Jedis jedis = RedisUtil.getInstance().getResource();
        jedis.rpush(requestDTO.getUserId() + ":" + requestDTO.getSectorId(), requestDTO.getAppId() + ":" + pcr);
        jedis.close();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.wso2telco.core.pcrservice.dao.PersistablePcr#getExistingPCR(com.
     * wso2telco.core.pcrservice.model.UserAssignmentDTO)
     */
    @Override
    public String getExistingPCR(RequestDTO requestDTO) throws PCRException {
        Jedis jedis = RedisUtil.getInstance().getResource();
        String pcr = null;
        List<String> pcrList = jedis.lrange(requestDTO.getUserId() + ":" + requestDTO.getSectorId(), 0, -1);
        for (String appPcr : pcrList) {
            if (appPcr.startsWith(requestDTO.getAppId())) {
                pcr = appPcr.split(":")[1];
                break;
            }
        }
        jedis.close();
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
    public List<String> getApplicationIdList(String sectorId) throws PCRException {

        List<String> applicationIds = new ArrayList<String>();
        Jedis jedis = RedisUtil.getInstance().getResource();
        List<String> appidlist = jedis.lrange(sectorId, 0, -1);
        for (String appinfo : appidlist) {
            applicationIds.add(appinfo.split(":")[0]);
        }
        jedis.close();
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
        Jedis jedis = RedisUtil.getInstance().getResource();
        List<String> appidlist = jedis.lrange(sectorId, 0, -1);
        boolean related = true;
        for (String appinfo : appidlist) {
            if (appinfo.startsWith(appId)) {
                related = appinfo.split(":")[1].equals("true");
                break;
            }
        }
        jedis.close();
        return related;
    }

    /* (non-Javadoc)
     * @see com.wso2telco.core.pcrservice.dao.PersistablePcr#checkApplicationExists(java.lang.String, java.lang.String)
     */
    @Override
    public boolean checkApplicationExists(String sectorId, String appId) throws PCRException {
        List<String> applicationIds = new ArrayList<String>();
        Jedis jedis = RedisUtil.getInstance().getResource();
        List<String> appidlist = jedis.lrange(sectorId, 0, -1);
        for (String appinfo : appidlist) {
            applicationIds.add(appinfo.split(":")[0]);
        }
        boolean exists = applicationIds.contains(appId);
        jedis.close();
        return exists;
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
        Jedis jedis = RedisUtil.getInstance().getResource();
        jedis.rpush(sectorId, appId + ":" + isRelated);
        jedis.close();
    }

    @Override
    public List<String> getAppIdListForUserSectorCombination(String userId, String sectorId) throws PCRException {
        List<String> applicationIds = new ArrayList<String>();
        Jedis jedis = RedisUtil.getInstance().getResource();
        List<String> appInfoList = jedis.lrange(userId + ":" + sectorId, 0, -1);
        for (String appInfo : appInfoList) {
            applicationIds.add(appInfo.split(":")[0]);
        }
        jedis.close();
        return applicationIds;
    }

    @Override
    public void createNewPCRMSISDNEntry(String userId, String sectorId, String pcr) throws PCRException {

        Jedis jedis= RedisUtil.getInstance().getResource();
        jedis.rpush(sectorId + ":" + pcr,userId);
        jedis.close();

    }

    @Override
    public String getMSISDNbyPcr(String sectorId, String pcr) throws PCRException {

        String msisdn = null;
        List<String> retreiveMsisdnList = new ArrayList();

        Jedis jedis = RedisUtil.getInstance().getResource();
        retreiveMsisdnList = jedis.lrange(sectorId + ":" + pcr, 0, -1);
        msisdn = retreiveMsisdnList.get(retreiveMsisdnList.size() - 1);

        jedis.close();
        return msisdn;

    }
}
