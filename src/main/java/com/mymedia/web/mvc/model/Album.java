package com.mymedia.web.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "ALBUMS")
public class Album implements Comparable<Album> {
	@Id
	@Column(name = "ALBUM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "BIRTH_DATE")
	private Date birthDate;
	
	@Column(name = "RATING",nullable = true)
	private double rating;
	
	@ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
	private Author author;
	
	@OneToMany(mappedBy = "album",cascade=CascadeType.ALL)
	private List<Song> songs;

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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public double getRating() {
		double sum = 0.0;
		if(songs==null||songs.isEmpty()){
			return sum;
		}
		for (Song song : songs) {
			sum+=song.getRating();
		}
		return sum/songs.size();
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public int compareTo(Album album) {
		return album.getRating()-this.getRating()>0?1:-1;
	}
}
