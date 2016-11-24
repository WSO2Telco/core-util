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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class LOAConfig.
 */
@XmlRootElement(name = "LOAConfiguration")
public class LOAConfig {
	
    /** The log. */
    private static Log log = LogFactory.getLog(LOAConfig.class); 

	/** The loas. */
	private List<LOA> loas;

	/**
	 * Gets the loas.
	 *
	 * @return the loas
	 */
	@XmlElement(name = "LOA", nillable = false)
	public List<LOA> getLoas() {
		return loas;
	}

	/**
	 * Sets the loas.
	 *
	 * @param loas the new loas
	 */
	public void setLoas(List<LOA> loas) {
		this.loas = loas;
	}

	/**
	 * Gets the loa.
	 *
	 * @param level the level
	 * @return the loa
	 */
	public LOA getLOA(String level) {
		for (LOA loa : loas) {
			if (loa.getLevel().equals(level)) {
				return loa;
			}
		}
		return null;
	}

	/**
	 * Inits the.
	 */
	public void init() {
		for (LOA loa : loas) {
			loa.init();
		}
	}

	/**
	 * Prints the.
	 */
	public void print() {
		for (LOA loa : loas) {
			List<Authenticators> l = loa.getAuthentication().getAuthenticatorList();
			if(log.isDebugEnabled()){
				log.debug(l.size());
			}
			for (Authenticators s : l) {
				List<Authenticator> d = s.getAuthenticators();
				for (Authenticator v : d) {
					if(log.isDebugEnabled()){
						log.debug(v.getAuthenticatorName());
					}
				}
			}
		}
	}

}
 
