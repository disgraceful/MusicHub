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

import com.mymedia.web.dto.AlbumBeanEntity;
import com.mymedia.web.dto.AuthorBeanEntity;
import com.mymedia.web.dto.GenreBeanEntity;
import com.mymedia.web.dto.SongBeanEntity;
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

	@GetMapping(value = "/{id}")
	public @ResponseBody SongBeanEntity getSongById(@PathVariable int id) {
		SongBeanEntity song = songService.getSong(id);
		LOG.info(song.toString());
		return song;
	}

	@GetMapping
	public @ResponseBody List<SongBeanEntity> listSongs() {
		return songService.getAllSongs();
	}

	@PostMapping
	public @ResponseBody SongBeanEntity addUser(@RequestBody SongBeanEntity song) {
		return songService.addSong(song);
	}
	
	@PatchMapping
	public @ResponseBody SongBeanEntity updateSong(@RequestBody SongBeanEntity song) {
		return songService.updateSong(song);
	}

	@DeleteMapping(value = "/{id}")
	public @ResponseBody void deleteSong(@PathVariable int id) {
		songService.deleteSong(id);
	}
	
	@GetMapping(value = "/{id}/genre")
	public @ResponseBody GenreBeanEntity getGenre(@PathVariable int id){
		return genreService.getGenre(songService.getSong(id).getGenreId());
	}
	
	@GetMapping(value = "/{id}/album")
	public @ResponseBody AlbumBeanEntity getAlbum(@PathVariable int id){
		return albumService.getAlbum(songService.getSong(id).getAlbumId());
	}
	
	@GetMapping(value = "/{id}/author")
	public @ResponseBody AuthorBeanEntity getAuthor(@PathVariable int id){
		return authorService.getAuthorBySongId(id);
	}
}
