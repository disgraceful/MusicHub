package com.mymedia.web.mvc.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.dto.PlaylistBeanEntity;
import com.mymedia.web.mvc.model.User;
import com.mymedia.web.requestmodel.CreateConsumerRequestModel;
import com.mymedia.web.requestmodel.CreatePublisherRequestModel;
import com.mymedia.web.requestmodel.LoginRequestModel;
import com.mymedia.web.responsemodel.TokenResponseModel;
import com.mymedia.web.service.ConsumerService;
import com.mymedia.web.service.PlaylistService;
import com.mymedia.web.service.PublisherService;
import com.mymedia.web.service.TokenService;
import com.mymedia.web.service.UserService;

@RestController
@RequestMapping("/account")
public class AccountController {

	private UserService userService;
	private TokenService tokenService;

	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private PublisherService publisherService;

	@Autowired
	private PlaylistService playlistService;

	@Autowired
	ServletContext servletContext;

	private static final Logger LOG = LogManager.getLogger(AccountController.class);

	@SuppressWarnings("unused")
	public AccountController() {
		this(null, null);
	}

	@Autowired
	public AccountController(UserService userService, TokenService tokenService) {
		this.userService = userService;
		this.tokenService = tokenService;
	}

	@PostMapping(value = "/login")
	public TokenResponseModel login(@RequestBody LoginRequestModel model) {
		LOG.info(model.getUsername() + " " + model.getPassword());
		User u = userService.getByUsername(model.getUsername());
		if (u.getPassword().trim().equals(model.getPassword().trim())) {
			String token = tokenService.createJWT(u);

			TokenResponseModel respModel = new TokenResponseModel();
			respModel.setAccessToken(token);
			return respModel;
		}
		return null;
	}

	@PostMapping(value = "/register")
	public void register(@RequestBody CreateConsumerRequestModel model) {
		LOG.info("username : " + model.getUsername());
		LOG.info("pass : " + model.getPassword());
		LOG.info("confirm : " + model.getConfirmPassword());
		consumerService.createConsumer(model);
	}

	@PostMapping(value = "/register/publisher")
	public void register(@RequestBody CreatePublisherRequestModel model) {
		publisherService.createPublisher(model);
	}

	@GetMapping(value = "/{id}/playlists")
	public List<PlaylistBeanEntity> getPlaylists(@PathVariable int id) {
		return playlistService.getPlaylistByUserId(id);
	}
}