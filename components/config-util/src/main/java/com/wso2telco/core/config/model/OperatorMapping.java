package com.wso2telco.core.config.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public  class OperatorMapping {

    private String operator;
    private String senderAddress;
    private String operatorCode;
    private String senderName;

    @XmlAttribute(name = "operator")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @XmlElement(name = "senderAddress")
    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    @XmlElement(name = "operatorCode")
    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    @XmlElement(name = "senderName")
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String operatorName) {
        this.senderName = operatorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OperatorMapping)) return false;

        OperatorMapping that = (OperatorMapping) o;

        return getOperator().equals(that.getOperator());
    }

    @Override
    public int hashCode() {
        return getOperator().hashCode();
    }
}