package com.mymedia.web.mvc.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.dto.PlaylistBeanEntity;
import com.mymedia.web.dto.SongBeanEntity;
import com.mymedia.web.mvc.model.User;
import com.mymedia.web.requestmodel.PlaylistRequestModel;
import com.mymedia.web.requestmodel.SongRequestModel;
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
	public ResponseEntity<PlaylistBeanEntity> getPlaylistById(@PathVariable int id) {
		PlaylistBeanEntity playlist = playlistService.getPlaylist(id);
		if(playlist!=null){
			return new ResponseEntity<>(playlist,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<List<PlaylistBeanEntity>> getPlaylists() {
		List<PlaylistBeanEntity> playlists = playlistService.getAllPlaylists();
		if(playlists!=null&&!playlists.isEmpty()){
			return new ResponseEntity<>(playlists,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/{id}/songs")
	public ResponseEntity<List<SongBeanEntity>> getSongs(@PathVariable int id) {
		List<SongBeanEntity> songs = songService.getSongsByPlaylistId(id);
		if(songs!=null&&!songs.isEmpty()){
			return new ResponseEntity<>(songs,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<PlaylistBeanEntity> createPlaylist(@RequestBody PlaylistRequestModel model) {
		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PlaylistBeanEntity playlist = playlistService.createPlaylist(model, u.getId());
		if (playlist != null) {
			return new ResponseEntity<>(playlist, HttpStatus.OK);
		}
		return new ResponseEntity<>(playlist, HttpStatus.NOT_FOUND);
		// TODO ask Nazar
	}

	@PatchMapping
	public ResponseEntity<PlaylistBeanEntity> updatePlaylist(@RequestBody PlaylistBeanEntity playlist) {
		return new ResponseEntity<>(playlistService.updatePlaylist(playlist),HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public void deletePlaylist(@RequestBody int id) {
		playlistService.deletePlaylist(id);
	}

	@PostMapping(value = "/{id}/songs")
	public ResponseEntity<SongBeanEntity> addSong(@RequestBody SongRequestModel songModel, @PathVariable int id) {
		return new ResponseEntity<>(playlistService.addSong(songModel.getId(),id),HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}/songs")
	public void deleteSong(@RequestBody SongRequestModel songModel, @PathVariable int id) {
		playlistService.deleteSongFromPlaylist(songModel.getId(), id);
	}
}
