package com.mymedia.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.GenreDAO;
import com.mymedia.web.dto.GenreBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.mvc.model.Genre;

@Service
@EnableTransactionManagement
public class GenreService {

	private static final Logger LOG = LogManager.getLogger(GenreService.class);

	@Autowired
	GenreDAO genreDAO;

	@Transactional
	public List<GenreBeanEntity> getAllGenres() {
		try {
			List<Genre> genres = genreDAO.getAllGenres();
			if (genres.isEmpty()) {
				throw new MusicHubGenericException("No Genres Found", HttpStatus.NO_CONTENT);
			}
			List<GenreBeanEntity> list = new ArrayList<>();
			genres.stream().forEach(e -> list.add(genreToGenreEntity(e)));
			return list;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get Genre Collection", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public Genre getGenreByName(String name) {
		try {
			Optional<Genre> opt = genreDAO.getAllGenres().stream().filter(e -> e.getName().trim().equals(name.trim()))
					.findFirst();
			if (opt.isPresent()) {
				return opt.get();
			} else {
				Genre g = new Genre();
				g.setName(name);
				g.setSongList(new ArrayList<>());
				return genreDAO.addGenre(g);
			}
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to retrieve Genre with that name",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public GenreBeanEntity getGenre(String id) {
		try {
			Genre genre = genreDAO.getGenre(id);
			if (genre == null) {
				throw new MusicHubGenericException("Genre with that id does not exist!", HttpStatus.NOT_FOUND);
			}
			return genreToGenreEntity(genre);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get Genre", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public GenreBeanEntity addGenre(GenreBeanEntity entity) {
		try {
			Genre genre = genreDAO.addGenre(genreEntityToGenre(entity));
			if (genre == null) {
				throw new MusicHubGenericException("Not a valid request!", HttpStatus.BAD_REQUEST);
			}
			return genreToGenreEntity(genre);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to add Genre", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public Genre addGenre(Genre genre) {
		try {
			Genre g = genreDAO.addGenre(genre);
			if (g == null) {
				throw new MusicHubGenericException("Not a valid request!", HttpStatus.BAD_REQUEST);
			}
			return genre;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to add Genre", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public GenreBeanEntity updateGenre(GenreBeanEntity entity) {
		try {
			Genre genre = genreDAO.updateGenre(genreEntityToGenre(entity));
			return genreToGenreEntity(genre);
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to update Genre", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public void deleteGenre(String id) {
		try {
			genreDAO.deleteGenre(id);
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to delete Genre", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private Genre genreEntityToGenre(GenreBeanEntity entity) {
		Genre genre = new Genre();
		genre.setId(entity.getId());
		genre.setName(entity.getName());
		return genre;
	}

	private GenreBeanEntity genreToGenreEntity(Genre genre) {
		GenreBeanEntity entity = new GenreBeanEntity();
		entity.setId(genre.getId());
		entity.setName(genre.getName());
		return entity;
	}
}
