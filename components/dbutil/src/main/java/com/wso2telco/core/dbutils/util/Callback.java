package com.wso2telco.core.dbutils.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "payload",
        "success",
        "message"
})
public class Callback {

    @JsonProperty("payload")
    private Object payload;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("message")
    private String message;

    @JsonProperty("payload")
    public Object getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public Callback setPayload(Object payload) {
        this.payload = payload;
        return this;
    }

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public Callback setSuccess(Boolean success) {
        this.success = success;
        return this;

    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public Callback setMessage(String message) {
        this.message = message;
        return this;
    }
}