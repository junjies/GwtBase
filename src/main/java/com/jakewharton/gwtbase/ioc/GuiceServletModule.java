package com.jakewharton.gwtbase.ioc;

import com.google.gwt.requestfactory.client.DefaultRequestTransport;
import com.google.gwt.requestfactory.server.RequestFactoryServlet;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;

public class GuiceServletModule extends ServletModule {
	private static final String URL = "/" + DefaultRequestTransport.URL;
	
	@Override
	protected void configureServlets() {
		bind(RequestFactoryServlet.class).in(Singleton.class);
		serve(GuiceServletModule.URL).with(RequestFactoryServlet.class);
	}
}
