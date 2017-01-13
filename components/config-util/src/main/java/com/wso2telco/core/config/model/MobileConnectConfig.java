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
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Class MobileConnectConfig.
 */
@XmlRootElement(name = "MobileConnectConfig")
public class MobileConnectConfig {

    /**
     * The data source name.
     */
    private String dataSourceName;

    /**
     * The encrypt append.
     */
    private String encryptAppend;

    /**
     * The keyfile.
     */
    private String keyfile;

    /**
     * The gsma exchange config.
     */
    private GSMAExchangeConfig gsmaExchangeConfig;

    /**
     * The sms config.
     */
    private SMSConfig smsConfig;

    /**
     * The ussd config.
     */
    private USSDConfig ussdConfig;

    /**
     * The listener webapp host.
     */
    private String listenerWebappHost;

    protected MSISDN msisdn;

    protected AuthProxy authProxy;

    private String authEndpointUrl;

    private String defaultClaimUrl;

    private String openIdRegClaimUrl;

    private String adminUrl;

    private String adminUsername;

    private String adminPassword;

    /** The MIFEOpenIDTokenBuilderConfig. */
    protected MIFEOpenIDTokenBuilderConfig mifeOpenIDTokenBuilderConfig;

    /** The SessionUpdaterConfig. */
    protected SessionUpdaterConfig sessionUpdaterConfig;

    protected AuthenticatorSelectionConfig authenticatorSelectionConfig;

    @XmlElement(name = "AdminUrl")
    public String getAdminUrl() {
        return adminUrl;
    }

    public void setAdminUrl(String adminUrl) {
        this.adminUrl = adminUrl;
    }

    @XmlElement(name = "AdminUsername")
    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    @XmlElement(name = "AdminPassword")
    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    @XmlElement(name = "AuthenticationEndpoint", defaultValue = "authenticationendpoint")
    public String getAuthEndpointUrl() {
        return authEndpointUrl;
    }

    public void setAuthEndpointUrl(String authEndpointUrl) {
        this.authEndpointUrl = authEndpointUrl;
    }

    @XmlElement(name = "DefaultClaimUrl", defaultValue = "http://wso2.org/claims")
    public String getDefaultClaimUrl() {
        return defaultClaimUrl;
    }

    public void setDefaultClaimUrl(String defaultClaimUrl) {
        this.defaultClaimUrl = defaultClaimUrl;
    }

    @XmlElement(name = "OpenIdRegClaimUrl", defaultValue = "http://schema.openid.net/2007/05/claims")
    public String getOpenIdRegClaimUrl() {
        return openIdRegClaimUrl;
    }

    public void setOpenIdRegClaimUrl(String openIdRegClaimUrl) {
        this.openIdRegClaimUrl = openIdRegClaimUrl;
    }

    /**
     * The mss.
     */
    @XmlElement(name = "MSS")
    protected MSS mss;

    /**
     * The headerenrich.
     */
    @XmlElement(name = "HEADERENRICH")
    protected HEADERENRICH headerenrich;

    @XmlElement(name = "SessionUpdaterConfig")
    public SessionUpdaterConfig getSessionUpdaterConfig() {
        return sessionUpdaterConfig;
    }

    public void setSessionUpdaterConfig(SessionUpdaterConfig sessionUpdaterConfig) {
        this.sessionUpdaterConfig = sessionUpdaterConfig;
    }

    @XmlElement(name = "MIFEOpenIDTokenBuilderConfig")
    public MIFEOpenIDTokenBuilderConfig getMifeOpenIDTokenBuilderConfig() {
        return mifeOpenIDTokenBuilderConfig;
    }

    public void setMifeOpenIDTokenBuilderConfig(MIFEOpenIDTokenBuilderConfig mifeOpenIDTokenBuilderConfig) {
        this.mifeOpenIDTokenBuilderConfig = mifeOpenIDTokenBuilderConfig;
    }

    @XmlElement(name = "AuthenticatorSelectionConfig")
    public AuthenticatorSelectionConfig getAuthenticatorSelectionConfig() {
        return authenticatorSelectionConfig;
    }

    public void setAuthenticatorSelectionConfig(AuthenticatorSelectionConfig authenticatorSelectionConfig) {
        this.authenticatorSelectionConfig = authenticatorSelectionConfig;
    }


