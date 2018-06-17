package com.matafe.mocks.business;

/**
 * Object Already Exists Exception.
 * 
 * @author matafe
 */
public class ObjectAlreadyExists extends MyDomainException {

	private static final long serialVersionUID = 1L;

	public ObjectAlreadyExists(String message) {
		super(message);
	}

}
