package com.jakewharton.gwtbase.client.ui.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.jakewharton.gwtbase.client.ui.editors.PersonEditor;
import com.jakewharton.gwtbase.model.PersonProxy;

public class PersonEditView extends Composite implements IsWidget {
	public static interface Presenter {
		void saveClicked();
		void cancelClicked();
	}
	
	interface LayoutUiBinder extends UiBinder<Widget, PersonEditView> {}
	private static LayoutUiBinder uiBinder = GWT.create(LayoutUiBinder.class);
	
	public interface PersonDriver extends RequestFactoryEditorDriver<PersonProxy, PersonEditor> {}
	private final PersonDriver personDriver = GWT.create(PersonDriver.class);
	
	@UiField PersonEditor person;
	
	private Presenter presenter;
	
	public PersonEditView() {
		this.initWidget(uiBinder.createAndBindUi(this));
		
		//Initialize editor
		this.personDriver.initialize(this.person);
	}
	
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	public PersonDriver getPersonDriver() {
		return this.personDriver;
	}
	
	@UiHandler("save")
	void saveClicked(ClickEvent event) {
		this.presenter.saveClicked();
	}
	@UiHandler("cancel")
	void cancelClicked(ClickEvent event) {
		this.presenter.cancelClicked();
	}
}
