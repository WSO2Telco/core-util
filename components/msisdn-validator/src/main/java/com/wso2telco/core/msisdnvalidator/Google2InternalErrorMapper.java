package com.wso2telco.core.msisdnvalidator;

import java.util.HashMap;
import java.util.Map;

import com.google.i18n.phonenumbers.NumberParseException.ErrorType;
/**
 * Maps google error at google NumberPaserException to internal error 
 *
 */
final class Google2InternalErrorMapper {
	
	static Map<ErrorType, InvalidMSISDNException.ErrorType> errorMapper = new HashMap<ErrorType, InvalidMSISDNException.ErrorType>();
	static{
		errorMapper.put(ErrorType.INVALID_COUNTRY_CODE, InvalidMSISDNException.ErrorType.INVALID_COUNTRY_CODE);
		errorMapper.put(ErrorType.NOT_A_NUMBER, InvalidMSISDNException.ErrorType.NOT_A_NUMBER);
		errorMapper.put(ErrorType.TOO_LONG, InvalidMSISDNException.ErrorType.TOO_LONG);
		errorMapper.put(ErrorType.TOO_SHORT_AFTER_IDD, InvalidMSISDNException.ErrorType.TOO_SHORT_AFTER_IDD);
		errorMapper.put(ErrorType.TOO_SHORT_NSN, InvalidMSISDNException.ErrorType.TOO_SHORT_NSN);
	}

	public static InvalidMSISDNException.ErrorType mapErrorType(ErrorType errorType){
		return errorMapper.get(errorType);
	}
}
