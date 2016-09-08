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
package com.wso2telco.core.pcrservice.util;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.wso2telco.core.pcrservice.model.ConfigDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class YamlReader.
 */
public class YamlReader {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(YamlReader.class);
	
	/**
	 * Gets the configuration.
	 *
	 * @return the configuration
	 */
	public static ConfigDTO getConfiguration(){
		
		File file = new File("deploy/config.yml");
		ConfigDTO configPojo = new ConfigDTO();
		
		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory()); // jackson databind
	    
	    try {
			configPojo = mapper.readValue(file, ConfigDTO.class);
		} catch (JsonParseException e) {
			log.error("Yaml Parsing Error",e);			
		} catch (JsonMappingException e) {			
			log.error("Yaml Mapping Error",e);	
		} catch (IOException e) {			
			log.error("Yaml File Error",e);	
		}		
	    
	    return configPojo;
	}
}
