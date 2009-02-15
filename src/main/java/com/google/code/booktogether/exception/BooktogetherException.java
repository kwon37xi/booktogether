package com.google.code.booktogether.exception;

public class BooktogetherException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BooktogetherException() {
		super();
	}
	
	public BooktogetherException(String message) {
		super(message);
	}
	
	public BooktogetherException(String message,Throwable cause) {
		super(message,cause);
	}
	
	
}
