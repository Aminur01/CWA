package com.A1.cwa.exception;

public class TestTransactionException extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;
	private final String message;

	public TestTransactionException(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	
}
