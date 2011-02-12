package com.jakewharton.gwtbase.client;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.jakewharton.gwtbase.client.activities.PersonEditActivity;
import com.jakewharton.gwtbase.client.activities.PersonListActivity;
import com.jakewharton.gwtbase.client.places.PersonPlace;
import com.jakewharton.gwtbase.shared.LogUtility;

public class ApplicationActivityMapper implements ActivityMapper {
	private static final Logger LOGGER = LogUtility.get(ApplicationActivityMapper.class);
	
	private final Provider<PersonListActivity> personListActivity;
	private final Provider<PersonEditActivity> personEditActivity;
	
	@Inject
	public ApplicationActivityMapper(
			Provider<PersonListActivity> personListActivity,
			Provider<PersonEditActivity> personEditActivity
	) {
		this.personListActivity = personListActivity;
		this.personEditActivity = personEditActivity;
	}
	
	@Override
	public Activity getActivity(Place place) {
		if (place instanceof PersonPlace) {
			PersonPlace personPlace = (PersonPlace)place;
			if (personPlace.identifier.equals(PersonPlace.LIST)) {
				return this.personListActivity.get();
			} else if (personPlace.identifier.equals(PersonPlace.EDIT)) {
				return this.personEditActivity.get().edit(personPlace.id);
			} else if (personPlace.identifier.equals(PersonPlace.CREATE)) {
				return this.personEditActivity.get().create();
			}
		}
		
		LOGGER.log(Level.WARNING, "Place not found. Returning 404 activity.");
		return null; //TODO: 404
	}
}
