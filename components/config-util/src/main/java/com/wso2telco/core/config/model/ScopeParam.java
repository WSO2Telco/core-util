/*******************************************************************************
 * Copyright  (c) 2015-2017, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
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
package com.wso2telco.core.config.model;

import java.util.List;

//todo: this class is duplicated also in gsma authenticator. need to move to core-utils
public class ScopeParam {

    public enum msisdnMismatchResultTypes {
        ERROR_RETURN,
        OFFNET_FALLBACK,
        OFFNET_FALLBACK_TRUST_LOGINHINT,
        OFFNET_FALLBACK_TRUST_HEADER,
        CONTINUE_WITH_HEADER
    }

    public enum heFailureResults {
        BREAK,
        UNTRUST_MSISDN,
        TRUST_HEADER_MSISDN,
        TRUST_LOGINHINT_MSISDN
    }

    private boolean isLoginHintMandatory;
    private boolean isHeaderMsisdnMandatory;
    private List<LoginHintFormatDetails> loginHintFormat;
    private boolean isTncVisible;
    private msisdnMismatchResultTypes msisdnMismatchResult;
    private heFailureResults heFailureResult;
    private String scope;
    private int scope_id;
    private boolean isConsentPage;
    private String description;
    private String consentType;
    private String consent_validity_type;

    public boolean isConsentPage() {
		return isConsentPage;
	}

	public void setConsentPage(boolean isConsentPage) {
		this.isConsentPage = isConsentPage;
	}

	public int getScope_id() {
		return scope_id;
	}

	public void setScope_id(int scope_id) {
		this.scope_id = scope_id;
	}

	public List<LoginHintFormatDetails> getLoginHintFormat() {
        return loginHintFormat;
    }

    public boolean isLoginHintMandatory() {
        return isLoginHintMandatory;
    }

    public boolean isHeaderMsisdnMandatory() { return isHeaderMsisdnMandatory; }

    public msisdnMismatchResultTypes getMsisdnMismatchResult() {
        return msisdnMismatchResult;
    }

    public String getScope() { return scope; }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public heFailureResults getHeFailureResult() {
        return heFailureResult;
    }

    public void setHeFailureResult(heFailureResults heFailureResult) {
        this.heFailureResult = heFailureResult;
    }

    public boolean isTncVisible() {
        return isTncVisible;
    }

    public void setTncVisible(boolean isTncVisible) {
        this.isTncVisible = isTncVisible;
    }

    public void setLoginHintFormat(List<LoginHintFormatDetails> loginHintFormat) {
        this.loginHintFormat = loginHintFormat;
    }

    public void setLoginHintMandatory(boolean isLoginHintMandatory) {
        this.isLoginHintMandatory = isLoginHintMandatory;
    }

    public void setHeaderMsisdnMandatory(boolean headerMsisdnMandatory) {
        isHeaderMsisdnMandatory = headerMsisdnMandatory;
    }

    public void setMsisdnMismatchResult(msisdnMismatchResultTypes msisdnMismatchResult) {
        this.msisdnMismatchResult = msisdnMismatchResult;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public String getConsentType() {
        return consentType;
    }

    public void setConsentType(String consentType) {
        this.consentType = consentType;
    }

    public String getConsent_validity_type() {
        return consent_validity_type;
    }

    public void setConsent_validity_type(String consent_validity_type) {
        this.consent_validity_type = consent_validity_type;
    }

}
