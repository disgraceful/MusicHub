package com.mymedia.web.mvc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "AUTHORS")
public class Author implements Comparable<Author> {

	@Id
	@Column(name = "AUTHOR_ID")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "SUR_NAME")
	private String surName;

	@OneToMany(mappedBy = "author",cascade=CascadeType.ALL)
	private List<Album> albums;

	@OneToMany(mappedBy = "author",cascade=CascadeType.ALL)
	private List<Song> songs;

	@Column(name = "BIRTH_DATE")
	private Date birthDate;
	
	@Column(name = "RATING",nullable = true)
	private double rating;

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

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public double getRating() {
		double sum = 0.0;
		if(albums==null||albums.isEmpty()){
			return sum;
		}
		for (Album album : albums) {
			sum+=album.getRating();
		}
		return sum/albums.size();
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	@Override
	public int compareTo(Author author) {
		return author.getRating()-this.getRating()>0?1:-1;
	}

}
