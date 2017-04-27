package com.mymedia.web.mvc.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
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

	@PostMapping
	public @ResponseBody PlaylistBeanEntity createPlaylist(@RequestBody PlaylistRequestModel model) {
		SecurityContext context=SecurityContextHolder.getContext(); 
		LOG.info(context);
		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (u.getRole().getName().trim().equals("CONSUMER")) {
			return playlistService.createPlaylist(model, u.getId());
		}
		return null;

	}

	@GetMapping(value = "/{id}")
	public @ResponseBody PlaylistBeanEntity getPlaylistById(@PathVariable int id) {
		PlaylistBeanEntity playlist = playlistService.getPlaylist(id);
		LOG.info(playlist.toString());
		return playlist;
	}

	@GetMapping
	public @ResponseBody List<PlaylistBeanEntity> getPlaylists() {
		return playlistService.getAllPlaylists();
	}

	@PatchMapping
	public @ResponseBody PlaylistBeanEntity updatePlaylist(@RequestBody PlaylistBeanEntity playlist) {
		return playlistService.updatePlaylist(playlist);
	}

	@DeleteMapping(value = "/{id}")
	public void deletePlaylist(@RequestBody int id) {
		playlistService.deletePlaylist(id);
	}

	@GetMapping(value = "/{id}/songs")
	public List<SongBeanEntity> getSongs(@PathVariable int id) {
		return songService.getSongsByPlaylistId(id);
	}

	@PostMapping(value = "/{id}/songs")
	public @ResponseBody SongBeanEntity addSong(@RequestBody SongRequestModel songModel, @PathVariable int id) {
		return songService.addSong(songModel.getId(), id);
	}

}
