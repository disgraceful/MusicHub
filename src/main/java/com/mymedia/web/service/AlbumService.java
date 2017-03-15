package com.mymedia.web.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.AlbumDAO;
import com.mymedia.web.mvc.model.Album;
import com.mymedia.web.mvc.model.Authour;

@Service
@EnableTransactionManagement
public class AlbumService {
	private static final Logger LOG = LogManager.getLogger(AlbumService.class);

	@Autowired
	AlbumDAO albumDAO;

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
}
