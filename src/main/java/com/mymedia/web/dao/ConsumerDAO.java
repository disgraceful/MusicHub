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

import com.mymedia.web.mvc.model.Consumer;

@Repository
@EnableTransactionManagement
public class ConsumerDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger LOG = LogManager.getLogger(ConsumerDAO.class);

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	public List<Consumer> getAllConsumers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Consumer> consumers= session.createQuery("from Consumer").list();
		return consumers;
	}

	@Transactional
	public Consumer getConsumer(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Consumer.class, new Integer(id));
	}

	@Transactional
	public Consumer addConsumer(Consumer consumer) {
		Session session = this.sessionFactory.getCurrentSession();
		int id = (Integer) session.save(consumer);
		return getConsumer(id);
	}

	@Transactional
	public Consumer updateConsumer(Consumer consumer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(consumer);
		return getConsumer(consumer.getId());
	}

	@Transactional
	public void deleteConsumer(Consumer consumer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(consumer);
	}

	@Transactional
	public void deleteConsumer(int id) {
		deleteConsumer(getConsumer(id));
	}
}
