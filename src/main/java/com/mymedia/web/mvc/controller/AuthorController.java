package com.mymedia.web.mvc.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.dto.AlbumBeanEntity;
import com.mymedia.web.dto.AuthorBeanEntity;
import com.mymedia.web.dto.SongBeanEntity;
import com.mymedia.web.service.AlbumService;
import com.mymedia.web.service.AuthorService;
import com.mymedia.web.service.SongService;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private static final Logger LOG = LogManager.getLogger(AuthorController.class);

    @Autowired
    private AuthorService authorService;
    
    @Autowired
    private SongService songService;
    
    @Autowired
    private AlbumService albumService;

    @GetMapping(value="/top")
    public @ResponseBody List<AuthorBeanEntity> getTop() {
        return authorService.getTop10();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody AuthorBeanEntity getAuthorById(@PathVariable int id) {
        AuthorBeanEntity author = authorService.getAuthor(id);
        LOG.info(author.toString());
        return author;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<AuthorBeanEntity> getAuthors() {
        return authorService.getAllAuthors();
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody AuthorBeanEntity postAuthor(@RequestBody AuthorBeanEntity author) {
        return authorService.addAuthor(author);
    }
    
    @RequestMapping(method = RequestMethod.PATCH)
	public @ResponseBody AuthorBeanEntity updateAuthor(@RequestBody AuthorBeanEntity song) {
		return authorService.updateAuthor(song);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteAuthor(@PathVariable int id) {
		authorService.deleteAuthor(id);
	}
	
	@RequestMapping(value = "/{id}/songs", method = RequestMethod.GET)
	public @ResponseBody List<SongBeanEntity> getSongs(@PathVariable int id) {
		return songService.getSongsByAuthorId(id);
	}
	
	@RequestMapping(value = "/{id}/albums", method = RequestMethod.GET)
	public @ResponseBody List<AlbumBeanEntity> getAlbums(@PathVariable int id) {
		return albumService.getAlbumsByAuthorId(id);
	}
}
