package com.wso2telco.core.pcrservice.model;

import java.io.Serializable;

/**
 * The Class AppDTO.
 */
public class ApplicationDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4841094865588233257L;

	/** The app id. */
	private String appId;

	/**
	 * Gets the app id.
	 *
	 * @return the app id
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * Sets the app id.
	 *
	 * @param appId the new app id
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}
}
