/*******************************************************************************
 * Copyright  (c) 2015-2016, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 * 
 * WSO2.Telco Inc. licences this file to you under  the Apache License, Version 2.0 (the "License");
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
package com.wso2telco.core.dbutils.exception;

public enum PolicyError implements ThrowableError {
	TOO_MANY_ADDRESSES_SPECIFIED("POL0003","Too many addresses specified in message part %1"),
	DUPLICATED_ADDRESS("POL0013","Duplicated addresses"),
	NO_VALID_SERVICES_AVAILABLE("POL0014", "No Valid Services Available");

	private String code;
	private String message;

	private PolicyError(String code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getCode() {
		return code;
	}

}
