package com.wso2telco.core.config.model;


import java.util.EnumSet;
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

    public enum scopeTypes {
        MAIN,
        APICONSENT
    }

    private boolean isLoginHintMandatory;
    private boolean isHeaderMsisdnMandatory;
    private List<LoginHintFormatDetails> loginHintFormat;
    private boolean isTncVisible;
    private msisdnMismatchResultTypes msisdnMismatchResult;
    private heFailureResults heFailureResult;
    private String scope;
    private boolean isConsentPage;


    public boolean isConsentPage() {
		return isConsentPage;
	}
	public void setConsentPage(boolean isConsentPage) {
		this.isConsentPage = isConsentPage;
	}
	public List<LoginHintFormatDetails> getLoginHintFormat() {
        return loginHintFormat;
    }
    public boolean isLoginHintMandatory() {
        return isLoginHintMandatory;
    }
    public boolean isHeaderMsisdnMandatory() { return isHeaderMsisdnMandatory; }
    public String getScope() { return scope; }
    public msisdnMismatchResultTypes getMsisdnMismatchResult() {
        return msisdnMismatchResult;
    }
    public heFailureResults getHeFailureResult() {
        return heFailureResult;
    }
    public boolean isTncVisible() {
        return isTncVisible;
    }
    private EnumSet<scopeTypes> scopeTypesList;

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

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setHeFailureResult(heFailureResults heFailureResult) {
        this.heFailureResult = heFailureResult;
    }

    public void setTncVisible(boolean isTncVisible) {
        this.isTncVisible = isTncVisible;
    }


    public EnumSet<scopeTypes> getScopeTypesList() {  return scopeTypesList;  }

    public void setScopeTypesList(EnumSet<scopeTypes> scopeTypesList) {    this.scopeTypesList = scopeTypesList;  }
}
