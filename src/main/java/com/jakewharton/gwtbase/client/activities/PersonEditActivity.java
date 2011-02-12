package com.jakewharton.gwtbase.client.activities;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.Receiver;
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
import com.jakewharton.gwtbase.shared.LogUtility;
import com.jakewharton.gwtbase.shared.ApplicationRequestFactory;

public class PersonEditActivity extends AbstractActivity implements PersonEditView.Presenter {
	private static final Logger LOGGER = LogUtility.get(PersonEditActivity.class);
	
	private final PersonEditView personEditView;
	private final ApplicationRequestFactory requestFactory;
	private final PlaceController placeController;
	
	@Inject
	public PersonEditActivity(
			PersonEditView personEditView,
			ApplicationRequestFactory requestFactory,
			PlaceController placeController
	) {
		LOGGER.log(Level.FINEST, "Instantiating");
		
		this.personEditView = personEditView;
		this.requestFactory = requestFactory;
		this.placeController = placeController;
	}
	
	public PersonEditActivity edit(final int personId) {
		LOGGER.log(Level.FINER, "Requesting Person proxy object.");
		this.requestFactory.personRequest().findPerson(personId).fire(new Receiver<PersonProxy>() {
			@Override
			public void onSuccess(PersonProxy person) {
				if (person != null) {
					LOGGER.log(Level.FINE, "Received Person proxy object.");
					final ApplicationRequestFactory.PersonRequest personRequest = requestFactory.personRequest();
					personRequest.edit(person);
					personEditView.getPersonDriver().edit(person, personRequest);
					personRequest.persist().using(person);
				} else {
					LOGGER.log(Level.WARNING, "Could not locate person with id " + personId);
				}
			}
		});
		
		return this;
	}
	public PersonEditActivity create() {
		ApplicationRequestFactory.PersonRequest personRequest = this.requestFactory.personRequest();
		PersonProxy person = personRequest.create(PersonProxy.class);
		this.personEditView.getPersonDriver().edit(person, personRequest);
		personRequest.persist().using(person);
		
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		//Tell the view we are the controller
		this.personEditView.setPresenter(this);
		
		panel.setWidget(this.personEditView);
	}

	@Override
	public void saveClicked() {
		LOGGER.log(Level.FINE, "Attempting save of Person.");
		
		final PersonDriver personDriver = this.personEditView.getPersonDriver();
		RequestContext context = personDriver.flush();
		
		if (personDriver.hasErrors()) {
			Window.alert("ERRORS");
			return;
		}
		
		context.fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void arg0) {
				LOGGER.log(Level.FINE, "Person save successful.");
				placeController.goTo(PersonPlace.list());
			}

			@Override
			public void onFailure(ServerFailure error) {
				LOGGER.log(Level.WARNING, "Person save failed. " + error.getMessage());
				Window.alert("FAILURE\n\n" + error.getMessage());
			}

			@Override
			public void onViolation(Set<Violation> errors) {
				LOGGER.log(Level.FINE, "Person save failed. Violation errors present.");
				personDriver.setViolations(errors);
			}
		});
	}

	@Override
	public void cancelClicked() {
		this.placeController.goTo(PersonPlace.list());
	}

	@Override
	public String mayStop() {
		//TODO: fix AutoBean frozen error
		
		/*
		PersonDriver personDriver = this.personEditView.getPersonDriver();
		if (personDriver().isChanged()) {
			return "You have unsaved changes. Are you sure you want to navigate away?";
		}
		*/
		return null;
	}

	@Override
	public void onCancel() {
		LOGGER.log(Level.FINER, "Cancelling");
		this.personEditView.setPresenter(null);
	}

	@Override
	public void onStop() {
		LOGGER.log(Level.FINER, "Stopping");
		this.personEditView.setPresenter(null);
	}
}
