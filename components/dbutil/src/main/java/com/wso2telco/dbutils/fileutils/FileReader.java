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

	private Log log = LogFactory.getLog(FileReader.class);

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

			log.debug(PropertyFileNames.MEDIATOR_CONF_FILE.getFileName() + " file not found in " + filePath, e);
		} catch (IOException e) {

			log.debug("unable to close " + PropertyFileNames.MEDIATOR_CONF_FILE.getFileName() + " file ", e);
		} finally {

			try {

				fileStream.close();
			} catch (IOException e) {

				log.debug("unable to close " + PropertyFileNames.MEDIATOR_CONF_FILE.getFileName() + " file ", e);
			}
		}

		return mediatorConfMap;
	}

	public Map<String, String> readOneAPIValidationConfFile() throws FileNotFoundException, IOException {

		Map<String, String> oneAPIValidationConfMap = new HashMap<String, String>();
		Properties props = new Properties();
		FileInputStream fileStream = null;

		String filePath = CarbonUtils.getCarbonConfigDirPath() + File.separator
				+ PropertyFileNames.ONEAPI_VALIDATION_CONF_FILE.getFileName();

		try {

			fileStream = new FileInputStream(filePath);

			props.load(fileStream);

			// SMS max batch size for sendSMS
			oneAPIValidationConfMap.put("sms.batchSize", props.getProperty("sms.batchSize"));
			oneAPIValidationConfMap.put("sms.limitToOne", props.getProperty("sms.limitToOne"));

			// API operations availability
			oneAPIValidationConfMap.put("payment.charge", props.getProperty("payment.charge"));
			oneAPIValidationConfMap.put("payment.refund", props.getProperty("payment.refund"));
			oneAPIValidationConfMap.put("payment.reserve_amount", props.getProperty("payment.reserve_amount"));
			oneAPIValidationConfMap.put("payment.reserve_additional_amount",
					props.getProperty("payment.reserve_additional_amount"));
			oneAPIValidationConfMap.put("payment.charge_against_reservation",
					props.getProperty("payment.charge_against_reservation"));
			oneAPIValidationConfMap.put("payment.release_reservation",
					props.getProperty("payment.release_reservation"));
			oneAPIValidationConfMap.put("payment.list_transactions", props.getProperty("payment.list_transactions"));

			oneAPIValidationConfMap.put("sms.send_sms", props.getProperty("sms.send_sms"));
			oneAPIValidationConfMap.put("sms.retrive_sms", props.getProperty("sms.retrive_sms"));
			oneAPIValidationConfMap.put("sms.query_sms_delivery", props.getProperty("sms.query_sms_delivery"));
			oneAPIValidationConfMap.put("sms.start_delivery_subscription",
					props.getProperty("sms.start_delivery_subscription"));
			oneAPIValidationConfMap.put("sms.stop_delivery_subscription",
					props.getProperty("sms.stop_delivery_subscription"));
			oneAPIValidationConfMap.put("sms.retrive_sms_subscriptions",
					props.getProperty("sms.retrive_sms_subscriptions"));
			oneAPIValidationConfMap.put("sms.cancel_retrive_sms_subscriptions",
					props.getProperty("sms.cancel_retrive_sms_subscriptions"));

			oneAPIValidationConfMap.put("lbs.location", props.getProperty("lbs.location"));

			oneAPIValidationConfMap.put("ussd.ussd_send", props.getProperty("ussd.ussd_send"));
			oneAPIValidationConfMap.put("ussd.ussd_subscription", props.getProperty("ussd.ussd_subscription"));
			oneAPIValidationConfMap.put("ussd.stop_ussd_subscription",
					props.getProperty("ussd.stop_ussd_subscription"));
		} catch (FileNotFoundException e) {

			log.debug(PropertyFileNames.ONEAPI_VALIDATION_CONF_FILE.getFileName() + " file not found in " + filePath,
					e);
			throw e;
		} catch (IOException e) {

			log.debug("unable to close " + PropertyFileNames.ONEAPI_VALIDATION_CONF_FILE.getFileName() + " file ", e);
			throw e;
		} finally {

			try {

				fileStream.close();
			} catch (IOException e) {

				log.debug("unable to close " + PropertyFileNames.ONEAPI_VALIDATION_CONF_FILE.getFileName() + " file ",
						e);
			}
		}

		return oneAPIValidationConfMap;
	}
}
