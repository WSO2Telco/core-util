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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.utils.CarbonUtils;
import com.wso2telco.dbutils.util.PropertyFileNames;

public class FileReader {

	private Log LOG = LogFactory.getLog(FileReader.class);

	public Map<String, String> readMediatorConfFile() {

		Map<String, String> mediatorConfMap = new HashMap<String, String>();
		Properties props = new Properties();
		FileInputStream fileStream = null;

		String filePath = CarbonUtils.getCarbonConfigDirPath() + File.separator
				+ PropertyFileNames.MEDIATOR_CONF_FILE.getFileName();

		try {

			fileStream = new FileInputStream(filePath);
			props.load(fileStream);
			mediatorConfMap.put("ussdGatewayEndpoint", props.getProperty("ussdGatewayEndpoint"));
			mediatorConfMap.put("hubSubsGatewayEndpoint", props.getProperty("hubSubsGatewayEndpoint"));
			mediatorConfMap.put("hubGateway", props.getProperty("hubGateway"));
			mediatorConfMap.put("requestRouterUrl", props.getProperty("requestRouterUrl"));
			mediatorConfMap.put("sendSMSResourceURL", props.getProperty("sendSMSResourceURL"));
		} catch (FileNotFoundException e) {

			LOG.debug(PropertyFileNames.MEDIATOR_CONF_FILE.getFileName() + " file not found in " + filePath, e);
		} catch (IOException e) {

			LOG.debug("unable to close " + PropertyFileNames.MEDIATOR_CONF_FILE.getFileName() + " file ", e);
		} finally {

			try {

				fileStream.close();
			} catch (IOException e) {

				LOG.debug("unable to close " + PropertyFileNames.MEDIATOR_CONF_FILE.getFileName() + " file ", e);
			}
		}

		return mediatorConfMap;
	}
}
