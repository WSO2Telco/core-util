/*
 * Operatorendpoint.java
 * Mar 6, 2014  3:34:06 PM
 * Roshan.Saputhanthri
 *
 * Copyright (C) Dialog Axiata PLC. All Rights Reserved.
 */

package com.axiata.dialog.dbutils;

import java.io.Serializable;

/**
 * <TO-DO> <code>Operatorendpoint</code>
 * @version $Id: Operatorendpoint.java,v 1.00.000
 */
public class Operatorendpoint implements Serializable {

	private int id;
    int operatorid;
    String operatorcode;
    String api;
    String endpoint;

    public Operatorendpoint(int operatorid, String operatorcode, String api, String endpoint) {
        this.operatorid = operatorid;
        this.operatorcode = operatorcode;
        this.api = api;
        this.endpoint = endpoint;
    }
    
    public int getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(int operatorid) {
        this.operatorid = operatorid;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getOperatorcode() {
        return operatorcode;
    }

    public void setOperatorcode(String operatorcode) {
        this.operatorcode = operatorcode;
    }

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}    
    
}
