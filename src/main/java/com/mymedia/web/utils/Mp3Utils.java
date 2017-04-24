package com.mymedia.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.mymedia.web.dto.SongBeanEntity;
import com.mymedia.web.mvc.model.Genre;
import com.mymedia.web.service.GenreService;
import com.mymedia.web.service.SongService;

@Service
public class Mp3Utils {

	private static final Logger LOG = LogManager.getLogger(Mp3Utils.class);
	@Autowired
	GenreService genreService;

	@Autowired
	SongService songService;

	public SongBeanEntity fileToSongBeanEntity(File file, String path) {
		if (!file.exists()) {
			LOG.info("file not exists");
			return null;
		}
		try (FileInputStream inputstream = new FileInputStream(file)) {
			BodyContentHandler handler = new BodyContentHandler();
			Metadata metadata = new Metadata();
			ParseContext pcontext = new ParseContext();

			Mp3Parser mp3Parser = new Mp3Parser();
			mp3Parser.parse(inputstream, handler, metadata, pcontext);

			for (String name : metadata.names()) {
				LOG.info(name + ":" + metadata.get(name));
			}

			SongBeanEntity entity = new SongBeanEntity();
			entity.setName(metadata.get("title"));
			entity.setBirthDate(new Date());

			NumberFormat format = NumberFormat.getInstance(Locale.US);
			long time = (long) format.parse(metadata.get("xmpDM:duration")).doubleValue();
			String duration = String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes(time),
					TimeUnit.MILLISECONDS.toSeconds(time)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
			entity.setDuration(duration);
			LOG.info("Duration: " + duration);
			String genreName = metadata.get("xmpDM:genre");
			Genre genre = genreService.getGenreByName(genreName);
			LOG.info("genre id " + genre.getId());
			genre.getSongList().add(songService.songEntityToSong(entity));

			entity.setGenreId(genre.getId());
			entity.setUrl(getFileURL(file, path));

			LOG.info(entity.getName() + " " + entity.getBirthDate());
			return entity;

		} catch (SAXException | TikaException | IOException | ParseException e) {
			LOG.error("caught exception {}", e);
		}
		return null;
	}

	public String getFileURL(File file, String path) {
		LOG.info(file.exists());
		if (!file.exists()) {
			return "";
		}
		LOG.info(path);
		String serverPath = "http://localhost:8080" + path.substring(path.lastIndexOf("/")) + "/" + file.getName();
		LOG.info(serverPath);
		return serverPath;
	}
}
