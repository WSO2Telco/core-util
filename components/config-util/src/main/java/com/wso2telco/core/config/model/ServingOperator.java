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

import javax.xml.bind.annotation.XmlElement;

// TODO: Auto-generated Javadoc
/**
 * The Class ServingOperator.
 */
public class ServingOperator {

	/** The mcc. */
	private String mcc = null;
	
	/** The mnc. */
	private String mnc = null;
	
	/**
	 * Gets the mcc.
	 *
	 * @return the mcc
	 */
	@XmlElement(name = "mcc")
	public String getMcc() {
		return mcc;
	}
	
	/**
	 * Gets the mnc.
	 *
	 * @return the mnc
	 */
	@XmlElement(name = "mnc")
	public String getMnc() {
		return mnc;
	}
	
	/**
	 * Sets the mcc.
	 *
	 * @param mcc the new mcc
	 */
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	
	/**
	 * Sets the mnc.
	 *
	 * @param mnc the new mnc
	 */
	public void setMnc(String mnc) {
		this.mnc = mnc;
	}
}
