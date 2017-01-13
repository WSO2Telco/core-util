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
import com.wso2telco.core.sp.config.utils.service.ScopeValidateService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by isuru dilshan on 9/21/16.
 */
public class ScopeValidateServiceImpl implements ScopeValidateService {

    private static Logger logger = Logger.getLogger(ScopeValidateServiceImpl.class);

    public boolean isValid(String clientId, List<String> receivedScopes) throws Exception {

        SpConfigService spConfigService = new SpConfigServiceImpl();

        List<String> definedScopes = spConfigService.getScopes(clientId);

        logger.info("Defined scopes " + definedScopes + " Received scopes " + receivedScopes);

        boolean isValid = definedScopes.containsAll(receivedScopes);

        if (isValid) {
            logger.info("Scopes are valid");
        } else {
            logger.error("Scopes are invalid");
        }
        return isValid;
    }
}
