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

import com.mymedia.web.mvc.model.Author;
import com.mymedia.web.mvc.model.Consumer;
import com.mymedia.web.mvc.model.Genre;

@Repository
@EnableTransactionManagement
public class AuthorDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger LOG = LogManager.getLogger(AuthorDAO.class);

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	public Author getUniqueAuthorByField(String fieldName, String fieldValue) {
		List<Author> queryResult = getAuthorsByField(fieldName, fieldValue);
		Author returnObject = null;
		if (CollectionUtils.isNotEmpty(queryResult)) {
			returnObject = queryResult.get(0);
		}

		return returnObject;
	}
	
	@Transactional
	public List<Author> getAuthorsByField(String fieldName, String fieldValue) {
		EntityManager entityManager = sessionFactory.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
		Root<Author> root = criteriaQuery.from(Author.class);
		criteriaQuery.select(root);

		ParameterExpression<String> params = criteriaBuilder.parameter(String.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get(fieldName), params));

		TypedQuery<Author> query = entityManager.createQuery(criteriaQuery);
		query.setParameter(params, fieldValue);

		List<Author> queryResult = query.getResultList();

		return queryResult;
	}
	@Transactional
	public List<Author> getAllAuthors() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Author> authorList = session.createQuery("from Author").list();
		return authorList;
	}

	@Transactional
	public Author getAuthor(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Author.class, new String(id));
	}

	@Transactional
	public Author addAuthor(Author author) {
		Session session = this.sessionFactory.getCurrentSession();
		String id = (String)session.save(author);
		return getAuthor(id);

	}

	@Transactional
	public Author updateAuthor(Author author) {
		Session session = this.sessionFactory.getCurrentSession();
		Author a = (Author)session.merge(author);
		return getAuthor(a.getId());
	}

	@Transactional
	public void deleteAuthor(Author author) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(author);
	}
	
	@Transactional
	public void deleteAuthor(String id){
		deleteAuthor(getAuthor(id));
	}
	
}
