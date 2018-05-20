package com.mymedia.web.mvc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "SONGS")
public class Song  implements Comparable<Song>{

	@Id
	@Column(name = "SONG_ID")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@Column(name = "NAME")
	private String name;

	@Column(name = "RATING", nullable = true)
	private double rating;

	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@ManyToOne
	@JoinColumn(name = "GENRE_ID")
	private Genre genre;

	@Column(name = "SONG_URL")
	private String url;

	@Column(name = "SONG_DURATION")
	private String duration;

	@ManyToOne
	@JoinColumn(name = "ALBUM_ID")
	private Album album;

	@ManyToOne
	@JoinColumn(name = "AUTHOR_ID")
	private Author author;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "SONG_PLAYLISTS", joinColumns = { @JoinColumn(name = "SONG_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PLAYLIST_ID") })
	private List<Playlist> playlists;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
	
	@Override
	public int compareTo(Song song) {
		return (int)(song.getRating()-this.getRating());
	}

}
