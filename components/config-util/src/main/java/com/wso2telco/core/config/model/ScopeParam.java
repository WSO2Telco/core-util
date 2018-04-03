/*******************************************************************************
 * Copyright  (c) 2015-2018, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
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

import java.util.EnumSet;
import java.util.List;

//todo: this class is duplicated also in gsma authenticator. need to move to core-utils
public class ScopeParam {

    public enum MsisdnMismatchResultTypes {
        ERROR_RETURN,
        OFFNET_FALLBACK,
        OFFNET_FALLBACK_TRUST_LOGINHINT,
        OFFNET_FALLBACK_TRUST_HEADER,
        CONTINUE_WITH_HEADER
    }

    public enum HeFailureResults {
        BREAK,
        UNTRUST_MSISDN,
        TRUST_HEADER_MSISDN,
        TRUST_LOGINHINT_MSISDN
    }

    public enum scopeTypes {
        MAIN,
        APICONSENT
    }

    private boolean isLoginHintMandatory;
    private boolean isHeaderMsisdnMandatory;
    private List<LoginHintFormatDetails> loginHintFormat;
    private boolean isTncVisible;
    private MsisdnMismatchResultTypes msisdnMismatchResult;
    private HeFailureResults heFailureResult;
    private String scope;
    private int scopeId;
    private boolean isConsentPage;
    private String description;
    private String consentType;
    private String consentValidityType;

    public boolean isConsentPage() {
		return isConsentPage;
	}

	public void setConsentPage(boolean isConsentPage) {
		this.isConsentPage = isConsentPage;
	}

	public int getScopeId() {
		return scopeId;
	}

	public void setScopeId(int scopeId) {
		this.scopeId = scopeId;
	}

	public List<LoginHintFormatDetails> getLoginHintFormat() {
        return loginHintFormat;
    }

    public boolean isLoginHintMandatory() {
        return isLoginHintMandatory;
    }

    public boolean isHeaderMsisdnMandatory() { return isHeaderMsisdnMandatory; }

    public MsisdnMismatchResultTypes getMsisdnMismatchResult() {
        return msisdnMismatchResult;
    }

    public String getScope() { return scope; }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public HeFailureResults getHeFailureResult() {
        return heFailureResult;
    }

    public void setHeFailureResult(HeFailureResults heFailureResult) {
        this.heFailureResult = heFailureResult;
    }

    public boolean isTncVisible() {
        return isTncVisible;
    }
    private EnumSet<scopeTypes> scopeTypesList;

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

    public void setMsisdnMismatchResult(MsisdnMismatchResultTypes msisdnMismatchResult) {
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

    public String getConsentValidityType() {
        return consentValidityType;
    }

    public void setConsentValidityType(String consentValidityType) {
        this.consentValidityType = consentValidityType;
    }

    public EnumSet<scopeTypes> getScopeTypesList() {  return scopeTypesList;  }

    public void setScopeTypesList(EnumSet<scopeTypes> scopeTypesList) {    this.scopeTypesList = scopeTypesList;  }
}
