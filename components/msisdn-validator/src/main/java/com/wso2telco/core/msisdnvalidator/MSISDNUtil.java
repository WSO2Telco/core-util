package com.wso2telco.core.msisdnvalidator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

/**
 * Wrap the google phonenumber utility
 *
 */
public class MSISDNUtil {
	PhoneNumberUtil util = null;
	{
		util = PhoneNumberUtil.getInstance();
	}

	/**
	 * parse the given text into phone number using google phonenumber lib.
	 * it is mandatory to have + sign in the rowmsisdn string.
	 * @param rowmsisdn
	 * @return MSISDN
	 * @throws InvalidMSISDNException
	 */
	public MSISDN parse(String rowmsisdn) throws InvalidMSISDNException {
		try {
			PhoneNumber number = util.parse(rowmsisdn, null);
			MSISDN msisdn = new MSISDN(number.getCountryCode(), number.getNationalNumber());
			return msisdn;
		} catch (NumberParseException e) {
			throw new InvalidMSISDNException(Google2InternalErrorMapper.mapErrorType(e.getErrorType()) );
		}
	}
}
