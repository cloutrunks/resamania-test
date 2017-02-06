package com.resamania.test.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents an activity with values provided by rest services
 * 
 * @author Mouhamed.tall
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Activity {
	private int id;
	private String name;
	private String description;
	private String duration;
	private int defaultNbPlace;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getDefaultNbPlace() {
		return defaultNbPlace;
	}

	public void setDefaultNbPlace(int defaultNbPlace) {
		this.defaultNbPlace = defaultNbPlace;
	}

	@Override
	public String toString() {
		return name;
	}

}
