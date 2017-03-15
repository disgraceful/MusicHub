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

import com.mymedia.web.mvc.model.Song;
import com.mymedia.web.service.SongService;
import com.mymedia.web.utils.SpringBeanProvider;

@Controller
public class SongController {
	private static final Logger LOG = LogManager.getLogger(SongController.class);

	private static SongService getService() {
		return SpringBeanProvider.getBean(SongService.class);
	}

	@RequestMapping(value = "/getsong/{id}", method = RequestMethod.GET)
	public @ResponseBody Song getSongById(@PathVariable int id) {
		Song a = getService().getSong(id);
		LOG.info(a.toString());
		return a;
	}

	@RequestMapping(value = "/listsongs", method = RequestMethod.GET)
	public @ResponseBody List<Song> listSongs() {
		return getService().getAllSongs();
	}

	@RequestMapping(value="/addsong",method=RequestMethod.POST)
	public @ResponseBody Song addUser(@RequestBody Song song) {
		return getService().addSong(song);
	}
}