    /**
     * Gets the data source name.
     *
     * @return the data source name
     */
    @XmlElement(name = "DataSourceName", defaultValue = "jdbc/CONNECT_DB")
    public String getDataSourceName() {
        return dataSourceName;
    }

    /**
     * Gets the encrypt append.
     *
     * @return the encrypt append
     */
    @XmlElement(name = "EncryptAppend")
    public String getEncryptAppend() {
        return encryptAppend;
    }

    /**
     * Gets the keyfile.
     *
     * @return the keyfile
     */
    @XmlElement(name = "Keyfile")
    public String getKeyfile() {
        return keyfile;
    }

    /**
     * Gets the gsma exchange config.
     *
     * @return the gsma exchange config
     */
    @XmlElement(name = "GSMAExchangeConfig")
    public GSMAExchangeConfig getGsmaExchangeConfig() {
        return gsmaExchangeConfig;
    }

    /**
     * Gets the sms config.
     *
     * @return the sms config
     */
    @XmlElement(name = "SMS")
    public SMSConfig getSmsConfig() {
        return smsConfig;
    }

    /**
     * Gets the ussd config.
     *
     * @return the ussd config
     */
    @XmlElement(name = "USSD")
    public USSDConfig getUssdConfig() {
        return ussdConfig;
    }

    @XmlElement(name = "MSISDN")
    public MSISDN getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(MSISDN msisdn) {
        this.msisdn = msisdn;
    }

    @XmlElement(name = "AuthProxy")
    public AuthProxy getAuthProxy() {
        return authProxy;
    }

    public void setAuthProxy(AuthProxy authProxy) {
        this.authProxy = authProxy;
    }

    /**
     * Gets the listener webapp host.
     *
     * @return the listener webapp host
     */
    @XmlElement(name = "ListenerWebappHost")
    public String getListenerWebappHost() {
        if (listenerWebappHost == null || listenerWebappHost.isEmpty()) {
            return "http://" + System.getProperty("carbon.local.ip") + ":9764";
        }
        return listenerWebappHost;
    }

    /**
     * Sets the data source name.
     *
     * @param dataSourceName the new data source name
     */
    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    /**
     * Sets the encrypt append.
     *
     * @param EncryptAppend the new encrypt append
     */
    public void setEncryptAppend(String EncryptAppend) {
        this.encryptAppend = EncryptAppend;
    }

    /**
     * Sets the keyfile.
     *
     * @param Keyfile the new keyfile
     */
    public void setKeyfile(String Keyfile) {
        this.keyfile = Keyfile;
    }

    /**
     * Sets the gsma exchange config.
     *
     * @param gsmaExchangeConfig the new gsma exchange config
     */
    public void setGsmaExchangeConfig(GSMAExchangeConfig gsmaExchangeConfig) {
        this.gsmaExchangeConfig = gsmaExchangeConfig;
    }

    /**
     * Sets the sms config.
     *
     * @param smsConfig the new sms config
     */
    public void setSmsConfig(SMSConfig smsConfig) {
        this.smsConfig = smsConfig;
    }

    /**
     * Sets the ussd config.
     *
     * @param ussdConfig the new ussd config
     */
    public void setUssdConfig(USSDConfig ussdConfig) {
        this.ussdConfig = ussdConfig;
    }

    /**
     * Sets the listener webapp host.
     *
     * @param listenerWebappHost the new listener webapp host
     */
    public void setListenerWebappHost(String listenerWebappHost) {
        this.listenerWebappHost = listenerWebappHost;
    }

    /**
     * Gets the headerenrich.
     *
     * @return the headerenrich
     */
    public HEADERENRICH getHEADERENRICH() {
        return headerenrich;
    }

    /**
     * Sets the headerenrich.
     *
     * @param value the new headerenrich
     */
    public void setHEADERENRICH(HEADERENRICH value) {
        this.headerenrich = value;
    }

    /**
     * Gets the mss.
     *
     * @return the mss
     */
    public MSS getMSS() {
        return mss;
    }

    /**
     * Sets the mss.
     *
     * @param mss the new mss
     */
    public void setMSS(MSS mss) {
        this.mss = mss;
    }


    /**
     * The Class GSMAExchangeConfig.
     */
    public static class GSMAExchangeConfig {

        /**
         * The serving operator host.
         */
        private String servingOperatorHost;

        /**
         * The organization.
         */
        private String organization;

