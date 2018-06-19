package com.mymedia.web.dto;

public class PlaylistBeanEntity {

	private String id;
	private String name;
	private String consumerId;
	private int songAmount;
	private long rating;
	private String imgPath;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public int getSongAmount() {
		return songAmount;
	}

	public void setSongAmount(int songAmount) {
		this.songAmount = songAmount;
	}

	public long getRating() {
		return rating;
	}

	public void setRating(long rating) {
		this.rating = rating;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	

}
