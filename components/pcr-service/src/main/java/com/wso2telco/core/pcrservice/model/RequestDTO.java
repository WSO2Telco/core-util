package com.wso2telco.core.pcrservice.model;

/**
 * The Class RequestDTO.
 */
public class RequestDTO {

	/** The user id. */
	private String userId;
	
	/** The app id. */
	private String appId;
	
	/** The sector id. */
	private String sectorId;
	
	/** The is related. */
	private boolean isRelated;

	/**
	 * Instantiates a new request DTO.
	 *
	 * @param userId the user id
	 * @param appId the app id
	 * @param sectorId the sector id
	 * @param isRelated the is related
	 */
	public RequestDTO(String userId, String appId, String sectorId, boolean isRelated) {
		this.userId = userId;
		this.appId = appId;
		this.sectorId = sectorId;
		this.isRelated = isRelated;
	}
	
	/**
	 * Instantiates a new request DTO.
	 *
	 * @param userId the user id
	 * @param appId the app id
	 * @param sectorId the sector id
	 */
	public RequestDTO(String userId, String appId, String sectorId) {
		this.userId = userId;
		this.appId = appId;
		this.sectorId = sectorId;
		this.isRelated=true;
	}
	
		
	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}
		
	/**
	 * Gets the app id.
	 *
	 * @return the app id
	 */
	public String getAppId() {
		return appId;
	}	
	
	/**
	 * Gets the sector id.
	 *
	 * @return the sector id
	 */
	public String getSectorId() {
		return sectorId;
	}	
	
	/**
	 * Checks if is related.
	 *
	 * @return true, if is related
	 */
	public boolean isRelated() {
		return isRelated;
	}	
}
