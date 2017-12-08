package com.wso2telco.core.userprofile.cache;

import com.wso2telco.core.dbutils.exception.BusinessException;
import com.wso2telco.core.userprofile.dto.UserProfileDTO;

public interface UserProfileCachable {
	public UserProfileDTO get(String key) throws BusinessException;
	public void cache() throws BusinessException;

}
