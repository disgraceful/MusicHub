package com.mymedia.web.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.ConsumerDAO;
import com.mymedia.web.dao.UserDAO;
import com.mymedia.web.dto.ConsumerBeanEntity;
import com.mymedia.web.mvc.model.Consumer;
import com.mymedia.web.mvc.model.User;
import com.mymedia.web.requestmodel.CreateConsumerRequestModel;

@Service
public class ConsumerService {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ConsumerDAO consumerDAO;
	
	private static final Logger LOG = LogManager.getLogger(ConsumerService.class);
	@Transactional
	public Consumer createConsumer(CreateConsumerRequestModel model){
		User user = userService.createUser(model);
		userService.addRole(user, 2);
		
		Consumer consumer = new Consumer();
		consumer.setUser(user);
		return consumerDAO.addConsumer(consumer);
	}
	
	@Transactional
	public Consumer getConsumerByUserId(int id){
		return consumerDAO.getAllConsumers().stream().filter(e->e.getUser().getId()==id).findFirst().get();
	}
	
	
	public static ConsumerBeanEntity consumerToConsumerEntity(Consumer consumer){
		ConsumerBeanEntity entity = new ConsumerBeanEntity();
		entity.setId(consumer.getId());
		entity.setUserId(consumer.getUser().getId());
		return entity;
	}
	
	public Consumer consumerEntityToConsumer(ConsumerBeanEntity entity){
		Consumer consumer = new Consumer();
		consumer.setId(entity.getId());
		consumer.setUser(userDAO.getUser(entity.getUserId()));
		return consumer;
	}
}
