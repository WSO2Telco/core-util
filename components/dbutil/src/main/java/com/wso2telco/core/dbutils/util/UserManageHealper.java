package com.wso2telco.core.dbutils.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wso2telco.core.dbutils.exception.BusinessException;
import com.wso2telco.core.dbutils.exception.GenaralError;

public class UserManageHealper {
	Log log = LogFactory.getLog(UserManageHealper.class);
	UserManageHealper() {

	}
	
	private static UserManageHealper instace;

	public static synchronized UserManageHealper getInstace() {
		if (instace == null) {
			instace = new UserManageHealper();
		}
		return instace;
	}
	/**
	 * this is for extracting the user from the basic auth string.
	 * eg :Basic YWRtaW46YWRtaW4= 
	 *     return the admin as user
	 * @param authHeader
	 * @return
	 * @throws BusinessException
	 */
	public String getUser( String authHeader) throws BusinessException	{
		/**
		 * validate null
		 */
		if(authHeader==null) {
			log.debug("Auth header is null : "+authHeader);
			throw new BusinessException(GenaralError.AUTH_HEADER_NULL);
		}
		/**
		 * validate auth Header string this need to formated as Basic encodeBase64(userName:password)
		 */
		if(!(authHeader.contains("Basic")||authHeader.contains("basic"))) { //if Basic missing in the string
			log.debug("keyword Basic is missing in the string : "+authHeader);
			throw new BusinessException(GenaralError.INVALID_AUTH_HEADER);
		}
		if(authHeader.length()<=5) { //if encodeBase64(userName:password) missing in the string
			log.debug("encodeBase64(userName:password) is missing in the string : "+authHeader);
			throw new BusinessException(GenaralError.INVALID_AUTH_HEADER);
		}
		
		final String  credential= authHeader.substring(5,authHeader.length()-1).trim();
			
		try {
			/**
			 * decode the credential and convert into string
			 */
			final String userPwd = new String(  Base64.getDecoder().decode(credential), "utf-8");
			String [] userPwdArry= userPwd.split(":");
			/**
			 * Split the username:password by :
			 */
			if(userPwdArry.length==2) {//get the user name from the array as first index
				return userPwdArry[0];
			}else {
				
				/**
				 *  if length is less than two, that implies error on format
				 */
				
				log.debug("Invalid format of userName:password  in the string : "+authHeader);
				throw new BusinessException(GenaralError.INVALID_AUTH_HEADER);
			}
		} catch (UnsupportedEncodingException e) {
			log.error("invalid Auth header format" +authHeader,e);
			throw new BusinessException(GenaralError.INVALID_AUTH_HEADER);
		}
	}
	
	
}
