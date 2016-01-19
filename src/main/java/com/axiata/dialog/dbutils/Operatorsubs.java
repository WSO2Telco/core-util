/*
 * Operatorsubs.java
 * Mar 6, 2014  1:53:16 PM
 * Roshan.Saputhanthri
 *
 * Copyright (C) Dialog Axiata PLC. All Rights Reserved.
 */

package com.axiata.dialog.dbutils;

/**
 * <TO-DO> <code>Operatorsubs</code>
 * @version $Id: Operatorsubs.java,v 1.00.000
 */
public class Operatorsubs {
    String operator;
    String domain;

    public Operatorsubs(String operator, String domain) {
        this.operator = operator;
        this.domain = domain;
    }
        
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
