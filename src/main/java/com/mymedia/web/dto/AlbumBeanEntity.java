package com.mymedia.web.dto;

import java.util.Date;

public class AlbumBeanEntity {

	private int id;
	private String name;
	private Date birthDate;
	private double rating;
	private int authourId;
	
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getAuthourId() {
		return authourId;
	}
	public void setAuthourId(int authourId) {
		this.authourId = authourId;
	}
	
	
}
