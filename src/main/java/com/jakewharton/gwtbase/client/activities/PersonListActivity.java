package com.jakewharton.gwtbase.client.activities;

import java.util.List;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.jakewharton.gwtbase.client.places.PersonPlace;
import com.jakewharton.gwtbase.client.ui.views.PersonListView;
import com.jakewharton.gwtbase.model.PersonProxy;
import com.jakewharton.gwtbase.shared.MyRequestFactory;

public class PersonListActivity implements Activity, PersonListView.Presenter {
	private final PersonListView personListView;
	private final MyRequestFactory requestFactory;
	private final PlaceController placeController;
	
	@Inject
	public PersonListActivity(
			PersonListView personListView,
			MyRequestFactory requestFactory,
			PlaceController placeController
	) {
		this.personListView = personListView;
		this.requestFactory = requestFactory;
		this.placeController = placeController;
		
		//Tell the view we are the presenter
		this.personListView.setPresenter(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(this.personListView);
		
		this.requestFactory.personRequest().list().fire(new Receiver<List<PersonProxy>>() {
			@Override
			public void onSuccess(List<PersonProxy> people) {
				personListView.setPeople(people);
			}
		});
	}

	@Override
	public void editPerson(int id) {
		this.placeController.goTo(PersonPlace.edit(id));
	}
	
	@Override
	public String mayStop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCancel() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
	}
}
