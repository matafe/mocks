
package com.matafe.mocks.business.internal;

import java.rmi.ConnectException;

import com.matafe.mocks.business.IMyDomainRepository;
import com.matafe.mocks.business.MyDomainException;
import com.matafe.mocks.business.MyDomainObject;

/**
 * My Domain Real World Repository Implementation.
 * 
 * @author matafe
 */
public class MyDomainRepositoryReal implements IMyDomainRepository {

	@Override
	public MyDomainObject find(String name) throws MyDomainException {
		Object found;

		try {
			// example
			found = RealDB.query("select * from Domain where name = " + name);
		} catch (ConnectException e) {
			throw new MyDomainException("Failed to query to the database", e);
		}
		return (MyDomainObject) found;
	}

	@Override
	public MyDomainObject save(MyDomainObject obj) throws MyDomainException {

		try {
			// example
			RealDB.insert("insert into ...");
		} catch (ConnectException e) {
			throw new MyDomainException("Failed to insert into the database.", e);
		}

		return obj;
	}

	private static class RealDB {

		public static Object query(String sql) throws ConnectException {
			throw new ConnectException("Could not connect with a real database");
		}

		public static int insert(String sql) throws ConnectException {
			throw new ConnectException("Could not connect with a real database");
		}
	}

}
