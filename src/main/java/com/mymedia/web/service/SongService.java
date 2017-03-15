package com.mymedia.web.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.SongDAO;
import com.mymedia.web.mvc.model.Song;

@Service
@EnableTransactionManagement
public class SongService {
	private static final Logger LOG = LogManager.getLogger(SongService.class);

	@Autowired
	SongDAO songDAO;

	@Transactional
	public List<Song> getAllSongs() {
		return songDAO.getAllSongs();
	}

	@Transactional
	public Song getSong(int id) {
		return songDAO.getSong(id);
	}

	@Transactional
	public Song addSong(Song song) {
		return songDAO.addSong(song);
	}
}
