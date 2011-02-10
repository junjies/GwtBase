package com.jakewharton.gwtbase.model;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.ProxyFor;
import com.jakewharton.gwtbase.domain.Person;

@ProxyFor(Person.class)
public interface PersonProxy extends EntityProxy {
	Integer getId();
	void setId(Integer id);
	String getName();
	void setName(String name);
	
	EntityProxyId<PersonProxy> stableId();
}
