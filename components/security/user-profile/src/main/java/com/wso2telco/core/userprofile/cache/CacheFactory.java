/*******************************************************************************
 * Copyright  (c) 2015-2016, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 * <p>
 * WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.wso2telco.core.userprofile.cache;

import com.wso2telco.core.dbutils.exception.BusinessException;
import com.wso2telco.core.userprofile.util.CacheType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class CacheFactory {

	Log log = LogFactory.getLog(CacheFactory.class);

	/*
	 * chache type cannot be change after server startup
	 */

	private UserProfileCachable cachable;
	private static CacheFactory instance;

	private CacheFactory(CacheType type) throws BusinessException {

		log.debug(" cache factory invoked " + type);
		switch (type) {

		case Memchached:
			throw new BusinessException("memcached not supported yet,factory initialization failed");
			// break;
		case Redis:
			throw new BusinessException("memcached not supported yet,factory initialization failed");
			// break;
		default:
			cachable = new DefaultCachedService();
			break;
		}
	}

	public static CacheFactory getInstance(CacheType type) throws BusinessException {

		if (instance == null) {

			instance = new CacheFactory(type);
		}

		return instance;
	}

	public UserProfileCachable getService() {

		return cachable;
	}
}
