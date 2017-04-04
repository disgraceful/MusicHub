package com.mymedia.web.mvc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "SONGS")
public class Song {

	@Id
	@Column(name = "SONG_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "NAME")
	private String name;

	@Column(name = "RATING",nullable = true)
	private double rating;
	
	@Column(name = "LYRICS")
	private String lyrics;
	
	@Column(name = "BIRTH_DATE")
	private Date birthDate;
	
	@ManyToOne
	@JoinColumn(name = "GENRE_ID")
	private Genre genre;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "ALBUM_ID")
	private Album album;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "SONG_AUTHOURS",
	joinColumns = {@JoinColumn(name = "SONG_ID") },
	inverseJoinColumns = { @JoinColumn(name = "AUTHOUR_ID")})
	private List<Authour> authours;

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

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<Authour> getAuthours() {
		return authours;
	}

	public void setAuthours(List<Authour> authours) {
		this.authours = authours;
	}

}
