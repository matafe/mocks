
package com.matafe.mocks.business.internal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.matafe.mocks.business.IMyDomainRepository;
import com.matafe.mocks.business.MyDomainObject;

/**
 * My Domain Dummy Repository Implementation.
 * 
 * @author matafe
 */
public class MyDomainRepositoryDummy implements IMyDomainRepository {

	private Map<String, MyDomainObject> DB = new ConcurrentHashMap<>();

	@Override
	public MyDomainObject find(String name) {
		return DB.get(name);
	}

	@Override
	public MyDomainObject save(MyDomainObject obj) {

		MyDomainObject found = find(obj.getName());

		if (found == null) {
			long id = SequenceGenerator.getInstance().getNextValue();
			obj.setId(id);
		}

		DB.put(obj.getName(), obj);

		return obj;
	}

}
