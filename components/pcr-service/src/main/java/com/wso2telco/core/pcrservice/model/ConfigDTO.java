package com.wso2telco.core.pcrservice.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

/**
 * @author nuwan
 *
 */
public class ConfigDTO extends Configuration implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8356032431902326005L;
	

	@JsonProperty
	private DataSourceFactory database = new DataSourceFactory();
	
	public DataSourceFactory getDataSourceFactory() {
		return database;
	}

}
