
package com.matafe.mocks.business.internal;

import java.util.Date;

import com.matafe.mocks.business.IMyDomainRepository;
import com.matafe.mocks.business.IMyDomainService;
import com.matafe.mocks.business.MyDomainException;
import com.matafe.mocks.business.MyDomainObject;
import com.matafe.mocks.business.ObjectAlreadyExists;
import com.matafe.mocks.business.ObjectNotFound;

/**
 * My Domain Service Implementation.
 * 
 * @author matafe
 */
public class MyDomainService implements IMyDomainService {

	private IMyDomainRepository repository;

	public MyDomainService() {
		this(new MyDomainRepositoryReal());
	}

	public MyDomainService(IMyDomainRepository repository) {
		this.repository = repository;
	}

	@Override
	public MyDomainObject createDomain(MyDomainObject obj) throws MyDomainException {
		try {
			MyDomainObject found = findDomainByName(obj.getName());
			throw new ObjectAlreadyExists(String.format("Object with name %s already exists!", found.getName()));
		} catch (ObjectNotFound e) {
			obj.setCreatedDate(new Date());
			return this.repository.save(obj);
		}
	}

	@Override
	public MyDomainObject updateDomain(MyDomainObject obj) throws MyDomainException {
		MyDomainObject found = findDomainByName(obj.getName());
		found.setValue(obj.getValue());
		obj.setUpdatedDate(new Date());
		return this.repository.save(found);
	}

	@Override
	public MyDomainObject findDomainByName(String name) throws MyDomainException {
		MyDomainObject found = repository.find(name);

		if (found == null) {
			throw new ObjectNotFound(String.format("Object with name {} not found: " + name));
		}

		return found;
	}

}
