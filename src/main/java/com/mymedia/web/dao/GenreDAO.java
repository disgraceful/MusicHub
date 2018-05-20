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

import com.mymedia.web.mvc.model.Genre;

@Repository
@EnableTransactionManagement
public class GenreDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger LOG = LogManager.getLogger(GenreDAO.class);

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	public List<Genre> getAllGenres() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Genre> genreList = session.createQuery("from Genre").list();
		return genreList;
	}

	@Transactional
	public Genre getGenre(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Genre.class, new String(id));
	}

	@Transactional
	public Genre addGenre(Genre genre) {
		Session session = this.sessionFactory.getCurrentSession();
		String id = (String)session.save(genre);
		return getGenre(id);
	}

	@Transactional
	public Genre updateGenre(Genre genre) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(genre);
		return getGenre(genre.getId());
	}

	@Transactional
	public void deleteGenre(Genre Genre) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(Genre);
	}
	
	@Transactional
	public void deleteGenre(String id){
		deleteGenre(getGenre(id));
	}
	
}
