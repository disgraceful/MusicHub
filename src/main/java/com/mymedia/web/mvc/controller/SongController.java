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

	@GetMapping
	public ResponseEntity<List<SongBeanEntity>> getSongs() {
		List<SongBeanEntity> songs = songService.getAllSongs();
		if (!songs.isEmpty() && songs != null) {
			return new ResponseEntity<>(songs, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<SongBeanEntity> getSongById(@PathVariable int id) {
		SongBeanEntity song = songService.getSong(id);
		if(song!=null){
			return new ResponseEntity<>(song, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/{id}/genre")
	public ResponseEntity<GenreBeanEntity> getGenreBySongId(@PathVariable int id) {
		GenreBeanEntity genre = genreService.getGenre(songService.getSong(id).getGenreId());
		if(genre!=null){
			return new ResponseEntity<>(genre,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/{id}/album")
	public ResponseEntity<AlbumBeanEntity> getAlbumBySongId(@PathVariable int id) {
		AlbumBeanEntity album = albumService.getAlbum(songService.getSong(id).getAlbumId());
		if(album!=null){
			return new ResponseEntity<>(album,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/{id}/author")
	public ResponseEntity<AuthorBeanEntity> getAuthorBySongId(@PathVariable int id) {
		AuthorBeanEntity author= authorService.getAuthorBySongId(id);
		if(author!=null){
			return new ResponseEntity<>(author,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<SongBeanEntity> addSong (@RequestBody SongBeanEntity song) {
		return new ResponseEntity<>(songService.addSong(song),HttpStatus.OK);
	}

	@PatchMapping
	public ResponseEntity<SongBeanEntity> updateSong(@RequestBody SongBeanEntity song) {
		return new ResponseEntity<>(songService.updateSong(song),HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public @ResponseBody void deleteSong(@PathVariable int id) {
		songService.deleteSong(id);
	}

}
