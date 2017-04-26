package com.wso2telco.utils.exception;

import com.wso2telco.utils.exception.ThrowableError;

@Deprecated
public enum GenaralError implements ThrowableError {

    UNDEFINED("CORE0001", "Undefined Error"), INTERNAL_SERVER_ERROR("CORE0299", "Internal Server Error");

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
