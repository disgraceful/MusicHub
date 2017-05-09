package com.mymedia.web.mvc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mymedia.web.dto.SongBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.mvc.model.Publisher;
import com.mymedia.web.mvc.model.Role;
import com.mymedia.web.mvc.model.User;
import com.mymedia.web.service.Mp3Service;
import com.mymedia.web.service.PublisherService;
import com.mymedia.web.service.SongService;
import com.mymedia.web.service.UserService;

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
	private Mp3Service mp3Service;

	@PostMapping(value = "/upload/{albumId}")
	public ResponseEntity<?> upload(@RequestBody MultipartFile file, @PathVariable int albumId) {
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
			SongBeanEntity entity = mp3Service.fileToSongBeanEntity(musicFile);
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
