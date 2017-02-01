package com.wso2telco.core.config.model;


import java.util.List;

//todo: this class is duplicated also in gsma authenticator. need to move to core-utils
public class ScopeParam {

    public enum msisdnMismatchResultTypes {
        ERROR_RETURN,
        OFFNET_FALLBACK
    }

    public enum heFailureResults{
        BREAK,
        UNTRUST_MSISDN,
        TRUST_MSISDN,
        TRUST_LOGIN_HINT
    }

    private boolean isLoginHintMandatory;
    private List<LoginHintFormatDetails> loginHintFormat;
    private boolean isTncVisible;
    private msisdnMismatchResultTypes msisdnMismatchResult;
    private heFailureResults heFailureResult;

    public void setLoginHintFormat(List<LoginHintFormatDetails> loginHintFormat) {
        this.loginHintFormat = loginHintFormat;
    }

    public List<LoginHintFormatDetails> getLoginHintFormat() {
        return loginHintFormat;
    }

    public void setLoginHintMandatory(boolean isLoginHintMandatory) {
        this.isLoginHintMandatory = isLoginHintMandatory;
    }

    public boolean isLoginHintMandatory() {
        return isLoginHintMandatory;
    }

    public void setMsisdnMismatchResult(msisdnMismatchResultTypes msisdnMismatchResult) {
        this.msisdnMismatchResult = msisdnMismatchResult;
    }

    public msisdnMismatchResultTypes getMsisdnMismatchResult() {
        return msisdnMismatchResult;
    }

    public heFailureResults getHeFailureResult() {
        return heFailureResult;
    }

    public void setHeFailureResult(heFailureResults heFailureResult) {
        this.heFailureResult = heFailureResult;
    }

    public void setTncVisible(boolean isTncVisible) {
        this.isTncVisible = isTncVisible;
    }

    public boolean isTncVisible() {
        return isTncVisible;
    }
}
