package com.wso2telco.core.userprofile.cache;

import com.wso2telco.core.dbutils.exception.BusinessException;
import  com.wso2telco.core.dbutils.exception.ThrowableError;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class CacheFactory {

	Log log = LogFactory.getLog(CacheFactory.class);

	/*
	 * chache type cannot be change after server startup
	 */

	private UserProfileCachable cachable;
	
	private static CacheFactory instance;
	
	

	private CacheFactory(CacheType type) throws BusinessException{
		log.debug(" cache factory invoked "+type);
		switch (type) {
		case Memchached:
			throw new BusinessException("memcached not supported yet,factory initialization failed");
//			break;
		case Redis:	
			throw new BusinessException("memcached not supported yet,factory initialization failed");
//			break;
		default:
			cachable = new DefaultCachedService();
			break;
		
		}
	}

	public CacheFactory getInstance(CacheType type)  throws BusinessException{
		if (instance == null) {
			instance = new CacheFactory(type);
		}
		return instance;
	}

	public  UserProfileCachable getService() {
		return cachable;
	}
}
