package com.mymedia.web.mvc.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mymedia.web.dto.AuthorBeanEntity;
import com.mymedia.web.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private static final Logger LOG = LogManager.getLogger(AuthorController.class);

    @Autowired
    private AuthorService authorService;

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
}
