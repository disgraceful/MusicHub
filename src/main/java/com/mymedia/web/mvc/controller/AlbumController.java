package com.mymedia.web.mvc.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mymedia.web.mvc.model.Album;
import com.mymedia.web.mvc.model.Authour;
import com.mymedia.web.service.AlbumService;
import com.mymedia.web.utils.SpringBeanProvider;

@Controller
@RequestMapping("/albums")
public class AlbumController {

	private static final Logger LOG = LogManager.getLogger(AlbumController.class);

	private static AlbumService getService() {
		return SpringBeanProvider.getBean(AlbumService.class);
	}

	@RequestMapping(value = "/getalbum/{id}", method = RequestMethod.GET)
	public @ResponseBody Album getAlbumByID(@PathVariable int id) {
		Album a = getService().getAlbum(id);
		LOG.info(a.toString());
		return a;
	}

	@RequestMapping(value = "/listalbums", method = RequestMethod.GET)
	public @ResponseBody List<Album> listAlbums() {
		return getService().getAllAlbums();
	}

	@RequestMapping(value="/addalbums",method=RequestMethod.POST)
	public @ResponseBody Album postUser(@RequestBody Album album) {
		return getService().addAlbum(album);
	}
}
