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
package com.wso2telco.dbutils.fileutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Deprecated
public class FileReader {

	private Log log = LogFactory.getLog(FileReader.class);

	public Map<String, String> readPropertyFile(String file) {

		HashMap<String, String> confPropertyMap = new HashMap<String, String>();
		Properties props = new Properties();
		FileInputStream fileStream = null;

		try {

			fileStream = new FileInputStream(file);

			props.load(fileStream);

			for (String key : props.stringPropertyNames()) {

				String value = props.getProperty(key);
				confPropertyMap.put(key, value);
			}
		} catch (FileNotFoundException e) {

			log.debug("file not found in " + file, e);
		} catch (IOException e) {

			log.debug("unable to close " + file, e);
		} finally {

			try {

				fileStream.close();
			} catch (IOException e) {

				log.debug("unable to close " + file, e);
			}
		}

		return confPropertyMap;
	}
}
