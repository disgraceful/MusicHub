package com.mymedia.web.mvc.model;

import java.util.List;

public class Consumer {
	private String name;
	private String surName;
	List<Playlist> playlsits;
	
	//TODO Check BIRTH DATE For Song lyrics filter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public List<Playlist> getPlaylsits() {
		return playlsits;
	}
	public void setPlaylsits(List<Playlist> playlsits) {
		this.playlsits = playlsits;
	}
	
	
	
	

}
