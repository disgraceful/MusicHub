package com.mymedia.web.mvc.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.dto.GenreBeanEntity;
import com.mymedia.web.service.GenreService;

@RestController
@RequestMapping("/genre")
public class GenreController {
	private static final Logger LOG = LogManager.getLogger(GenreController.class);

	@Autowired
	private GenreService genreService;

	@GetMapping(value = "/{id}")
	public @ResponseBody GenreBeanEntity getGenreById(@PathVariable int id) {
		GenreBeanEntity genre = genreService.getGenre(id);
		LOG.info(genre.toString());
		return genre;
	}

	@GetMapping
	public @ResponseBody List<GenreBeanEntity> getGenres() {
		return genreService.getAllGenres();
	}

	@PostMapping
	public @ResponseBody GenreBeanEntity postGenre(@RequestBody GenreBeanEntity genre) {
		return genreService.addGenre(genre);
	}

	@PatchMapping
	public @ResponseBody GenreBeanEntity updateGenre(@RequestBody GenreBeanEntity genre) {
		return genreService.updateGenre(genre);
	}

	@DeleteMapping(value = "/{id}")
	public @ResponseBody void deleteGenre(@PathVariable int id) {
		genreService.deleteGenre(id);
	}
}
