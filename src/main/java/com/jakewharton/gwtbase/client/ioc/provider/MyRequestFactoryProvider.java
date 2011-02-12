package com.jakewharton.gwtbase.client.ioc.provider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.jakewharton.gwtbase.shared.ApplicationRequestFactory;

public class MyRequestFactoryProvider implements Provider<ApplicationRequestFactory> {
	private final EventBus eventBus;
	
	@Inject
	public MyRequestFactoryProvider(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	@Override
	public ApplicationRequestFactory get() {
		ApplicationRequestFactory requestFactory = GWT.create(ApplicationRequestFactory.class);
		requestFactory.initialize(this.eventBus);
		
		return requestFactory;
	}
}