        /**
         * The auth token.
         */
        private String authToken;

        /**
         * The serving operator.
         */
        private ServingOperator servingOperator;

        /**
         * Gets the serving operator host.
         *
         * @return the serving operator host
         */
        @XmlElement(name = "SOHost")
        public String getServingOperatorHost() {
            return servingOperatorHost;
        }

        /**
         * Gets the organization.
         *
         * @return the organization
         */
        @XmlElement(name = "Organization")
        public String getOrganization() {
            return organization;
        }

        /**
         * Gets the auth token.
         *
         * @return the auth token
         */
        @XmlElement(name = "AuthToken")
        public String getAuthToken() {
            return authToken;
        }

        /**
         * Gets the serving operator.
         *
         * @return the serving operator
         */
        @XmlElement(name = "ServingOperator")
        public ServingOperator getServingOperator() {
            return servingOperator;
        }

        /**
         * Sets the serving operator host.
         *
         * @param servingOperatorHost the new serving operator host
         */
        public void setServingOperatorHost(String servingOperatorHost) {
            this.servingOperatorHost = servingOperatorHost;
        }

        /**
         * Sets the organization.
         *
         * @param organization the new organization
         */
        public void setOrganization(String organization) {
            this.organization = organization;
        }

        /**
         * Sets the auth token.
         *
         * @param authToken the new auth token
         */
        public void setAuthToken(String authToken) {
            this.authToken = authToken;
        }

        /**
         * Sets the serving operator.
         *
         * @param servingOperator the new serving operator
         */
        public void setServingOperator(ServingOperator servingOperator) {
            this.servingOperator = servingOperator;
        }
    }

    /**
     * The Class SMSConfig.
     */
    public static class SMSConfig {

        /**
         * The endpoint.
         */
        private String endpoint;

        /**
         * The auth token.
         */
        private String authToken;

        /**
         * The message.
         */
        private String message;

        /**
         * Gets the endpoint.
         *
         * @return the endpoint
         */
        @XmlElement(name = "Endpoint")
        public String getEndpoint() {
            return endpoint;
        }

        /**
         * Gets the auth token.
         *
         * @return the auth token
         */
        @XmlElement(name = "AuthToken")
        public String getAuthToken() {
            return authToken;
        }

        /**
         * Gets the message.
         *
         * @return the message
         */
        @XmlElement(name = "MessageContent")
        public String getMessage() {
            return message;
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
         * Sets the auth token.
         *
         * @param authToken the new auth token
         */
        public void setAuthToken(String authToken) {
            this.authToken = authToken;
        }

        /**
         * Sets the message.
         *
         * @param message the new message
         */
        public void setMessage(String message) {
            this.message = message;
        }
    }

    /**
     * The Class USSDConfig.
     */
    public static class USSDConfig {

        /**
         * The endpoint.
         */
        private String endpoint;

        /**
         * The auth token.
         */
        private String authToken;

        /**
         * The message.
         */
        private String message;

        /**
         * The short code.
         */
        private String shortCode;

        /**
         * The keyword.
         */
        private String keyword;

        /**
         * The pinauth.
         */
        private String pinauth;

        /**
         * The dash board.
         */
        private String dashBoard;

        /**
         * The ussd context endpoint.
         */
        private String ussdContextEndpoint;

        /**
         * The ussd pin context endpoint.
         */
        private String ussdPinContextEndpoint;

        /**
         * The ussd notify url for login
         */
        private String loginNotifyUrl;

        /**
         * The ussd notify url for registration
         */
        private String registrationNotifyUrl;

        /**
         * The ussd message for registration
         */
        private String ussdRegistrationMessage;

        /**
         * The ussd message for login
         */
        private String pinLoginMessage;

        /**
         * The ussd message for login with pin
         */
        private String pinRegistrationMessage;

        /**
         * The ussd message for registration with pin
         */
        private String ussdLoginMessage;

        /**
         * The pin registration notification url
         */
        private String pinRegistrationNotifyUrl;

        /**
         * The pin login notify url
         */
        private String pinLoginNotifyUrl;

        /**
         * The pin registration confirm message
         */
        private String pinRegistrationConfirmMessage;

        /**
         * The pin maximum length
         */
        private String pinMaxLength;

        /** The pin confirmation message */
        private String pinConfirmMessage;

        /** The pin invalid format message */
        private String pinInvalidFormatMessage;

