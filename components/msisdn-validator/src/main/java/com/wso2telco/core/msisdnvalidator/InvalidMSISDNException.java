package com.wso2telco.core.msisdnvalidator;

import com.wso2telco.core.dbutils.exception.BusinessException;
import com.wso2telco.core.dbutils.exception.ThrowableError;

public class InvalidMSISDNException extends BusinessException {

	public InvalidMSISDNException(ThrowableError error) {
		super(error);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8888897104816898713L;

	public enum ErrorType implements ThrowableError {
		INVALID_COUNTRY_CODE("MSISDN001", "Invalid country code"),
		// This generally indicates the string passed in had less than 3 digits
		// in it. More
		// specifically, the number failed to match the regular expression
		// VALID_PHONE_NUMBER in
		// PhoneNumberUtil.java.
		NOT_A_NUMBER("MSISDN001", "The string supplied did not seem to be a phone number."),
		// This indicates the string started with an international dialing
		// prefix, but after this was
		// stripped from the number, had less digits than any valid phone number
		// (including country
		// code) could have.
		TOO_SHORT_AFTER_IDD("MSISDN002",
				"Phone number had an IDD, but after this was not " + "long enough to be a viable phone number."),
		// This indicates the string, after any country code has been stripped,
		// had less digits than any
		// valid phone number could have.
		TOO_SHORT_NSN("MSISDN003", "The string supplied is too short to be a phone number."),
		// This indicates the string had more digits than any valid phone number
		// could have.
		TOO_LONG("MSISDN004", "The string supplied was too long to parse.");

		ErrorType(final String code, final String msg) {
			this.code = code;
			this.msg = msg;
		}

		private String msg;
		private String code;

		public String getMessage() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getCode() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	@Override
	public String toString() {
		return "InvalidMSISDNException [getErrorType()=" + getErrorType() + ", getMessage()=" + getMessage() + "]";
	}
	
	
	


}
