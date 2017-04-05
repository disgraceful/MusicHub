package com.mymedia.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.GenreDAO;
import com.mymedia.web.dto.GenreBeanEntity;
import com.mymedia.web.dto.SongBeanEntity;
import com.mymedia.web.mvc.model.Album;
import com.mymedia.web.mvc.model.Genre;
import com.mymedia.web.mvc.model.Song;

@Service
@EnableTransactionManagement
public class GenreService {
	
	@Autowired
	GenreDAO genreDAO;
	
	@Transactional
	public List<GenreBeanEntity> getAllGenres() {
		List<Genre> genres = genreDAO.getAllGenres();
		List<GenreBeanEntity> genreEntities = new ArrayList<>();
		GenreBeanEntity entity = new GenreBeanEntity();
		for (Genre genre: genres) {
			genreEntities.add(genreToGenreEntity(genre));
		}
		return genreEntities;
	}

	@Transactional
	public GenreBeanEntity getGenre(int id) {
		Genre genre = genreDAO.getGenre(id);
		return genreToGenreEntity(genre);
	}

	@Transactional
	public GenreBeanEntity addGenre(GenreBeanEntity entity) {
		Genre genre = genreDAO.addGenre(genreEntityToGenre(entity));
		return genreToGenreEntity(genre);
	}
	
	@Transactional
	public GenreBeanEntity updateGenre(GenreBeanEntity entity) {
		Genre genre = genreDAO.updateGenre(genreEntityToGenre(entity));
		return genreToGenreEntity(genre);
	}	
	
	@Transactional
	public void deleteGenre(int id){
		genreDAO.deleteGenre(id);
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
