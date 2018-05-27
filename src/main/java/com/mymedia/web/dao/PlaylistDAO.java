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

import com.mymedia.web.mvc.model.Playlist;

@Repository
@EnableTransactionManagement
public class PlaylistDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger LOG = LogManager.getLogger(PlaylistDAO.class);

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	public Playlist getUniquePlaylistByField(String fieldName, String fieldValue) {
		List<Playlist> queryResult = getPlaylistsByField(fieldName, fieldValue);
		Playlist returnObject = null;
		if (CollectionUtils.isNotEmpty(queryResult)) {
			returnObject = queryResult.get(0);
		}
		return returnObject;
	}

	@Transactional
	public List<Playlist> getPlaylistsByField(String fieldName, String fieldValue) {
		EntityManager entityManager = sessionFactory.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Playlist> criteriaQuery = criteriaBuilder.createQuery(Playlist.class);
		Root<Playlist> root = criteriaQuery.from(Playlist.class);
		criteriaQuery.select(root);

		ParameterExpression<String> params = criteriaBuilder.parameter(String.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get(fieldName), params));

		TypedQuery<Playlist> query = entityManager.createQuery(criteriaQuery);
		query.setParameter(params, fieldValue);

		List<Playlist> queryResult = query.getResultList();
		return queryResult;
	}

	@Transactional
	public List<Playlist> getAllPlaylists() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Playlist> playlists = session.createQuery("from Playlist").list();
		return playlists;
	}

	@Transactional
	public Playlist getPlaylist(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Playlist.class, new String(id));
	}

	@Transactional
	public Playlist addPlaylist(Playlist playlist) {
		Session session = this.sessionFactory.getCurrentSession();
		LOG.info(playlist.getName() + " ");
		String id = (String) session.save(playlist);
		return getPlaylist(id);
	}

	@Transactional
	public Playlist updatePlaylist(Playlist playlist) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(playlist);
		return getPlaylist(playlist.getId());
	}

	@Transactional
	public void deletePlaylist(Playlist playlist) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(playlist);
	}

	@Transactional
	public void deletePlaylist(String id) {
		deletePlaylist(getPlaylist(id));
	}

}
