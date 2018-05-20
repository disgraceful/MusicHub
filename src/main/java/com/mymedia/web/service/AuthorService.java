package com.mymedia.web.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.AuthorDAO;
import com.mymedia.web.dao.SongDAO;
import com.mymedia.web.dto.AuthorBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.mvc.model.Author;
import com.mymedia.web.mvc.model.Song;

@Service
@EnableTransactionManagement
public class AuthorService {
	private static final Logger LOG = LogManager.getLogger(AuthorService.class);

	@Autowired
	AuthorDAO authorDAO;

	@Autowired
	SongDAO songDAO;

	@Transactional
	public List<AuthorBeanEntity> getTop10() {
		try {
			List<Author> authors = authorDAO.getAllAuthors();
			if (authors.isEmpty()) {
				throw new MusicHubGenericException("No Authors Found", HttpStatus.NO_CONTENT);
			}
			List<AuthorBeanEntity> list = new ArrayList<>();
			Collections.sort(authors);
			int max = authors.size() < 10 ? authors.size() : 10;
			authors.subList(0, max).stream().forEach(e -> list.add(authorToAuthorEntity(e)));
			return list;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to retrieve Top Author Collection",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public List<AuthorBeanEntity> getAllAuthors() {
		try {
			List<Author> authors = authorDAO.getAllAuthors();
			if (authors.isEmpty()) {
				throw new MusicHubGenericException("No Authors Found", HttpStatus.NO_CONTENT);
			}
			List<AuthorBeanEntity> list = new ArrayList<>();
			authors.stream().forEach(e -> list.add(authorToAuthorEntity(e)));

			return list;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to retrieve Author Collection",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public AuthorBeanEntity getAuthor(String id) {
		try {
			Author author = authorDAO.getAuthor(id);
			if (author == null) {
				throw new MusicHubGenericException("Author with id " + id + " does not exist", HttpStatus.NOT_FOUND);
			}
			return authorToAuthorEntity(author);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to retrieve Author", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public AuthorBeanEntity getAuthorBySongId(String id) {
		try {
			Song song = songDAO.getSong(id);
			if (song == null) {
				throw new MusicHubGenericException("Song with that id does not exist!", HttpStatus.NOT_FOUND);
			}
			return authorToAuthorEntity(song.getAuthor());
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get Author", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public AuthorBeanEntity addAuthor(AuthorBeanEntity entity) {
		try {
			if (entity == null) {
				throw new MusicHubGenericException("Not a valid request!", HttpStatus.BAD_REQUEST);
			}
			Author author = authorDAO.addAuthor(authorEntityToAuthor(entity));
			return authorToAuthorEntity(author);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to add Author", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public Author addAuthor(Author author) {
		try {
			if (author == null) {
				throw new MusicHubGenericException("Not a valid request!", HttpStatus.BAD_REQUEST);
			}
			return authorDAO.addAuthor(author);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to add Author", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Transactional
	public AuthorBeanEntity updateAuthor(AuthorBeanEntity entity) {
		try {
			return authorToAuthorEntity(authorDAO.updateAuthor(authorEntityToAuthor(entity)));
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to update Author", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public void deleteAuthor(String id) {
		try {
			authorDAO.deleteAuthor(id);
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to delete Author", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private Author authorEntityToAuthor(AuthorBeanEntity entity) throws ParseException {
		Author a = new Author();
		a.setId(entity.getId());
		a.setName(entity.getName());
		a.setSurName(entity.getSurName());
		a.setRating(entity.getRating());
		a.setBirthDate(new SimpleDateFormat("dd/M/yyyy").parse(entity.getBirthDate()));
		return a;
	}

	private AuthorBeanEntity authorToAuthorEntity(Author author) {
		AuthorBeanEntity entity = new AuthorBeanEntity();
		entity.setId(author.getId());
		entity.setName(author.getName());
		entity.setSurName(author.getSurName());
		entity.setRating(author.getRating());
		entity.setBirthDate(new SimpleDateFormat("dd/M/yyyy").format(author.getBirthDate()));
		return entity;
	}
}
