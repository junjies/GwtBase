package com.jakewharton.gwtbase.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface NotificationEventHandler extends EventHandler {
	void onNotification(NotificationEvent event);
}
