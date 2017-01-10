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

    private String challengeQuestion1;

    private String challengeQuestion2;

    private String challengeAnswer1;

    private String challengeAnswer2;

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

    public String getChallengeQuestion1() {
        return challengeQuestion1;
    }

    public void setChallengeQuestion1(String challengeQuestion1) {
        this.challengeQuestion1 = challengeQuestion1;
    }

    public String getChallengeQuestion2() {
        return challengeQuestion2;
    }

    public String getChallengeAnswer1() {
        return challengeAnswer1;
    }

    public void setChallengeAnswer1(String challengeAnswer1) {
        this.challengeAnswer1 = challengeAnswer1;
    }

    public String getChallengeAnswer2() {
        return challengeAnswer2;
    }

    public void setChallengeAnswer2(String challengeAnswer2) {
        this.challengeAnswer2 = challengeAnswer2;
    }

    public void setChallengeQuestion2(String challengeQuestion2) {
        this.challengeQuestion2 = challengeQuestion2;
    }

    public CurrentStep getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(CurrentStep currentStep) {
        this.currentStep = currentStep;
    }

    public enum CurrentStep {
        REGISTRATION,
        CONFIRMATION,
        LOGIN,
        PIN_RESET,
        PIN_RESET_CONFIRMATION
    }
}
