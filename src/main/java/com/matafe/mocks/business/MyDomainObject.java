package com.matafe.mocks.business;

import java.util.Date;

/**
 * My Domain Object.
 * 
 * @author matafe
 */
public class MyDomainObject {

	private Long id;

	private String name;

	private String value;

	private Date createdDate;

	private Date updatedDate;

	public MyDomainObject() {
		super();
	}

	public MyDomainObject(String name, String value) {
		this(null, name, value);
	}

	public MyDomainObject(Long id, String name, String value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "MyDomainObject [id=" + id + ", name=" + name + ", value=" + value + "]";
	}

}
