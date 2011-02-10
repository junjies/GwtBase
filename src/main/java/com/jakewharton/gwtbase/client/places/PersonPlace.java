package com.jakewharton.gwtbase.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class PersonPlace extends Place {
	private static final String SEPARATOR = ":";
	
	public static final String LIST = "List";
	public static final String EDIT = "Edit";
	public static final String CREATE = "Create";
	
	public final String identifier;
	public final int id;
	
	private PersonPlace(String identifier, int id) {
		this.identifier = identifier;
		this.id = id;
	}
	
	public static PersonPlace list() {
		return new PersonPlace(LIST, 0);
	}
	public static PersonPlace edit(int id) {
		return new PersonPlace(EDIT, id);
	}
	public static PersonPlace create() {
		return new PersonPlace(CREATE, 0);
	}

	@Prefix("Person")
	public static class Tokenizer implements PlaceTokenizer<PersonPlace> {
		@Override
		public String getToken(PersonPlace place) {
			if (place.id != 0) {
				return place.identifier + SEPARATOR + Integer.toString(place.id);
			} else {
				return place.identifier;
			}
		}

		@Override
		public PersonPlace getPlace(String token) {
			String[] tokens = token.split(SEPARATOR);
			if (tokens.length == 1) {
				return new PersonPlace(tokens[0], 0);
			} else if (tokens.length == 2) {
				return new PersonPlace(tokens[0], Integer.parseInt(tokens[1]));
			}
			return null;
		}
	}
}