        /** The pin mismatch message */
        private String pinMismatchMessage;

        /** The pin registration success message */
        private String pinRegistrationSuccessMessage;

        /** The pin invalid format attempts message */
        private String pinInvalidFormatAttemptsExceedMessage;

        /** The pin mismatch attempts exceed message */
        private String pinMismatchAttemptsExceedMessage;

        /** The pin mismatch attempts */
        private String pinMismatchAttempts;

        /** The invalid format pin attempts */
        private String invalidFormatPinAttempts;

        @XmlElement(name = "PinConfirmMessage")
        public String getPinConfirmMessage() {
            return pinConfirmMessage;
        }

        @XmlElement(name = "PinInvalidFormatMessage")
        public String getPinInvalidFormatMessage() {
            return pinInvalidFormatMessage;
        }

        @XmlElement(name = "PinMismatchMessage")
        public String getPinMismatchMessage() {
            return pinMismatchMessage;
        }

        @XmlElement(name = "PinRegistrationSuccessMessage")
        public String getPinRegistrationSuccessMessage() {
            return pinRegistrationSuccessMessage;
        }

        @XmlElement(name = "PinInvalidFormatAttemptsExceedMessage")
        public String getPinInvalidFormatAttemptsExceedMessage() {
            return pinInvalidFormatAttemptsExceedMessage;
        }

        @XmlElement(name = "PinMismatchAttemptsExceedMessage")
        public String getPinMismatchAttemptsExceedMessage() {
            return pinMismatchAttemptsExceedMessage;
        }

        @XmlElement(name = "PinMaxLength")
        public String getPinMaxLength() {
            return pinMaxLength;
        }

        @XmlElement(name = "PinRegistrationConfirmMessage")
        public String getPinRegistrationConfirmMessage() {
            return pinRegistrationConfirmMessage;
        }

        @XmlElement(name = "PinRegistrationNotifyUrl")
        public String getPinRegistrationNotifyUrl() {
            return pinRegistrationNotifyUrl;
        }

        @XmlElement(name = "PinLoginNotifyUrl")
        public String getPinLoginNotifyUrl() {
            return pinLoginNotifyUrl;
        }

        @XmlElement(name = "PinLoginMessage")
        public String getPinLoginMessage() {
            return pinLoginMessage;
        }

        @XmlElement(name = "PinRegistrationMessage")
        public String getPinRegistrationMessage() {
            return pinRegistrationMessage;
        }

        @XmlElement(name = "LoginMessage")
        public String getUssdLoginMessage() {
            return ussdLoginMessage;
        }

        @XmlElement(name = "RegistrationMessage")
        public String getUssdRegistrationMessage() {
            return ussdRegistrationMessage;
        }

        @XmlElement(name = "PinMismatchAttempts")
        public String getPinMismatchAttempts() {
            return pinMismatchAttempts;
        }

        @XmlElement(name = "InvalidFormatPinAttempts")
        public String getInvalidFormatPinAttempts() {
            return invalidFormatPinAttempts;
        }

        /**
         * Gets the endpoint.
         *
         * @return the endpoint
         */
        @XmlElement(name = "Endpoint")
        public String getEndpoint() {
            return endpoint;
        }

        /**
         * Gets the auth token.
         *
         * @return the auth token
         */
        @XmlElement(name = "AuthToken")
        public String getAuthToken() {
            return authToken;
        }

        /**
         * Gets the message.
         *
         * @return the message
         */
        @XmlElement(name = "MessageContent")
        public String getMessage() {
            return message;
        }

        /**
         * Gets the short code.
         *
         * @return the short code
         */
        @XmlElement(name = "ShortCode")
        public String getShortCode() {
            return shortCode;
        }

        /**
         * Gets the keyword.
         *
         * @return the keyword
         */
        @XmlElement(name = "Keyword")
        public String getKeyword() {
            return keyword;
        }

        /**
         * Gets the pinauth.
         *
         * @return the pinauth
         */
        @XmlElement(name = "Pinauth")
        public String getPinauth() {
            return pinauth;
        }

        /**
         * Gets the dash board.
         *
         * @return the dash board
         */
        @XmlElement(name = "DashBoard")
        public String getDashBoard() {
            return dashBoard;
        }

