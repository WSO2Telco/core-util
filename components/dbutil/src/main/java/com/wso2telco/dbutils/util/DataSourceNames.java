package com.wso2telco.dbutils.util;

@Deprecated
public enum DataSourceNames {

    WSO2AM_DB("jdbc/WSO2AM_DB"),
    WSO2AM_STATS_DB("jdbc/WSO2AM_STATS_DB"),
    WSO2UM_DB("jdbc/WSO2UM_DB"),
    WSO2REG_DB("jdbc/WSO2REG_DB"),
    WSO2TELCO_DEP_DB("jdbc/AXIATA_MIFE_DB");

    private String jndiName;

    private DataSourceNames(String jndiName) {
        this.jndiName = jndiName;
    }

    public String jndiName() {
        return jndiName;
    }
}
