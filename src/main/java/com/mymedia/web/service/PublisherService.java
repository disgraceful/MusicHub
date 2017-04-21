package com.mymedia.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.PublisherDAO;
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

	@Transactional
	public Publisher getPublisher(int id) {
		return publisherDAO.getPublisher(id);
	}

	@Transactional
	public Publisher createPublisher(CreatePublisherRequestModel model) {
		User user = userService.createUser(model);
		userService.addRole(user, 1);

		Author author = new Author();
		author.setName(model.getAuthorName());
		authorService.addAuthor(author);

		Publisher publisher = new Publisher();
		publisher.setUser(user);
		publisher.setAuthor(author);
		return publisherDAO.addPublisher(publisher);
	}
}
