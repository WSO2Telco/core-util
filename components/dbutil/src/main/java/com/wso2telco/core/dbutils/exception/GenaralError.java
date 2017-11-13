package com.wso2telco.core.dbutils.exception;

import com.wso2telco.core.dbutils.exception.ThrowableError;

public enum GenaralError implements ThrowableError {

    UNDEFINED("CORE0001", "Undefined Error"), 
    INTERNAL_SERVER_ERROR("CORE0299", "Internal Server Error"),
    AUTH_HEADER_NULL("CORE0300", "Auth Header is null"),
    INVALID_AUTH_HEADER("CORE0301", "Auth Header needs to be Basic encodeBase64(username:password)");

    private String code;
    private String desc;

    GenaralError(final String code, final String desc) {
        this.desc = desc;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.desc;
    }

    @Override
    public String getCode() {
        return this.code;
    }
}
