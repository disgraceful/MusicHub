package com.mymedia.web.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.service.AuthorService;
import com.mymedia.web.service.PublisherService;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

	@Autowired private PublisherService publisherService;
	@Autowired private AuthorService authorService;
	
	@GetMapping(value = "/{id}/author")
	public ResponseEntity<?> getAuthorByPublisherId(@PathVariable String id) {
		try {
			return new ResponseEntity<>(authorService.getAuthorByPublisherId(id), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}
}
