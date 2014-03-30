package com.export.exception;

public class BussinessException extends Exception {
	public BussinessException() {}
	
	public BussinessException(String message) {
		this(message, null);
	}
	
	public BussinessException(Throwable cause) {
        this(cause.getMessage(), cause);
    }
	
	public BussinessException(String message, Throwable cause) {
        super(message);
        if(cause != null) {
        	this.setStackTrace(cause.getStackTrace());
        }
    }
}
