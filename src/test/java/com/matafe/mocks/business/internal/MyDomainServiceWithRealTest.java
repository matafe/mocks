package com.matafe.mocks.business.internal;

import org.junit.Before;
import org.junit.Test;

import com.matafe.mocks.business.IMyDomainService;
import com.matafe.mocks.business.MyDomainException;
import com.matafe.mocks.business.MyDomainObject;

/**
 * Simple Test with with a Real Repository.
 * 
 * @author matafe
 */
public class MyDomainServiceWithRealTest {

	IMyDomainService myDomainService;

	@Before
	public void setUp() throws Exception {
		this.myDomainService = new MyDomainService();
	}

	@Test(expected = MyDomainException.class)
	public void shouldCreateDomain() throws Exception {
		MyDomainObject obj = new MyDomainObject();
		obj.setName("Domain01");
		obj.setValue("Domain Test Save 01");
		myDomainService.createDomain(obj);

	}

	@Test(expected = MyDomainException.class)
	public void shouldFindDomainByName() throws Exception {
		MyDomainObject obj = new MyDomainObject("Domain01", "Domain Test Save 01");
		MyDomainObject saved = myDomainService.createDomain(obj);
		myDomainService.findDomainByName(saved.getName());
	}

}
