package com.mymedia.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.LyricsHandler;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.xml.sax.SAXException;

import com.mymedia.web.dto.SongBeanEntity;

public class Mp3Utils {

	private static final Logger LOG = LogManager.getLogger(Mp3Utils.class);

	public static SongBeanEntity fileToSongBeanEntity(File file,String path) {
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
		
			SongBeanEntity entity = new SongBeanEntity();
			entity.setName(metadata.get("title"));
			entity.setBirthDate(new Date());
			entity.setDuration(metadata.get("xpmDM:duration"));
			entity.setUrl(getFileURL(file,path));
			
			LOG.info(entity.getName() + " " + entity.getBirthDate());
			return entity;

		} catch (SAXException | TikaException | IOException e) {
			LOG.error("caught exception {}", e);
		}
		return null;
	}

	public static String getFileURL(File file,String path) {
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
