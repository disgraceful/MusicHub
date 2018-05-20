package com.mymedia.web.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.AuthorDAO;
import com.mymedia.web.dao.PublisherDAO;
import com.mymedia.web.dao.UserDAO;
import com.mymedia.web.dto.PublisherBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.mvc.model.Author;
import com.mymedia.web.mvc.model.Publisher;
import com.mymedia.web.mvc.model.User;
import com.mymedia.web.requestmodel.CreatePublisherRequestModel;

@Service
public class PublisherService {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private PublisherDAO publisherDAO;

	@Autowired
	private AuthorDAO authorDAO;

	@Autowired
	private UserDAO userDAO;

	@Transactional
	public PublisherBeanEntity getPublisher(String id) {
		try {
			Publisher pub = publisherDAO.getPublisher(id);
			if (pub == null) {
				throw new MusicHubGenericException("User with that id does not exist!", HttpStatus.NOT_FOUND);
			}
			return publisherToPublisherEntity(pub);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get Publisher", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public Publisher getPublisherByUserId(String id) {
		try {
			Optional<Publisher> publisherOpt = publisherDAO.getAllPublishers().stream()
					.filter(e -> e.getUser().getId() == id).findFirst();
			if (!publisherOpt.isPresent()) {
				throw new MusicHubGenericException("User with that id does not exist!", HttpStatus.NOT_FOUND);
			}
			return publisherOpt.get();
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get Publisher", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public PublisherBeanEntity createPublisher(CreatePublisherRequestModel model) {
		try {
			User user = userService.createUser(model);
			userService.addRole(user, "1");

			if(!validatePublisherModel(model)){
				throw new MusicHubGenericException("Author name is invalid!", HttpStatus.BAD_REQUEST);
			}
			Author author = new Author();
			author.setName(model.getAuthorName());
			authorService.addAuthor(author);

			Publisher publisher = new Publisher();
			publisher.setUser(user);
			publisher.setAuthor(author);
			return publisherToPublisherEntity(publisherDAO.addPublisher(publisher));
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to create Publisher", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private boolean validatePublisherModel(CreatePublisherRequestModel model){
		return model.getAuthorName()==null||model.getAuthorName().trim().isEmpty()?false:true;
	}

	private PublisherBeanEntity publisherToPublisherEntity(Publisher publisher) {
		PublisherBeanEntity entity = new PublisherBeanEntity();
		entity.setId(publisher.getId());
		entity.setAuthorId(publisher.getAuthor().getId());
		entity.setUserId(publisher.getUser().getId());
		return entity;
	}

	private Publisher publisherEntityToPublisher(PublisherBeanEntity entity) {
		Publisher publisher = new Publisher();
		publisher.setId(entity.getId());
		publisher.setAuthor(authorDAO.getAuthor(entity.getAuthorId()));
		publisher.setUser(userDAO.getUser(entity.getUserId()));
		return publisher;
	}
}
