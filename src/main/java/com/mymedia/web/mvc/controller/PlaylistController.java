package com.mymedia.web.mvc.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.dto.PlaylistBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.mvc.model.User;
import com.mymedia.web.requestmodel.PlaylistRequestModel;
import com.mymedia.web.requestmodel.AddSongToPlaylistRequestModel;
import com.mymedia.web.service.PlaylistService;
import com.mymedia.web.service.SongService;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
	private static final Logger LOG = LogManager.getLogger(PlaylistController.class);

	@Autowired
	private PlaylistService playlistService;

	@Autowired
	private SongService songService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getPlaylistById(@PathVariable String id) {
		try {
			return new ResponseEntity<>(playlistService.getPlaylistById(id), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping
	public ResponseEntity<?> getPlaylists() {
		try {
			return new ResponseEntity<>(playlistService.getAllPlaylists(), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping(value = "/{id}/songs")
	public ResponseEntity<?> getSongs(@PathVariable String id) {
		try {
			return new ResponseEntity<>(songService.getSongsByPlaylistId(id), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@PostMapping
	public ResponseEntity<?> createPlaylist(@RequestBody PlaylistRequestModel model) {
		try {
			PlaylistBeanEntity playlist = playlistService.createPlaylist(model);
			return new ResponseEntity<>(playlist, HttpStatus.CREATED);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}catch(Exception exc){
			return new ResponseEntity<>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping
	public ResponseEntity<?> updatePlaylist(@RequestBody PlaylistBeanEntity playlist) {
		try{
		return new ResponseEntity<>(playlistService.updatePlaylist(playlist), HttpStatus.ACCEPTED);
		}catch(MusicHubGenericException exc){
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletePlaylist(@PathVariable String id) {
		try{
		playlistService.deletePlaylist(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}catch(MusicHubGenericException exc){
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@PostMapping(value = "/{id}/songs")
	public ResponseEntity<?> addSong(@RequestBody AddSongToPlaylistRequestModel songModel, @PathVariable String id) {
		try{
		return new ResponseEntity<>(playlistService.addSong(songModel.getId(), id), HttpStatus.CREATED);
		}catch(MusicHubGenericException exc){
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@DeleteMapping(value = "/{id}/songs")
	public ResponseEntity<?>  deleteSong(@RequestBody AddSongToPlaylistRequestModel songModel, @PathVariable String id) {
		try{
		playlistService.deleteSongFromPlaylist(songModel.getId(), id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}catch(MusicHubGenericException exc){
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}
}
