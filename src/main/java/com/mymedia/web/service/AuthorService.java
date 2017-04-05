package com.mymedia.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.AuthorDAO;
import com.mymedia.web.dto.AuthorBeanEntity;
import com.mymedia.web.dto.SongBeanEntity;
import com.mymedia.web.mvc.model.Author;

@Service
@EnableTransactionManagement
public class AuthorService {
	private static final Logger LOG = LogManager.getLogger(AuthorService.class);

	@Autowired
	AuthorDAO authorDAO;
	
	@Autowired
	SongService songService;

	@Transactional
	public List<AuthorBeanEntity> getAllAuthors() {
		List<Author> list = authorDAO.getAllAuthors();
		List<AuthorBeanEntity> entityList = new ArrayList<>();
		for (Author author : list) {
			entityList.add(authorToAuthorBeanEntity(author));
		}
		return entityList;
	}

	@Transactional
	public AuthorBeanEntity getAuthor(int id) {
		return authorToAuthorBeanEntity(authorDAO.getAuthor(id));
	}

	@Transactional
	public AuthorBeanEntity addAuthor(AuthorBeanEntity entity) {
		authorDAO.addAuthor(authorBeanEntityToAuthor(entity));
		return entity;
	}
	
	@Transactional
	public List<SongBeanEntity> getSongs(int id){
		return songService.getSongsByAuthourId(id);
	}

	private Author authorBeanEntityToAuthor(AuthorBeanEntity entity) {
		Author a = new Author();
		a.setId(entity.getId());
		a.setName(entity.getName());
		a.setSurName(entity.getSurName());
		a.setRating(entity.getRating());
		a.setBirthDate(entity.getBirthDate());
		return a;
	}

	private AuthorBeanEntity authorToAuthorBeanEntity(Author author) {
		AuthorBeanEntity entity = new AuthorBeanEntity();
		entity.setId(author.getId());
		entity.setName(author.getName());
		entity.setSurName(author.getSurName());
		entity.setRating(author.getRating());
		entity.setBirthDate(author.getBirthDate());
		return entity;
	}
}
