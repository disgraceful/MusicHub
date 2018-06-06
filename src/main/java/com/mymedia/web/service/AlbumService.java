package com.mymedia.web.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.AlbumDAO;
import com.mymedia.web.dao.AuthorDAO;
import com.mymedia.web.dao.GenreDAO;
import com.mymedia.web.dto.AlbumBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.mvc.model.Album;
import com.mymedia.web.mvc.model.Genre;
import com.mymedia.web.requestmodel.AlbumCreateRequestModel;

@Service
@EnableTransactionManagement
public class AlbumService {
	private static final Logger LOG = LogManager.getLogger(AlbumService.class);

	@Autowired
	private AlbumDAO albumDAO;

	@Autowired
	private AuthorDAO authorDAO;

	@Autowired
	private GenreDAO genreDAO;

	@Transactional
	public List<AlbumBeanEntity> getAllAlbums() {
		try {
			List<Album> albums = albumDAO.getAllAlbums();
			if (albums.isEmpty()) {
				throw new MusicHubGenericException("No Albums Found", HttpStatus.NO_CONTENT);
			}
			List<AlbumBeanEntity> albumEntities = new ArrayList<>();
			albums.stream().forEach(e -> albumEntities.add(albumToAlbumEntity(e)));
			return albumEntities;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to retrieve Album  Collection",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public List<AlbumBeanEntity> getTop10() {
		try {
			List<Album> albums = albumDAO.getAllAlbums();
			if (albums.isEmpty()) {
				throw new MusicHubGenericException("No Albums Found", HttpStatus.NO_CONTENT);
			}
			List<AlbumBeanEntity> list = new ArrayList<>();
			Collections.sort(albums);
			int max = albums.size() < 10 ? albums.size() : 10;
			albums.subList(0, max).stream().forEach(e -> list.add(albumToAlbumEntity(e)));
			return list;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to retrieve Top Albums", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public List<AlbumBeanEntity> getNewAlbumsByGenre(String id, int amount) {
		try {
			List<Album> albums = getAlbumsByGenre(id);
			Collections.sort(albums,Album.albumComparator);
			int max = albums.size() < amount? albums.size() : amount;
			return albums.subList(0, max).stream().map(f->albumToAlbumEntity(f)).collect(Collectors.toList());
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to retrieve Top Albums", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	public List<AlbumBeanEntity> getTopAlbumsByGenre(String id, int amount) {
		try {
			List<Album> albums = getAlbumsByGenre(id);
			Collections.sort(albums);
			int max = albums.size() < amount? albums.size() : amount;
			return albums.subList(0, max).stream().map(f->albumToAlbumEntity(f)).collect(Collectors.toList());
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to retrieve Top Albums", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@Transactional
	public List<AlbumBeanEntity> getAlbumsByGenreAsEntity(String id,int amount) {
		try {
			List<Album> albums = getAlbumsByGenre(id);
			int max = albums.size() < amount? albums.size() : amount;
			return albums.subList(0, max).stream().map(f->albumToAlbumEntity(f)).collect(Collectors.toList()); 
			
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to retrieve Top Albums", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Transactional
	public List<Album> getAlbumsByGenre(String id) {
		try {
			List<Album> albums = albumDAO.getAllAlbums();
			List<Album>genreAlbums = albums.stream().filter(e->e.getGenre().getId().equals(id)).collect(Collectors.toList());
			return genreAlbums;	
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to retrieve Top Albums", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Transactional
	public AlbumBeanEntity getAlbum(String id) {
		try {
			Album album = albumDAO.getAlbum(id);
			if (album == null) {
				throw new MusicHubGenericException("Album with id " + id + " does not exist", HttpStatus.NOT_FOUND);
			}
			return albumToAlbumEntity(album);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to retrieve Album", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public List<AlbumBeanEntity> getAlbumsByAuthorId(String id) {
		try {
			List<Album> albums = authorDAO.getAuthor(id).getAlbums();
			if (albums.isEmpty()) {
				throw new MusicHubGenericException("No Albums Found", HttpStatus.NO_CONTENT);
			}
			List<AlbumBeanEntity> list = new ArrayList<>();
			albums.stream().forEach(e -> list.add(albumToAlbumEntity(e)));
			return list;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to retrieve Albums", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public AlbumBeanEntity createAlbum(AlbumCreateRequestModel model) {
		try {
			if (validateAlbumModel(model)) {
				throw new MusicHubGenericException("Invalid album name!", HttpStatus.BAD_REQUEST);
			}
			Album album = new Album();
			album.setName(model.getName().trim());
			album.setAuthor(authorDAO.getAuthor(model.getAuthorId()));
			album.setRecordDate(model.getRecordDate());
			album.setRating(0);
			album.setGenre(genreDAO.getGenre(model.getGenreId()));
			album.setImgPath(model.getImgPath());
			return albumToAlbumEntity(albumDAO.addAlbum(album));
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to create Album", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public AlbumBeanEntity updateAlbum(AlbumBeanEntity entity) {
		try {
			return albumToAlbumEntity(albumDAO.updateAlbum(albumEntityToAlbum(entity)));
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to update Album", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public void deleteAlbum(String id) {
		try {
			albumDAO.deleteAlbum(id);
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to delete Album", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private boolean validateAlbumModel(AlbumCreateRequestModel model) {
		return model.getName() == null;
	}

	public Album albumEntityToAlbum(AlbumBeanEntity entity) {
		Album album = new Album();
		album.setId(entity.getId());
		album.setName(entity.getName());
		album.setRecordDate(entity.getRecordDate());
		album.setRating(entity.getRating());
		album.setAuthor(authorDAO.getAuthor(entity.getAuthorId()));
		album.setImgPath(entity.getImgPath());
		return album;
	}

	public AlbumBeanEntity albumToAlbumEntity(Album album) {
		AlbumBeanEntity entity = new AlbumBeanEntity();
		entity.setId(album.getId());
		entity.setName(album.getName());
		entity.setRecordDate(album.getRecordDate());
		entity.setRating(album.getRating());
		entity.setAuthorId(album.getAuthor().getId());
		entity.setAuthorName(album.getAuthor().getName());
		entity.setSongAmount(album.getSongs() == null ? 0 : album.getSongs().size());
		entity.setGenreId(album.getGenre().getId());
		entity.setGenreName(album.getGenre().getName());
		entity.setImgPath(album.getImgPath());
		return entity;
	}
}
