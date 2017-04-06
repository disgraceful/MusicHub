package com.mymedia.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mymedia.web.dao.PlaylistDAO;
import com.mymedia.web.dto.PlaylistBeanEntity;
import com.mymedia.web.mvc.model.Album;
import com.mymedia.web.mvc.model.Playlist;

@Service
@EnableTransactionManagement
public class PlaylistService {

	private static final Logger LOG = LogManager.getLogger(PlaylistService.class);

	@Autowired
	private PlaylistDAO playlistDAO;

	public List<PlaylistBeanEntity>getAllPlaylists(){
		List<PlaylistBeanEntity> list = new ArrayList<PlaylistBeanEntity>();
		playlistDAO.getAllPlaylists().stream().forEach(e->list.add(playlistToPlaylistEntity(e)));
		return list;
	}
	
	public PlaylistBeanEntity getPlaylist(int id){
		return playlistToPlaylistEntity(playlistDAO.getPlaylist(id));
	}
	
	public PlaylistBeanEntity addPlaylist(PlaylistBeanEntity entity){
		Playlist playlist = playlistDAO.addPlaylist(playlistEntityToPlaylist(entity));
		return playlistToPlaylistEntity(playlist);
	}
	
	public PlaylistBeanEntity updatePlaylist(PlaylistBeanEntity entity){
		Playlist playlist = playlistDAO.updatePlaylist(playlistEntityToPlaylist(entity));
		return playlistToPlaylistEntity(playlist);
	}
	
	public void deletePlaylist(int id){
		playlistDAO.deletePlaylist(id);
	}
	
	public Playlist playlistEntityToPlaylist(PlaylistBeanEntity entity){
		Playlist playlist = new Playlist();
		playlist.setId(entity.getId());
		playlist.setName(entity.getName());
		return playlist;
	}
	
	public PlaylistBeanEntity playlistToPlaylistEntity(Playlist playlist){
		PlaylistBeanEntity entity = new PlaylistBeanEntity();
		entity.setId(playlist.getId());
		entity.setName(playlist.getName());
		return entity;
	}
}
