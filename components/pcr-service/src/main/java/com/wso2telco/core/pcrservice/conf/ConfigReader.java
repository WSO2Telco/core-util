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
package com.wso2telco.core.pcrservice.conf;

import java.io.Serializable;

import com.wso2telco.core.pcrservice.model.ConfigDTO;

import io.dropwizard.setup.Environment;


/**
 * The Class ConfigReader.
 */
public class ConfigReader implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8945346314273239957L;
	
	/** The config DTO. */
	private ConfigDTO configDTO;
	
	/** The reader. */
	private static ConfigReader reader;
	
	/** The env. */
	private Environment env;


	/**
	 * Instantiates a new config reader.
	 *
	 * @param configDTO the config DTO
	 * @param env the env
	 */
	private ConfigReader(final ConfigDTO configDTO,final Environment env) {
		this.configDTO = configDTO;
		this.env= env;

	}
	
	/**
	 * Inits the.
	 *
	 * @param configDTO the config DTO
	 * @param env the env
	 */
	public static void init(final ConfigDTO configDTO,final Environment env){
		reader = new ConfigReader(configDTO,env);
	}


	/**
	 * Gets the single instance of ConfigReader.
	 *
	 * @return single instance of ConfigReader
	 */
	public static synchronized ConfigReader getInstance() {
		return reader;
	}
	
	/**
	 * Gets the config DTO.
	 *
	 * @return the config DTO
	 */
	public ConfigDTO getConfigDTO(){
		return configDTO;
	}
	
	/**
	 * Gets the environment.
	 *
	 * @return the environment
	 */
	public Environment getEnvironment(){
		return env;
	}
}
