package com.wso2telco.core.userprofile.cache;

import java.util.HashMap;
import java.util.Map;
import com.wso2telco.core.dbutils.exception.BusinessException;
import com.wso2telco.core.userprofile.dto.UserProfileDTO;

class DefaultCachedService extends AbstractCacheServie{
	private  Map<String, CacheEntry> localCache;
	//Default session Expiory is 15 seconds
	final private long defaultSessionExpiory=15000;
	
	DefaultCachedService() {
		localCache = new HashMap<String, CacheEntry>();
	}



	@Override
	public void cache(final String key,UserProfileDTO profile) throws BusinessException {
		 
		localCache.put(key.trim(),new CacheEntry(profile));
	}

	@Override
	public boolean isExpired(String key) throws BusinessException {
		boolean returnVal =true;
		if(localCache.containsKey(key)) {
			long created = localCache.get(key).getCreatedTimeinMls();
			
			
			boolean expierdStatus= created+defaultSessionExpiory >System.currentTimeMillis()?true:false;
			if (expierdStatus) {
				localCache.remove(key);
			}else {
				returnVal=false;
			}
		}
		return returnVal;
	}

}
class CacheEntry {
	
	private UserProfileDTO profile;
	private long timeStamp;
	
	 CacheEntry(UserProfileDTO profile){
		 this.profile =profile;
		 this.timeStamp = System.currentTimeMillis();
	 }
	 
	 public UserProfileDTO getValue() {
		 return profile;
	 }
	 public long getCreatedTimeinMls() {
		 return this.timeStamp;
	 }
	
}