package com.jakewharton.gwtbase.client.application;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.jakewharton.gwtbase.client.application.ioc.DesktopInjectorWrapper;
import com.jakewharton.gwtbase.client.application.ioc.InjectorWrapper;

public class Main implements EntryPoint {
	// we are going to use our little injectorWrapper to get everything started for us
	// We will use deferred binding so we can create all our different apps (Desktop & Mobile)
	// see the Gwt_seminar.gwt.xml to see how we switch in MobileInjectorWrapper when on a mobile device
	private final InjectorWrapper injectorWrapper = GWT.create(DesktopInjectorWrapper.class);
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		this.injectorWrapper.getInjector().getApplication().run();
	}
}