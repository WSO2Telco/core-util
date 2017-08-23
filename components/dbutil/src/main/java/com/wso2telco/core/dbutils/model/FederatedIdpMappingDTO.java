package com.wso2telco.core.dbutils.model;

public class FederatedIdpMappingDTO {

    private String operator;
    private String fidp_authCode;
    private String accessToken;
    private String fidp_accessToken;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getFidpAuthCode() {
        return fidp_authCode;
    }

    public void setFidpAuthCode(String fipd_authCode) {
        this.fidp_authCode = fipd_authCode;
    }

    public String getFidpAccessToken() {
        return fidp_accessToken;
    }

    public void setFidpAccessToken(String fidp_accessToken) {
        this.fidp_accessToken = fidp_accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
