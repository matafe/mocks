package com.matafe.mocks.business;

/**
 * Object Not Found Exception.
 * 
 * @author matafe
 */
public class ObjectNotFound extends MyDomainException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFound(String message) {
		super(message);
	}

}
