package com.mymedia.web.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.service.RecommendationService;

@RestController
@RequestMapping("/recommended")
public class RecommendedController {
	@Autowired
	private RecommendationService recService;

	@GetMapping(value = "/songs/genre")
	public ResponseEntity<?> getRecommendedSongsByGenre() {
		try {
			return new ResponseEntity<>(recService.getRecommendedSongsByGenre(), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}
	
	@GetMapping(value = "/songs/author")
	public ResponseEntity<?> getRecommendedSongsByAuthor() {
		try {
			return new ResponseEntity<>(recService.getRecommendedSongsByAuthor(), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}
	
	@GetMapping(value = "/authors/author")
	public ResponseEntity<?> getRecommendedAuthorsByAuthor() {
		try {
			return new ResponseEntity<>(recService.getRecommendedAuthorsByAuthor(), HttpStatus.OK);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}
}
