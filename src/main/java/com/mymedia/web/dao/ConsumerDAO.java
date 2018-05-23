package com.mymedia.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.apache.commons.collections4.CollectionUtils;
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
	public Consumer getConsumerByField(String fieldName, String fieldValue) {
		EntityManager entityManager = sessionFactory.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Consumer> criteriaQuery = criteriaBuilder.createQuery(Consumer.class);
		Root<Consumer> root = criteriaQuery.from(Consumer.class);
		criteriaQuery.select(root);

		ParameterExpression<String> params = criteriaBuilder.parameter(String.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get(fieldName), params));

		TypedQuery<Consumer> query = entityManager.createQuery(criteriaQuery);
		query.setParameter(params, fieldValue);

		List<Consumer> queryResult = query.getResultList();

		Consumer returnObject = null;

		if (CollectionUtils.isNotEmpty(queryResult)) {
			returnObject = queryResult.get(0);
		}

		return returnObject;
	}

	@Transactional
	public List<Consumer> getAllConsumers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Consumer> consumers = session.createQuery("from Consumer").list();
		return consumers;
	}

	@Transactional
	public Consumer getConsumer(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Consumer.class, new String(id));
	}

	@Transactional
	public Consumer addConsumer(Consumer consumer) {
		Session session = this.sessionFactory.getCurrentSession();
		String id = (String) session.save(consumer);
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
	public void deleteConsumer(String id) {
		deleteConsumer(getConsumer(id));
	}
}
