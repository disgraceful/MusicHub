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

import com.mymedia.web.mvc.model.Publisher;
import com.mymedia.web.mvc.model.User;

@Repository
@EnableTransactionManagement
public class PublisherDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger LOG = LogManager.getLogger(PublisherDAO.class);

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	public List<Publisher> getAllPublishers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Publisher> publishers= session.createQuery("from Publisher").list();
		return publishers;
	}

	@Transactional
	public Publisher getPublisher(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Publisher.class, new String(id));
	}

	@Transactional
	public Publisher addPublisher(Publisher publisher) {
		Session session = this.sessionFactory.getCurrentSession();
		String id = (String) session.save(publisher);
		return getPublisher(id);
	}

	@Transactional
	public Publisher updatePublisher(Publisher publisher) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(publisher);
		return getPublisher(publisher.getId());
	}

	@Transactional
	public void deletePublisher(Publisher publisher) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(publisher);
	}

	@Transactional
	public void deletePublisher(String id) {
		deletePublisher(getPublisher(id));
	}
}
