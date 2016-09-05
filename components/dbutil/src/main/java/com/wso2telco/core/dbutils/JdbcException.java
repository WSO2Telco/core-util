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
package com.wso2telco.core.dbutils;

import java.sql.Connection;

// TODO: Auto-generated Javadoc
/**
 * The Class JdbcException.
 */
class JdbcException extends Exception {

    /** The conn. */
    Connection conn;

    /**
     * Instantiates a new jdbc exception.
     *
     * @param e the e
     */
    public JdbcException(Exception e) {
        super(e.getMessage());
        conn = null;
    }

    /**
     * Instantiates a new jdbc exception.
     *
     * @param e the e
     * @param con the con
     */
    public JdbcException(Exception e, Connection con) {
        super(e.getMessage());
        conn = con;
    }

    /**
     * Handle.
     */
    public void handle() {
        System.out.println(getMessage());
        System.out.println();

        if (conn != null) {
            try {
                System.out.println("--Rollback the transaction-----");
                conn.rollback();
                System.out.println("  Rollback done!");
            } catch (Exception e) {
            };
        }
    } // handle

    /**
     * Handle expected err.
     */
    public void handleExpectedErr() {
        System.out.println();
        System.out.println(
                "**************** Expected Error ******************\n");
        System.out.println(getMessage());
        System.out.println(
                "**************************************************");
    } // handleExpectedError
} // JdbcException
