package com.mymedia.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.ConsumerDAO;
import com.mymedia.web.dao.PlaylistDAO;
import com.mymedia.web.dao.SongDAO;
import com.mymedia.web.dto.PlaylistBeanEntity;
import com.mymedia.web.dto.SongBeanEntity;
import com.mymedia.web.mvc.model.Playlist;
import com.mymedia.web.mvc.model.Song;
import com.mymedia.web.requestmodel.PlaylistRequestModel;

@Service
@EnableTransactionManagement
public class PlaylistService {
	private static final Logger LOG = LogManager.getLogger(PlaylistService.class);

	@Autowired
	private PlaylistDAO playlistDAO;

	@Autowired
	private ConsumerDAO consumerDAO;

	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private SongDAO songDAO;
	
	@Autowired
	private SongService songService;
	
	@Transactional
	@PreAuthorize("hasAuthority('CONSUMER')")
	public PlaylistBeanEntity createPlaylist(PlaylistRequestModel model, int userId) {
		Playlist playlist = new Playlist();
		playlist.setName(model.getName().trim());
		playlist.setSongs(new ArrayList<Song>());
		playlist.setConsumer(consumerService.getConsumerByUserId(userId));
		return playlistToPlaylistEntity(playlistDAO.addPlaylist(playlist));
	}

	@Transactional
	public List<PlaylistBeanEntity> getPlaylistByUserId(int id) {
		List<PlaylistBeanEntity> list = new ArrayList<>();
		consumerDAO.getConsumer(id).getPlaylsits().stream().forEach(e -> list.add(playlistToPlaylistEntity(e)));
		return list;
	}

	@Transactional
	public List<PlaylistBeanEntity> getAllPlaylists() {
		List<PlaylistBeanEntity> list = new ArrayList<PlaylistBeanEntity>();
		playlistDAO.getAllPlaylists().stream().forEach(e -> list.add(playlistToPlaylistEntity(e)));
		return list;
	}

	@Transactional
	public SongBeanEntity addSong(int songId, int playlistId) {
		Playlist playlist = playlistDAO.getPlaylist(playlistId);
		Song song = songDAO.getSong(songId);
		playlist.getSongs().add(song);
		song.getPlaylists().add(playlist);
		playlistDAO.updatePlaylist(playlist);
		return songService.songToSongEntity(songDAO.updateSong(song));
		
	}

	@Transactional
	public void deleteSongFromPlaylist(int songId, int playlistId) {
		Playlist playlist = playlistDAO.getPlaylist(playlistId);
		Song song = songDAO.getSong(songId);
		playlist.getSongs().remove(song);
		song.getPlaylists().remove(playlist);
		playlistDAO.updatePlaylist(playlist);
		songDAO.updateSong(song);
	}

	@Transactional
	public PlaylistBeanEntity getPlaylist(int id) {
		return playlistToPlaylistEntity(playlistDAO.getPlaylist(id));
	}

	@Transactional
	public PlaylistBeanEntity updatePlaylist(PlaylistBeanEntity entity) {
		Playlist playlist = playlistDAO.updatePlaylist(playlistEntityToPlaylist(entity));
		return playlistToPlaylistEntity(playlist);
	}

	@Transactional
	public void deletePlaylist(int id) {
		playlistDAO.deletePlaylist(id);
	}

	public Playlist playlistEntityToPlaylist(PlaylistBeanEntity entity) {
		Playlist playlist = new Playlist();
		playlist.setId(entity.getId());
		playlist.setName(entity.getName());
		playlist.setConsumer(consumerDAO.getConsumer(entity.getConsumerId()));
		return playlist;
	}

	public PlaylistBeanEntity playlistToPlaylistEntity(Playlist playlist) {
		PlaylistBeanEntity entity = new PlaylistBeanEntity();
		entity.setId(playlist.getId());
		entity.setName(playlist.getName());
		entity.setConsumerId(playlist.getConsumer().getId());
		entity.setSongAmount(playlist.getSongs().size());
		return entity;
	}
}
