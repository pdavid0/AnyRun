package com.wci.android.anyrun.models;

import java.util.List;

/**
 *
 * Created by phil on 15-02-07.
 *
 */
public class PeopleGroup {
	String       name;
	List<People> peopleInGroup;
	boolean accepts_specials = true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<People> getPeopleInGroup() {
		return peopleInGroup;
	}

	public void setPeopleInGroup(List<People> peopleInGroup) {
		this.peopleInGroup = peopleInGroup;
	}

	public boolean isAccepts_specials() {
		return accepts_specials;
	}

	public void setAccepts_specials(boolean accepts_specials) {
		this.accepts_specials = accepts_specials;
	}

	public PeopleGroup() {

	}
}
