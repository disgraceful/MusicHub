package com.mymedia.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.LyricsHandler;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import com.mymedia.web.dto.SongBeanEntity;

public class Mp3Utils {

	private static final Logger LOG = LogManager.getLogger(Mp3Utils.class);

	public static void parseMp3(File file) {
		if (!file.exists()) {
			LOG.info("file not exists");
			return;
		}
		try (FileInputStream inputstream = new FileInputStream(file)) {
			BodyContentHandler handler = new BodyContentHandler();
			Metadata metadata = new Metadata();
			ParseContext pcontext = new ParseContext();
			// Mp3 parser

			Mp3Parser mp3Parser = new Mp3Parser();
			mp3Parser.parse(inputstream, handler, metadata, pcontext);
			LyricsHandler lyrics = new LyricsHandler(inputstream, handler);

			while (lyrics.hasLyrics()) {
				LOG.info(lyrics.toString());
			}

			LOG.info("Contents of the document:" + handler.toString());
			LOG.info("Metadata of the document:");
			LOG.info("song duration(2366): " + metadata.get("xmpDM:duration"));

			String[] metadataNames = metadata.names();
			for (String name : metadataNames) {
				LOG.info(name + ": " + metadata.get(name));
			}
		} catch (SAXException | TikaException | IOException e) {
			LOG.error("caught exception {}", e);
		}

	}

	public static SongBeanEntity fileToSongBeanEntity(File file) {
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
			LyricsHandler lyrics = new LyricsHandler(inputstream, handler);

			while (lyrics.hasLyrics()) {
				LOG.info(lyrics.toString());
			}
			

			LOG.info("Contents of the document:" + handler.toString());
			LOG.info("Metadata of the document:");
			LOG.info("song duration(2366): " + metadata.get("xmpDM:duration"));

			String[] metadataNames = metadata.names();
			for (String name : metadataNames) {
				LOG.info(name + ": " + metadata.get(name));
			}
			
			SongBeanEntity entity = new SongBeanEntity();
			entity.setName(metadata.get(""));
			
		
		} catch (SAXException | TikaException |	IOException e) {
			LOG.error("caught exception {}", e);
		}
	}
}

