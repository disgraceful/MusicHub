package com.mymedia.web.mvc.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "CONSUMERS")
public class Consumer {

	@Id
	@Column(name = "CONSUMER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL)
	List<Playlist> playlsits;

	// TODO Check BIRTH DATE For Song lyrics filter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Playlist> getPlaylsits() {
		return playlsits;
	}

	public void setPlaylsits(List<Playlist> playlsits) {
		this.playlsits = playlsits;
	}

}
