package com.jakewharton.gwtbase.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class NotificationEvent extends GwtEvent<NotificationEventHandler> {
	public static Type<NotificationEventHandler> TYPE = new Type<NotificationEventHandler>();
	
	private final String message;
	
	public NotificationEvent(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	@Override
	protected void dispatch(NotificationEventHandler handler) {
		handler.onNotification(this);
	}
	@Override
	public Type<NotificationEventHandler> getAssociatedType() {
		return NotificationEvent.TYPE;
	}
}
