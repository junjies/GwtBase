package com.jakewharton.gwtbase.client.ui.editors;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.ui.client.ValueBoxEditorDecorator;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.jakewharton.gwtbase.model.PersonProxy;

public class PersonEditor extends Composite implements Editor<PersonProxy> {
	interface LayoutUiBinder extends UiBinder<Widget, PersonEditor> {}
	private static LayoutUiBinder uiBinder = GWT.create(LayoutUiBinder.class);
	
	@UiField ValueBoxEditorDecorator<String> name;
	
	public PersonEditor() {
		this.initWidget(uiBinder.createAndBindUi(this));
	}
}
