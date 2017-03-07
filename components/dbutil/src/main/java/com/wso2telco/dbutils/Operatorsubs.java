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


// TODO: Auto-generated Javadoc

/**
 * The Class Operatorsubs.
 */
@Deprecated
public class Operatorsubs {

    /**
     * The operator.
     */
    String operator;

    /**
     * The domain.
     */
    String domain;

    /**
     * Instantiates a new operatorsubs.
     *
     * @param operator the operator
     * @param domain   the domain
     */
    public Operatorsubs(String operator, String domain) {
        this.operator = operator;
        this.domain = domain;
    }

    /**
     * Gets the operator.
     *
     * @return the operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Sets the operator.
     *
     * @param operator the new operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * Gets the domain.
     *
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets the domain.
     *
     * @param domain the new domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }
}
