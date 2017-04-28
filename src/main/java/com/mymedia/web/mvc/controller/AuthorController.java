package com.mymedia.web.mvc.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @GetMapping(value = "/{id}")
    public @ResponseBody AuthorBeanEntity getAuthorById(@PathVariable int id) {
        AuthorBeanEntity author = authorService.getAuthor(id);
        LOG.info(author.toString());
        return author;
    }

    @GetMapping
    public @ResponseBody List<AuthorBeanEntity> getAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping
    public @ResponseBody AuthorBeanEntity postAuthor(@RequestBody AuthorBeanEntity author) {
        return authorService.addAuthor(author);
    }
    
    @PatchMapping
	public @ResponseBody AuthorBeanEntity updateAuthor(@RequestBody AuthorBeanEntity song) {
		return authorService.updateAuthor(song);
	}

	@DeleteMapping(value = "/{id}")
	public @ResponseBody void deleteAuthor(@PathVariable int id) {
		authorService.deleteAuthor(id);
	}
	
	@GetMapping(value = "/{id}/songs")
	public @ResponseBody List<SongBeanEntity> getSongs(@PathVariable int id) {
		return songService.getSongsByAuthorId(id);
	}
	
	@GetMapping(value = "/{id}/albums")
	public @ResponseBody List<AlbumBeanEntity> getAlbums(@PathVariable int id) {
		return albumService.getAlbumsByAuthorId(id);
	}
}
