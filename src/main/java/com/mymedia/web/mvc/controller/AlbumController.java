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

import com.mymedia.web.dto.AlbumBeanEntity;
import com.mymedia.web.dto.AuthorBeanEntity;
import com.mymedia.web.dto.SongBeanEntity;
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
	public ResponseEntity<List<AlbumBeanEntity>> getAlbums() {
		List<AlbumBeanEntity> albums = albumService.getAllAlbums();
		if (!albums.isEmpty() && albums != null) {
			return new ResponseEntity<>(albums, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/top")
	public ResponseEntity<List<AlbumBeanEntity>> getTopAlbums() {
		List<AlbumBeanEntity> albums = albumService.getTop10();
		if (!albums.isEmpty() && albums != null) {
			return new ResponseEntity<>(albums, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AlbumBeanEntity> getAlbumById(@PathVariable int id) {
		AlbumBeanEntity album = albumService.getAlbum(id);
		LOG.info(album==null);
		if (album != null) {
			return new ResponseEntity<>(album, HttpStatus.OK);
		}
		return new ResponseEntity<AlbumBeanEntity>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/{id}/author")
	public ResponseEntity<AuthorBeanEntity> getAuthorByAlbumId(@PathVariable int id) {
		AlbumBeanEntity album = albumService.getAlbum(id);
		AuthorBeanEntity author = authorService.getAuthor(album.getAuthorId());
		if (author != null) {
			return new ResponseEntity<>(author, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/{id}/songs")
	public ResponseEntity<List<SongBeanEntity>> getSongsByAlbumId(@PathVariable int id) {
		List<SongBeanEntity> songs = songService.getSongsByAlbumId(id);
		if (!songs.isEmpty() && songs != null) {
			return new ResponseEntity<>(songs, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping
	public ResponseEntity<AlbumBeanEntity> postAlbum(@RequestBody AlbumBeanEntity album) {
		return new ResponseEntity<>(albumService.addAlbum(album), HttpStatus.OK);
		// TODO Ask Nazar about this stuff
	}

	@PatchMapping
	public ResponseEntity<AlbumBeanEntity> updateAlbum(@RequestBody AlbumBeanEntity album) {
		return new ResponseEntity<>(albumService.updateAlbum(album), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteAlbum(@RequestBody int id) {
		albumService.deleteAlbum(id);
	}

}
