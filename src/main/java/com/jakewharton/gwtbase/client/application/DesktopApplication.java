package com.jakewharton.gwtbase.client.application;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.CachingActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;
import com.jakewharton.gwtbase.client.events.NotificationEvent;
import com.jakewharton.gwtbase.client.places.PersonPlace;
import com.jakewharton.gwtbase.client.ui.ShellDesktop;

public class DesktopApplication extends Application implements ShellDesktop.Presenter {
	
	private final ShellDesktop shell;
	private final EventBus eventBus;
	private final PlaceController placeController;
	private final ApplicationActivityMapper activityMapper;
	private final ApplicationPlaceHistoryMapper appPlaceHistoryMapper;
	
	private final Place defaultPlace = PersonPlace.list();
	
	@Inject
	public DesktopApplication(
			ShellDesktop shell,
			EventBus eventBus,
			PlaceController placeController, 
			ApplicationActivityMapper activityMapper,
			ApplicationPlaceHistoryMapper appPlaceHistoryMapper) {
		
		this.shell = shell;
		this.shell.setPresenter(this);
		this.eventBus = eventBus;
		this.placeController = placeController;
		this.activityMapper = activityMapper;
		this.appPlaceHistoryMapper = appPlaceHistoryMapper;
		
		// tell the event bus that the shell can handle notification events
		this.eventBus.addHandler(NotificationEvent.TYPE, shell);
	}
	
	@Override
	public void run() {
		/*
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable e) {
				Window.alert("Hey, something went wrong: " + e.getMessage());
			}
		});*/
		
		//Set up mappings
		CachingActivityMapper cached = new CachingActivityMapper(activityMapper);
		final ActivityManager mainActivityManager = new ActivityManager(cached, eventBus);
		mainActivityManager.setDisplay(this.shell.getContent());
		
		// History management
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(this.appPlaceHistoryMapper);
		historyHandler.register(this.placeController, this.eventBus, this.defaultPlace);
		
		RootLayoutPanel.get().clear();
		RootLayoutPanel.get().add(this.shell);
		
		//Trigger history
		historyHandler.handleCurrentHistory();
	}

	@Override
	public void listClick() {
		this.placeController.goTo(PersonPlace.list());
	}

	@Override
	public void createClick() {
		this.placeController.goTo(PersonPlace.create());
	}
}
