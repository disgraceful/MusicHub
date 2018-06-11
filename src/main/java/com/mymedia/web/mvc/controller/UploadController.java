package com.mymedia.web.mvc.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mymedia.web.dto.SongBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.mvc.model.Consumer;
import com.mymedia.web.mvc.model.Publisher;
import com.mymedia.web.mvc.model.User;
import com.mymedia.web.service.ConsumerService;
import com.mymedia.web.service.FileService;
import com.mymedia.web.service.PublisherService;
import com.mymedia.web.service.SongService;

@RestController
public class UploadController {

	private static final Logger LOG = LogManager.getLogger(UploadController.class);
	@Autowired
	private ServletContext servletContext;

	@Autowired
	private SongService songService;

	@Autowired
	private PublisherService publisherService;

	@Autowired
	private FileService fileService;
	
	@Autowired
	private ConsumerService consumerService;
	
	@PostMapping(value = "/upload/consumer/{id}")
	public ResponseEntity<?> save(@RequestBody MultipartFile file, @PathVariable String id) {
		try {
			if (file.isEmpty()) {
				throw new MusicHubGenericException("Uploaded file is not valid", HttpStatus.BAD_REQUEST);
			}
			Consumer consumer = consumerService.getConsumerByUserId(id);
			String imagePath = fileService.saveConsumerImage(file, consumer.getUser().getUsername());
			consumer.setImgPath(imagePath);
			return new ResponseEntity<>(consumerService.updateConsumer(consumer), HttpStatus.OK);
		} catch (Exception exc) {
			return new ResponseEntity<>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/upload/{albumId}")
	public ResponseEntity<?> upload(@RequestBody MultipartFile file, @PathVariable String albumId) {
		try {
			if (file.isEmpty()) {
				throw new MusicHubGenericException("Uploaded file is not valid", HttpStatus.BAD_REQUEST);
			}
			String pathToServerMusicDir = servletContext.getRealPath("WEB-INF/music");
			File serverMusicDir = new File(pathToServerMusicDir);
			if (!serverMusicDir.exists()) {
				serverMusicDir.mkdir();
			}
			File musicFile = new File(pathToServerMusicDir + "/" + file.getOriginalFilename());
			musicFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(musicFile);
			fos.write(file.getBytes());
			fos.close();
			LOG.info(musicFile.exists());
			LOG.info(musicFile.getAbsolutePath());
			SongBeanEntity entity = fileService.fileToSongBeanEntity(musicFile);
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			Publisher pub = publisherService.getPublisherByUserId(user.getId());
			entity.setAuthorId(pub.getAuthor().getId());
			entity.setAlbumId(albumId);
			SongBeanEntity song = songService.addSong(entity);
			return new ResponseEntity<>(song, HttpStatus.OK);
		} catch (Exception exc) {
			return new ResponseEntity<>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
