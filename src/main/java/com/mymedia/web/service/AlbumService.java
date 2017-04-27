package com.mymedia.web.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.AlbumDAO;
import com.mymedia.web.dao.AuthorDAO;
import com.mymedia.web.dto.AlbumBeanEntity;
import com.mymedia.web.mvc.model.Album;

@Service
@EnableTransactionManagement
public class AlbumService {
	private static final Logger LOG = LogManager.getLogger(AlbumService.class);

	@Autowired
	AlbumDAO albumDAO;

	@Autowired
	AuthorDAO authorDAO;

	@Transactional
	public List<AlbumBeanEntity>getTop10(){
		List<AlbumBeanEntity> list = new ArrayList<>();
		List<Album>albums =albumDAO.getAllAlbums(); 
		Collections.sort(albums);
		int max = albums.size()<10?albums.size():10;
		albums.subList(0, max).stream().forEach(e->list.add(albumToAlbumEntity(e)));
		return list;
	}
	
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
		Album album = albumDAO.addAlbum(albumEntityToAlbum(entity));
		return albumToAlbumEntity(album);
	}
	
	@Transactional
	public AlbumBeanEntity updateAlbum(AlbumBeanEntity entity){
		Album album = albumDAO.updateAlbum(albumEntityToAlbum(entity));
		return albumToAlbumEntity(album);
	}
	
	@Transactional
	public void deleteAlbum(int id){
		albumDAO.deleteAlbum(id);
	}

	@Transactional
	public List<AlbumBeanEntity> getAlbumsByAuthorId(int id){
		List<AlbumBeanEntity>list = new ArrayList<>();
		for(Album album : authorDAO.getAuthor(id).getAlbums()){
			list.add(albumToAlbumEntity(album));
		}
		return list;
	}
	
	public Album albumEntityToAlbum(AlbumBeanEntity entity) {
		Album album = new Album();
		album.setId(entity.getId());
		album.setName(entity.getName());
		album.setBirthDate(entity.getBirthDate());
		album.setRating(entity.getRating());
		album.setAuthor(authorDAO.getAuthor(entity.getAuthorId()));
		return album;
	}

	public AlbumBeanEntity albumToAlbumEntity(Album album) {
		AlbumBeanEntity entity = new AlbumBeanEntity();
		entity.setId(album.getId());
		entity.setName(album.getName());
		entity.setBirthDate(album.getBirthDate());
		entity.setRating(album.getRating());
		entity.setAuthorId(album.getAuthor().getId());
		entity.setAuthorName(album.getAuthor().getName());
		entity.setSongAmount(album.getSongs().size());
		return entity;
	}
}
