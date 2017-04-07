package com.wso2telco.core.config.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "AuthenticationLevels")
public class AuthenticationLevels {
    private List<AuthenticationLevel> authenticationLevelList;

    @XmlElement(name = "AuthenticationLevel")
    public List<AuthenticationLevel> getAuthenticationLevelList() {
        return authenticationLevelList;
    }

    public void setAuthenticationLevelList(List<AuthenticationLevel> authenticationLevelList) {
        this.authenticationLevelList = authenticationLevelList;
    }
}