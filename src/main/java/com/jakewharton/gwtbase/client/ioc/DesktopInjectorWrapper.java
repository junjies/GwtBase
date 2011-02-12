package com.jakewharton.gwtbase.client.ioc;

import com.google.gwt.core.client.GWT;

public class DesktopInjectorWrapper implements InjectorWrapper {
	// well this isn't the most exciting
	// we just define our getInjector() to
	// create and return the actual injector
	public ApplicationInjector getInjector() {
		return GWT.create(DesktopInjector.class);
	}
}
