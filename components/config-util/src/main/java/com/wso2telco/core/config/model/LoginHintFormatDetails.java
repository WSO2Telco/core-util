package com.wso2telco.core.config.model;

public class LoginHintFormatDetails {
    private loginHintFormatTypes formatType;
    private boolean isEncrypted;
    private String decryptAlgorithm;

    public enum loginHintFormatTypes {
        PLAINTEXT,
        ENCRYPTED,
        MSISDN,
        PCR
    }

    public void setFormatType(loginHintFormatTypes formatType) {
        this.formatType = formatType;
    }

    public loginHintFormatTypes getFormatType() {
        return formatType;
    }

    public void setEncrypted(boolean isEncrypted) {
        this.isEncrypted = isEncrypted;
    }

    public boolean isEncrypted() {
        return isEncrypted;
    }

    public void setDecryptAlgorithm(String decryptAlgorithm) {
        this.decryptAlgorithm = decryptAlgorithm;
    }

    public String getDecryptAlgorithm() {
        return decryptAlgorithm;
    }
}
