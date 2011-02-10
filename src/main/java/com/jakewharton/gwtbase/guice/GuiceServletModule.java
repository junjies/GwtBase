package com.jakewharton.gwtbase.guice;

import com.google.inject.servlet.ServletModule;

public class GuiceServletModule extends ServletModule {
	@Override
	protected void configureServlets() {
		this.serve("/gwtRequest").with(InjectingRequestFactoryServlet.class);
	}
}
