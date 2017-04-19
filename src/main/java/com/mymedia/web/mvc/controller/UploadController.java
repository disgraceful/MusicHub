package com.mymedia.web.mvc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
	@Autowired
	ServletContext servletContext;
	private static final Logger LOG = LogManager.getLogger(UploadController.class);

	@PostMapping(value = "/upload")
	public void upload(@RequestBody MultipartFile file, HttpServletRequest request) {
		LOG.info("we got file name! " + file.getOriginalFilename());
		String path = servletContext.getRealPath("WEB-INF/music");
		LOG.info(path);
		File f = new File(path);
		LOG.info(f.exists());
		if(!f.exists()){
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
			LOG.info(convFile.getAbsolutePath());
		} catch (IOException e) {
			LOG.info("caught exception {}", e);
		}
	}

	@GetMapping(value = "/getup/{filename:.+}")
	public String getUploadedFile(@PathVariable String filename) {
		String path = servletContext.getRealPath("WEB-INF/music");
		LOG.info(filename);
		LOG.info(path);
		LOG.info(new File(path).exists());
		File f = new File(path+"/"+filename);
		LOG.info(new File(path+"/"+filename).exists());
		String serverPath = "http://localhost:8080/"+path.substring(path.lastIndexOf("/")) + "/"+f.getName(); 
		return serverPath;
	}
}
