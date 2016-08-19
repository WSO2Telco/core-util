package com.wso2telco.core.pcrservice.conf;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wso2telco.core.pcrservice.dao.ApplicationDAO;
import com.wso2telco.core.pcrservice.model.ApplicationDTO;
import com.wso2telco.core.pcrservice.model.ConfigDTO;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class Initializer extends Application<ConfigDTO> {

	private static Logger log = LoggerFactory.getLogger(Initializer.class);
	
	@Override
	public void run(ConfigDTO configuration, Environment environment)
			throws Exception {
		/**
		 * initialize configuration reading
		 */
		ConfigReader.init(configuration, environment);
		
	}
	
	public static void main(String[] args) {
		
		try {
			new Initializer().run("server","deploy/config.yml");
			
			ApplicationDTO applicationDTO = new ApplicationDTO();
			applicationDTO.setAppId("1234567890");
			
			ApplicationDAO applicationDAO = new ApplicationDAO();
			applicationDAO.saveNewApplication(applicationDTO);
			
		} catch (Exception e) {			
			log.error("Initialization Error",e);
		}
	}

}
