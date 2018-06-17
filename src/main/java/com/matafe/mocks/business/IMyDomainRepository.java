package com.matafe.mocks.business;

/**
 * My Domain Repository Interface.
 * 
 * @author matafe
 */
public interface IMyDomainRepository {

	MyDomainObject find(String name) throws MyDomainException;

	MyDomainObject save(MyDomainObject obj) throws MyDomainException;

}