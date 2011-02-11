package com.jakewharton.gwtbase.client.activities;

import java.util.List;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.jakewharton.gwtbase.client.places.PersonPlace;
import com.jakewharton.gwtbase.client.ui.views.PersonListView;
import com.jakewharton.gwtbase.model.PersonProxy;
import com.jakewharton.gwtbase.shared.MyRequestFactory;

public class PersonListActivity extends AbstractActivity implements PersonListView.Presenter {
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
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		//Tell the view we are the presenter
		this.personListView.setPresenter(this);
		
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
	public void onCancel() {
		this.personListView.setPresenter(null);
	}

	@Override
	public void onStop() {
		this.personListView.setPresenter(null);
	}
}
