/*
 * Operator.java
 * Mar 3, 2014  9:50:44 AM
 * Roshan.Saputhanthri
 *
 * Copyright (C) Dialog Axiata PLC. All Rights Reserved.
 */

package com.axiata.dialog.dbutils;

/**
 * <TO-DO> <code>Operator</code>
 * @version $Id: Operator.java,v 1.00.000
 */
public class Operator {

    int id,applicationid, operatorid,isactive;
    String operatorname;
    String note;
    String created;
    String created_date;
    String lastupdated;
    String lastupdated_date;
    String refreshtoken;
    long tokenvalidity;
    long tokentime;
    String token;
    String tokenurl;
    String tokenauth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApplicationid() {
        return applicationid;
    }

    public String getOperatorname() {
        return operatorname;
    }

    public void setOperatorname(String operatorname) {
        this.operatorname = operatorname;
    }

    public void setApplicationid(int applicationid) {
        this.applicationid = applicationid;
    }

    public int getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(int operatorid) {
        this.operatorid = operatorid;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(String lastupdated) {
        this.lastupdated = lastupdated;
    }

    public String getLastupdated_date() {
        return lastupdated_date;
    }

    public void setLastupdated_date(String lastupdated_date) {
        this.lastupdated_date = lastupdated_date;
    }

    public String getRefreshtoken() {
        return refreshtoken;
    }

    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }

    public double getTokenvalidity() {
        return tokenvalidity;
    }

    public void setTokenvalidity(long tokenvalidity) {
        this.tokenvalidity = tokenvalidity;
    }

    public double getTokentime() {
        return tokentime;
    }

    public void setTokentime(long tokentime) {
        this.tokentime = tokentime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenurl() {
        return tokenurl;
    }

    public void setTokenurl(String tokenurl) {
        this.tokenurl = tokenurl;
    }

    public String getTokenauth() {
        return tokenauth;
    }

    public void setTokenauth(String tokenauth) {
        this.tokenauth = tokenauth;
    }
    
}
