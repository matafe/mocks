package com.matafe.mocks.business.internal;

import org.junit.Before;
import org.junit.Test;

import com.matafe.mocks.business.IMyDomainRepository;
import com.matafe.mocks.business.MyDomainException;
import com.matafe.mocks.business.MyDomainObject;

/**
 * Unit test for <code>MyDomainRepositoryReal</code>.
 * 
 * @author matafe
 */
public class MyDomainRepositoryRealTest {

	private IMyDomainRepository repository;

	@Before
	public void setUp() throws Exception {
		this.repository = new MyDomainRepositoryReal();
	}

	@Test(expected = MyDomainException.class)
	public void shouldFind() throws Exception {
		this.repository.find("something");
	}

	@Test(expected = MyDomainException.class)
	public void shouldSave() throws Exception {
		MyDomainObject obj = new MyDomainObject("Domain01", "Domain Test Save 01");
		this.repository.save(obj);
	}

}
