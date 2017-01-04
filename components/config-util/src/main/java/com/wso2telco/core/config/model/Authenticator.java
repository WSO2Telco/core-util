/*******************************************************************************
 * Copyright (c) 2015-2016, WSO2.Telco Inc. (http://www.wso2telco.com) 
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
package com.wso2telco.core.config.model;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "Authenicator")
public class Authenticator {
	private String onfail;
	private String supportiveFlow;
	private String authenticatorName;

	@XmlAttribute
	public String getOnfail() {
		return onfail;
	}

	public void setOnfail(String onfail) {
		this.onfail = onfail;
	}

	@XmlAttribute
	public String getSupportiveFlow() {
		return supportiveFlow;
	}

	public void setSupportiveFlow(String supportiveFlow) {
		this.supportiveFlow = supportiveFlow;
	}

	@XmlValue
	public String getAuthenticatorName() {
		return authenticatorName;
	}

	public void setAuthenticatorName(String authName) {
		this.authenticatorName = authName;
	}
}