        /**
         * Gets the ussd context endpoint.
         *
         * @return the ussd context endpoint
         */
        @XmlElement(name = "USSDContextEndpoint")
        public String getUssdContextEndpoint() {
            return ussdContextEndpoint;
        }

        /**
         * Gets the ussd pin context endpoint.
         *
         * @return the ussd pin context endpoint
         */
        @XmlElement(name = "USSDPinContextEndpoint")
        public String getUssdPinContextEndpoint() {
            return ussdPinContextEndpoint;
        }

        @XmlElement(name = "LoginNotifyUrl")
        public String getLoginNotifyUrl() {
            return loginNotifyUrl;
        }

        @XmlElement(name = "RegistrationNotifyUrl")
        public String getRegistrationNotifyUrl() {
            return registrationNotifyUrl;
        }


        public void setLoginNotifyUrl(String loginNotifyUrl) {
            this.loginNotifyUrl = loginNotifyUrl;
        }

        public void setRegistrationNotifyUrl(String registrationNotifyUrl) {
            this.registrationNotifyUrl = registrationNotifyUrl;
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
         * Sets the auth token.
         *
         * @param authToken the new auth token
         */
        public void setAuthToken(String authToken) {
            this.authToken = authToken;
        }

        /**
         * Sets the message.
         *
         * @param message the new message
         */
        public void setMessage(String message) {
            this.message = message;
        }

        /**
         * Sets the short code.
         *
         * @param shortCode the new short code
         */
        public void setShortCode(String shortCode) {
            this.shortCode = shortCode;
        }

        /**
         * Sets the keyword.
         *
         * @param keyword the new keyword
         */
        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        /**
         * Sets the pinauth.
         *
         * @param pinauth the new pinauth
         */
        public void setPinauth(String pinauth) {
            this.pinauth = pinauth;
        }

        /**
         * Sets the dash board.
         *
         * @param dashBoard the new dash board
         */
        public void setDashBoard(String dashBoard) {
            this.dashBoard = dashBoard;
        }

        /**
         * Sets the ussd context endpoint.
         *
         * @param ussdContextEndpoint the new ussd context endpoint
         */
        public void setUssdContextEndpoint(String ussdContextEndpoint) {
            this.ussdContextEndpoint = ussdContextEndpoint;
        }

        /**
         * Sets the ussd pin context endpoint.
         *
         * @param ussdPinContextEndpoint the new ussd pin context endpoint
         */
        public void setUssdPinContextEndpoint(String ussdPinContextEndpoint) {
            this.ussdPinContextEndpoint = ussdPinContextEndpoint;
        }

        public void setUssdLoginMessage(String ussdLoginMessage) {
            this.ussdLoginMessage = ussdLoginMessage;
        }

        public void setUssdRegistrationMessage(String ussdRegistrationMessage) {
            this.ussdRegistrationMessage = ussdRegistrationMessage;
        }

        public void setPinRegistrationMessage(String pinRegistrationMessage) {
            this.pinRegistrationMessage = pinRegistrationMessage;
        }

        public void setPinLoginNotifyUrl(String pinLoginNotifyUrl) {
            this.pinLoginNotifyUrl = pinLoginNotifyUrl;
        }

        public void setPinMaxLength(String pinMaxLength) {
            this.pinMaxLength = pinMaxLength;
        }

        public void setPinRegistrationConfirmMessage(String pinRegistrationConfirmMessage) {
            this.pinRegistrationConfirmMessage = pinRegistrationConfirmMessage;
        }

        public void setPinRegistrationNotifyUrl(String pinRegistrationNotifyUrl) {
            this.pinRegistrationNotifyUrl = pinRegistrationNotifyUrl;
        }

        public void setPinLoginMessage(String pinLoginMessage) {
            this.pinLoginMessage = pinLoginMessage;
        }

        public void setPinConfirmMessage(String pinConfirmMessage) {
            this.pinConfirmMessage = pinConfirmMessage;
        }

        public void setPinInvalidFormatMessage(String pinInvalidFormatMessage) {
            this.pinInvalidFormatMessage = pinInvalidFormatMessage;
        }

        public void setPinMismatchMessage(String pinMismatchMessage) {
            this.pinMismatchMessage = pinMismatchMessage;
        }

        public void setPinRegistrationSuccessMessage(String pinRegistrationSuccessMessage) {
            this.pinRegistrationSuccessMessage = pinRegistrationSuccessMessage;
        }

        public void setPinInvalidFormatAttemptsExceedMessage(String pinInvalidFormatAttemptsExceedMessage) {
            this.pinInvalidFormatAttemptsExceedMessage = pinInvalidFormatAttemptsExceedMessage;
        }

        public void setPinMismatchAttemptsExceedMessage(String pinMismatchAttemptsExceedMessage) {
            this.pinMismatchAttemptsExceedMessage = pinMismatchAttemptsExceedMessage;
        }

        public void setPinMismatchAttempts(String pinMismatchAttempts) {
            this.pinMismatchAttempts = pinMismatchAttempts;
        }

        public void setInvalidFormatPinAttempts(String invalidFormatPinAttempts) {
            this.invalidFormatPinAttempts = invalidFormatPinAttempts;
        }
    }


