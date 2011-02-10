package com.jakewharton.gwtbase.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class ContextListener extends GuiceServletContextListener {
	@Override
	protected Injector getInjector() {
		try {
			return Guice.createInjector(
				new GuiceServletModule(),
				new ServletModule()
			);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
