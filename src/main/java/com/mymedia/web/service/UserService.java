package com.mymedia.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.RoleDAO;
import com.mymedia.web.dao.UserDAO;
import com.mymedia.web.dto.UserBeanEntity;
import com.mymedia.web.mvc.model.Role;
import com.mymedia.web.mvc.model.User;
import com.mymedia.web.requestmodel.CreateUserRequestModel;

/**
 * Created by Nazar on 11.04.2017.
 */
@Service
@EnableTransactionManagement
public class UserService {
	@Autowired
	UserDAO userDAO;
	@Autowired
	RoleDAO roleDAO;
	private static final Logger LOG = LogManager.getLogger(UserService.class);

	@Transactional
	public User createUser(CreateUserRequestModel model) {
		if (model.getPassword().trim().equals(model.getConfirmPassword().trim())) {
			User user = new User();
			user.setPassword(model.getPassword());
			user.setUsername(model.getUsername());
			return userDAO.addUser(user);
		}
		LOG.info("FAILED TO CREATE USER");
		return null;
	}

	@Transactional
	public User addRole(User user, int id) {
		//Role role = roleDAO.getAllRoles().stream().filter(e -> e.getName().trim().equals(roleName.trim())).findAny().orElse(null);//.get();
		Role role = roleDAO.getRole(id);
		user.setRole(role);
		
		return userDAO.updateUser(user);
	}

	@Transactional
	public List<UserBeanEntity> getAllUsers() {
		List<User> users = userDAO.getAllUsers();
		List<UserBeanEntity> userEntities = new ArrayList<>();
		UserBeanEntity entity = new UserBeanEntity();
		for (User user : users) {
			userEntities.add(userToUserEntity(user));
		}
		return userEntities;
	}

	@Transactional
	public UserBeanEntity getUser(int id) {
		User user = userDAO.getUser(id);
		return userToUserEntity(user);
	}

	@Transactional
	public User getByUsername(String username) {
		List<User> users = userDAO.getAllUsers();
		for (User user : users) {
			if (user.getUsername().trim().equals(username.trim())) {
				return user;
			}
		}
		return new User();
	}

	@Transactional
	public UserBeanEntity addUser(UserBeanEntity entity) {
		User user = userDAO.addUser(userEntityToUser(entity));
		return userToUserEntity(user);
	}

	@Transactional
	public User addUser(User user) {
		return userDAO.addUser(user);
	}

	@Transactional
	public UserBeanEntity updateUser(UserBeanEntity entity) {
		User user = userDAO.updateUser(userEntityToUser(entity));
		return userToUserEntity(user);
	}

	@Transactional
	public void deleteUser(int id) {
		userDAO.deleteUser(id);
	}

	public User userEntityToUser(UserBeanEntity entity) {
		User user = new User();
		user.setId(entity.getId());
		user.setUsername(entity.getUsername());
		user.setPassword(entity.getPassword());
		user.setRole(roleDAO.getRole(entity.getRoleId()));
		return user;
	}

	public UserBeanEntity userToUserEntity(User song) {
		UserBeanEntity entity = new UserBeanEntity();
		entity.setId(song.getId());
		entity.setUsername(song.getUsername());
		entity.setPassword(song.getPassword());
		entity.setRoleId(song.getRole().getId());
		return entity;
	}
}
