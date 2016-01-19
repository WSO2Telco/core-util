/*
 * JdbcException.java
 * Sep 3, 2013  11:41:40 AM
 * Roshan.Saputhanthri
 *
 * Copyright (C) Dialog Axiata PLC. All Rights Reserved.
 */

package com.axiata.dialog.dbutils;

import java.sql.Connection;

class JdbcException extends Exception {

    Connection conn;

    public JdbcException(Exception e) {
        super(e.getMessage());
        conn = null;
    }

    public JdbcException(Exception e, Connection con) {
        super(e.getMessage());
        conn = con;
    }

    public void handle() {
        System.out.println(getMessage());
        System.out.println();

        if (conn != null) {
            try {
                System.out.println("--Rollback the transaction-----");
                conn.rollback();
                System.out.println("  Rollback done!");
            } catch (Exception e) {
            };
        }
    } // handle

    public void handleExpectedErr() {
        System.out.println();
        System.out.println(
                "**************** Expected Error ******************\n");
        System.out.println(getMessage());
        System.out.println(
                "**************************************************");
    } // handleExpectedError
} // JdbcException