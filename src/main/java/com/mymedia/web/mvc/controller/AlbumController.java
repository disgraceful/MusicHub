package com.mymedia.web.mvc.controller;

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

import com.mymedia.web.dto.AlbumBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.requestmodel.AlbumCreateRequestModel;
import com.mymedia.web.service.AlbumService;
import com.mymedia.web.service.AuthorService;
import com.mymedia.web.service.SongService;

@RestController
@RequestMapping("/album")
public class AlbumController {
	private static final Logger LOG = LogManager.getLogger(AlbumController.class);

	@Autowired
	private AlbumService albumService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private SongService songService;

	@GetMapping
	public ResponseEntity<?> getAlbums() {
		try {
			return new ResponseEntity<>(albumService.getAllAlbums(), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping(value = "/top")
	public ResponseEntity<?> getTopAlbums() {
		try {
			return new ResponseEntity<>(albumService.getTop10(), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getAlbumById(@PathVariable int id) {
		try {
			return new ResponseEntity<>(albumService.getAlbum(id), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping(value = "/{id}/author")
	public ResponseEntity<?> getAuthorByAlbumId(@PathVariable int id) {
		try {
			AlbumBeanEntity album = albumService.getAlbum(id);
			return new ResponseEntity<>(authorService.getAuthor(album.getAuthorId()), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping(value = "/{id}/songs")
	public ResponseEntity<?> getSongsByAlbumId(@PathVariable int id) {
		try {
			return new ResponseEntity<>(songService.getSongsByAlbumId(id), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@PostMapping
	public ResponseEntity<?> postAlbum(@RequestBody AlbumCreateRequestModel model) {
		try {
			return new ResponseEntity<>(albumService.createAlbum(model), HttpStatus.CREATED);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@PatchMapping
	public ResponseEntity<?> updateAlbum(@RequestBody AlbumBeanEntity album) {
		try {
			return new ResponseEntity<>(albumService.updateAlbum(album), HttpStatus.ACCEPTED);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteAlbum(@PathVariable int id) {
		try {
			albumService.deleteAlbum(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}
}
