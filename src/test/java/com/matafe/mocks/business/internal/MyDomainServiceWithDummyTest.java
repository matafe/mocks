package com.matafe.mocks.business.internal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import com.matafe.mocks.business.IMyDomainService;
import com.matafe.mocks.business.MyDomainObject;
import com.matafe.mocks.business.ObjectAlreadyExists;

/**
 * Simple Test with with a Stub Dummy Repository.
 * 
 * @author matafe
 */
public class MyDomainServiceWithDummyTest {

	IMyDomainService myDomainService;

	@Before
	public void setUp() throws Exception {
		// change me to see real world dep.
		// this.myDomainService = new MyDomainRepositoryReal();
		this.myDomainService = new MyDomainService(new MyDomainRepositoryDummy());
	}

	@Test
	public void shouldCreateDomain() throws Exception {
		MyDomainObject obj = new MyDomainObject();
		obj.setName("Domain01");
		obj.setValue("Domain Test Save 01");
		MyDomainObject saved = myDomainService.createDomain(obj);
		assertIsCreated(saved);
	}

	@Test(expected = ObjectAlreadyExists.class)
	public void shouldCreateDomainDuplicated() throws Exception {
		MyDomainObject obj = new MyDomainObject("Domain01", "Domain Test Save 01");
		MyDomainObject saved = myDomainService.createDomain(obj);
		assertIsCreated(saved);
		myDomainService.createDomain(obj);
	}

	@Test
	public void shouldUpdateDomain() throws Exception {
		MyDomainObject obj = new MyDomainObject("Domain01", "Domain Test Save 01");
		MyDomainObject saved = myDomainService.createDomain(obj);
		saved.setValue(obj.getValue() + " Updated");
		MyDomainObject updated = myDomainService.updateDomain(obj);
		assertIsUpdated(updated);
		assertThat(updated.getValue(), CoreMatchers.containsString("Updated"));
	}

	@Test
	public void shouldFindDomainByName() throws Exception {
		MyDomainObject obj = new MyDomainObject("Domain01", "Domain Test Save 01");
		MyDomainObject saved = myDomainService.createDomain(obj);
		MyDomainObject found = myDomainService.findDomainByName(saved.getName());
		assertThat(found.getId(), equalTo(saved.getId()));
		assertThat(found.getValue(), equalTo(saved.getValue()));
	}

	private void assertIsCreated(MyDomainObject obj) {
		assertThat(obj.getId(), notNullValue());
		assertThat(obj.getCreatedDate(), notNullValue());
		// only for coverage
		assertThat(obj.toString(), notNullValue());
	}

	private void assertIsUpdated(MyDomainObject obj) {
		assertThat(obj.getId(), notNullValue());
		assertThat(obj.getCreatedDate(), notNullValue());
		assertThat(obj.getUpdatedDate(), notNullValue());
	}
}
