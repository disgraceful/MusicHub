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

import com.mymedia.web.mvc.model.Album;

@Repository
@EnableTransactionManagement
public class AlbumDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger LOG = LogManager.getLogger(AlbumDAO.class);
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	public List<Album> getAllAlbums() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Album> albumList = session.createQuery("from Album").list();
		return albumList;
	}

	@Transactional
	public Album getAlbum(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Album.class, new Integer(id));
	}

	@Transactional
	public Album addAlbum(Album album) {
		Session session = this.sessionFactory.getCurrentSession();
		LOG.info(album.getName() + " " + album.getAuthor());
		int i = (Integer)session.save(album);
		LOG.info(session.save(album));
		return getAlbum(i);
//		return album;
	}
	
	@Transactional
	public Album updateAlbum(Album album) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(album);
		return album;
	}

	@Transactional
	public void deleteAlbum(Album album) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(album);
	}
	
	@Transactional
	public void deleteAlbumById(int id){
		deleteAlbum(getAlbum(id));
	}
	
}
