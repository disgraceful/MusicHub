package com.mymedia.web.mvc.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "ALBUMS")
public class Album implements Comparable<Album> {
	@Id
	@Column(name = "ALBUM_ID")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "RECORD_DATE")
	private String recordDate;

	@Column(name = "RATING")
	private long rating;

	@ManyToOne
	@JoinColumn(name = "AUTHOR_ID")
	private Author author;

	@ManyToOne
	@JoinColumn(name = "GENRE_ID")
	private Genre genre;

	@OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
	private List<Song> songs;

	@Column(name="IMG_PATH")
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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public long getRating() {
		return rating;
	}

	public void setRating(long rating) {
		this.rating = rating;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String birthDate) {
		this.recordDate = birthDate;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Override
	public int compareTo(Album album) {
		return album.getRating() - this.getRating() > 0 ? 1 : -1;
	}
}
