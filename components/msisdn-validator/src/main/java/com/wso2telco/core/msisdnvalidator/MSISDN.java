package com.wso2telco.core.msisdnvalidator;

import java.io.Serializable;

public class MSISDN implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2376265894389976503L;

	private long nationalNumber_ = 0L;

	private int countryCode_ = 0;

	private String prefix_ = null;

	public MSISDN(int countryCode, long nationalNumber) {
		this.countryCode_ = countryCode;
		this.nationalNumber_ = nationalNumber;
	}

	public long getNationalNumber() {
		return nationalNumber_;
	}

	public String getPrefix() {
		return prefix_;
	}

	public void setPrefix(String prefix_) {
		this.prefix_ = prefix_;
	}

	public int getCountryCode() {
		return countryCode_;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + countryCode_;
		result = prime * result + (int) (nationalNumber_ ^ (nationalNumber_ >>> 32));

		if (this.prefix_ != null){
			long stringHash = 7L;

			for (int count = 0; count < this.prefix_.length(); count++){
				stringHash = stringHash * prime + this.prefix_.charAt(count);
			}

			result = prime * result + (int) stringHash;
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MSISDN other = (MSISDN) obj;
		if (countryCode_ != other.countryCode_)
			return false;
		if (nationalNumber_ != other.nationalNumber_)
			return false;
		return true;
	}
	

}
