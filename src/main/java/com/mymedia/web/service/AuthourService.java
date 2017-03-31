package com.mymedia.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.AuthourDAO;
import com.mymedia.web.dto.AuthourBeanEntity;
import com.mymedia.web.dto.SongBeanEntity;
import com.mymedia.web.mvc.controller.AuthUserController;
import com.mymedia.web.mvc.model.Album;
import com.mymedia.web.mvc.model.Authour;
import com.mymedia.web.mvc.model.Song;

@Service
@EnableTransactionManagement
public class AuthourService {
	private static final Logger LOG = LogManager.getLogger(AuthUserController.class);

	@Autowired
	AuthourDAO authourDAO;
	
	@Autowired
	SongService songService;

	@Transactional
	public List<AuthourBeanEntity> getAllAuthours() {
		List<Authour> list = authourDAO.getAllAuthours();
		List<AuthourBeanEntity> entityList = new ArrayList<>();
		for (Authour authour : list) {
			entityList.add(authourToAuthourBeanEntity(authour));
		}
		return entityList;
	}

	@Transactional
	public AuthourBeanEntity getAuthour(int id) {
		return authourToAuthourBeanEntity(authourDAO.getAuthour(id));
	}

	@Transactional
	public AuthourBeanEntity addAuthour(AuthourBeanEntity entity) {
		authourDAO.addAuhtour(authourBeanEntityToAuthour(entity));
		return entity;
	}
	
	@Transactional
	public List<SongBeanEntity>getSongs(int id){
		return songService.getSongsByAuthourId(id);
	}

	private Authour authourBeanEntityToAuthour(AuthourBeanEntity entity) {
		Authour a = new Authour();
		a.setId(entity.getId());
		a.setName(entity.getName());
		a.setSurName(entity.getSurName());
		a.setRating(entity.getRating());
		a.setBirthDate(entity.getBirthDate());
		return a;
	}

	private AuthourBeanEntity authourToAuthourBeanEntity(Authour authour) {
		AuthourBeanEntity entity = new AuthourBeanEntity();
		entity.setId(authour.getId());
		entity.setName(authour.getName());
		entity.setSurName(authour.getSurName());
		entity.setRating(authour.getRating());
		entity.setBirthDate(authour.getBirthDate());
		return entity;
	}
}
