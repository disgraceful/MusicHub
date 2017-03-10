package com.mymedia.web.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.AuthUserDAO;
import com.mymedia.web.mvc.controller.AuthUserController;
import com.mymedia.web.mvc.model.AuthUser;

@Service
@EnableTransactionManagement
public class AuthUserService {
	
	private static final Logger LOG = LogManager.getLogger(AuthUserController.class);
	
	@Autowired
	AuthUserDAO authUserDAO;

	@Transactional
	public List<AuthUser> getAllAuthUsers() {
		return authUserDAO.getAllAuthUsers();
	}

	@Transactional
	public AuthUser getAuthUser(int id) {
		LOG.info("service was called");
		return authUserDAO.getAuthUser(id);
	}


}
