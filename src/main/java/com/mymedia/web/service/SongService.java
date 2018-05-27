package com.mymedia.web.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.AlbumDAO;
import com.mymedia.web.dao.AuthorDAO;
import com.mymedia.web.dao.GenreDAO;
import com.mymedia.web.dao.PlaylistDAO;
import com.mymedia.web.dao.SongDAO;
import com.mymedia.web.dto.SongBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.mvc.model.Album;
import com.mymedia.web.mvc.model.Author;
import com.mymedia.web.mvc.model.Playlist;
import com.mymedia.web.mvc.model.Song;

@Service
@EnableTransactionManagement
public class SongService {
	private static final Logger LOG = LogManager.getLogger(SongService.class);

	@Autowired
	SongDAO songDAO;

	@Autowired
	AuthorDAO authorDAO;

	@Autowired
	AlbumDAO albumDAO;

	@Autowired
	PlaylistDAO playlistDAO;

	@Autowired
	GenreDAO genreDAO;

	@Transactional
	public List<SongBeanEntity> getAllSongs() {
		try {
			List<Song> songs = songDAO.getAllSongs();
			if (songs.isEmpty()) {
				throw new MusicHubGenericException("No Songs Found", HttpStatus.NO_CONTENT);
			}
			List<SongBeanEntity> list = new ArrayList<>();
			songs.stream().forEach(e -> list.add(songToSongEntity(e)));
			return list;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get Song Collection", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public List<SongBeanEntity> getTop10() {
		try {
			List<Song> songs = songDAO.getAllSongs();
			if (songs.isEmpty()) {
				throw new MusicHubGenericException("No Songs Found", HttpStatus.NO_CONTENT);
			}
			List<SongBeanEntity> list = new ArrayList<>();
			Collections.sort(songs);
			int max = songs.size() < 10 ? songs.size() : 10;
			songs.subList(0, max).stream().forEach(e -> list.add(songToSongEntity(e)));
			return list;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get Song Collection", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public SongBeanEntity getSong(String id) {
		try {
			Song song = songDAO.getSong(id);
			if (song == null) {
				throw new MusicHubGenericException("Song with that id does not exist!", HttpStatus.NOT_FOUND);
			}
			return songToSongEntity(song);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get Song", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public List<SongBeanEntity> getBestSongsByAuthorId(String id) {
		try {
			Author author = authorDAO.getAuthor(id);
			List<Song> songs = author.getSongs();
			List<SongBeanEntity> list = new ArrayList<>();
			Collections.sort(songs);
			int max = songs.size() < 5 ? songs.size() : 5;
			songs.subList(0, max).stream().forEach(e -> list.add(songToSongEntity(e)));
			return list;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get Songs", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Transactional
	public List<SongBeanEntity> getSongsByAuthorId(String id) {
		try {
			Author author = authorDAO.getAuthor(id);
			if (author == null) {
				throw new MusicHubGenericException("Author with that id does not exist!", HttpStatus.NOT_FOUND);
			}
			List<SongBeanEntity> list = new ArrayList<>();
			author.getSongs().stream().forEach(e -> list.add(songToSongEntity(e)));
			return list;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get Songs", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public List<SongBeanEntity> getSongsByAlbumId(String id) {
		try {
			Album album = albumDAO.getAlbum(id);
			if (album == null) {
				throw new MusicHubGenericException("Album with that id does not exist!", HttpStatus.NOT_FOUND);
			}
			List<SongBeanEntity> list = new ArrayList<>();
			album.getSongs().stream().forEach(e -> list.add(songToSongEntity(e)));
			return list;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get Songs", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public List<SongBeanEntity> getSongsByGenreId(String id) {
		try {
			List<SongBeanEntity> list = new ArrayList<>();
			getAllSongs().stream().filter(e -> e.getGenreId() == id).forEach(e -> list.add(e));
			return list;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get Songs", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public List<SongBeanEntity> getSongsByPlaylistId(String id) {
		try {
			Playlist playlist = playlistDAO.getPlaylist(id);
			if (playlist == null) {
				throw new MusicHubGenericException("Playlist with that id does not exist!", HttpStatus.NOT_FOUND);
			}
			List<SongBeanEntity> list = new ArrayList<>();
			playlist.getSongs().stream().forEach(e -> list.add(songToSongEntity(e)));
			return list;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get Songs", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public SongBeanEntity addSong(SongBeanEntity entity) {
		try {
			if (entity == null) {
				throw new MusicHubGenericException("Not a valid request!", HttpStatus.BAD_REQUEST);
			}
			Song song = songDAO.addSong(songEntityToSong(entity));
			return songToSongEntity(song);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to add Song", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public Song addSong(Song song) {
		try {
			if (song == null) {
				throw new MusicHubGenericException("Not a valid request!", HttpStatus.BAD_REQUEST);
			}
			return songDAO.addSong(song);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to add Song", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public SongBeanEntity updateSong(SongBeanEntity entity) {
		try {
			Song song = songDAO.updateSong(songEntityToSong(entity));
			return songToSongEntity(song);
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to update Song", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public void deleteSong(String id) {
		try {
			songDAO.deleteSongById(id);
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to delete Song", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public Song songEntityToSong(SongBeanEntity entity) {
		Song song = new Song();
		song.setId(entity.getId());
		song.setName(entity.getName());
		song.setRating(entity.getRating());
		song.setDuration(entity.getDuration());
		song.setUrl(entity.getUrl());
		song.setAuthor(authorDAO.getAuthor(entity.getAuthorId()));
		song.setAlbum(albumDAO.getAlbum(entity.getAlbumId()));
		song.setGenre(genreDAO.getGenre(entity.getGenreId()));
		return song;
	}

	public SongBeanEntity songToSongEntity(Song song) {
		SongBeanEntity entity = new SongBeanEntity();
		entity.setId(song.getId());
		entity.setName(song.getName());
		entity.setRating(song.getRating());
		entity.setDuration(song.getDuration());
		entity.setUrl(song.getUrl());
		entity.setAuthorId(song.getAuthor().getId());
		entity.setAuthorName(song.getAuthor().getName());
		entity.setAlbumId(song.getAlbum().getId());
		entity.setGenreId(song.getGenre().getId());
		entity.setAlbumName(song.getAlbum().getName());
		entity.setImgPath(song.getAlbum().getImgPath());
		return entity;
	}
}
