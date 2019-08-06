package com.wso2telco.core.dbutils.exception;

public class BusinessException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -5524636629189260363L;
	private ThrowableError error;

	public BusinessException(ThrowableError error) {
		super(error.getMessage());
		this.error = error;
	}

	public BusinessException( Throwable cause)  {
		super(cause);
	}

  /**
   * Constructor with the support of @{@link ThrowableError} and {@link Throwable}
   *
   * @param cause
   * @param error
   */
	public BusinessException(ThrowableError error, Throwable cause) {
		super(cause);
		this.error = error;
	}

	public BusinessException(final String cause)  {
		this(new ThrowableError() {
			
			@Override
			public String getMessage() {
				return cause;
			}
			
			@Override
			public String getCode() {
				return "Undefined";
			}
		});
	}
	
	public ThrowableError getErrorType() {
		return this.error;
	}
}
