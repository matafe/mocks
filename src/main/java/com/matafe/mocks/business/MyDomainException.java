package com.matafe.mocks.business;

/**
 * My Domain Exception.
 * 
 * @author matafe
 */
public class MyDomainException extends Exception {

	private static final long serialVersionUID = 1L;

	public MyDomainException(String message) {
		super(message);
	}

	public MyDomainException(String message, Throwable cause) {
		super(message, cause);
	}

}
