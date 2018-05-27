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

import com.mymedia.web.dto.GenreBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.service.AuthorService;
import com.mymedia.web.service.GenreService;
import com.mymedia.web.service.SongService;

@RestController
@RequestMapping("/genre")
public class GenreController {
	private static final Logger LOG = LogManager.getLogger(GenreController.class);

	@Autowired
	private GenreService genreService;

	@Autowired
	private SongService songService;
	
	@GetMapping
	public ResponseEntity<?> getGenres() {
		try {
			return new ResponseEntity<>(genreService.getAllGenres(), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getGenreById(@PathVariable String id) {
		try {
			return new ResponseEntity<>(genreService.getGenre(id), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping(value = "/{id}/songs")
	public ResponseEntity<?> getSongsByGenreId(@PathVariable String id) {
		try {
			return new ResponseEntity<>(songService.getSongsByGenreId(id), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> postGenre(@RequestBody GenreBeanEntity genre) {
		return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
	}

	@PatchMapping
	public ResponseEntity<?> updateGenre(@RequestBody GenreBeanEntity genre) {
		try {
			return new ResponseEntity<>(genreService.updateGenre(genre), HttpStatus.ACCEPTED);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteGenre(@PathVariable int id) {
		return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
	}
}
