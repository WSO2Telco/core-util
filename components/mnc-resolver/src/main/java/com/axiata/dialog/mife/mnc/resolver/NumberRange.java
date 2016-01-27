/*
 * NumberRange.java
 * Nov 19, 2014  2:14:08 PM
 * Roshan.Saputhanthri
 *
 * Copyright (C) Dialog Axiata PLC. All Rights Reserved.
 */

package com.axiata.dialog.mife.mnc.resolver;

/**
 * <TO-DO> <code>NumberRange</code>
 * @version $Id: NumberRange.java,v 1.00.000
 */
public class NumberRange {

    long rangefrom;
    long rangeto;    
    String mnccode;
    String brand;

    public NumberRange(long rangefrom, long rangeto, String mnccode, String brand) {
        this.rangefrom = rangefrom;
        this.rangeto = rangeto;
        this.mnccode = mnccode;
        this.brand = brand;
    }
    
    public long getRangefrom() {
        return rangefrom;
    }

    public void setRangefrom(long rangefrom) {
        this.rangefrom = rangefrom;
    }

    public long getRangeto() {
        return rangeto;
    }

    public void setRangeto(long rangeto) {
        this.rangeto = rangeto;
    }

    public String getMnccode() {
        return mnccode;
    }

    public void setMnccode(String mnccode) {
        this.mnccode = mnccode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
}
