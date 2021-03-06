package com.mymedia.web.mvc.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.dto.ConsumerBeanEntity;
import com.mymedia.web.dto.PublisherBeanEntity;
import com.mymedia.web.dto.UserBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.mvc.model.Consumer;
import com.mymedia.web.mvc.model.User;
import com.mymedia.web.requestmodel.CreateConsumerRequestModel;
import com.mymedia.web.requestmodel.CreatePublisherRequestModel;
import com.mymedia.web.requestmodel.LoginRequestModel;
import com.mymedia.web.service.ConsumerService;
import com.mymedia.web.service.PublisherService;
import com.mymedia.web.service.TokenService;
import com.mymedia.web.service.UserService;

@RestController
public class AccountController {
	@Autowired
	private UserService userService;

	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private PublisherService publisherService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	ServletContext servletContext;

	private static final Logger LOG = LogManager.getLogger(AccountController.class);

	@PostMapping(value = "/login/Google")
	public ResponseEntity<UserBeanEntity> loginGoogle(@RequestBody String tokenId) {
		try {
			UserBeanEntity user = userService.userToUserEntity(tokenService.getUserFromGoogleToken(tokenId));
			return new ResponseEntity<UserBeanEntity>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<UserBeanEntity>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/register/Google")
	public ResponseEntity<String> registerGoogle(@RequestBody String id) {
		try {
			return new ResponseEntity<String>(id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestModel model) {
		try {
			return new ResponseEntity<>(userService.getToken(model), HttpStatus.ACCEPTED);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@PostMapping(value = "/register")
	public ResponseEntity<?> register(@RequestBody CreateConsumerRequestModel model) {
		try {
			return new ResponseEntity<>(consumerService.createConsumerAsBeanEntity(model), HttpStatus.CREATED);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@PostMapping(value = "/register/publisher")
	public ResponseEntity<?> register(@RequestBody CreatePublisherRequestModel model) {
		try {
			return new ResponseEntity<>(publisherService.createPublisher(model), HttpStatus.CREATED);
		} catch (MusicHubGenericException exc) {
			return new ResponseEntity<>(exc.getMessage(), exc.getCode());
		}
	}

	@GetMapping(value = "/account")
	// @PreAuthorize("isFullyAuthenticated()")
	public ResponseEntity<?> getLoggedUser() {
		try {
			User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return new ResponseEntity<>(userService.userToUserEntity(u), HttpStatus.OK);
		} catch (Exception exc) {
			return new ResponseEntity<>("Authorization required!", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping(value = "/account/consumer")
	// @PreAuthorize("isFullyAuthenticated()")
	public ResponseEntity<ConsumerBeanEntity> getLoggedConsumer() {
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ConsumerBeanEntity consumer = consumerService.getConsumerEnitityByUserId(user.getId());
			return new ResponseEntity<>(consumer, HttpStatus.OK);
		} catch (Exception exc) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping(value = "/account/publisher")
	// @PreAuthorize("isFullyAuthenticated()")
	public ResponseEntity<PublisherBeanEntity> getLoggedPublisher() {
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			PublisherBeanEntity publisher = publisherService.getPublisherEntityByUserId(user.getId());
			return new ResponseEntity<>(publisher, HttpStatus.OK);
		} catch (Exception exc) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(value = "account/logout")
	// @PreAuthorize("isFullyAuthenticated()")
	public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			new SecurityContextLogoutHandler().logout(request, response, auth);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception exc) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}
