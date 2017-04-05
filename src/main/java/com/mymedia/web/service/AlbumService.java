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

	@Transactional
	public List<AlbumBeanEntity> getAllAlbums() {
		List<Album> albums = albumDAO.getAllAlbums();
		List<AlbumBeanEntity> albumEntities = new ArrayList<>();
		for (Album album : albums) {
			albumEntities.add(albumToAlbumEntity(album));
		}
		return albumEntities;
	}

	@Transactional
	public AlbumBeanEntity getAlbum(int id) {
		return albumToAlbumEntity(albumDAO.getAlbum(id));
	}

	@Transactional
	public AlbumBeanEntity addAlbum(AlbumBeanEntity entity) {
		albumDAO.addAlbum(albumEntityToAlbum(entity));
		return entity;
	}

	public Album albumEntityToAlbum(AlbumBeanEntity entity) {
		Album album = new Album();
		album.setName(entity.getName());
		album.setBirthDate(entity.getBirthDate());
		album.setRating(entity.getRating());
		album.setAuthour(authourDAO.getAuthour(entity.getAuthourId()));
		return album;
	}

	public AlbumBeanEntity albumToAlbumEntity(Album album) {
		AlbumBeanEntity entity = new AlbumBeanEntity();
		entity.setId(album.getId());
		entity.setName(album.getName());
		entity.setBirthDate(album.getBirthDate());
		entity.setRating(album.getRating());
		entity.setAuthourId(album.getAuthour().getId());
		return entity;
	}
}
