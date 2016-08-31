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
package com.wso2telco.core.pcrservice.model;

// TODO: Auto-generated Javadoc
/**
 * The Class UserAssignmentDTO.
 */
public class UserAssignmentDTO {
	
	/** The pcr. */
	private String pcr;
	
	/** The userdid. */
	private int userdid;
	
	/** The appdid. */
	private int appdid;
	
	/** The sectordid. */
	private int sectordid;

	/**
	 * Gets the pcr.
	 *
	 * @return the pcr
	 */
	public String getPcr() {
		return pcr;
	}
	
	/**
	 * Sets the pcr.
	 *
	 * @param pcr the new pcr
	 */
	public void setPcr(String pcr) {
		this.pcr = pcr;
	}
	
	/**
	 * Gets the userdid.
	 *
	 * @return the userdid
	 */
	public int getUserdid() {
		return userdid;
	}
	
	/**
	 * Sets the userdid.
	 *
	 * @param userdid the new userdid
	 */
	public void setUserdid(int userdid) {
		this.userdid = userdid;
	}
	
	/**
	 * Gets the appdid.
	 *
	 * @return the appdid
	 */
	public int getAppdid() {
		return appdid;
	}
	
	/**
	 * Sets the appdid.
	 *
	 * @param appdid the new appdid
	 */
	public void setAppdid(int appdid) {
		this.appdid = appdid;
	}
	
	/**
	 * Gets the sectordid.
	 *
	 * @return the sectordid
	 */
	public int getSectordid() {
		return sectordid;
	}
	
	/**
	 * Sets the sectordid.
	 *
	 * @param sectordid the new sectordid
	 */
	public void setSectordid(int sectordid) {
		this.sectordid = sectordid;
	}
	

}
