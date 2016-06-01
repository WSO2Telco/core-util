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
package com.wso2telco.dbutils;

import java.io.Serializable;

 
// TODO: Auto-generated Javadoc
/**
 * The Class Operatorendpoint.
 */
@Deprecated
public class Operatorendpoint implements Serializable {

	/** The id. */
	private int id;
    
    /** The operatorid. */
    int operatorid;
    
    /** The operatorcode. */
    String operatorcode;
    
    /** The api. */
    String api;
    
    /** The endpoint. */
    String endpoint;

    /**
     * Instantiates a new operatorendpoint.
     *
     * @param operatorid the operatorid
     * @param operatorcode the operatorcode
     * @param api the api
     * @param endpoint the endpoint
     */
    public Operatorendpoint(int operatorid, String operatorcode, String api, String endpoint) {
        this.operatorid = operatorid;
        this.operatorcode = operatorcode;
        this.api = api;
        this.endpoint = endpoint;
    }
    
    /**
     * Gets the operatorid.
     *
     * @return the operatorid
     */
    public int getOperatorid() {
        return operatorid;
    }

    /**
     * Sets the operatorid.
     *
     * @param operatorid the new operatorid
     */
    public void setOperatorid(int operatorid) {
        this.operatorid = operatorid;
    }

    /**
     * Gets the api.
     *
     * @return the api
     */
    public String getApi() {
        return api;
    }

    /**
     * Sets the api.
     *
     * @param api the new api
     */
    public void setApi(String api) {
        this.api = api;
    }

    /**
     * Gets the endpoint.
     *
     * @return the endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * Sets the endpoint.
     *
     * @param endpoint the new endpoint
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Gets the operatorcode.
     *
     * @return the operatorcode
     */
    public String getOperatorcode() {
        return operatorcode;
    }

    /**
     * Sets the operatorcode.
     *
     * @param operatorcode the new operatorcode
     */
    public void setOperatorcode(String operatorcode) {
        this.operatorcode = operatorcode;
    }

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}    
    
}
