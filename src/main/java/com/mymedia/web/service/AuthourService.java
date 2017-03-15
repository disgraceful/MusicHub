package com.mymedia.web.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.AuthourDAO;
import com.mymedia.web.mvc.controller.AuthUserController;
import com.mymedia.web.mvc.model.Authour;

@Service
@EnableTransactionManagement
public class AuthourService {
	private static final Logger LOG = LogManager.getLogger(AuthUserController.class);

	@Autowired
	AuthourDAO authourDAO;

	@Transactional
	public List<Authour> getAllAuthours() {
		return authourDAO.getAllAuthours();
	}

	@Transactional
	public Authour getAuthour(int id) {
		return authourDAO.getAuthour(id);
	}

	@Transactional
	public Authour addAuthour(Authour authour) {
		return authourDAO.addAuhtour(authour);
	}
}
