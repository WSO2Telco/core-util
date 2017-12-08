package com.wso2telco.core.userprofile.cache;

import com.wso2telco.core.dbutils.exception.BusinessException;
import com.wso2telco.core.userprofile.dto.UserProfileDTO;

abstract class AbstractCacheServie  implements UserProfileCachable {
	
	@Override
	final public UserProfileDTO get(String key) throws BusinessException {
		if(!isExpired(key)) {
			return get(key);
		}
		throw new BusinessException("Session allready expired "+key);
	}
}
