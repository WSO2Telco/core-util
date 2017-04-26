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

    private SaaConfig saaConfig;

    /**
     * toggles sp validation
     */
    private boolean isSpValidationDisabled;

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

    private boolean isPcrServiceEnabled;

    public boolean isPcrServiceEnabled() {
        return isPcrServiceEnabled;
    }

    @XmlElement(name = "IsPcrServiceEnabled")
    public void setPcrServiceEnabled(boolean pcrServiceEnabled) {
        isPcrServiceEnabled = pcrServiceEnabled;
    }

    /**
     * gets sp validation value
     *
     * @return returns the boolean flag
     */
    public boolean isSpValidationDisabled() {
        return isSpValidationDisabled;
    }

    /**
     * sets sp validation boolean flag
     *
     * @param isSpValidationDisabled boolean flag to disable sp validation
     */
    @XmlElement(name = "IsSpValidationDisabled")
    public void setIsSpValidationDisabled(boolean isSpValidationDisabled) {
        this.isSpValidationDisabled = isSpValidationDisabled;
    }

    /**
     * The MIFEOpenIDTokenBuilderConfig.
     */
    protected MIFEOpenIDTokenBuilderConfig mifeOpenIDTokenBuilderConfig;

    /**
     * The SessionUpdaterConfig.
     */
    protected SessionUpdaterConfig sessionUpdaterConfig;

    protected AuthenticatorSelectionConfig authenticatorSelectionConfig;

    /**
     * get the saa config
     *
     * @return
     */
    @XmlElement(name = "SAA")
    public SaaConfig getSaaConfig() {
        return saaConfig;
    }

    /**
     * sets the saa config
     *
     * @param saaConfig saa config
     */
    public void setSaaConfig(SaaConfig saaConfig) {
        this.saaConfig = saaConfig;
    }

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

    @XmlElement(name = "HEADERENRICH")
    protected HEADERENRICH headerenrich;

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
         * aes key
         */
        private String aesKey;

        /**
         * sender address
         */
        private String senderAddress;

        /**
         * boolean flog to check whether the short url enabled
         */
        private boolean isShortUrl;

        /**
         * class to laod when shortening url
         */
        private String shortUrlClass;

        /**
         * short url service
         */
        private String shortUrlService;

        /**
         * The access token of the short URL service
         */
        private String accessToken;

        /**
         * The timeout configs for async requests
         */
        private TimeoutConfig timeoutConfig;

        /**
         * The url of the sms authentication
         */
        private String authUrl;

        private Boolean welcomeMessageDisabled;

        private String defaultWelcomeMessage;

        private List<OperatorSmsConfig> operatorSmsConfigs;

        @XmlElement(name = "DefaultWelcomeMessage")
        public String getDefaultWelcomeMessage() {
            return defaultWelcomeMessage;
        }

        public void setDefaultWelcomeMessage(String defaultWelcomeMessage) {
            this.defaultWelcomeMessage = defaultWelcomeMessage;
        }

        @XmlElement(name = "WelcomeMessageDisabled")
        public Boolean getWelcomeMessageDisabled() {
            return welcomeMessageDisabled;
        }

        public void setWelcomeMessageDisabled(Boolean welcomeMessageDisabled) {
            this.welcomeMessageDisabled = welcomeMessageDisabled;
        }

        @XmlElementWrapper(name = "OperatorWelcomeMessage")
        @XmlElement(name = "Operator")
        public List<OperatorSmsConfig> getOperatorSmsConfigs() {
            return operatorSmsConfigs;
        }

        public void setOperatorSmsConfigs(List<OperatorSmsConfig> operatorSmsConfigs) {
            this.operatorSmsConfigs = operatorSmsConfigs;
        }

        /**
         * The sms login message
         */
        private String loginMessage;

        /**
         * The sms registration message
         */
        private String registrationMessage;

        /**
         * The Operator Specific Messages
         */
        private OperatorSpecificMessages operatorSpecificMessages;

        /**
         * @return
         */
        @XmlElement(name = "RegistrationMessage")
        public String getRegistrationMessage() {
            return registrationMessage;
        }

        /**
         * Gets SMS login message
         *
         * @return the generic login message
         */
        @XmlElement(name = "LoginMessage")
        public String getLoginMessage() {
            return loginMessage;
        }

        /**
         * The Operator Specific SMS Messages
         *
         * @return the operator specific messages object
         */
        @XmlElement(name = "OperatorSpecificMessages")
        public OperatorSpecificMessages getOperatorSpecificMessages() {
            return operatorSpecificMessages;
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

        @XmlElement(name = "IsShortUrl")
        public boolean getIsShortUrl() {
            return isShortUrl;
        }

        /**
         * gets the aes key
         *
         * @return returns the aes key
         */
        @XmlElement(name = "AesKey")
        public String getAesKey() {
            return aesKey;
        }

        /**
         * Sets the AES key.
         *
         * @param aesKey the AES key
         */
        public void setAesKey(String aesKey) {
            this.aesKey = aesKey;
        }

        /**
         * get sthe sender address
         *
         * @return
         */
        @XmlElement(name = "SenderAddress")
        public String getSenderAddress() {
            return senderAddress;
        }

        /**
         * sets the access token
         *
         * @param accessToken access token
         */
        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        /**
         * get the boolean flag to check the short url
         *
         * @return returns the boolean flag
         */
        public boolean isShortUrl() {
            return isShortUrl;
        }

        /**
         * sets the boolean flag
         *
         * @param shortUrl short url
         */
        public void setShortUrl(boolean shortUrl) {
            isShortUrl = shortUrl;
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
         * Sets the a URL shortening service is enabled.
         *
         * @param isShortUrl URL shortening service enabling status
         */
        public void setIsShortUrl(boolean isShortUrl) {
            this.isShortUrl = isShortUrl;
        }

        /**
         * Returns the fully qualified name of the Short URL service class.
         *
         * @return the fully qualified name of the Short URL service class
         */
        @XmlElement(name = "ShortUrlClass")
        public String getShortUrlClass() {
            return shortUrlClass;
        }

        /**
         * Sets the fully qualified name of the Short URL service class.
         *
         * @param shortUrlClass the fully qualified name of the Short URL service class
         */
        public void setShortUrlClass(String shortUrlClass) {
            this.shortUrlClass = shortUrlClass;
        }

        /**
         * Returns the URL of the Short URL Service.
         *
         * @return the URL of the Short URL Service
         */
        @XmlElement(name = "ShortUrlService")
        public String getShortUrlService() {
            return shortUrlService;
        }

        /**
         * Sets the URL of the Short URL Service.
         *
         * @param shortUrlService URL of the Short URL Service
         */
        public void setShortUrlService(String shortUrlService) {
            this.shortUrlService = shortUrlService;
        }

        /**
         * Returns the access token for the short URL service.
         *
         * @return the access token for the short URL service
         */
        @XmlElement(name = "AccessToken")
        public String getAccessToken() {
            return accessToken;
        }

        /**
         * Returns the auth url for sms
         *
         * @return auth url
         */
        @XmlElement(name = "AuthUrl")
        public String getAuthUrl() {
            return authUrl;
        }

        /**
         * sets the auth url for sms
         *
         * @param authUrl auth url
         */
        public void setAuthUrl(String authUrl) {
            this.authUrl = authUrl;
        }

        /**
         * sets the sender address
         *
         * @param senderAddress sender address
         */

        public void setSenderAddress(String senderAddress) {
            this.senderAddress = senderAddress;
        }

        @XmlElement(name = "TimeoutConfig")
        public TimeoutConfig getTimeoutConfig() {
            return timeoutConfig;
        }

        public void setTimeoutConfig(TimeoutConfig timeoutConfig) {
            this.timeoutConfig = timeoutConfig;
        }

        /**
         * Sets the SMS login message
         *
         * @param loginMessage the login message
         */
        public void setLoginMessage(String loginMessage) {
            this.loginMessage = loginMessage;
        }

        /**
         * Sets the SMS registration message
         *
         * @param registrationMessage the registration message
         */
        public void setRegistrationMessage(String registrationMessage) {
            this.registrationMessage = registrationMessage;
        }

        /**
         * Sets the operator specific messages
         *
         * @param operatorSpecificMessages The operator specific messages
         */
        public void setOperatorSpecificMessages(OperatorSpecificMessages operatorSpecificMessages) {
            this.operatorSpecificMessages = operatorSpecificMessages;
        }
    }

    public static class SaaConfig {

        /**
         * authentication endpoint of the saa server
         */
        private String authenticationEndpoint;

        /**
         * registration endpoint for saa server
         */
        private String registrationEndpoint;

        /**
         * push service endpoint for saa server
         */
        private String pushServiceEndpoint;

        /**
         * get the auth endpoint
         *
         * @return
         */
        @XmlElement(name = "AuthenticationEndpoint")
        public String getAuthenticationEndpoint() {
            return authenticationEndpoint;
        }

        /**
         * gets the registration endpoint
         *
         * @return registration endpoint
         */
        @XmlElement(name = "RegistrationEndpoint")
        public String getRegistrationEndpoint() {
            return registrationEndpoint;
        }

        /**
         * sets the registration endpoint
         *
         * @param registrationEndpoint
         */
        public void setRegistrationEndpoint(String registrationEndpoint) {
            this.registrationEndpoint = registrationEndpoint;
        }

        /**
         * sets authentication endpoint
         *
         * @param authenticationEndpoint auth endpoint
         */
        public void setAuthenticationEndpoint(String authenticationEndpoint) {
            this.authenticationEndpoint = authenticationEndpoint;
        }

        /**
         * gets the push service endpoint
         *
         * @return return push service endpoint
         */
        @XmlElement(name = "PushServiceEndpoint")
        public String getPushServiceEndpoint() {
            return pushServiceEndpoint;
        }

        /**
         * sets push service endpoint
         *
         * @param pushServiceEndpoint push service endpoint
         */
        public void setPushServiceEndpoint(String pushServiceEndpoint) {
            this.pushServiceEndpoint = pushServiceEndpoint;
        }
    }

    /**
     * The Class for Operator Specific USSD Message
     */
    public static class OperatorSpecificMessage {
        /**
         * The Operator name
         */
        private String operator;

        /**
         * The USSD Registration Message
         */
        private String registrationMessage;

        /**
         * The USSD Login Message
         */
        private String loginMessage;

        @XmlAttribute(name = "operator")
        public String getOperator() {
            return operator;
        }

        @XmlElement(name = "RegistrationMessage")
        public String getRegistrationMessage() {
            return registrationMessage;
        }

        @XmlElement(name = "LoginMessage")
        public String getLoginMessage() {
            return loginMessage;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public void setRegistrationMessage(String registrationMessage) {
            this.registrationMessage = registrationMessage;
        }

        public void setLoginMessage(String loginMessage) {
            this.loginMessage = loginMessage;
        }
    }

    /**
     * The Class for Operator Specific USSD Messages
     */
    public static class OperatorSpecificMessages {
        private OperatorSpecificMessage[] OperatorSpecificMessage;

        @XmlElement(name = "OperatorSpecificMessage")
        public OperatorSpecificMessage[] getOperatorSpecificMessage() {
            return OperatorSpecificMessage;
        }

        public void setOperatorSpecificMessage(OperatorSpecificMessage[] OperatorSpecificMessage) {
            this.OperatorSpecificMessage = OperatorSpecificMessage;
        }
    }

    /**
     * The Class for Operator Specific Pin USSD Messages
     */
    public static class OperatorSpecificPinMessages {
        private OperatorSpecificMessage[] OperatorSpecificPinMessage;

        @XmlElement(name = "OperatorSpecificMessage")
        public OperatorSpecificMessage[] getOperatorSpecificPinMessage() {
            return OperatorSpecificPinMessage;
        }

        public void setOperatorSpecificPinMessage(OperatorSpecificMessage[] OperatorSpecificPinMessage) {
            this.OperatorSpecificPinMessage = OperatorSpecificPinMessage;
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

        /**
         * The pin confirmation message
         */
        private String pinConfirmMessage;

        /**
         * The pin invalid format message
         */
        private String pinInvalidFormatMessage;

        /**
         * The pin mismatch message
         */
        private String pinMismatchMessage;

        /**
         * The pin registration success message
         */
        private String pinRegistrationSuccessMessage;

        /**
         * The pin invalid format attempts message
         */
        private String pinInvalidFormatAttemptsExceedMessage;

        /**
         * The pin mismatch attempts exceed message
         */
        private String pinMismatchAttemptsExceedMessage;

        /**
         * The pin mismatch attempts
         */
        private String pinMismatchAttempts;

        /**
         * The invalid format pin attempts
         */
        private String invalidFormatPinAttempts;

        /**
         * The USSD accept user responses
         */
        private String acceptUserInputs;

        /**
         * The USSD reject user responses
         */
        private String rejectUserInputs;

        /**
         * The Operator Specific Messages
         */
        private OperatorSpecificMessages operatorSpecificMessages;

        /**
         * The timeout configs for async requests
         */
        private TimeoutConfig timeoutConfig;
        /**
         * The Operator Specific Messages
         */
        private OperatorSpecificPinMessages operatorSpecificPinMessages;

        @XmlElement(name = "TimeoutConfig")
        public TimeoutConfig getTimeoutConfig() {
            return timeoutConfig;
        }

        public void setTimeoutConfig(TimeoutConfig timeoutConfig) {
            this.timeoutConfig = timeoutConfig;
        }

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

        @XmlElement(name = "OperatorSpecificPinMessages")
        public OperatorSpecificPinMessages getOperatorSpecificPinMessages() {
            return operatorSpecificPinMessages;
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

        @XmlElement(name = "AcceptUserInputs")
        public String getAcceptUserInputs() {
            return acceptUserInputs;
        }

        @XmlElement(name = "RejectUserInputs")
        public String getRejectUserInputs() {
            return rejectUserInputs;
        }

        @XmlElement(name = "OperatorSpecificMessages")
        public OperatorSpecificMessages getOperatorSpecificMessages() {
            return operatorSpecificMessages;
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

        public void setAcceptUserInputs(String acceptUserInputs) {
            this.acceptUserInputs = acceptUserInputs;
        }

        public void setRejectUserInputs(String rejectUserInputs) {
            this.rejectUserInputs = rejectUserInputs;
        }

        public void setOperatorSpecificMessages(OperatorSpecificMessages operatorSpecificMessages) {
            this.operatorSpecificMessages = operatorSpecificMessages;
        }

        public void setOperatorSpecificPinMessages(OperatorSpecificPinMessages operatorSpecificPinMessages) {
            this.operatorSpecificPinMessages = operatorSpecificPinMessages;
        }

    }

    public static class TimeoutConfig {

        /**
         * socket timeout
         */
        private int socketTimeout;

        /**
         * connection timeout
         */
        private int connectionTimeout;

        /**
         * connection request timeout
         */
        private int connectionRequestTimeout;

        /**
         * gets the socket timeout
         *
         * @return socket timeout
         */
        @XmlElement(name = "SocketTimeout")
        public int getSocketTimeout() {
            return socketTimeout;
        }

        /**
         * sets the socket timeout
         *
         * @param socketTimeout socket timeout
         */
        public void setSocketTimeout(int socketTimeout) {
            this.socketTimeout = socketTimeout;
        }

        /**
         * gets the connection timeout
         *
         * @return connection timeout
         */
        @XmlElement(name = "ConnectionTimeout")
        public int getConnectionTimeout() {
            return connectionTimeout;
        }

        /**
         * sets the connection timeout
         *
         * @param connectionTimeout connection timeout
         */
        public void setConnectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
        }

        /**
         * gets the connection request timeout
         *
         * @return connection request timeout
         */
        @XmlElement(name = "ConnectionRequestTimeout")
        public int getConnectionRequestTimeout() {
            return connectionRequestTimeout;
        }

        /**
         * sets the connection request timeout
         *
         * @param connectionRequestTimeout connection request timeout
         */
        public void setConnectionRequestTimeout(int connectionRequestTimeout) {
            this.connectionRequestTimeout = connectionRequestTimeout;
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
         * The IP Header name
         */
        private String IPHeaderName;

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
         * Sets the IPHeaderName
         *
         * @param IPHeaderName IP Header name
         */
        public void setIPHeaderName(String IPHeaderName) {
            this.IPHeaderName = IPHeaderName;
        }

        /**
         * Gets the IPHeaderName
         *
         * @return IP Header name
         */
        @XmlElement(name = "ipHeaderName")
        public String getIPHeaderName() {
            return IPHeaderName;
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

    private DataPublisher dataPublisher;

    public void setDataPublisher(DataPublisher dataPublisher) {
        this.dataPublisher = dataPublisher;
    }

    @XmlElement(name = "DataPublisher")
    public DataPublisher getDataPublisher() {
        return dataPublisher;
    }

    public static class DataPublisher {
        private boolean enabled;
        private String username;
        private String password;
        private String dasUrl;
        private String dasSecureUrl;

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        @XmlElement(name = "Enabled")
        public boolean isEnabled() {
            return enabled;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @XmlElement(name = "UserName")
        public String getUsername() {
            return username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @XmlElement(name = "Password")
        public String getPassword() {
            return password;
        }

        public void setDasUrl(String dasUrl) {
            this.dasUrl = dasUrl;
        }

        @XmlElement(name = "DASUrl")
        public String getDasUrl() {
            return dasUrl;
        }

        public void setDasSecureUrl(String dasSecureUrl) {
            this.dasSecureUrl = dasSecureUrl;
        }

        @XmlElement(name = "DASSecureUrl")
        public String getDasSecureUrl() {
            return dasSecureUrl;
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

        /**
         * The acr Access Token.
         */
        private String acrAccessToken;

        /**
         * The acr Host Uri.
         */
        private String acrHostUri;

        /**
         * The retrieve Service.
         */
        private String retrieveService;

        /**
         * The create Service.
         */
        private String createService;

        /**
         * The app Prov Service.
         */
        private String appProvService;

        /**
         * The service Provider.
         */
        private String serviceProvider;

        /**
         * The service Key.
         */
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

    private boolean seamlessProvisioningEnabled;

    @XmlElement(name = "SeamlessProvisioningEnabled")
    public boolean isSeamlessProvisioningEnabled() {
        return seamlessProvisioningEnabled;
    }

    public void setSeamlessProvisioningEnabled(boolean seamlessProvisioningEnabled) {
        this.seamlessProvisioningEnabled = seamlessProvisioningEnabled;
    }

    private DiscoveryConfig discoveryConfig;

    @XmlElement(name = "DiscoveryConfigs")
    public DiscoveryConfig getDiscoveryConfig() {
        return discoveryConfig;
    }

    public void setDiscoveryConfig(DiscoveryConfig discoveryConfig) {
        this.discoveryConfig = discoveryConfig;
    }

    public static class DiscoveryConfig {

        private EksDiscoveryConfig eksDiscoveryConfig;
        private CrValidateDiscoveryConfig crValidateDiscoveryConfig;

        @XmlElement(name = "DiscoveryCrConfig")
        public CrValidateDiscoveryConfig getCrValidateDiscoveryConfig() {
            return crValidateDiscoveryConfig;
        }

        public void setCrValidateDiscoveryConfig(CrValidateDiscoveryConfig crValidateDiscoveryConfig) {
            this.crValidateDiscoveryConfig = crValidateDiscoveryConfig;
        }

        @XmlElement(name = "DiscoveryEksConfig")
        public EksDiscoveryConfig getEksDiscoveryConfig() {
            return eksDiscoveryConfig;
        }

        public void setEksDiscoveryConfig(EksDiscoveryConfig eksDiscoveryConfig) {
            this.eksDiscoveryConfig = eksDiscoveryConfig;
        }

    }

    public static class EksDiscoveryConfig {

        private String serviceUrl;
        private String redirectUrl;
        private String msisdn;

        @XmlElement(name = "EksRedirectUrl")
        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        @XmlElement(name = "EksServiceUrl")
        public String getServiceUrl() {
            return serviceUrl;
        }

        public void setServiceUrl(String serviceUrl) {
            this.serviceUrl = serviceUrl;
        }

        @XmlElement(name = "Msisdn")
        public String getMsisdn() {
            return msisdn;
        }

        public void setMsisdn(String msisdn) {
            this.msisdn = msisdn;
        }

    }

    public static class CrValidateDiscoveryConfig {

        private String serviceUrl;

        @XmlElement(name = "CrdServiceUrl")
        public String getServiceUrl() {
            return serviceUrl;
        }

        public void setServiceUrl(String serviceUrl) {
            this.serviceUrl = serviceUrl;
        }

    }

    private OpenAdminService openAdminService;

    public void setOpenAdminService(OpenAdminService openAdminService) {
        this.openAdminService = openAdminService;
    }

    @XmlElement(name = "OpenIDAdminServices")
    public OpenAdminService getOpenAdminService() {
        return openAdminService;
    }

    public static class OpenAdminService {
        private String userName;
        private String password;

        @XmlElement(name = "UserName")
        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @XmlElement(name = "Password")
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }

    private ServiceProvider serviceProvider;

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @XmlElement(name = "ServiceProviderHeaders")
    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public static class ServiceProvider {
        private String username;
        private String password;

        @XmlElement(name = "UserName")
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @XmlElement(name = "Password")
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    /*
    * SP Provision confid
    * */
    private SpProvisionConfig spProvisionConfig;

    @XmlElement(name = "SpProvisionConfig")
    public SpProvisionConfig getSpProvisionConfig() {
        return spProvisionConfig;
    }

    public void setSpProvisionConfig(SpProvisionConfig spProvisionConfig) {
        this.spProvisionConfig = spProvisionConfig;
    }

    public static class SpProvisionConfig {

        private String adminServiceUrl;
        private String ApplicationManagementHostUrl;
        private String stubAccessUserName;
        private String stubAccessPassword;
        private int maximumTotalHttpConnections;
        private int maximumHttpConnectionsPerHost;
        private Config config;

        @XmlElement(name = "AdminServiceHostUrl")
        public String getAdminServiceUrl() {
            return adminServiceUrl;
        }

        public void setAdminServiceUrl(String adminServiceUrl) {
            this.adminServiceUrl = adminServiceUrl;
        }

        @XmlElement(name = "ApplicationManagementHostUrl")
        public String getApplicationManagementHostUrl() {
            return ApplicationManagementHostUrl;
        }

        public void setApplicationManagementHostUrl(String applicationManagementHostUrl) {
            ApplicationManagementHostUrl = applicationManagementHostUrl;
        }

        @XmlElement(name = "UserName")
        public String getStubAccessUserName() {
            return stubAccessUserName;
        }

        public void setStubAccessUserName(String stubAccessUserName) {
            this.stubAccessUserName = stubAccessUserName;
        }

        @XmlElement(name = "Password")
        public String getStubAccessPassword() {
            return stubAccessPassword;
        }

        public void setStubAccessPassword(String stubAccessPassword) {
            this.stubAccessPassword = stubAccessPassword;
        }

        @XmlElement(name = "MaximumTotalHttpConections")
        public int getMaximumTotalHttpConections() {
            return maximumTotalHttpConnections;
        }

        public void setMaximumTotalHttpConections(int maximumTotalHttpConections) {
            this.maximumTotalHttpConnections = maximumTotalHttpConections;
        }

        @XmlElement(name = "MaximumHttpConnectionsPerHost")
        public int getMaximumHttpConnectionsPerHost() {
            return maximumHttpConnectionsPerHost;
        }

        public void setMaximumHttpConnectionsPerHost(int maximumHttpConnectionsPerHost) {
            this.maximumHttpConnectionsPerHost = maximumHttpConnectionsPerHost;
        }

        @XmlElement(name = "config")
        public Config getConfig() {
            return config;
        }

        public void setConfig(Config config) {
            this.config = config;
        }
    }

    public static class Config {

        private boolean alwaysSendMappedLocalSubjectId;
        private boolean localClaimDialect;
        private String inboundAuthType;
        private boolean confidential;
        private String defaultValue;
        private String propertyName;
        private boolean propertyRequired;
        private boolean provisioningEnabled;
        private String provisioningUserStore;
        private boolean saasApp;
        private String localAuthenticatorConfigsDisplayName;
        private boolean localAuthenticatorConfigsEnabled;
        private String localAuthenticatorConfigsName;
        private boolean localAuthenticatorConfigsValid;
        private String localAuthenticatorConfigsAuthenticationType;
        private String username;
        private String password;
        private String oAuthVersion;
        private String grantTypes;
        private boolean pkceMandatory;
        private boolean pkceSupportPlain;

        @XmlElement(name = "AlwaysSendMappedLocalSubjectId")
        public boolean isAlwaysSendMappedLocalSubjectId() {
            return alwaysSendMappedLocalSubjectId;
        }

        public void setAlwaysSendMappedLocalSubjectId(boolean alwaysSendMappedLocalSubjectId) {
            this.alwaysSendMappedLocalSubjectId = alwaysSendMappedLocalSubjectId;
        }

        @XmlElement(name = "LocalClaimDialect")
        public boolean isLocalClaimDialect() {
            return localClaimDialect;
        }

        public void setLocalClaimDialect(boolean localClaimDialect) {
            this.localClaimDialect = localClaimDialect;
        }

        @XmlElement(name = "InboundAuthType")
        public String getInboundAuthType() {
            return inboundAuthType;
        }

        public void setInboundAuthType(String inboundAuthType) {
            this.inboundAuthType = inboundAuthType;
        }

        @XmlElement(name = "Confidential")
        public boolean isConfidential() {
            return confidential;
        }

        public void setConfidential(boolean confidential) {
            this.confidential = confidential;
        }

        @XmlElement(name = "DefaultValue")
        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        @XmlElement(name = "PropertyName")
        public String getPropertyName() {
            return propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        @XmlElement(name = "PropertyRequired")
        public boolean isPropertyRequired() {
            return propertyRequired;
        }

        public void setPropertyRequired(boolean propertyRequired) {
            this.propertyRequired = propertyRequired;
        }

        @XmlElement(name = "ProvisioningEnabled")
        public boolean isProvisioningEnabled() {
            return provisioningEnabled;
        }

        public void setProvisioningEnabled(boolean provisioningEnabled) {
            this.provisioningEnabled = provisioningEnabled;
        }

        @XmlElement(name = "ProvisioningUserStore")
        public String getProvisioningUserStore() {
            return provisioningUserStore;
        }

        public void setProvisioningUserStore(String provisioningUserStore) {
            this.provisioningUserStore = provisioningUserStore;
        }

        @XmlElement(name = "SaasApp")
        public boolean isSaasApp() {
            return saasApp;
        }

        public void setSaasApp(boolean saasApp) {
            this.saasApp = saasApp;
        }

        @XmlElement(name = "LocalAuthenticatorConfigsDisplayName")
        public String getLocalAuthenticatorConfigsDisplayName() {
            return localAuthenticatorConfigsDisplayName;
        }

        public void setLocalAuthenticatorConfigsDisplayName(String localAuthenticatorConfigsDisplayName) {
            this.localAuthenticatorConfigsDisplayName = localAuthenticatorConfigsDisplayName;
        }

        @XmlElement(name = "LocalAuthenticatorConfigsEnabled")
        public boolean isLocalAuthenticatorConfigsEnabled() {
            return localAuthenticatorConfigsEnabled;
        }

        public void setLocalAuthenticatorConfigsEnabled(boolean localAuthenticatorConfigsEnabled) {
            this.localAuthenticatorConfigsEnabled = localAuthenticatorConfigsEnabled;
        }

        @XmlElement(name = "LocalAuthenticatorConfigsName")
        public String getLocalAuthenticatorConfigsName() {
            return localAuthenticatorConfigsName;
        }

        public void setLocalAuthenticatorConfigsName(String localAuthenticatorConfigsName) {
            this.localAuthenticatorConfigsName = localAuthenticatorConfigsName;
        }

        @XmlElement(name = "LocalAuthenticatorConfigsValid")
        public boolean isLocalAuthenticatorConfigsValid() {
            return localAuthenticatorConfigsValid;
        }

        public void setLocalAuthenticatorConfigsValid(boolean localAuthenticatorConfigsValid) {
            this.localAuthenticatorConfigsValid = localAuthenticatorConfigsValid;
        }

        @XmlElement(name = "LocalAuthenticatorConfigsAuthenticationType")
        public String getLocalAuthenticatorConfigsAuthenticationType() {
            return localAuthenticatorConfigsAuthenticationType;
        }

        public void setLocalAuthenticatorConfigsAuthenticationType(String localAuthenticatorConfigsAuthenticationType) {
            this.localAuthenticatorConfigsAuthenticationType = localAuthenticatorConfigsAuthenticationType;
        }

        @XmlElement(name = "UserName")
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @XmlElement(name = "Password")
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @XmlElement(name = "OauthVersion")
        public String getoAuthVersion() {
            return oAuthVersion;
        }

        public void setoAuthVersion(String oAuthVersion) {
            this.oAuthVersion = oAuthVersion;
        }

        @XmlElement(name = "GrantTypes")
        public String getGrantTypes() {
            return grantTypes;
        }

        public void setGrantTypes(String grantTypes) {
            this.grantTypes = grantTypes;
        }

        @XmlElement(name = "PkceMandatory")
        public boolean isPkceMandatory() {
            return pkceMandatory;
        }

        public void setPkceMandatory(boolean pkceMandatory) {
            this.pkceMandatory = pkceMandatory;
        }

        @XmlElement(name = "PkceSupportPlain")
        public boolean isPkceSupportPlain() {
            return pkceSupportPlain;
        }

        public void setPkceSupportPlain(boolean pkceSupportPlain) {
            this.pkceSupportPlain = pkceSupportPlain;
        }
    }

}
