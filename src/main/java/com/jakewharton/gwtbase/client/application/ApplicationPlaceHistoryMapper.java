package com.jakewharton.gwtbase.client.application;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.jakewharton.gwtbase.client.places.PersonPlace;

@WithTokenizers({
	PersonPlace.Tokenizer.class
})
public interface ApplicationPlaceHistoryMapper extends PlaceHistoryMapper {}