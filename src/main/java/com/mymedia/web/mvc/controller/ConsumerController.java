package com.mymedia.web.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.dto.PlaylistBeanEntity;
import com.mymedia.web.service.PlaylistService;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
	@Autowired
	private PlaylistService playlistService;

	@GetMapping(value = "/{id}/playlists")
	public ResponseEntity<List<PlaylistBeanEntity>> getPlaylists(@PathVariable int id) {
		List<PlaylistBeanEntity> playlists = playlistService.getPlaylistByUserId(id);
		if(!playlists.isEmpty()&&playlists!=null){
			return new ResponseEntity<>(playlists,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
