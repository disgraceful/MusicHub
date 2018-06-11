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
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.dto.AuthorBeanEntity;
import com.mymedia.web.dto.PlaylistBeanEntity;
import com.mymedia.web.dto.SongBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.mvc.model.Consumer;
import com.mymedia.web.service.AlbumService;
import com.mymedia.web.service.AuthorService;
import com.mymedia.web.service.ConsumerService;
import com.mymedia.web.service.PlaylistService;
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
	
	@Autowired
	private PlaylistService playlistService;

	@GetMapping
	public ResponseEntity<?> getAuthors() {
		try {
			return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping(value="/{id}/similar")
	public ResponseEntity<List<AuthorBeanEntity>> getSimilarAuthors(@PathVariable String id) {
		try {
			return new ResponseEntity<>(authorService.getSimilarAuthors(id), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getCode());
		}
	}
	
	@GetMapping(value="/{id}/popularsongs")
	public ResponseEntity<List<SongBeanEntity>> getPopularSongs(@PathVariable String id) {
		try {
			List<SongBeanEntity>songs = songService.getBestSongsEntityByAuthorId(id, 5);
			return new ResponseEntity<>(songs, HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getCode());
		}
	}
	
	@GetMapping(value="/{id}/playlists")
	public ResponseEntity<List<PlaylistBeanEntity>> getPlaylistsForAuthor(@PathVariable String id) {
		try {
			LOG.info(id);
			List<PlaylistBeanEntity>playlists = playlistService.generatePlaylistForAuthor(id);
			return new ResponseEntity<>(playlists, HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getCode());
		}
	}
	
	
	@GetMapping(value = "/top")
	public ResponseEntity<?> getTopAuthors() {
		try {
			return new ResponseEntity<>(authorService.getTop10(), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getAuthorById(@PathVariable String id) {
		try {
			return new ResponseEntity<>(authorService.getAuthor(id), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping(value = "/{id}/songs")
	public ResponseEntity<?> getSongsByAuthorId(@PathVariable String id) {
		try {
			return new ResponseEntity<>(songService.getSongsByAuthorId(id), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping(value = "/{id}/albums")
	public ResponseEntity<?> getAlbumsByAuthorId(@PathVariable String id) {
		try {
			return new ResponseEntity<>(albumService.getAlbumsByAuthorId(id), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@PostMapping
	public ResponseEntity<?> postAuthor(@RequestBody AuthorBeanEntity author) {
		return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
	}

	@PatchMapping
	public ResponseEntity<?> updateAuthor(@RequestBody AuthorBeanEntity author) {
		try {
			return new ResponseEntity<>(authorService.updateAuthor(author), HttpStatus.ACCEPTED);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteAuthor(@PathVariable int id) {
		return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
	}

}
