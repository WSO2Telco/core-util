package com.wso2telco.core.pcrservice.persistable;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wso2telco.core.pcrservice.dao.ApplicationDAO;
import com.wso2telco.core.pcrservice.dao.SectorDAO;
import com.wso2telco.core.pcrservice.dao.RelationBasedPcrDAOImpl;
import com.wso2telco.core.pcrservice.dao.UserDAO;
import com.wso2telco.core.pcrservice.exception.PCRException;
import com.wso2telco.core.pcrservice.model.ApplicationDTO;
import com.wso2telco.core.pcrservice.model.RequestDTO;
import com.wso2telco.core.pcrservice.model.SectorDTO;
import com.wso2telco.core.pcrservice.model.UserAssignmentDTO;
import com.wso2telco.core.pcrservice.model.UserDTO;

public class PcrService {

	private static Logger log = LoggerFactory.getLogger(PcrService.class);
	
	public void createNewPcrEntry(RequestDTO requestDTO) throws PCRException{
		
		UserDTO userDTO = new UserDTO(requestDTO.getUserId());
		ApplicationDTO applicationDTO = new ApplicationDTO(requestDTO.getAppId());
		SectorDTO sectorDTO = new SectorDTO(requestDTO.getSectorId());
		UserAssignmentDTO userAssignmentDTO = new UserAssignmentDTO();
		
		UserDAO userDAO = new UserDAO();
		ApplicationDAO applicationDAO = new ApplicationDAO();
		SectorDAO sectorDAO = new SectorDAO();
		RelationBasedPcrDAOImpl userAssignmentDAO = new RelationBasedPcrDAOImpl();
		
		int userdid = 0;
		int appdid = 0;
		int sectordid = 0;
		
		try {
			userdid = userDAO.saveNewUser(userDTO);
			appdid = applicationDAO.saveNewApplication(applicationDTO);
			sectordid = sectorDAO.saveNewSector(sectorDTO);
			userAssignmentDTO.setUserdid(userdid);
			userAssignmentDTO.setAppdid(appdid);
			userAssignmentDTO.setSectordid(sectordid);
			userAssignmentDAO.createNewPcrEntry(userAssignmentDTO);
		} catch (SQLException e) {			
			log.error("error in createNewPcrEntry()",e);
			throw new PCRException("error in createNewPcrEntry()",e);
		}
		
	}
	
	public String getExistingPcr(UserAssignmentDTO userAssignmentDTO) throws PCRException{
		RelationBasedPcrDAOImpl userAssignmentDAO = new RelationBasedPcrDAOImpl();
		String pcr = null;
		pcr = userAssignmentDAO.getExistingPCR(userAssignmentDTO);		
		return pcr;
	}
}
