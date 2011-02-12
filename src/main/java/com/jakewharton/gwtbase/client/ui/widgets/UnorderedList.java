package com.jakewharton.gwtbase.client.ui.widgets;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class UnorderedList extends ComplexPanel {
	private final UListElement list;
	
	public UnorderedList() {
		this.list = Document.get().createULElement();
		this.setElement(this.list);
	}
	
	@Override
	public void add(Widget child) {
		Element li = Document.get().createLIElement().cast();
		this.list.appendChild(li);
		super.add(child, li);
	}
	
	@Override
	public void add(IsWidget child) {
		this.add(child.asWidget());
	}
	
	@Override
	public void clear() {
		while (this.list.hasChildNodes()) {
			this.list.removeChild(this.list.getFirstChild());
		}
	}
}
