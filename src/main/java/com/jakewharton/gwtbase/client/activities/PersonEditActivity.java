package com.jakewharton.gwtbase.client.activities;

import java.util.Set;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.ServerFailure;
import com.google.gwt.requestfactory.shared.Violation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.jakewharton.gwtbase.client.places.PersonPlace;
import com.jakewharton.gwtbase.client.ui.views.PersonEditView;
import com.jakewharton.gwtbase.client.ui.views.PersonEditView.PersonDriver;
import com.jakewharton.gwtbase.model.PersonProxy;
import com.jakewharton.gwtbase.shared.MyRequestFactory;

public class PersonEditActivity implements Activity, PersonEditView.Presenter {
	private final PersonEditView personEditView;
	private final MyRequestFactory requestFactory;
	private final PlaceController placeController;
	
	@Inject
	public PersonEditActivity(
			PersonEditView personEditView,
			MyRequestFactory requestFactory,
			PlaceController placeController
	) {
		this.personEditView = personEditView;
		this.requestFactory = requestFactory;
		this.placeController = placeController;
		
		//Tell the view we are the controller
		this.personEditView.setPresenter(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(this.personEditView);
	}
	
	public void setPersonId(int personId) {
		this.requestFactory.personRequest().findPerson(personId).fire(new Receiver<PersonProxy>() {
			@Override
			public void onSuccess(PersonProxy person) {
				final MyRequestFactory.PersonRequest personRequest = requestFactory.personRequest();
				personRequest.edit(person);
				personEditView.getPersonDriver().edit(person, personRequest);
				personRequest.persist().using(person);
			}
		});
	}
	public void createNew() {
		MyRequestFactory.PersonRequest personRequest = this.requestFactory.personRequest();
		PersonProxy person = personRequest.create(PersonProxy.class);
		this.personEditView.getPersonDriver().edit(person, personRequest);
		personRequest.persist().using(person);
		
	}

	@Override
	public void saveClicked() {
		final PersonDriver personDriver = this.personEditView.getPersonDriver();
		RequestContext context = personDriver.flush();
		
		if (personDriver.hasErrors()) {
			Window.alert("ERRORS");
			return;
		}
		
		context.fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void arg0) {
				Window.alert("SUCCESS");
				placeController.goTo(PersonPlace.list());
			}

			@Override
			public void onFailure(ServerFailure error) {
				Window.alert("FAILURE\n\n" + error.getMessage());
			}

			@Override
			public void onViolation(Set<Violation> errors) {
				Window.alert("VIOLATION");
				personDriver.setViolations(errors);
			}
		});
	}

	@Override
	public void cancelClicked() {
		this.placeController.goTo(PersonPlace.list());
	}

	/* OTHER ACTIVITY METHODS */

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
