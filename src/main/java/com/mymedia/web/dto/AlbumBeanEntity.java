package com.mymedia.web.dto;

public class AlbumBeanEntity {

	private int id;
	private String name;
	private String birthDate;
	private double rating;
	private int authorId;
	private String authorName;
	private int songAmount;

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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public int getSongAmount() {
		return songAmount;
	}

	public void setSongAmount(int songAmount) {
		this.songAmount = songAmount;
	}

}
