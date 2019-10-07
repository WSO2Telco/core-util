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
			rowmsisdn = appendPlusIfNotExist(rowmsisdn);
			PhoneNumber number = util.parse(rowmsisdn, null);
			MSISDN msisdn = new MSISDN(number.getCountryCode(), number.getNationalNumber());
			return msisdn;
		} catch (NumberParseException e) {
			throw new InvalidMSISDNException(Google2InternalErrorMapper.mapErrorType(e.getErrorType()) );
		}
	}

	/**
	 * '+' should be appended to the prefix of the msisdn if it is not present for all valid formats.
	 * @param rawmsisdn
	 * @return
	 */
	private String appendPlusIfNotExist(String rawmsisdn) {


		String formattedNumber = null;
		String validationRegex = "tel\\:[a-zA-Z0-9]+";
		String digitsOnlyRegex = "\\d+";

		if (rawmsisdn != null && rawmsisdn.matches(validationRegex)) {
			StringBuilder builder = new StringBuilder(rawmsisdn);
			builder.insert(4, "+");
			formattedNumber = builder.toString();
		} else if (rawmsisdn != null && rawmsisdn.contains("etel:")) {
			String[] msidn = rawmsisdn.split(":");
			formattedNumber = msidn[0] + "+" + msidn[1];
        } else if (rawmsisdn != null && rawmsisdn.matches(digitsOnlyRegex)) {
			formattedNumber = "+"+rawmsisdn;
        }
		else {
			formattedNumber = rawmsisdn;
		}

		return formattedNumber;
	}
        
        public boolean resourceInMsisdnFormat(String msisdnResurce) {
            return (appendPlusIfNotExist(msisdnResurce) != null );            
        }
}
