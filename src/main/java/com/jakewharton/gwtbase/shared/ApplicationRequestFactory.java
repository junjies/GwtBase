package com.jakewharton.gwtbase.shared;

import java.util.List;

import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.RequestFactory;
import com.google.gwt.requestfactory.shared.Service;
import com.jakewharton.gwtbase.domain.Person;
import com.jakewharton.gwtbase.model.PersonProxy;

public interface ApplicationRequestFactory extends RequestFactory {

	@Service(Person.class)
	interface PersonRequest extends RequestContext {
		Request<List<PersonProxy>> list();
		Request<PersonProxy> findPerson(Integer personId);
		InstanceRequest<PersonProxy, Void> persist();
	}
	
	PersonRequest personRequest();
}
