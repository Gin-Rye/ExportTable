package com.export.base.exception;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 1819169897799074249L;

	public BusinessException() {}
	
	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(Throwable cause) {
		super(cause);
    }
	
	public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
