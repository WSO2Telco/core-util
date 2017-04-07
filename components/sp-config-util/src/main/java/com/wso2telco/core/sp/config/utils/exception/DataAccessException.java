package com.wso2telco.core.sp.config.utils.exception;

/**
 * Created by isuru on 3/9/17.
 */
public class DataAccessException extends Exception {

    public DataAccessException() {
    }

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
