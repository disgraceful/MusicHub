package com.mymedia.web.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.ConsumerDAO;
import com.mymedia.web.mvc.controller.AccountController;
import com.mymedia.web.mvc.model.Consumer;
import com.mymedia.web.mvc.model.User;
import com.mymedia.web.requestmodel.CreateConsumerRequestModel;

@Service
public class ConsumerService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ConsumerDAO consumerDAO;
	private static final Logger LOG = LogManager.getLogger(ConsumerService.class);
	@Transactional
	public Consumer createConsumer(CreateConsumerRequestModel model){
		User user = userService.createUser(model);
		LOG.info(user.getId()+ " "+ user.getUsername() + " "+ user.getPassword());
		userService.addRole(user, 2);
		
		Consumer consumer = new Consumer();
		consumer.setUser(user);
		return consumerDAO.addConsumer(consumer);
	}
}
