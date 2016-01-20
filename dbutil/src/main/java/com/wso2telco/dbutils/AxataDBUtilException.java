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
/*
 * AxataDBUtilException.java
 * Oct 16, 2014  11:56:32 AM
 * Roshan.Saputhanthri
 *
 * Copyright (C) Dialog Axiata PLC. All Rights Reserved.
 */

package com.wso2telco.dbutils;

// TODO: Auto-generated Javadoc
/**
 * <TO-DO> <code>AxataDBUtilException</code>.
 *
 * @version $Id: AxataDBUtilException.java,v 1.00.000
 */

public class AxataDBUtilException extends Exception {

    /**
     * Instantiates a new axata db util exception.
     *
     * @param message the message
     */
    public AxataDBUtilException(String message) {
        super(message);
    }

    /**
     * Instantiates a new axata db util exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public AxataDBUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new axata db util exception.
     *
     * @param cause the cause
     */
    public AxataDBUtilException(Throwable cause) {
        super(cause);
    }
}
