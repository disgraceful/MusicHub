package com.mymedia.web.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dto.AuthorBeanEntity;
import com.mymedia.web.dto.SongBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.mvc.model.Author;
import com.mymedia.web.mvc.model.Genre;
import com.mymedia.web.mvc.model.Playlist;
import com.mymedia.web.mvc.model.Song;

@Service
public class RecommendationService {

	@Autowired
	private PlaylistService playlistService;
	@Autowired
	private AuthorService authorService;

	@Autowired
	private SongService songService;

	private static final Logger LOG = LogManager.getLogger(RecommendationService.class);

	@Transactional
	private String getDominatedGenreId() {
		try {
			Playlist playlist = playlistService.getHistoryPlaylist();
			List<Song> historySongs = playlist.getSongs();
			List<Song> songs = historySongs.subList(Math.max(historySongs.size() - 10, 0), historySongs.size());
			List<Genre> genres = songs.stream().map(f -> f.getGenre()).collect(Collectors.toList());
			HashMap<String, Integer> hm = new HashMap<>();
			for (Genre genre : genres) {
				int value = 0;
				String key = genre.getId();
				if (hm.containsKey(key)) {
					value = hm.get(key);
					hm.put(key, value + 1);
				} else {
					hm.put(key, 1);
				}
			}
			String mostPopularGenre = Collections.max(hm.entrySet(), Comparator.comparingInt(Map.Entry::getValue))
					.getKey();
			return mostPopularGenre;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	private Author getRandomAuthorFromConsumerHistory() {
		try {
			List<Song> songs = playlistService.getHistoryPlaylist().getSongs();
			if (songs.isEmpty()) {
				return null;
			}
			Collections.shuffle(songs);
			return songs.get(0).getAuthor();
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private Author getRandomAuthorFromConsumerPlaylist() {
		List<Song> songs = playlistService.getSongsFromPlaylists();
		if (songs.isEmpty()) {

		}
		Collections.shuffle(songs);
		return songs.get(0).getAuthor();
	}

	private Author getRecommededAuhtor() {
		Author author = getRandomAuthorFromConsumerHistory();
		if(author!=null) {
			return author;
		}
		author = getRandomAuthorFromConsumerPlaylist();
		if(author!=null) {
			return author;
		}
		return authorService.getRandomAuthor(); 
	}
	

	@Transactional
	public List<SongBeanEntity> getRecommendedSongsByGenre() {
		try {
			String genreId = getDominatedGenreId();
			List<Song> genreSongs = songService.getLimitedSongsByGenreId(genreId, 100);
			Collections.shuffle(genreSongs);
			List<Song> history = playlistService.getHistoryPlaylist().getSongs();
			int max = history.size() > 10 ? 10 : history.size();
			history = history.subList(0, max);
			genreSongs.removeAll(history);
			return genreSongs.subList(0, max).stream().map(f -> songService.songToSongEntity(f))
					.collect(Collectors.toList());
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public List<SongBeanEntity> getRecommendedSongsByAuthor() {
		try {
			Author author = getRecommededAuhtor();
			return songService.getBestSongsEntityByAuthorId(author.getId(), 5);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	public List<AuthorBeanEntity> getRecommendedAuthorsByAuthor() {
		try {
			Author author = getRecommededAuhtor();
			return authorService.getSimilarAuthors(author.getId());
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
