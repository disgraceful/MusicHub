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
	private UserService userService;

	@Autowired
	private PublisherService publisherService;

	@Autowired
	private Mp3Service mp3Service;

	@PostMapping(value = "/upload/{albumId}")
	public ResponseEntity<SongBeanEntity> upload(@RequestBody MultipartFile file, @PathVariable int albumId) {
		String path = servletContext.getRealPath("WEB-INF/music");
		LOG.info(path);
		File f = new File(path);
		LOG.info(f.exists());
		if (!f.exists()) {
			f.mkdir();
			LOG.info("making dir " + f.getName());
		}
		LOG.info(f.exists());
		try {
			LOG.info(path + "/" + file.getOriginalFilename());
			File convFile = new File(path + "/" + file.getOriginalFilename());
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
			SongBeanEntity entity = mp3Service.fileToSongBeanEntity(convFile, path);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			LOG.info("user:" + auth.getName());
			User u = userService.getByUsername(auth.getName());
			Role r = u.getRole();
			LOG.info(r.getName());
			if (r.getName().trim().equals("PUBLISHER")) {
				Publisher pub = publisherService.getPublisherByUserId(u.getId());
				LOG.info(pub.getAuthor().getId());

				entity.setAuthorId(pub.getAuthor().getId());
				entity.setAlbumId(albumId);
				SongBeanEntity song = songService.addSong(entity);
				if (song != null) {
					return new ResponseEntity<>(song, HttpStatus.OK);
				}

			}
			LOG.info(convFile.getAbsolutePath());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			LOG.info("caught exception {}", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
