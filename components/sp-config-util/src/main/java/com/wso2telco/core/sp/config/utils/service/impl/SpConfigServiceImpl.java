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

import com.wso2telco.core.sp.config.utils.service.SpConfigService;
import com.wso2telco.core.sp.config.utils.dao.SpConfigDAO;
import com.wso2telco.core.sp.config.utils.dao.impl.SpConfigDAOImpl;
import com.wso2telco.core.sp.config.utils.domain.Config;

import java.util.List;

/**
 * Created by isuru on 9/20/16.
 */
public class SpConfigServiceImpl implements SpConfigService {

    public void save(Config config) throws Exception {

        SpConfigDAO spConfigDAO = new SpConfigDAOImpl();

        spConfigDAO.save(config);
    }

    public void delete(Config config) throws Exception {

        SpConfigDAO spConfigDAO = new SpConfigDAOImpl();

        spConfigDAO.delete(config);
    }

    public List<String> getScopes(String clientId) throws Exception {

        SpConfigDAO spConfigDAO = new SpConfigDAOImpl();

        return spConfigDAO.getScopes(clientId);
    }

    @Override
    public String getSaaMessage(String clientId) throws Exception {
        SpConfigDAO spConfigDAO = new SpConfigDAOImpl();

        return spConfigDAO.getSaaMessage(clientId);
    }

    @Override
    public String getSaaImageUrl(String clientId) throws Exception {
        SpConfigDAO spConfigDAO = new SpConfigDAOImpl();

        return spConfigDAO.getSaaImageUrl(clientId);
    }
}
