package com.liam.webfluxdemo.exceptions;

public class InputValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String MSG = "Allowed range is 10 to 20";
	
	private static final int errorCode = 100;
	
	private final int input;

	public InputValidationException(int input) {
		super(MSG);
		this.input = input;
	}
	
	
	

}
