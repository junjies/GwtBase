package com.jakewharton.gwtbase.client.application;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.jakewharton.gwtbase.client.activities.PersonEditActivity;
import com.jakewharton.gwtbase.client.activities.PersonListActivity;
import com.jakewharton.gwtbase.client.places.PersonPlace;

public class ApplicationActivityMapper implements ActivityMapper {
	private final PersonListActivity personListActivity;
	private final PersonEditActivity personEditActivity;

	@Inject
	public ApplicationActivityMapper(
			PersonListActivity personListActivity,
			PersonEditActivity personEditActivity
	) {
		this.personEditActivity = personEditActivity;
		this.personListActivity = personListActivity;
	}
	
	@Override
	public Activity getActivity(Place place) {
		if (place instanceof PersonPlace) {
			PersonPlace personPlace = (PersonPlace)place;
			if (personPlace.identifier.equals(PersonPlace.LIST)) {
				return this.personListActivity;
			} else if (personPlace.identifier.equals(PersonPlace.EDIT)) {
				this.personEditActivity.setPersonId(personPlace.id);
				return this.personEditActivity;
			} else if (personPlace.identifier.equals(PersonPlace.CREATE)) {
				this.personEditActivity.createNew();
				return this.personEditActivity;
			}
		}
		
		return null; //TODO: 404
	}
}
