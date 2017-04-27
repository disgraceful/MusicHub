package com.mymedia.web.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.dto.PlaylistBeanEntity;
import com.mymedia.web.requestmodel.PlaylistRequestModel;
import com.mymedia.web.service.PlaylistService;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
	@Autowired
	private PlaylistService playlistService;

	@GetMapping(value = "/{id}/playlists")
	public List<PlaylistBeanEntity> getPlaylists(@PathVariable int id) {
		return playlistService.getPlaylistByUserId(id);
	}
}