    /**
     * The Class HEADERENRICH.
     */
    public static class HEADERENRICH {

        /**
         * The Operators.
         */
        private List<OPERATOR> Operators;

        /**
         * The endpoint.
         */
        private String endpoint;

        /**
         * The enrichflg.
         */
        private String enrichflg;

        /**
         * Gets the endpoint.
         *
         * @return the endpoint
         */
        @XmlElement(name = "Endpoint")
        public String getEndpoint() {
            return endpoint;
        }

        /**
         * Gets the enrichflg.
         *
         * @return the enrichflg
         */
        @XmlElement(name = "Enrichflg")
        public String getEnrichflg() {
            return enrichflg;
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
         * Sets the enrichflg.
         *
         * @param enrichflg the new enrichflg
         */
        public void setEnrichflg(String enrichflg) {
            this.enrichflg = enrichflg;
        }

        /**
         * Gets the operators.
         *
         * @return the operators
         */
        @XmlElement(name = "Operator")
        public List<OPERATOR> getOperators() {
            return Operators;
        }

        /**
         * Sets the operators.
         *
         * @param Operators the new operators
         */
        public void setOperators(List<OPERATOR> Operators) {
            this.Operators = Operators;
        }

    }

    /**
     * The Class OPERATOR.
     */
    public static class OPERATOR {

        /**
         * The operator name.
         */
        private String operatorName;

        /**
         * The ip validation.
         */
        private String ipValidation;

        /**
         * The message.
         */
        private String message;

        /**
         * The mobile ip ranges.
         */
        private List<String> mobileIPRanges;

        /**
         * Gets the mobile ip ranges.
         *
         * @return the mobile ip ranges
         */
        @XmlElementWrapper(name = "MobileIPRanges")
        @XmlElement(name = "IPRange")
        public List<String> getMobileIPRanges() {
            return mobileIPRanges;
        }

        /**
         * Sets the mobile ip ranges.
         *
         * @param mobileIPRanges the new mobile ip ranges
         */
        public void setMobileIPRanges(List<String> mobileIPRanges) {
            this.mobileIPRanges = mobileIPRanges;
        }


        /**
         * Gets the operator name.
         *
         * @return the operator name
         */
        @XmlElement(name = "operatorName")
        public String getOperatorName() {
            return operatorName;
        }

        /**
         * Sets the operator name.
         *
         * @param operatorName the new operator name
         */
        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }

        /**
         * Gets the ip validation.
         *
         * @return the ip validation
         */
        @XmlElement(name = "ipValidation")
        public String getIpValidation() {
            return ipValidation;
        }

        /**
         * Sets the ip validation.
         *
         * @param ipValidation the new ip validation
         */
        public void setIpValidation(String ipValidation) {
            this.ipValidation = ipValidation;
        }


    }

    public static class MSISDN {
        private String encryptionKey;
        private String validationRegex;

        @XmlElement(name = "EncryptionKey")
        public String getEncryptionKey() {
            return encryptionKey;
        }

        public void setEncryptionKey(String encryptionKey) {
            this.encryptionKey = encryptionKey;
        }

        public void setValidationRegex(String validationRegex) {
            this.validationRegex = validationRegex;
        }

        @XmlElement(name = "ValidationRegex")
        public String getValidationRegex() {
            return validationRegex;
        }
    }

    public static class AuthProxy {
        private String authorizeURL;
        private String dataSourceName;

        @XmlElement(name = "AuthorizeURL")
        public String getAuthorizeURL() {
            return authorizeURL;
        }

        public void setAuthorizeURL(String authorizeURL) {
            this.authorizeURL = authorizeURL;
        }

