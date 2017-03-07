package com.wso2telco.core.mi;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class ConfigDTO extends Configuration implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8356032431902326005L;

    private DataSourceFactory dataSourceFactory
            = new DataSourceFactory();

    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

	/*@JsonProperty
	private String host;
	
	@JsonProperty
	private int port;*/

    @JsonProperty
    private Boolean isMaster;


    public Boolean getIsMaster() {
        return isMaster;
    }

    public Boolean isMaster() {
        return isMaster;
    }

    public void setIsMaster(Boolean isMaster) {
        this.isMaster = isMaster;
    }


    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @Override
    public String toString() {
        return "ConfigDTO [database=" + database + ", isMaster=" + isMaster + "]";
    }


}
