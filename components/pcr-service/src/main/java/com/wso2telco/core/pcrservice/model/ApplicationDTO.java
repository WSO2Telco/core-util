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

import java.io.Serializable;

// TODO: Auto-generated Javadoc

/**
 * The Class ApplicationDTO.
 */
public class ApplicationDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -4841094865588233257L;

    /**
     * The app id.
     */
    private String appId;

    /**
     * Instantiates a new application DTO.
     */
    public ApplicationDTO() {

    }

    /**
     * Instantiates a new application DTO.
     *
     * @param appId the app id
     */
    public ApplicationDTO(String appId) {
        this.appId = appId;
    }

    /**
     * Gets the app id.
     *
     * @return the app id
     */
    public String getAppId() {
        return appId;
    }

    /**
     * Sets the app id.
     *
     * @param appId the new app id
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }
}