        @XmlElement(name = "DataSourceName")
        public String getDataSourceName() {
            return dataSourceName;
        }

        public void setDataSourceName(String dataSourceName) {
            this.dataSourceName = dataSourceName;
        }
    }

    /**
     * The Class MSS.
     */
    public static class MSS {

        /**
         * The endpoint.
         */
        private String endpoint;

        /**
         * The success status.
         */
        private int successStatus;

        /**
         * The mss text.
         */
        private String mssText;

        /**
         * The mss pin test.
         */
        private String mssPinTest;

        /**
         * Gets the mss text.
         *
         * @return the mss text
         */
        @XmlElement(name = "MssText")
        public String getMssText() {
            return mssText;
        }

        /**
         * Sets the mss text.
         *
         * @param mssText the new mss text
         */
        public void setMssText(String mssText) {
            this.mssText = mssText;
        }

        /**
         * Gets the mss pin test.
         *
         * @return the mss pin test
         */
        @XmlElement(name = "MssPinText")
        public String getMssPinTest() {
            return mssPinTest;
        }

        /**
         * Sets the mss pin test.
         *
         * @param mssPinTest the new mss pin test
         */
        public void setMssPinTest(String mssPinTest) {
            this.mssPinTest = mssPinTest;
        }


        /**
         * Gets the endpoint.
         *
         * @return the endpoint
         */
        @XmlElement(name = "Endpoint")
        public String getEndpoint() {
            return endpoint;
        }

        /**
         * Gets the success status.
         *
         * @return the success status
         */
        @XmlElement(name = "SuccessStatus")
        public int getSuccessStatus() {
            return successStatus;
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
         * Sets the success status.
         *
         * @param successStatus the new success status
         */
        public void setSuccessStatus(int successStatus) {
            this.successStatus = successStatus;
        }

    }

    /**
     * The Class MIFEOpenIDTokenBuilderConfig.
     */
    public static class MIFEOpenIDTokenBuilderConfig {

        /** The acr Access Token. */
        private String acrAccessToken;

        /** The acr Host Uri. */
        private String acrHostUri;

        /** The retrieve Service. */
        private String retrieveService;

        /** The create Service. */
        private String createService;

        /** The app Prov Service. */
        private String appProvService;

        /** The service Provider. */
        private String serviceProvider;

        /** The service Key. */
        private String serviceKey;

        public void setAcrAccessToken(String acrAccessToken) {
            this.acrAccessToken = acrAccessToken;
        }

        public void setAcrHostUri(String acrHostUri) {
            this.acrHostUri = acrHostUri;
        }

        public void setRetrieveService(String retrieveService) {
            this.retrieveService = retrieveService;
        }

        public void setCreateService(String createService) {
            this.createService = createService;
        }

        public void setAppProvService(String appProvService) {
            this.appProvService = appProvService;
        }

        public void setServiceProvider(String serviceProvider) {
            this.serviceProvider = serviceProvider;
        }

        public void setServiceKey(String serviceKey) {
            this.serviceKey = serviceKey;
        }

        @XmlElement(name = "acrAccessToken")
        public String getAcrAccessToken() {
            return acrAccessToken;
        }

        @XmlElement(name = "acrHostUri")
        public String getAcrHostUri() {
            return acrHostUri;
        }

        @XmlElement(name = "retrieveService")
        public String getRetrieveService() {
            return retrieveService;
        }

        @XmlElement(name = "createService")
        public String getCreateService() {
            return createService;
        }

        @XmlElement(name = "appProvService")
        public String getAppProvService() {
            return appProvService;
        }

        @XmlElement(name = "serviceProvider")
        public String getServiceProvider() {
            return serviceProvider;
        }

        @XmlElement(name = "serviceKey")
        public String getServiceKey() {
            return serviceKey;
        }

    }

    /**
     * The Class SessionUpdaterConfig.
     */
    public static class SessionUpdaterConfig {

        /**
         * The ussd send
         */
        private String ussdsend;

        /**
         * The notify url
         */
        private String notifyurl;

        /**
         * The access token
         */
        private String accesstoken;

        /**
         * The short code
         */
        private String shortcode;

        /**
         * The message
         */
        private String message;

        /**
         * The retry message
         */
        private String retry_message;

        /**
         * The error message
         */
        private String error_message;

