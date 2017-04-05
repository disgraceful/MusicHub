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

import com.mymedia.web.dto.AlbumBeanEntity;
import com.mymedia.web.mvc.model.Album;
import com.mymedia.web.service.AlbumService;
import com.mymedia.web.utils.SpringBeanProvider;

@Controller
@RequestMapping("/albums")
public class AlbumController {

	private static final Logger LOG = LogManager.getLogger(AlbumController.class);

	private static AlbumService getAlbumService() {
		return SpringBeanProvider.getBean(AlbumService.class);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody AlbumBeanEntity getAlbumByID(@PathVariable int id) {
		AlbumBeanEntity a = getAlbumService().getAlbum(id);
		LOG.info(a.toString());
		return new AlbumBeanEntity();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<AlbumBeanEntity> listAlbums() {
		return getAlbumService().getAllAlbums();
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody AlbumBeanEntity postUser(@RequestBody AlbumBeanEntity album) {
		return getAlbumService().addAlbum(album);
	}
}
