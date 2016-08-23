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

import org.junit.*;
import static org.junit.Assert.*;
import org.skife.jdbi.v2.DBI;

/**
 * The Class JDBIUtilTest.
 */
public class JDBIUtilTest {
	
	/**
	 * Test get instance 1.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetInstance_1()
		throws Exception {

		DBI result = JDBIUtil.getInstance();

		assertNotNull(result);
	}

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp()
		throws Exception {
		
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown()
		throws Exception {
		
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(JDBIUtilTest.class);
	}
}