        /**
         * The keyword
         */
        private String keyword;

        /**
         * The admin url
         */
        private String admin_url;

        /**
         * The aes key
         */
        private String aeskey;

        /**
         * The admin username
         */
        private String adminusername;

        /**
         * The admin password
         */
        private String adminpassword;

        /**
         * The mepin access token
         */
        private String mpinaccesstoken;

        /**
         * The mepin url
         */
        private String mpinurl;

        /**
         * The mepin client id
         */
        private String mpinclientid;

        @XmlElement(name = "ussdsend")
        public String getUssdsend() {
            return ussdsend;
        }

        @XmlElement(name = "notifyurl")
        public String getNotifyurl() {
            return notifyurl;
        }

        @XmlElement(name = "accesstoken")
        public String getAccesstoken() {
            return accesstoken;
        }

        @XmlElement(name = "shortcode")
        public String getShortcode() {
            return shortcode;
        }

        @XmlElement(name = "message")
        public String getMessage() {
            return message;
        }

        @XmlElement(name = "retry_message")
        public String getRetry_message() {
            return retry_message;
        }

        @XmlElement(name = "error_message")
        public String getError_message() {
            return error_message;
        }

        @XmlElement(name = "keyword")
        public String getKeyword() {
            return keyword;
        }

        @XmlElement(name = "admin_url")
        public String getAdmin_url() {
            return admin_url;
        }

        @XmlElement(name = "aeskey")
        public String getAeskey() {
            return aeskey;
        }

        @XmlElement(name = "adminusername")
        public String getAdminusername() {
            return adminusername;
        }

        @XmlElement(name = "adminpassword")
        public String getAdminpassword() {
            return adminpassword;
        }

        @XmlElement(name = "mpin-accesstoken")
        public String getMePinAccessToken() {
            return mpinaccesstoken;
        }

        @XmlElement(name = "mpin-url")
        public String getMePinUrl() {
            return mpinurl;
        }

        @XmlElement(name = "mpin-clientid")
        public String getMePinClientId() {
            return mpinclientid;
        }

        public void setUssdsend(String ussdsend) {
            this.ussdsend = ussdsend;
        }

        public void setNotifyurl(String notifyurl) {
            this.notifyurl = notifyurl;
        }

        public void setAccesstoken(String accesstoken) {
            this.accesstoken = accesstoken;
        }

        public void setShortcode(String shortcode) {
            this.shortcode = shortcode;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setRetry_message(String retry_message) {
            this.retry_message = retry_message;
        }

        public void setError_message(String error_message) {
            this.error_message = error_message;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public void setAdmin_url(String admin_url) {
            this.admin_url = admin_url;
        }

        public void setAeskey(String aeskey) {
            this.aeskey = aeskey;
        }

        public void setAdminusername(String adminusername) {
            this.adminusername = adminusername;
        }

        public void setAdminpassword(String adminpassword) {
            this.adminpassword = adminpassword;
        }

        public void setMePinAccessToken(String mpinaccesstoken) {
            this.mpinaccesstoken = mpinaccesstoken;
        }

        public void setMePinUrl(String mpinurl) {
            this.mpinurl = mpinurl;
        }

        public void setMePinClientid(String mpinclientid) {
            this.mpinclientid = mpinclientid;
        }
    }

    public static class AuthenticatorSelectionConfig {
        private String serviceProviderBasedSelectionEnabled;

        private String mobileNetworkOperatorBasedSelectionEnabled;

        @XmlElement(name = "ServiceProviderBasedSelectionEnabled")
        public String getServiceProviderBasedSelectionEnabled() {
            return serviceProviderBasedSelectionEnabled;
        }

        @XmlElement(name = "MobileNetworkOperatorBasedSelectionEnabled")
        public String getMobileNetworkOperatorBasedSelectionEnabled() {
            return mobileNetworkOperatorBasedSelectionEnabled;
        }

        public void setServiceProviderBasedSelectionEnabled(String serviceProviderBasedSelectionEnabled) {
            this.serviceProviderBasedSelectionEnabled = serviceProviderBasedSelectionEnabled;
        }

        public void setMobileNetworkOperatorBasedSelectionEnabled(String mobileNetworkOperatorBasedSelectionEnabled) {
            this.mobileNetworkOperatorBasedSelectionEnabled = mobileNetworkOperatorBasedSelectionEnabled;
        }
    }
}
