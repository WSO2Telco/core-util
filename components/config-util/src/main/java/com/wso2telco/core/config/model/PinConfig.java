package com.wso2telco.core.config.model;

/**
 * Created by isuru on 1/2/17.
 */
public class PinConfig {

    private String msisdn;

    private String sessionId;

    private int totalAttempts;

    private int invalidFormatAttempts;

    private int pinMismatchAttempts;

    private String registeredPin;

    private String confirmedPin;

    private CurrentStep currentStep;

    public void incrementTotatAttempts() {
        totalAttempts++;
    }

    public void incrementInvalidFormatAttempts() {
        invalidFormatAttempts++;
    }

    public void incrementPinMistmachAttempts() {
        pinMismatchAttempts++;
    }

    public String getRegisteredPin() {
        return registeredPin;
    }

    public void setRegisteredPin(String registeredPin) {
        this.registeredPin = registeredPin;
    }

    public String getConfirmedPin() {
        return confirmedPin;
    }

    public void setConfirmedPin(String confirmedPin) {
        this.confirmedPin = confirmedPin;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public void setTotalAttempts(int totalAttempts) {
        this.totalAttempts = totalAttempts;
    }

    public int getInvalidFormatAttempts() {
        return invalidFormatAttempts;
    }

    public void setInvalidFormatAttempts(int invalidFormatAttempts) {
        this.invalidFormatAttempts = invalidFormatAttempts;
    }

    public int getPinMismatchAttempts() {
        return pinMismatchAttempts;
    }

    public void setPinMismatchAttempts(int pinMismatchAttempts) {
        this.pinMismatchAttempts = pinMismatchAttempts;
    }

    public boolean isPinsMatched() {
        return registeredPin.equalsIgnoreCase(confirmedPin);
    }

    public CurrentStep getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(CurrentStep currentStep) {
        this.currentStep = currentStep;
    }

    public enum CurrentStep{
        REGISTRATION,
        CONFIRMATION,
        LOGIN
    }
}
