package com.mymedia.web.mvc.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "GENRES")
public class Genre {

	@Id
	@Column(name = "GENRE_ID")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(name = "GENRE_NAME")
	private String name;

	@OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
	private List<Song> songList;

	@OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
	private List<Author> authorList;
	
	@OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
	private List<Author> albumList;

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

	public List<Song> getSongList() {
		return songList;
	}

	public void setSongList(List<Song> songList) {
		this.songList = songList;
	}

	public List<Author> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(List<Author> authorList) {
		this.authorList = authorList;
	}

	public List<Author> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(List<Author> albumList) {
		this.albumList = albumList;
	}
	
	

}
