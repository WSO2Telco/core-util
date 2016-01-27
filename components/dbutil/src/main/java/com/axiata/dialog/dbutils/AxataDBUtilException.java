/*
 * AxataDBUtilException.java
 * Oct 16, 2014  11:56:32 AM
 * Roshan.Saputhanthri
 *
 * Copyright (C) Dialog Axiata PLC. All Rights Reserved.
 */

package com.axiata.dialog.dbutils;

/**
 * <TO-DO> <code>AxataDBUtilException</code>
 * @version $Id: AxataDBUtilException.java,v 1.00.000
 */

public class AxataDBUtilException extends Exception {

    public AxataDBUtilException(String message) {
        super(message);
    }

    public AxataDBUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public AxataDBUtilException(Throwable cause) {
        super(cause);
    }
}