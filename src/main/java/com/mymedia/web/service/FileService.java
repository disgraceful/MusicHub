package com.mymedia.web.service;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mymedia.web.dto.SongBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.mvc.model.Genre;

@Service
public class FileService {

	private static final Logger LOG = LogManager.getLogger(FileService.class);
	@Autowired
	GenreService genreService;

	@Autowired
	SongService songService;
	@Autowired
	private ServletContext servletContext;

	public String saveConsumerImage(MultipartFile file, String username) {
		try {
			if (file== null) {
				return "";
			}

			String pathToServerDir = servletContext.getRealPath("WEB-INF/resources");
			String consumerDir= pathToServerDir+"/consumer/"+username;
			File serverDir = new File(consumerDir);
			if(!serverDir.exists()) {
				serverDir.mkdirs();
			}
			File imageFile = new File(consumerDir + "/" +  file.getOriginalFilename());
			imageFile.createNewFile();
			file.transferTo(imageFile);
			return "http://localhost:8888/resources/consumer/" + username + "/" +  file.getOriginalFilename();
		} catch (Exception e) {
			throw new MusicHubGenericException("Failed to parse the file!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public SongBeanEntity fileToSongBeanEntity(File file) {
		try {
			Tika tika = new Tika();
			if (!file.exists() || !tika.detect(file).trim().equals("audio/mpeg")) {
				throw new MusicHubGenericException("Not a valid music file!", HttpStatus.BAD_REQUEST);
			}
			FileInputStream inputStream = new FileInputStream(file);
			BodyContentHandler handler = new BodyContentHandler();
			Metadata metadata = new Metadata();
			ParseContext pcontext = new ParseContext();

			Mp3Parser mp3Parser = new Mp3Parser();
			mp3Parser.parse(inputStream, handler, metadata, pcontext);

			SongBeanEntity entity = new SongBeanEntity();
			entity.setName(metadata.get("title"));
			entity.setDuration(parseDuration(metadata.get("xmpDM:duration")));
			String genreName = metadata.get("xmpDM:genre");
			Genre genre = genreService.getGenreByName(genreName);
			entity.setGenreId(genre.getId());
			entity.setUrl(getFileURL(file));
			inputStream.close();
			return entity;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to parse the file!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private String getFileURL(File file) {
		return "http://localhost:8080/music/" + file.getName();
	}

	private String parseDuration(String duration) throws ParseException {
		NumberFormat format = NumberFormat.getInstance(Locale.US);
		long time = (long) format.parse(duration).doubleValue();
		return String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes(time), TimeUnit.MILLISECONDS.toSeconds(time)
				- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
	}
}
