package com.jakewharton.gwtbase.client.ui.views;

import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.jakewharton.gwtbase.model.PersonProxy;
import com.jakewharton.gwtbase.widgets.UnorderedList;

public class PersonListView extends Composite implements IsWidget {
	private static final String ATTRIBUTE_PERSON_ID = "data-person-id";
	
	public static interface Presenter {
		void editPerson(int id);
	}
	
	interface LayoutUiBinder extends UiBinder<Widget, PersonListView> {}
	private static LayoutUiBinder uiBinder = GWT.create(LayoutUiBinder.class);
	
	private Presenter presenter;
	
	@UiField UnorderedList people;
	
	public PersonListView() {
		this.initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	public void setPeople(List<PersonProxy> people) {
		this.people.clear();
		for (PersonProxy person : people) {
			Anchor a = new Anchor(person.getName());
			a.getElement().setAttribute(ATTRIBUTE_PERSON_ID, person.getId().toString());
			a.addClickHandler(personClick);
			this.people.add(a);
		}
	}
	
	private ClickHandler personClick = new ClickHandler() {
		@Override
		public void onClick(ClickEvent target) {
			if (target.getSource() instanceof Anchor) {
				Anchor a = (Anchor)target.getSource();
				int id = Integer.parseInt(a.getElement().getAttribute(ATTRIBUTE_PERSON_ID));
				presenter.editPerson(id);
			}
		}
	};
}
