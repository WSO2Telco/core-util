package com.wso2telco.core.pcrservice.conf;

import java.io.Serializable;

import com.wso2telco.core.pcrservice.model.ConfigDTO;

import io.dropwizard.setup.Environment;

public class ConfigReader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8945346314273239957L;
	private ConfigDTO configDTO;
	private static ConfigReader reader;
	private Environment env;


	private ConfigReader(final ConfigDTO configDTO,final Environment env) {
		this.configDTO = configDTO;
		this.env= env;

	}
	public static void init(final ConfigDTO configDTO,final Environment env){
		reader = new ConfigReader(configDTO,env);
	}


	public static synchronized ConfigReader getInstance() {
		return reader;
	}
	
	public ConfigDTO getConfigDTO(){
		return configDTO;
	}
	
	public Environment getEnvironment(){
		return env;
	}
}
