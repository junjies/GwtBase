package com.jakewharton.gwtbase.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.jakewharton.gwtbase.client.events.NotificationEvent;

public class ShellDesktop extends Shell {
	public static interface Presenter {
		void listClick();
		void createClick();
	}
	
	private static ShellDesktopUiBinder uiBinder = GWT.create(ShellDesktopUiBinder.class);
	interface ShellDesktopUiBinder extends UiBinder<Widget, ShellDesktop> {}

	@UiField SimplePanel content;
	
	private Presenter presenter;
	
	public ShellDesktop() {
		this.initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public SimplePanel getContent() {
		return this.content;
	}
	
	@UiHandler("list")
	void listClick(ClickEvent event) {
		this.presenter.listClick();
	}
	
	@UiHandler("create")
	void createClick(ClickEvent event) {
		this.presenter.createClick();
	}

	@Override
	public void onNotification(NotificationEvent event) {
		Window.alert(event.getMessage());
	}
}