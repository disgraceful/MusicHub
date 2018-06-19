package com.mymedia.web.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.AuthorDAO;
import com.mymedia.web.dao.ConsumerDAO;
import com.mymedia.web.dao.GenreDAO;
import com.mymedia.web.dao.PlaylistDAO;
import com.mymedia.web.dao.SongDAO;
import com.mymedia.web.dao.UserDAO;
import com.mymedia.web.dto.PlaylistBeanEntity;
import com.mymedia.web.dto.SongBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.mvc.model.Author;
import com.mymedia.web.mvc.model.Consumer;
import com.mymedia.web.mvc.model.Genre;
import com.mymedia.web.mvc.model.Playlist;
import com.mymedia.web.mvc.model.Song;
import com.mymedia.web.mvc.model.User;
import com.mymedia.web.requestmodel.PlaylistRequestModel;

@Service
@EnableTransactionManagement
public class PlaylistService {
	private static final Logger LOG = LogManager.getLogger(PlaylistService.class);

	@Autowired
	private PlaylistDAO playlistDAO;

	@Autowired
	private ConsumerDAO consumerDAO;
	@Autowired
	private SongDAO songDAO;
	@Autowired
	private GenreDAO genreDAO;

	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private AuthorDAO authorDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private SongService songService;

	@Transactional
	public List<SongBeanEntity> getHistorySongs() {
		try {
			Playlist history = getHistoryPlaylist();
			return history.getSongs().stream().map(f -> songService.songToSongEntity(f)).collect(Collectors.toList());
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to create Playlist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public Playlist getHistoryPlaylist() {
		try {

			List<Playlist> playlists = getConsumerPlaylists();
			return playlists.stream().filter(e -> e.getName().equals("History")).findFirst().get();
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to create Playlist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public List<PlaylistBeanEntity> generatePlaylistForAuthor(String id) {
		try {
			List<PlaylistBeanEntity> list = new ArrayList<>();
			list.add(generateBestOfPlaylistForAuthor(id));
			list.add(generatePlaylistByGenre(id));

			return list;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to create Playlist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	private PlaylistBeanEntity generateBestOfPlaylistForAuthor(String id) {
		try {
			Consumer musicHubConsumer = consumerService.createMusicHubUser();
			Author author = authorDAO.getAuthor(id);
			String playlistName = "Best of " + author.getName();
			Playlist playlist = playlistDAO.getUniquePlaylistByField("name", playlistName);
			if (playlist != null) {
				return playlistToPlaylistEntity(playlist);
			}
			playlist = new Playlist();
			playlist.setName(playlistName);
			playlist.setConsumer(musicHubConsumer);
			playlist.setRating(30);
			playlist = playlistDAO.addPlaylist(playlist);
			List<Song> songList = songService.getBestSongsByAuthorId(id, 20);
			addSongList(songList, playlist);
			return playlistToPlaylistEntity(playlist);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to create Playlist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	private PlaylistBeanEntity generatePlaylistByGenre(String id) {
		try {
			Author author = authorDAO.getAuthor(id);
			Genre similarGenre = genreDAO.getGenre(author.getGenre().getId());
			Consumer musicHubConsumer = consumerService.createMusicHubUser();
			String playlistName = "Similar to " + author.getName();
			Playlist playlist = playlistDAO.getUniquePlaylistByField("name", playlistName);
			if (playlist != null) {
				return playlistToPlaylistEntity(playlist);
			}
			playlist = new Playlist();
			playlist.setName(playlistName);
			playlist.setConsumer(musicHubConsumer);
			playlist.setRating(20);
			playlist = playlistDAO.addPlaylist(playlist);
			List<Song> songList = songService.getLimitedSongsByGenreId(similarGenre.getId(), 20);
			addSongList(songList, playlist);
			return playlistToPlaylistEntity(playlist);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to create Playlist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public List<SongBeanEntity> getSongsFromPlaylistsAsEntities() {
		try {

			return getSongsFromPlaylists().stream().map(e -> songService.songToSongEntity(e))
					.collect(Collectors.toList());
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to create Playlist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public List<Song> getSongsFromPlaylists() {
		try {
			List<Playlist> playlists = getConsumerPlaylists();
			return playlists.stream().flatMap(f -> f.getSongs().stream()).collect(Collectors.toList());
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to create Playlist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public PlaylistBeanEntity createPlaylist(PlaylistRequestModel model) {
		try {
			if (!validatePlaylistModel(model)) {
				throw new MusicHubGenericException("Invalid playlist name!", HttpStatus.BAD_REQUEST);
			}
			User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Playlist playlist = new Playlist();
			playlist.setName(model.getName().trim());
			playlist.setSongs(new ArrayList<Song>());
			playlist.setConsumer(consumerService.getConsumerByUserId(u.getId()));
			return playlistToPlaylistEntity(playlistDAO.addPlaylist(playlist));
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to create Playlist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public PlaylistBeanEntity getPlaylistById(String id) {
		try {
			Playlist playlist = playlistDAO.getPlaylist(id);
			if (playlist == null) {
				throw new MusicHubGenericException("Playlist with that id does not exist!", HttpStatus.NOT_FOUND);
			}
			return playlistToPlaylistEntity(playlist);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get Playlist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public List<Playlist> getConsumerPlaylists() {
		try {
			User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (u == null) {
				throw new MusicHubGenericException("Failed to authorize", HttpStatus.UNAUTHORIZED);
			}
			Consumer consumer = consumerService.getConsumerByUserId(u.getId());
			List<Playlist> playlists = consumer.getPlaylsits();
			playlists.remove(0);
			return playlists;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get User Playlists", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public List<PlaylistBeanEntity> getConsumerPlaylistsAsEntities() {
		try {
			return getConsumerPlaylists().stream().map(f -> playlistToPlaylistEntity(f)).collect(Collectors.toList());
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get User Playlists", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public List<PlaylistBeanEntity> getAllPlaylists() {
		try {
			List<PlaylistBeanEntity> list = new ArrayList<>();
			playlistDAO.getAllPlaylists().stream().forEach(e -> list.add(playlistToPlaylistEntity(e)));
			return list;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get Playlist Collection", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public void addSongList(List<Song> songs, Playlist playlist) {
		try {
			playlist.setSongs(songs);
			songs.forEach(e -> e.getPlaylists().add(playlist));
			playlistDAO.updatePlaylist(playlist);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to add Song to Playlist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public void addSong(Song song, Playlist playlist) {
		try {
			playlist.getSongs().add(song);
			song.getPlaylists().add(playlist);
			playlistDAO.updatePlaylist(playlist);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to add Song to Playlist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public SongBeanEntity addSong(String songId, String playlistId) {
		try {
			Playlist playlist = playlistDAO.getPlaylist(playlistId);
			Song song = songDAO.getSong(songId);
			if (song == null || playlist == null) {
				throw new MusicHubGenericException("Requested object with that id does not exist",
						HttpStatus.NOT_FOUND);
			}
			playlist.getSongs().add(song);
			song.getPlaylists().add(playlist);
			playlistDAO.updatePlaylist(playlist);
			return songService.songToSongEntity(songDAO.updateSong(song));
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to add Song to Playlist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public void deleteSongFromPlaylist(String songId, String playlistId) {
		try {
			Playlist playlist = playlistDAO.getPlaylist(playlistId);
			Song song = songDAO.getSong(songId);
			playlist.getSongs().remove(song);
			if (song == null || playlist == null) {
				throw new MusicHubGenericException("Requested object with that id does not exist",
						HttpStatus.NOT_FOUND);
			}
			song.getPlaylists().remove(playlist);
			playlistDAO.updatePlaylist(playlist);
			songDAO.updateSong(song);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to delete Song from Playlist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public PlaylistBeanEntity updatePlaylist(PlaylistBeanEntity entity) {
		try {
			Playlist playlist = playlistDAO.updatePlaylist(playlistEntityToPlaylist(entity));
			if (playlist == null) {
				throw new MusicHubGenericException("Invalid request!", HttpStatus.BAD_REQUEST);
			}
			return playlistToPlaylistEntity(playlist);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to update Playlist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public void deletePlaylist(String id) {
		try {
			Playlist playlist = playlistDAO.getPlaylist(id);
			if (playlist == null) {
				throw new MusicHubGenericException("Playlist with that id does not exist", HttpStatus.NOT_FOUND);
			}
			playlist.getSongs().forEach(e -> e.getPlaylists().remove(playlist));
			playlist.getSongs().clear();
			playlistDAO.deletePlaylist(playlistDAO.updatePlaylist(playlist).getId());
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to delete Playlist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private boolean validatePlaylistModel(PlaylistRequestModel model) {
		return model.getName() == null || model.getName().isEmpty() ? false : true;
	}

	public Playlist playlistEntityToPlaylist(PlaylistBeanEntity entity) {
		Playlist playlist = new Playlist();
		playlist.setId(entity.getId());
		playlist.setName(entity.getName());
		playlist.setConsumer(consumerDAO.getConsumer(entity.getConsumerId()));
		playlist.setRating(entity.getRating());
		return playlist;
	}

	public PlaylistBeanEntity playlistToPlaylistEntity(Playlist playlist) {
		PlaylistBeanEntity entity = new PlaylistBeanEntity();
		entity.setId(playlist.getId());
		entity.setName(playlist.getName());
		entity.setConsumerId(playlist.getConsumer().getId());
		entity.setSongAmount(playlist.getSongs() == null ? 0 : playlist.getSongs().size());
		entity.setRating(playlist.getRating());
		if (playlist.getName().equals("Favorites")) {
			entity.setImgPath("http://localhost:8888/resources/playlist.png");
		} else if (playlist.getConsumer().getUser().getUsername().equals("musichub")) {
			entity.setImgPath(playlist.getSongs().get(0).getAuthor().getImgPath());
		} else if (playlist.getSongs().isEmpty()) {
			entity.setImgPath("http://localhost:8888/resources/playlist_cover.png");
		} else {
			entity.setImgPath(playlist.getSongs().get(0).getAlbum().getImgPath());
		}
		return entity;
	}
}
