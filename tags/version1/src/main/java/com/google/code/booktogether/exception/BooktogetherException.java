package com.google.code.booktogether.exception;

public class BooktogetherException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 단순에러 발생
	 */
	public BooktogetherException() {
		super();
	}
	
	/**
	 * 메세지출력
	 * @param message
	 */
	public BooktogetherException(String message) {
		super(message);
	}
	
	/**
	 * 메세지와 오류정보
	 * @param message
	 * @param cause
	 */
	public BooktogetherException(String message,Throwable cause) {
		super(message,cause);
	}
	
	
}
