package com.wso2telco.core.pcrservice.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.db.DataSourceFactory;

public class ConfigPojo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8356032431902326005L;
	

	@JsonProperty
	private DataSourceFactory database = new DataSourceFactory();
	
	@JsonProperty
	private Object server;
	
	@JsonProperty
	private Object logging;
	
	public DataSourceFactory getDataSourceFactory() {
		return database;
	}

}
