package com.mymedia.web.mvc.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/album")
public class AlbumController {
private static final Logger LOG = LogManager.getLogger(AlbumController.class);

	@Autowired
    private AlbumService albumService;
    
    @Autowired
    private AuthorService authorService;
    
    @Autowired
    private SongService songService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody AlbumBeanEntity getAlbumById(@PathVariable int id) {
        AlbumBeanEntity album = albumService.getAlbum(id);
        LOG.info(album.toString());
        return album;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<AlbumBeanEntity> getAlbums() {
        return albumService.getAllAlbums();
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody AlbumBeanEntity postAlbum(@RequestBody AlbumBeanEntity album) {
        return albumService.addAlbum(album);
    }
    
    
    @RequestMapping(method = RequestMethod.PATCH)
    public @ResponseBody AlbumBeanEntity updateAlbum(@RequestBody AlbumBeanEntity album){
    	return albumService.updateAlbum(album);
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public @ResponseBody void deleteAlbum(@RequestBody int id){
    	albumService.deleteAlbum(id);
    }
    
    @RequestMapping(value = "/{id}/author", method = RequestMethod.GET)
    public @ResponseBody AuthorBeanEntity getAuthorByAlbumId(@PathVariable int id) {
        AlbumBeanEntity album = albumService.getAlbum(id);
        LOG.info(album.toString());
        AuthorBeanEntity author = authorService.getAuthor(album.getAuthorId());
        LOG.info(author.toString());
        return author;
    }
    
    @RequestMapping(value = "/{id}/songs", method = RequestMethod.GET)
    public @ResponseBody List<SongBeanEntity> getSongsByAlbumId(@PathVariable int id) {
        return songService.getSongsByAlbumId(id);
    }
}
