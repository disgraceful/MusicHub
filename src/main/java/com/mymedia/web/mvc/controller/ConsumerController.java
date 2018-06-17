package com.mymedia.web.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.dto.ConsumerBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.mvc.model.User;
import com.mymedia.web.service.PlaylistService;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
	@Autowired
	private PlaylistService playlistService;

	@GetMapping(value = "/history")
	public ResponseEntity<?> getHistory() {
		try {
			return new ResponseEntity<>(playlistService.getHistorySongs(), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping(value = "/playlists")
	public ResponseEntity<?> getPlaylists() {
		try {
			
			return new ResponseEntity<>(playlistService.getConsumerPlaylistsAsEntities(),HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping(value = "/playlists/songs")
	public ResponseEntity<?> getSongsFromUserPlaylists() {
		try {
			return new ResponseEntity<>(playlistService.getSongsFromPlaylistsAsEntities(), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}
}
