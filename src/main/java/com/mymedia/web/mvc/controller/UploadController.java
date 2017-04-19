package com.mymedia.web.mvc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
	@Autowired
	ServletContext servletContext;
	private static final Logger LOG = LogManager.getLogger(UploadController.class);

	@RequestMapping(value = "/register/upload", method = RequestMethod.POST)
	public void upload(@RequestBody MultipartFile file, HttpServletRequest request) {
		LOG.info("we got file name! " + file.getName());
		LOG.info("we got file org name! " + file.getOriginalFilename());
		String path = servletContext.getRealPath("/music");
		LOG.info(path);

		try {
			File convFile = new File(path + "/" + file.getOriginalFilename());
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
			LOG.info(convFile.getAbsolutePath());
		} catch (IOException e) {
			LOG.info("caught exception {}", e);
		}
	}

	@GetMapping(value = "/register/getup/{filename:.+}")
	public void getUploadedFile(@PathVariable String filename) {
		String path = servletContext.getRealPath("/music");
		LOG.info(filename);
		LOG.info(path);
		LOG.info(new File(path).exists());
		LOG.info(new File(path+"/"+filename).getAbsolutePath());
		LOG.info(new File(path+"/"+filename).exists());
	}
}
