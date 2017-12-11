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

import java.util.HashMap;
import java.util.Map;
import com.wso2telco.core.dbutils.exception.BusinessException;
import com.wso2telco.core.userprofile.dto.UserProfileDTO;

class DefaultCachedService extends AbstractCacheServie {

	private Map<String, CacheEntry> localCache;
	// default session expiry is 15 seconds
	private static final long DEFAULTSESSIONEXPIRY = 900000;

	DefaultCachedService() {

		localCache = new HashMap<>();
	}

	@Override
	public void cache(final String key, UserProfileDTO profile) throws BusinessException {

		localCache.put(key.trim(), new CacheEntry(profile));
	}

	@Override
	public boolean isExpired(String key) throws BusinessException {

		boolean returnVal = true;
		if (localCache.containsKey(key)) {

			long created = localCache.get(key).getCreatedTimeinMls();

			boolean expierdStatus = created + DEFAULTSESSIONEXPIRY < System.currentTimeMillis() ? true : false;
			if (expierdStatus) {

				localCache.remove(key);
			} else {

				returnVal = false;
			}
		}

		return returnVal;
	}

	@Override
	protected UserProfileDTO loadProfile(String key) throws BusinessException {

		return localCache.get(key).getValue();
	}

}

class CacheEntry {

	private UserProfileDTO profile;
	private long timeStamp;

	CacheEntry(UserProfileDTO profile) {

		this.profile = profile;
		this.timeStamp = System.currentTimeMillis();
	}

	public UserProfileDTO getValue() {

		return profile;
	}

	public long getCreatedTimeinMls() {

		return this.timeStamp;
	}
}