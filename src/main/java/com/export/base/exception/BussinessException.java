package com.export.base.exception;

public class BussinessException extends Exception {
	private static final long serialVersionUID = 1819169897799074249L;

	public BussinessException() {}
	
	public BussinessException(String message) {
		super(message);
	}
	
	public BussinessException(Throwable cause) {
		super(cause);
    }
	
	public BussinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
