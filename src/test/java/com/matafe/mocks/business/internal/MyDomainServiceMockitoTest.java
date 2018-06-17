package com.matafe.mocks.business.internal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

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
 * Unit test for <code>MyDomainService</code> using mocks with <b>Mockito</b>.
 * 
 * @author matafe
 */
@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class MyDomainServiceMockitoTest {

	IMyDomainService myDomainService;

	@org.mockito.Mock
	IMyDomainRepository repositoryMock;

	@org.mockito.Mock
	MyDomainObject objMock;

	@Before
	public void setUp() throws Exception {
		this.myDomainService = new MyDomainService(repositoryMock);
	}

	@Test
	public void shouldCreateDomain() throws Exception {
		final String name = "Domain01";
		
		// Setup mock calls
		org.mockito.Mockito.when(objMock.getName()).thenReturn(name);
		org.mockito.Mockito.when(repositoryMock.find(objMock.getName())).thenReturn(null);
		org.mockito.Mockito.when(repositoryMock.save(objMock)).thenReturn(objMock);
		
		// Call the method under the test.
		MyDomainObject saved = myDomainService.createDomain(objMock);
		
		// Verify the call made.
		org.mockito.Mockito.verify(repositoryMock).find(name);
		org.mockito.Mockito.verify(objMock).setCreatedDate(org.mockito.Matchers.any(Date.class));
		
		org.mockito.Mockito.when(objMock.getId()).thenReturn(1L);
		org.mockito.Mockito.when(objMock.getCreatedDate()).thenReturn(new Date());
		
		assertIsCreated(saved);
	}

	@Test(expected = ObjectAlreadyExists.class)
	public void shouldCreateDomainDuplicated() throws Exception {
		final String name = "Domain01";
		
		// Setup mock calls
		org.mockito.Mockito.when(objMock.getName()).thenReturn(name);
		org.mockito.Mockito.when(repositoryMock.find(objMock.getName())).thenReturn(objMock);
		
		// Call the method under the test.
		myDomainService.createDomain(objMock);
		
		// Verify the call made.
		org.mockito.Mockito.verify(repositoryMock).find(name);
	}

	@Test
	public void shouldUpdateDomain() throws Exception {
		final String name = "Domain01";
		final String value = "value";
		
		// Setup mock calls
		org.mockito.Mockito.when(objMock.getName()).thenReturn(name);
		org.mockito.Mockito.when(repositoryMock.find(name)).thenReturn(objMock);		
		org.mockito.Mockito.when(objMock.getValue()).thenReturn(value);
		org.mockito.Mockito.when(repositoryMock.save(objMock)).thenReturn(objMock);
		
		// Call the method under the test.
		MyDomainObject updated = myDomainService.updateDomain(objMock);
		
		// Verify the call made.
		org.mockito.Mockito.verify(repositoryMock).find(name);
		org.mockito.Mockito.verify(objMock).setValue(org.mockito.Matchers.any(String.class));
		org.mockito.Mockito.verify(objMock).getValue();
		org.mockito.Mockito.verify(objMock).setUpdatedDate(org.mockito.Matchers.any(Date.class));
		org.mockito.Mockito.verify(repositoryMock).save(objMock);
		
		org.mockito.Mockito.when(objMock.getId()).thenReturn(1L);
		org.mockito.Mockito.when(objMock.getCreatedDate()).thenReturn(new Date());
		org.mockito.Mockito.when(objMock.getUpdatedDate()).thenReturn(new Date());
		
		assertIsUpdated(updated);
	}

	@Test
	public void shouldFindDomainByName() throws Exception {
		final String name = "Domain01";

		// Setup mock calls
		org.mockito.Mockito.when(repositoryMock.find(name)).thenReturn(objMock);
		org.mockito.Mockito.when(objMock.getId()).thenReturn(1L);

		// Call the method under the test.
		MyDomainObject found = myDomainService.findDomainByName(name);
		
		// Verify the call made.
		org.mockito.Mockito.verify(repositoryMock).find(name);
		
		assertThat(found.getId(), equalTo(objMock.getId()));
	}

	@Test(expected = ObjectNotFound.class)
	public void shouldFindDomainByNameNotFound() throws Exception {
		final String name = "Domain01";

		// Setup mock calls
		org.mockito.Mockito.when(repositoryMock.find(name)).thenReturn(null);

		// Call the method under the test.
		myDomainService.findDomainByName(name);
		
		// Verify the call made.
		org.mockito.Mockito.verify(repositoryMock).find(name);
	}

//	@Test
//	@Ignore
//	public void shouldProcess() {
//		fail("Not yet implemented");
//	}

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
