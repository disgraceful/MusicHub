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
@RequestMapping("/album")
public class AlbumController {
private static final Logger LOG = LogManager.getLogger(AlbumController.class);

	@Autowired
    private AlbumService albumService;
    
    @Autowired
    private AuthorService authorService;
    
    @Autowired
    private SongService songService;

    @GetMapping(value="/top")
    public @ResponseBody List<AlbumBeanEntity> getTop() {
        return albumService.getTop10();
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody AlbumBeanEntity getAlbumById(@PathVariable int id) {
        AlbumBeanEntity album = albumService.getAlbum(id);
        LOG.info(album.toString());
        return album;
    }

    @GetMapping
    public @ResponseBody List<AlbumBeanEntity> getAlbums() {
        return albumService.getAllAlbums();
    }

    @PostMapping
    public @ResponseBody AlbumBeanEntity postAlbum(@RequestBody AlbumBeanEntity album) {
        return albumService.addAlbum(album);
    }
    
    @PatchMapping
    public @ResponseBody AlbumBeanEntity updateAlbum(@RequestBody AlbumBeanEntity album){
    	return albumService.updateAlbum(album);
    }
    
    @DeleteMapping(value = "/{id}")
    public @ResponseBody void deleteAlbum(@RequestBody int id){
    	albumService.deleteAlbum(id);
    }
    
    @GetMapping(value = "/{id}/author")
    public @ResponseBody AuthorBeanEntity getAuthorByAlbumId(@PathVariable int id) {
        AlbumBeanEntity album = albumService.getAlbum(id);
        LOG.info(album.toString());
        AuthorBeanEntity author = authorService.getAuthor(album.getAuthorId());
        LOG.info(author.toString());
        return author;
    }
    
    @GetMapping(value = "/{id}/songs")
    public @ResponseBody List<SongBeanEntity> getSongsByAlbumId(@PathVariable int id) {
        return songService.getSongsByAlbumId(id);
    }
}
