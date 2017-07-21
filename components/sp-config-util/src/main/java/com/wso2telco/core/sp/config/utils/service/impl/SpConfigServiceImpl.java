/*******************************************************************************
 * Copyright (c) 2015-2017, WSO2.Telco Inc. (http://www.wso2telco.com)
 *
 * All Rights Reserved. WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
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

package com.wso2telco.core.sp.config.utils.service.impl;

import com.wso2telco.core.sp.config.utils.exception.DataAccessException;
import com.wso2telco.core.sp.config.utils.service.SpConfigService;
import com.wso2telco.core.sp.config.utils.dao.SpConfigDAO;
import com.wso2telco.core.sp.config.utils.dao.impl.SpConfigDAOImpl;
import com.wso2telco.core.sp.config.utils.domain.Config;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * SP Config service
 */
public class SpConfigServiceImpl implements SpConfigService,Serializable {

    SpConfigDAO spConfigDAO = null;

    public SpConfigServiceImpl() {
        this.spConfigDAO = new SpConfigDAOImpl();
    }

    public void save(Config config) throws Exception {
        spConfigDAO.save(config);
    }

    public void delete(Config config) throws Exception {
        spConfigDAO.delete(config);
    }

    public List<String> getScopes(String clientId) throws Exception {
        return spConfigDAO.getScopes(clientId);
    }

    @Override
    public String getSaaMessage(String clientId) throws Exception {
        return spConfigDAO.getSaaMessage(clientId);
    }

    @Override
    public String getSaaImageUrl(String clientId) throws Exception {
        return spConfigDAO.getSaaImageUrl(clientId);
    }

    @Override
    public String getUSSDLoginMessage(String clientId) {
        return spConfigDAO.getUSSDLoginMessage(clientId);
    }

    @Override
    public String getUSSDRegistrationMessage(String clientId) {
        return spConfigDAO.getUSSDRegistrationMessage(clientId);
    }

    @Override
    public String getUSSDPinLoginMessage(String clientId) {
        return spConfigDAO.getUSSDPinLoginMessage(clientId);
    }

    @Override
    public String getUSSDPinRegistrationMessage(String clientId) {
        return spConfigDAO.getUSSDPinRegistrationMessage(clientId);
    }

    @Override
    public String getSMSRegistrationMessage(String clientId) {
        return spConfigDAO.getSMSRegistrationMessage(clientId);
    }

    @Override
    public String getSMSLoginMessage(String clientId) {
        return spConfigDAO.getSMSLoginMessage(clientId);
    }

    @Override
    public Map<String, String> getWelcomeSMSConfig(String clientId) throws DataAccessException {
        SpConfigDAO spConfigDAO = new SpConfigDAOImpl();
        return  spConfigDAO.getWelcomeSMSConfig(clientId);
    }
}
