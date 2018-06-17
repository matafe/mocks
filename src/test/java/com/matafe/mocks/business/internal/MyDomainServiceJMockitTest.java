package com.matafe.mocks.business.internal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.matafe.mocks.business.IMyDomainRepository;
import com.matafe.mocks.business.IMyDomainService;
import com.matafe.mocks.business.MyDomainObject;
import com.matafe.mocks.business.ObjectAlreadyExists;
import com.matafe.mocks.business.ObjectNotFound;

/**
 * Unit test for <code>MyDomainService</code> using mocks with <b>JMockit</b>.
 * 
 * @author matafe
 */
@RunWith(mockit.integration.junit4.JMockit.class)
public class MyDomainServiceJMockitTest {

	@mockit.Tested
	IMyDomainService myDomainService;

	@mockit.Injectable
	IMyDomainRepository repositoryMock;

	@mockit.Injectable
	MyDomainObject objMock;

	@Before
	public void setUp() throws Exception {
		this.myDomainService = new MyDomainService(repositoryMock);
	}

	@Test
	public void shouldCreateDomain() throws Exception {
		final String name = "Domain01";

		// Setup mock calls
		new mockit.Expectations() {
			{
				objMock.getName();
				result = name;

				repositoryMock.find(name);
				result = null;

				repositoryMock.save(objMock);
				result = objMock;
			}
		};

		// Call the method under the test.
		MyDomainObject saved = myDomainService.createDomain(objMock);

		// Verify the call made.
		new mockit.Verifications() {
			{
				repositoryMock.find(name);
				repositoryMock.save(objMock);
			}
		};

		assertIsCreated(saved);
	}

	@Test(expected = ObjectAlreadyExists.class)
	public void shouldCreateDomainDuplicated() throws Exception {
		final String name = "Domain01";

		// Setup mock calls
		new mockit.Expectations() {
			{
				objMock.getName();
				result = name;

				repositoryMock.find(name);
				result = objMock;
			}
		};

		// Call the method under the test.
		myDomainService.createDomain(objMock);

		// Verify the call made.
		new mockit.Verifications() {
			{
				repositoryMock.find(name);
				// repositoryMock.save(objMock);
			}
		};
	}

	@Test
	public void shouldUpdateDomain() throws Exception {
		final String name = "Domain01";
		final String value = "value";

		// Setup mock calls
		new mockit.Expectations() {
			{
				objMock.getName();
				result = name;

				repositoryMock.find(name);
				result = objMock;

				objMock.getValue();
				result = value;

				repositoryMock.save(objMock);
				result = objMock;
			}
		};

		// Call the method under the test.
		MyDomainObject updated = myDomainService.updateDomain(objMock);

		// Verify the call made.
		new mockit.Verifications() {
			{
				repositoryMock.find(name);
				repositoryMock.save(objMock);
			}
		};

		assertIsUpdated(updated);
	}

	@Test
	public void shouldFindDomainByName() throws Exception {
		final String name = "Domain01";

		// Setup mock calls
		new mockit.Expectations() {
			{
				repositoryMock.find(name);
				result = objMock;

				objMock.getId();
				result = 1L;
			}
		};

		// Call the method under the test.
		MyDomainObject found = myDomainService.findDomainByName(name);

		// Verify the call made.
		new mockit.Verifications() {
			{
				repositoryMock.find(name);
			}
		};

		assertThat(found.getId(), equalTo(objMock.getId()));
	}

	@Test(expected = ObjectNotFound.class)
	public void shouldFindDomainByNameNotFound() throws Exception {
		final String name = "Domain01";

		// Setup mock calls
		new mockit.Expectations() {
			{
				repositoryMock.find(name);
				result = null;
			}
		};

		// Call the method under the test.
		myDomainService.findDomainByName(name);

		// Verify the call made.
		new mockit.Verifications() {
			{
				repositoryMock.find(name);
			}
		};
	}

	// @Test
	// @Ignore
	// public void shouldProcess() {
	// fail("Not yet implemented");
	// }

	private void assertIsCreated(MyDomainObject obj) {
		assertThat(obj.getId(), notNullValue());
		assertThat(obj.getCreatedDate(), notNullValue());
	}

	private void assertIsUpdated(MyDomainObject obj) {
		assertThat(obj.getId(), notNullValue());
		assertThat(obj.getCreatedDate(), notNullValue());
		assertThat(obj.getUpdatedDate(), notNullValue());
	}
}