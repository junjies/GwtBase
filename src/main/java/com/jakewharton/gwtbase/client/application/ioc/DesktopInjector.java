package com.jakewharton.gwtbase.client.application.ioc;

import com.google.gwt.inject.client.GinModules;
import com.jakewharton.gwtbase.client.application.DesktopApplication;

// Okay so we are just going to define an Injector interface 
// that uses the @annotation to point to the particular "AbstractGinModule" class

@GinModules(value = {ApplicationInjectorModule.class})
public interface DesktopInjector extends ApplicationInjector {
	// notice we are returning a "DesktopApp" which extends AcrintaFitnessApp
	// This will tell Gin to create a DesktopApp instead of a MobileApp or TabletApp
	DesktopApplication getApplication();
}
