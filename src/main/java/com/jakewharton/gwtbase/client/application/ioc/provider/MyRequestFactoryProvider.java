package com.jakewharton.gwtbase.client.application.ioc.provider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.jakewharton.gwtbase.shared.MyRequestFactory;

public class MyRequestFactoryProvider implements Provider<MyRequestFactory> {
	private final EventBus eventBus;
	
	@Inject
	public MyRequestFactoryProvider(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	@Override
	public MyRequestFactory get() {
		MyRequestFactory requestFactory = GWT.create(MyRequestFactory.class);
		requestFactory.initialize(this.eventBus);
		
		return requestFactory;
	}
}
