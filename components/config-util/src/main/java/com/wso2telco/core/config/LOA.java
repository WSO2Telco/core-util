/*******************************************************************************
 * Copyright (c) 2015-2016, WSO2.Telco Inc. (http://www.wso2telco.com) 
 * 
 * All Rights Reserved. WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.wso2telco.core.config;

import org.wso2.carbon.identity.application.authentication.framework.ApplicationAuthenticator;
import org.wso2.carbon.identity.application.authentication.framework.util.FrameworkUtils;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class LOA.
 */
public class LOA {

	/** The level. */
	private String level;
	
	/** The authentication. */
	private Authentication authentication;

	/** The authenticators. */
	private List<MIFEAbstractAuthenticator> authenticators = null;

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	@XmlElement(name = "Level")
	public String getLevel() {
		return level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * Gets the authentication.
	 *
	 * @return the authentication
	 */
	@XmlElement(name = "Authentication", nillable = false)
	public Authentication getAuthentication() {
		return authentication;
	}

	/**
	 * Sets the authentication.
	 *
	 * @param authentication the new authentication
	 */
	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	/**
	 * Inits the.
	 */
	public void init() {
		authenticators = new ArrayList<MIFEAbstractAuthenticator>();
		List<Authenticators> authenticatorConfigs = authentication.getAuthenticatorList();

		for (Authenticators a : authenticatorConfigs) {
			List<Authenticator> s = a.getAuthenticators();
			for (Authenticator authenticator : s) {
				MIFEAbstractAuthenticator mifeAuth = new MIFEAbstractAuthenticator();
				mifeAuth.setAuthenticator(FrameworkUtils.getAppAuthenticatorByName(authenticator
						.getAuthenticatorName()));
				mifeAuth.setOnFailAction(authenticator.getOnfail());
				authenticators.add(mifeAuth);
			}
		}
	}

	/**
	 * Gets the authenticators.
	 *
	 * @return the authenticators
	 */
	public List<MIFEAbstractAuthenticator> getAuthenticators() {
		return authenticators;
	}

	/**
	 * The Class MIFEAbstractAuthenticator.
	 */
	// Inner type to handle authenticators and respective external attributes
	public class MIFEAbstractAuthenticator {
		
		/** The authenticator. */
		private ApplicationAuthenticator authenticator;
		
		/** The on fail action. */
		private String onFailAction;

		/**
		 * Gets the authenticator.
		 *
		 * @return the authenticator
		 */
		public ApplicationAuthenticator getAuthenticator() {
			return authenticator;
		}

		/**
		 * Sets the authenticator.
		 *
		 * @param authenticator the new authenticator
		 */
		public void setAuthenticator(ApplicationAuthenticator authenticator) {
			this.authenticator = authenticator;
		}

		/**
		 * Gets the on fail action.
		 *
		 * @return the on fail action
		 */
		public String getOnFailAction() {
			return onFailAction;
		}

		/**
		 * Sets the on fail action.
		 *
		 * @param onFailAction the new on fail action
		 */
		public void setOnFailAction(String onFailAction) {
			this.onFailAction = onFailAction;
		}
	}

}
