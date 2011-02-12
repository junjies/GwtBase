package com.jakewharton.gwtbase.client.application;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.jakewharton.gwtbase.client.application.ioc.DesktopInjectorWrapper;
import com.jakewharton.gwtbase.client.application.ioc.InjectorWrapper;
import com.jakewharton.gwtbase.shared.MyLogFormatter;

public class Main implements EntryPoint {
	private static final String DAVE_NAME = "Dave Bowman";
	private static final String DAVE_TEXT = "Hello, HAL. Do you read me, HAL?";
	private static final String HAL_NAME = "HAL9000";
	private static final String HAL_TEXT = "Affirmative, Dave. I read you.";

	static {
		// https://groups.google.com/d/msg/google-web-toolkit/aEyDwNP1tgM/qEmTz1l6i5QJ
		Handler handlers[] = Logger.getLogger("").getHandlers();
		for (Handler handler : handlers) {
			handler.setFormatter(new MyLogFormatter(false));
		}
	}

	// We are going to use our little InjectorWrapper to get everything started for us
	// We will use deferred binding so we can create all our different apps (Desktop & Mobile)
	// see the Gwt_seminar.gwt.xml to see how we switch in MobileInjectorWrapper when on a mobile device
	private final InjectorWrapper injectorWrapper = GWT.create(DesktopInjectorWrapper.class);
	
	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		Logger.getLogger(Main.DAVE_NAME).log(Level.ALL, Main.DAVE_TEXT);
		Logger.getLogger(Main.HAL_NAME).log(Level.ALL, Main.HAL_TEXT);

		this.injectorWrapper.getInjector().getApplication().run();
	}
}