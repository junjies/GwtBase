package com.jakewharton.gwtbase.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.jakewharton.gwtbase.client.events.NotificationEventHandler;

public abstract class Shell extends Composite implements NotificationEventHandler {
	public SimplePanel getContent() {
		return null;
	}
	
	public SimplePanel getAlert() {
		return null;
	}
}
