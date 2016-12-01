package com.wso2telco.core.config;

import org.wso2.carbon.identity.application.authentication.framework.ApplicationAuthenticator;

import java.util.List;

public class MIFEAuthentication {
    private String levelToFail;
    private List<MIFEAbstractAuthenticator> authenticatorList = null;

    public String getLevelToFail() {
        return levelToFail;
    }

    public void setLevelToFail(String levelToFail) {
        this.levelToFail = levelToFail;
    }

    public List<MIFEAbstractAuthenticator> getAuthenticatorList() {
        return authenticatorList;
    }

    public void setAuthenticatorList(
            List<MIFEAbstractAuthenticator> authenticatorList) {
        this.authenticatorList = authenticatorList;
    }

    public static class MIFEAbstractAuthenticator {
        private String authenticator;
        private String onFailAction;
        private String supportFlow;

        public MIFEAbstractAuthenticator() {}

        public String getOnFailAction() {
            return onFailAction;
        }

        public void setOnFailAction(String onFailAction) {
            this.onFailAction = onFailAction;
        }

        public String getAuthenticator() {
            return authenticator;
        }

        public void setAuthenticator(
                String authenticator) {
            this.authenticator = authenticator;
        }

        public String getSupportFlow() {
            return supportFlow;
        }

        public void setSupportFlow(String supportFlow) {
            this.supportFlow = supportFlow;
        }
    }
}