package com.axiata.dialog.mife.mnc.resolver.dnsssl;

/*
 * Copyright (c) 2013 Neustar, Inc.  All Rights Reserved.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL NEUSTAR BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */



import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the DNS query result
 * 
 * @author sali
 * @version $Revision: 1.4 $
 */

public class DNSQueryResult implements DNSResponseCode {

	private RCODE rcode;

	private String naptrString;

	private String countryCode;

	private String tn;

	private int messageId;

	List<String> naptrArray = new ArrayList<String>();

	/**
	 * Method getNaptrArray.
	 * @return ArrayList<String>
	 */
	public List<String> getNaptrArray() {
		return naptrArray;
	}

	/**
	 * Method setNaptrArray.
	 * @param naptrArray ArrayList<String>
	 */
	public void setNaptrArray(final ArrayList<String> naptrArray) {
		this.naptrArray = naptrArray;
	}

	/**
	 * Method getMessageId.
	 * @return int
	 */
	public int getMessageId() {
		return messageId;
	}

	/**
	 * Method setMessageId.
	 * @param messageId int
	 */
	public void setMessageId(final int messageId) {
		this.messageId = messageId;
	}

	/**
	 * Method getCountryCode.
	 * @return String
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Method setCountryCode.
	 * @param countryCode String
	 */
	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * Method getTn.
	 * @return The TN
	 */
	public String getTn() {
		return tn;
	}

	/**
	 * Method setTN
	 * @param number the TN
	 */
	public void setTn(final String tn) {
		this.tn = tn;
	}

	/**
	 * Method getRcode.
	 * @return RCODE
	 */
	public RCODE getRcode() {
		return rcode;
	}

	/**
	 * Method setRcode.
	 * @param rc RCODE
	 */
	public void setRcode(final RCODE rcode) {
		this.rcode = rcode;
	}

	/**
	 * Method getNaptrString.
	 * @return String
	 */
	public String getNaptrString() {
		return naptrString;
	}

	/**
	 * Method setNaptrString.
	 * @param s String
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
