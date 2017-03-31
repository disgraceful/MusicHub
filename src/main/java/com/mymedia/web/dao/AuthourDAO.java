package com.mymedia.web.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.mvc.model.Authour;

@Repository
@EnableTransactionManagement
public class AuthourDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger LOG = LogManager.getLogger(AuthourDAO.class);

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	public List<Authour> getAllAuthours() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Authour> authourList = session.createQuery("from Authour").list();
		return authourList;
	}

	@Transactional
	public Authour getAuthour(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Authour.class, new Integer(id));
	}

	@Transactional
	public Authour addAuhtour(Authour authour) {
		Session session = this.sessionFactory.getCurrentSession();
		LOG.info(authour.getName() + " " + authour.getAlbums());
		session.save(authour);
		return authour;

	}

	@Transactional
	public Authour updateAuthour(Authour authour) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(authour);
		return authour;
	}

	@Transactional
	public void deleteAuthour(Authour authour) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(authour);
	}
	
	@Transactional
	public void deleteAuthourById(int id){
		deleteAuthour(getAuthour(id));
	}
	
}
