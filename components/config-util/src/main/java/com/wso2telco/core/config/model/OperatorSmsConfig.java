package com.wso2telco.core.config.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public  class OperatorSmsConfig {

    private String name;
    private String message;
    private boolean isDisabled;
    private String smsEndpoint;

    @XmlElement(name = "SMSEndpoint")
    public String getSmsEndpoint() {
        return smsEndpoint;
    }

    public void setSmsEndpoint(String smsEndpoint) {
        this.smsEndpoint = smsEndpoint;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "Message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @XmlElement(name = "Disabled")
    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        this.isDisabled = disabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OperatorSmsConfig)) return false;

        OperatorSmsConfig that = (OperatorSmsConfig) o;

        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}