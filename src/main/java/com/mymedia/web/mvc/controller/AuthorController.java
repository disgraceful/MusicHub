package com.mymedia.web.mvc.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.dto.AlbumBeanEntity;
import com.mymedia.web.dto.AuthorBeanEntity;
import com.mymedia.web.dto.SongBeanEntity;
import com.mymedia.web.service.AlbumService;
import com.mymedia.web.service.AuthorService;
import com.mymedia.web.service.SongService;

@RestController
@RequestMapping("/author")
public class AuthorController {

	private static final Logger LOG = LogManager.getLogger(AuthorController.class);

	@Autowired
	private AuthorService authorService;

	@Autowired
	private SongService songService;

	@Autowired
	private AlbumService albumService;

	@GetMapping
	public ResponseEntity<List<AuthorBeanEntity>> getAuthors() {
		List<AuthorBeanEntity> authors = authorService.getAllAuthors();
		if (!authors.isEmpty() && authors != null) {
			return new ResponseEntity<>(authors, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/top")
	public ResponseEntity<List<AuthorBeanEntity>> getTopAuthors() {
		List<AuthorBeanEntity> authors = authorService.getTop10();
		if (!authors.isEmpty() && authors != null) {
			return new ResponseEntity<>(authors, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AuthorBeanEntity> getAuthorById(@PathVariable int id) {
		AuthorBeanEntity author = authorService.getAuthor(id);
		if (author != null) {
			return new ResponseEntity<>(author, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/{id}/songs")
	public ResponseEntity<List<SongBeanEntity>> getSongsByAuthorId(@PathVariable int id) {
		List<SongBeanEntity> songs = songService.getSongsByAuthorId(id);
		if (!songs.isEmpty() && songs != null) {
			return new ResponseEntity<>(songs, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/{id}/albums")
	public ResponseEntity<List<AlbumBeanEntity>> getAlbumsByAuthorId(@PathVariable int id) {
		List<AlbumBeanEntity> albums = albumService.getAlbumsByAuthorId(id);
		if (!albums.isEmpty() && albums != null) {
			return new ResponseEntity<>(albums, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<AuthorBeanEntity> postAuthor(@RequestBody AuthorBeanEntity author) {
		return new ResponseEntity<>(authorService.addAuthor(author), HttpStatus.OK);
	}

	@PatchMapping
	public ResponseEntity<AuthorBeanEntity> updateAuthor(@RequestBody AuthorBeanEntity author) {
		return new ResponseEntity<>(authorService.updateAuthor(author), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteAuthor(@PathVariable int id) {
		authorService.deleteAuthor(id);
	}

}
