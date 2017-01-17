/*******************************************************************************
 * Copyright  (c) 2015-2017, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
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

package com.wso2telco.core.sp.config.utils.service.impl;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.wso2telco.core.sp.config.utils.service.SpConfigService;
import com.wso2telco.core.sp.config.utils.util.ConfigKey;
import com.wso2telco.core.sp.config.utils.domain.Config;
import com.wso2telco.core.sp.config.utils.service.ScopeValidateService;
import org.junit.Before;
import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ScopeValidateServiceImplTest {
//    @Before
    public void setUp() throws Exception {
        try {
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES,
                    "org.apache.naming");
            InitialContext ic = new InitialContext();

            ic.createSubcontext("java:");
            ic.createSubcontext("java:/comp");
            ic.createSubcontext("java:/comp/env");
            ic.createSubcontext("java:/comp/env/jdbc");

            MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setURL("jdbc:mysql://localhost/qadbConnect");
            ds.setUser("root");
            ds.setPassword("");

            ic.bind("java:/comp/env/jdbc/CONNECT_DB", ds);
        } catch (NamingException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    @Test
    public void validate() throws Exception {
        ScopeValidateService scopeValidateService = new ScopeValidateServiceImpl();
        SpConfigService spConfigService = new SpConfigServiceImpl();

        List<String> receivedScopes = new ArrayList<>();
        List<String> invalidScopes = new ArrayList<>();

        receivedScopes.add("openid");
        receivedScopes.add("mnv");

        invalidScopes.add("token");

        Config c1 = new Config("1", ConfigKey.SCOPE, "openid");
        Config c2 = new Config("1", ConfigKey.SCOPE, "mnv");
        Config c3 = new Config("1", ConfigKey.SCOPE, "mnv_validate");

        spConfigService.save(c1);
        spConfigService.save(c2);
        spConfigService.save(c3);

        assertTrue(scopeValidateService.isValid("1", receivedScopes));
        assertFalse(scopeValidateService.isValid("1", invalidScopes));

        spConfigService.delete(c1);
        spConfigService.delete(c2);
        spConfigService.delete(c3);
    }
}