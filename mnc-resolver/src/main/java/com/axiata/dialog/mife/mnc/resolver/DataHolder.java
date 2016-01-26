package com.axiata.dialog.mife.mnc.resolver;


public class DataHolder {

    private MCCConfiguration mobileMccConfig;

    private static DataHolder thisInstance = new DataHolder();

    private DataHolder() {}

    public static DataHolder getInstance() {
        return thisInstance;
    }

    public MCCConfiguration getMobileCountryConfig() {
        return mobileMccConfig;
    }

    public void setMobileCountryConfig(MCCConfiguration mobileCountryConfig) {
        this.mobileMccConfig = mobileCountryConfig;
    }
}
