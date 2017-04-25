package com.mymedia.web.mvc.controller;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.mvc.model.User;
import com.mymedia.web.requestmodel.CreateConsumerRequestModel;
import com.mymedia.web.requestmodel.CreatePublisherRequestModel;
import com.mymedia.web.requestmodel.LoginRequestModel;
import com.mymedia.web.responsemodel.TokenResponseModel;
import com.mymedia.web.service.ConsumerService;
import com.mymedia.web.service.PublisherService;
import com.mymedia.web.service.TokenService;
import com.mymedia.web.service.UserService;
import com.mymedia.web.utils.CryptUtils;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;

	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private PublisherService publisherService;

	@Autowired
	ServletContext servletContext;

	private static final Logger LOG = LogManager.getLogger(AccountController.class);

	@PostMapping(value = "/login")
	public TokenResponseModel login(@RequestBody LoginRequestModel model) {
		LOG.info(model.getUsername() + " " + model.getPassword());
		User u = userService.getByUsername(model.getUsername());
		LOG.info("model true pass "+model.getPassword());
		LOG.info("model hash pass "+ CryptUtils.generateHashSHA1(model.getPassword()));
		LOG.info("user hash pass "+ u.getPassword());
		if (u.getPassword().trim().equals(CryptUtils.generateHashSHA1(model.getPassword().trim()))) {
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

}