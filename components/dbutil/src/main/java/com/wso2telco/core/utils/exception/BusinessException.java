package com.wso2telco.core.utils.exception;

public  class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5524636629189260363L;
	private ThrowableError error ;
	
	public BusinessException(ThrowableError error){
		super(error.getMessage());
		this.error =error;
	}
	
	public ThrowableError getErrorType() {
		return this.error;
	}
}
