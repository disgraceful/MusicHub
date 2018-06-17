package com.mymedia.web.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.ConsumerDAO;
import com.mymedia.web.dao.PlaylistDAO;
import com.mymedia.web.dao.UserDAO;
import com.mymedia.web.dto.ConsumerBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.mvc.model.Consumer;
import com.mymedia.web.mvc.model.Playlist;
import com.mymedia.web.mvc.model.User;
import com.mymedia.web.requestmodel.CreateConsumerRequestModel;
import com.mymedia.web.requestmodel.GoogleLoginReqModel;

@Service
public class ConsumerService {
	@Autowired
	private UserService userService;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ConsumerDAO consumerDAO;
	@Autowired
	private PlaylistDAO playlistDAO;

	private static final Logger LOG = LogManager.getLogger(ConsumerService.class);

	@Transactional
	public ConsumerBeanEntity createConsumer(GoogleLoginReqModel model) {
		try {
			User user = userService.createUser(model);
			userService.addRole(user, "2");
			Consumer consumer = new Consumer();
			consumer.setImgPath(model.getAvatarPath());
			consumer.setUser(user);
			consumer = consumerDAO.addConsumer(consumer);
			addPlaylistsToNewConsumer(consumer);			
			return consumerToConsumerEntity(consumer);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to register User", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ConsumerBeanEntity createConsumerAsBeanEntity(CreateConsumerRequestModel model) {
		try {
			return consumerToConsumerEntity(createConsumer(model));
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to register User", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public Consumer createConsumer(CreateConsumerRequestModel model) {
		try {
			User user = userService.createUser(model);
			userService.addRole(user, "2");
			Consumer consumer = new Consumer();
			consumer.setUser(user);
			consumer = consumerDAO.addConsumer(consumer);
			addPlaylistsToNewConsumer(consumer);			
			return (consumer);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to register User", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public void addPlaylistsToNewConsumer(Consumer consumer) {
		Playlist favorites = new Playlist();
		favorites.setName("Favorites");
		favorites.setConsumer(consumer);
		favorites.setRating(0);
		playlistDAO.addPlaylist(favorites);
		
		Playlist history = new Playlist();
		favorites.setName("History");
		favorites.setConsumer(consumer);
		favorites.setRating(0);
		playlistDAO.addPlaylist(history);
	}
	
	@Transactional
	public Consumer createMusicHubUser() {
		User musicHubUser = userService.getByUsername("musichub");
		if (musicHubUser != null) {
			return getConsumerByUserId(musicHubUser.getId());
		}
		CreateConsumerRequestModel model = new CreateConsumerRequestModel();
		model.setEmail("musichub@gmail.com");
		model.setUsername("musichub");
		model.setPassword("111");
		model.setConfirmPassword("111");
		return createConsumer(model);
	}

	@Transactional
	public Consumer getConsumerByUserId(String id) {
		try {
			Consumer consumer = consumerDAO.getAllConsumers().stream().filter(e -> e.getUser().getId().equals(id))
					.findAny().get();
			return consumer;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get User", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ConsumerBeanEntity getConsumerEnitityByUserId(String id) {
		try {
			return consumerToConsumerEntity(getConsumerByUserId(id));
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get User", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ConsumerBeanEntity getConsumerById(String id) {
		try {
			Consumer consumer = consumerDAO.getConsumer(id);
			if (consumer == null) {
				throw new MusicHubGenericException("User with that id does not exist!", HttpStatus.NOT_FOUND);
			}
			return consumerToConsumerEntity(consumer);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get User", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ConsumerBeanEntity updateConsumer(Consumer consumer) {
		try {
			return consumerToConsumerEntity(consumerDAO.updateConsumer(consumer));		
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get User", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public static ConsumerBeanEntity consumerToConsumerEntity(Consumer consumer) {
		ConsumerBeanEntity entity = new ConsumerBeanEntity();
		entity.setId(consumer.getId());
		entity.setUserId(consumer.getUser().getId());
		entity.setImgPath(consumer.getImgPath());
		return entity;
	}

	public Consumer consumerEntityToConsumer(ConsumerBeanEntity entity) {
		Consumer consumer = new Consumer();
		consumer.setId(entity.getId());
		consumer.setUser(userDAO.getUser(entity.getUserId()));
		consumer.setImgPath(entity.getImgPath());
		return consumer;
	}
}
