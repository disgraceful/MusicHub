package com.mymedia.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.AlbumDAO;
import com.mymedia.web.dao.AuthorDAO;
import com.mymedia.web.dao.PlaylistDAO;
import com.mymedia.web.dao.SongDAO;
import com.mymedia.web.dto.SongBeanEntity;
import com.mymedia.web.mvc.model.Album;
import com.mymedia.web.mvc.model.Author;
import com.mymedia.web.mvc.model.Song;

@Service
@EnableTransactionManagement
public class SongService {
	private static final Logger LOG = LogManager.getLogger(SongService.class);

	@Autowired
	SongDAO songDAO;

	@Autowired
	AuthorDAO authorDAO;

	@Autowired
	AlbumDAO albumDAO;

	@Autowired
	PlaylistDAO playlistDAO;
	
	@Transactional
	public List<SongBeanEntity> getAllSongs() {
		List<Song> songs = songDAO.getAllSongs();
		List<SongBeanEntity> songEntities = new ArrayList<>();
		SongBeanEntity entity = new SongBeanEntity();
		for (Song song : songs) {
			songEntities.add(songToSongEntity(song));
		}
		return songEntities;
	}

	@Transactional
	public SongBeanEntity getSong(int id) {
		Song song = songDAO.getSong(id);
		return songToSongEntity(song);
	}

	@Transactional
	public SongBeanEntity addSong(SongBeanEntity entity) {
		Song song = songDAO.addSong(songEntityToSong(entity));
		return songToSongEntity(song);
	}

	@Transactional
	public SongBeanEntity updateSong(SongBeanEntity entity) {
		Song song = songDAO.updateSong(songEntityToSong(entity));
		return songToSongEntity(song);
	}

	@Transactional
	public void deleteSong(int id) {
		songDAO.deleteSongById(id);
	}

	@Transactional
	public List<SongBeanEntity> getSongsByAuthorId(int id) {
		Author a = authorDAO.getAuthor(id);
		List<SongBeanEntity> list = new ArrayList<>();
		for (Song s : a.getSongs()) {
			list.add(songToSongEntity(s));
		}
		return list;
	}

	@Transactional
	public List<SongBeanEntity> getSongsByAlbumId(int id) {
		Album a = albumDAO.getAlbum(id);
		List<SongBeanEntity> list = new ArrayList<>();
		for (Song s : a.getSongs()) {
			list.add(songToSongEntity(s));
		}
		return list;
	}

	@Transactional
	public List<SongBeanEntity> getSongsByGenreId(int id) {
		List<SongBeanEntity> list = new ArrayList<>();
		for (SongBeanEntity s : getAllSongs()) {
			if (s.getGenreId() == id) {
				list.add(s);
			}
		}
		return list;
	}
	
	@Transactional
	public List<SongBeanEntity>getSongsByPlaylistId(int id){
		List<SongBeanEntity> list = new ArrayList<>();
		playlistDAO.getPlaylist(id).getSongs().stream().forEach(e->list.add(songToSongEntity(e)));
		return list;
	}

	public Song songEntityToSong(SongBeanEntity entity) {
		Song song = new Song();
		song.setId(entity.getId());
		song.setName(entity.getName());
		song.setBirthDate(entity.getBirthDate());
		song.setRating(entity.getRating());
		return song;
	}

	public SongBeanEntity songToSongEntity(Song song) {
		SongBeanEntity entity = new SongBeanEntity();
		entity.setId(song.getId());
		entity.setName(song.getName());
		entity.setBirthDate(song.getBirthDate());
		entity.setRating(song.getRating());
		return entity;
	}
}
