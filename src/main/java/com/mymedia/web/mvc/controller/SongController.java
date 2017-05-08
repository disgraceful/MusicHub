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

import com.mymedia.web.dto.SongBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.service.AlbumService;
import com.mymedia.web.service.AuthorService;
import com.mymedia.web.service.GenreService;
import com.mymedia.web.service.SongService;

@RestController
@RequestMapping("/song")
public class SongController {
	private static final Logger LOG = LogManager.getLogger(SongController.class);

	@Autowired
	private SongService songService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private AlbumService albumService;

	@Autowired
	private AuthorService authorService;

	@GetMapping
	public ResponseEntity<?> getSongs() {
		try {
			return new ResponseEntity<>(songService.getAllSongs(), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping(value = "/top")
	public ResponseEntity<?> getTopSongs() {
		try {
			return new ResponseEntity<>(songService.getTop10(), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getSongById(@PathVariable int id) {
		try {
			return new ResponseEntity<>(songService.getSong(id), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping(value = "/{id}/genre")
	public ResponseEntity<?> getGenreBySongId(@PathVariable int id) {
		try {
			return new ResponseEntity<>(genreService.getGenre(songService.getSong(id).getGenreId()), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping(value = "/{id}/album")
	public ResponseEntity<?> getAlbumBySongId(@PathVariable int id) {
		try {
			return new ResponseEntity<>(albumService.getAlbum(songService.getSong(id).getAlbumId()), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping(value = "/{id}/author")
	public ResponseEntity<?> getAuthorBySongId(@PathVariable int id) {
		try {
			return new ResponseEntity<>(authorService.getAuthorBySongId(id), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@PostMapping
	public ResponseEntity<?> postSong(@RequestBody SongBeanEntity song) {
		return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
	}

	@PatchMapping
	public ResponseEntity<?> updateSong(@RequestBody SongBeanEntity song) {
		try {
			return new ResponseEntity<>(songService.updateSong(song), HttpStatus.ACCEPTED);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteSong(@PathVariable int id) {
		try {
			songService.deleteSong(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

}
