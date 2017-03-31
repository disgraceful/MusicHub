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
import com.mymedia.web.dao.AuthourDAO;
import com.mymedia.web.dto.AlbumBeanEntity;
import com.mymedia.web.dto.SongBeanEntity;
import com.mymedia.web.mvc.model.Album;
import com.mymedia.web.mvc.model.Song;

@Service
@EnableTransactionManagement
public class AlbumService {
	private static final Logger LOG = LogManager.getLogger(AlbumService.class);

	@Autowired
	AlbumDAO albumDAO;

	@Autowired
	AuthourDAO authourDAO;

	@Autowired
	SongService songService;

	@Transactional
	public List<Album> getAllAlbums() {
		return albumDAO.getAllAlbums();
	}

	@Transactional
	public Album getAlbum(int id) {
		return albumDAO.getAlbum(id);
	}

	@Transactional
	public Album addAlbum(Album album) {
		return albumDAO.addAlbum(album);
	}

	@Transactional
	public List<SongBeanEntity> getSongs(int id) {
		return songService.getSongsByAlbumId(id);
	}

	public Album albumBeanEntityToAlbum(AlbumBeanEntity entity) {
		Album album = new Album();
		album.setName(entity.getName());
		album.setBirthDate(entity.getBirthDate());
		album.setRating(entity.getRating());
		album.setAuthour(authourDAO.getAuthour(entity.getAuthourId()));
		return album;
	}

	public Album AlbumToAlbumBeanEntity(Album album) {
		AlbumBeanEntity entity = new AlbumBeanEntity();
		entity.setId(album.getId());
		entity.setName(album.getName());
		entity.setBirthDate(album.getBirthDate());
		entity.setRating(album.getRating());
		entity.setAuthourId(album.getAuthour().getId());
		return album;
	}
}
