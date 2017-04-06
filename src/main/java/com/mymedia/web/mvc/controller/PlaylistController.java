package com.mymedia.web.mvc.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.dto.PlaylistBeanEntity;
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
	
	 @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	    public @ResponseBody PlaylistBeanEntity getPlaylistById(@PathVariable int id) {
	        PlaylistBeanEntity playlist = playlistService.getPlaylist(id);
	        LOG.info(playlist.toString());
	        return playlist;
	    }

	    @RequestMapping(method = RequestMethod.GET)
	    public @ResponseBody List<PlaylistBeanEntity> getPlaylists() {
	        return playlistService.getAllPlaylists();
	    }

	    @RequestMapping(method = RequestMethod.POST)
	    public @ResponseBody PlaylistBeanEntity postPlaylist(@RequestBody PlaylistBeanEntity playlist) {
	        return playlistService.addPlaylist(playlist);
	    }
	    
	    
	    @RequestMapping(method = RequestMethod.PATCH)
	    public @ResponseBody PlaylistBeanEntity updatePlaylist(@RequestBody PlaylistBeanEntity playlist){
	    	return playlistService.updatePlaylist(playlist);
	    }
	    
	    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	    public @ResponseBody void deletePlaylist(@RequestBody int id){
	    	playlistService.deletePlaylist(id);
	    }
	    
	    @RequestMapping(value = "/{id}/songs",method = RequestMethod.GET)
	    public @ResponseBody void getSongs(@RequestBody int id){
	    	songService.getSongsByPlaylistId(id);
	    }
	
}
