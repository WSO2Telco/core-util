package com.wso2telco.core.pcrservice;

import com.wso2telco.core.pcrservice.exception.PCRException;
import com.wso2telco.core.pcrservice.model.RequestDTO;

public interface PCRGeneratable {
	Returnable getPCR(RequestDTO dto) throws PCRException;
}
