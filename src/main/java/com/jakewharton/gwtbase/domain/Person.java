package com.jakewharton.gwtbase.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.validation.constraints.Size;

public class Person {
	private Integer id;
	
	@Size(min = 2, message="Please enter a name of 2 or more characters")
	private String name;
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Person() {}
	private Person(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/* FAKE PERSISTANCE */
	
	private static final Random random = new Random();
	private static final HashMap<Integer, Person> people = new HashMap<Integer, Person>();
	private static final String[] names = new String[] {
		"Jake", "Bob", "Sue", "Dave", "Kate", "Mary", "Alice", "Eve", "Bill", "Ted", "Zed"
	};
	
	static {
		//Initialize person store
		for (String name : names) {
			int id = getUniqueRandomId();
			people.put(id, new Person(id, name));
		}
	}
	
	/**
	 * Generates a unique id that is not contained in the HashMap
	 * 
	 * @return Unique integer.
	 */
	private static int getUniqueRandomId() {
		int id;
		do {
			id = 1 + random.nextInt(1000);
		} while (people.containsKey(id));
		return id;
	}

	public void persist() {
		System.out.println("PERSIST");
		if (this.id != null) {
			if (!people.containsKey(this.id)) {
				throw new IllegalArgumentException("ID not in DB.");
			}
			System.out.println("> OLD " + this.id);
		} else {
			this.id = getUniqueRandomId();
			System.out.println("> NEW " + this.id);
		}
		
		people.put(this.id, this);
	}
	public static Person findPerson(Integer id) {
		System.out.println("FIND PERSON " + id);
		return people.get(id);
	}
	public static List<Person> list() {
		System.out.println("LIST");
		return new ArrayList<Person>(people.values());
	}
	
	
	public Integer getVersion() {
		System.out.println("GET VERSION");
		return 1;
	}
}
