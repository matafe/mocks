
package com.matafe.mocks.business;

/**
 * My Domain Service Interface.
 * 
 * @author matafe
 */
public interface IMyDomainService {

	MyDomainObject createDomain(MyDomainObject obj) throws MyDomainException;

	MyDomainObject updateDomain(MyDomainObject obj) throws MyDomainException;

	MyDomainObject findDomainByName(String name) throws MyDomainException;

}
