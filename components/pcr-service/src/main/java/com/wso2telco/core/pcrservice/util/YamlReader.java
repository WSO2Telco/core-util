package com.wso2telco.core.pcrservice.util;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.wso2telco.core.pcrservice.model.ConfigPojo;

public class YamlReader {

	private static Logger log = LoggerFactory.getLogger(YamlReader.class);
	
	public static ConfigPojo getConfiguration(){
		
		File file = new File("deploy/config.yml");
		ConfigPojo configPojo = new ConfigPojo();
		
		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory()); // jackson databind
	    
	    try {
			configPojo = mapper.readValue(file, ConfigPojo.class);
		} catch (JsonParseException e) {
			log.error("Yaml Parsing Error",e);			
		} catch (JsonMappingException e) {			
			log.error("Yaml Mapping Error",e);	
		} catch (IOException e) {			
			log.error("Yaml File Error",e);	
		}		
	    
	    return configPojo;
	}
}
