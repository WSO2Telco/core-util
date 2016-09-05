/*******************************************************************************
 * Copyright  (c) 2015-2016, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 * 
 * WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
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
package com.wso2telco.core.mnc.resolver.dnsssl;

 



import java.util.ArrayList;
import java.util.List;

 

// TODO: Auto-generated Javadoc
/**
 * The Class DNSQueryResult.
 */
public class DNSQueryResult implements DNSResponseCode {

	/** The rcode. */
	private RCODE rcode;

	/** The naptr string. */
	private String naptrString;

	/** The country code. */
	private String countryCode;

	/** The tn. */
	private String tn;

	/** The message id. */
	private int messageId;

	/** The naptr array. */
	List<String> naptrArray = new ArrayList<String>();

	 
	/**
	 * Gets the naptr array.
	 *
	 * @return the naptr array
	 */
	public List<String> getNaptrArray() {
		return naptrArray;
	}

	 
	/**
	 * Sets the naptr array.
	 *
	 * @param naptrArray the new naptr array
	 */
	public void setNaptrArray(final ArrayList<String> naptrArray) {
		this.naptrArray = naptrArray;
	}

	 
	/**
	 * Gets the message id.
	 *
	 * @return the message id
	 */
	public int getMessageId() {
		return messageId;
	}

	 
	/**
	 * Sets the message id.
	 *
	 * @param messageId the new message id
	 */
	public void setMessageId(final int messageId) {
		this.messageId = messageId;
	}

	 
	/**
	 * Gets the country code.
	 *
	 * @return the country code
	 */
	public String getCountryCode() {
		return countryCode;
	}

	 
	/**
	 * Sets the country code.
	 *
	 * @param countryCode the new country code
	 */
	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	 
	/**
	 * Gets the tn.
	 *
	 * @return the tn
	 */
	public String getTn() {
		return tn;
	}

	 
	/**
	 * Sets the tn.
	 *
	 * @param tn the new tn
	 */
	public void setTn(final String tn) {
		this.tn = tn;
	}

	 
	/**
	 * Gets the rcode.
	 *
	 * @return the rcode
	 */
	public RCODE getRcode() {
		return rcode;
	}

	 
	/**
	 * Sets the rcode.
	 *
	 * @param rcode the new rcode
	 */
	public void setRcode(final RCODE rcode) {
		this.rcode = rcode;
	}

	 
	/**
	 * Gets the naptr string.
	 *
	 * @return the naptr string
	 */
	public String getNaptrString() {
		return naptrString;
	}

	 
	/**
	 * Sets the naptr string.
	 *
	 * @param s the new naptr string
	 */
	public void setNaptrString(final String s) {
		naptrString = s;
	}

	 
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((countryCode == null) ? 0 : countryCode.hashCode());
		result = (prime * result) + messageId;
		result = (prime * result)
				+ ((naptrArray == null) ? 0 : naptrArray.hashCode());
		result = (prime * result)
				+ ((naptrString == null) ? 0 : naptrString.hashCode());
		result = (prime * result) + ((rcode == null) ? 0 : rcode.hashCode());
		result = (prime * result) + ((tn == null) ? 0 : tn.hashCode());
		return result;
	}

	 
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DNSQueryResult)) {
			return false;
		}
		DNSQueryResult other = (DNSQueryResult) obj;
		if (countryCode == null) {
			if (other.countryCode != null) {
				return false;
			}
		} else if (!countryCode.equals(other.countryCode)) {
			return false;
		}
		if (messageId != other.messageId) {
			return false;
		}
		if (naptrArray == null) {
			if (other.naptrArray != null) {
				return false;
			}
		} else if (!naptrArray.equals(other.naptrArray)) {
			return false;
		}
		if (naptrString == null) {
			if (other.naptrString != null) {
				return false;
			}
		} else if (!naptrString.equals(other.naptrString)) {
			return false;
		}
		if (rcode != other.rcode) {
			return false;
		}
		if (tn == null) {
			if (other.tn != null) {
				return false;
			}
		} else if (!tn.equals(other.tn)) {
			return false;
		}
		return true;
	}
}
