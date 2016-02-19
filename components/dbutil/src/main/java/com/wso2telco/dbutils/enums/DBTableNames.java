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
package com.wso2telco.dbutils.enums;

/**
 * The Enum DBTableNames.
 */
public enum DBTableNames {

    APPLICATION_SPEND_LIMIT("spendlimitexceeded_application"),
    ENDPOINT_APPS("endpointapps"),
    MERCHANTOPCO_BLACKLIST("merchantopco_blacklist"),
    MSISDN_SPEND_LIMIT("spendlimitexceeded_msisdn"),
    OPERATORS("operators"),
    OPERATORSUBS("operatorsubs"),
    OPERATOR_APPS("operatorapps"),
    OPERATOR_ENDPOINTS("operatorendpoints"),
    OPERATOR_CODES("operatorcodes"),
    OPERATOR_SPEND_LIMIT("spendlimitexceeded_operator"),
    OUTBOUND_OPERATORSUBS("outbound_operatorsubs"),
    OUTBOUND_SUBSCRIPTIONS("outbound_subscriptions"),
    SENDSMS_REQID("sendsms_reqid"),
    SP_TOKEN("sp_token"),
    SUBSCRIPTIONS("subscriptions"),
    SUBSCRIPTION_VALIDATOR("subscription_validator"),
    USSD_REQUEST_ENTRY("ussd_request_entry"),
    VALID_PAYMENT_CATEGORIES("valid_payment_categories"),;


    private String tableName;

    DBTableNames(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

}
