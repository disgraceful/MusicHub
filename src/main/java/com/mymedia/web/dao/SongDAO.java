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

import com.mymedia.web.mvc.model.Song;

@Repository
@EnableTransactionManagement
public class SongDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger LOG = LogManager.getLogger(SongDAO.class);
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	public List<Song> getAllSongs() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Song> songList = session.createQuery("from Song").list();
		return songList;
	}

	@Transactional
	public Song getSong(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Song.class, new Integer(id));
	}

	@Transactional
	public Song addSong(Song song) {
		Session session = this.sessionFactory.getCurrentSession();
		LOG.info("name: "+ song.getName() + " ");
		int id = (Integer)session.save(song);
		return getSong(id);
	}
	
	@Transactional
	public Song updateSong(Song song) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(song);
		return getSong(song.getId());
	}

	@Transactional
	public void deleteSong(Song song) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(song);
	}
	
	@Transactional
	public void deleteSongById(int id){
		deleteSong(getSong(id));
	}
	
}
