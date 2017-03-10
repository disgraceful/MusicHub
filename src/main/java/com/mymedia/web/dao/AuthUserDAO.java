package com.mymedia.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.mvc.model.AuthUser;

@Repository
@EnableTransactionManagement
public class AuthUserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Transactional
	public List<AuthUser> getAllAuthUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<AuthUser> authUserList = session.createQuery("from AuthUser").list();
		return authUserList;
	}

	@Transactional
	public AuthUser getAuthUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(AuthUser.class,new Integer(id));
	}
}
