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

import com.mymedia.web.mvc.model.Playlist;
import com.mymedia.web.mvc.model.Song;

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
		String id = (String)session.save(playlist);
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
	public void deletePlaylist(String id){
		deletePlaylist(getPlaylist(id));
	}
	
}
