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

import com.mymedia.web.dto.GenreBeanEntity;
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
	public ResponseEntity<List<GenreBeanEntity>> getGenres() {
		List<GenreBeanEntity> genres = genreService.getAllGenres();
		if(!genres.isEmpty()&&genres!=null){
			return new ResponseEntity<>(genres,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<GenreBeanEntity> getGenreById(@PathVariable int id) {
		GenreBeanEntity genre = genreService.getGenre(id);
		if(genre!=null){
			return new ResponseEntity<>(genre,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/{id}/songs")
	public ResponseEntity<?>getSongsByGenreId(@PathVariable int id){
		return new ResponseEntity<>(songService.getSongsByGenreId(id),HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<GenreBeanEntity> postGenre(@RequestBody GenreBeanEntity genre) {
		return new ResponseEntity<>(genreService.addGenre(genre),HttpStatus.OK);
	}

	@PatchMapping
	public ResponseEntity<GenreBeanEntity> updateGenre(@RequestBody GenreBeanEntity genre) {
		return new ResponseEntity<>(genreService.updateGenre(genre),HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteGenre(@PathVariable int id) {
		genreService.deleteGenre(id);
	}
}
