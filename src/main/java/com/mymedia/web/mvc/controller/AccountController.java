package com.mymedia.web.mvc.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.dto.UserBeanEntity;
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
	public ResponseEntity<TokenResponseModel> login(@RequestBody LoginRequestModel model) {
		LOG.info(model.getUsername() + " " + model.getPassword());
		User u = userService.getByUsername(model.getUsername());
		LOG.info("model true pass " + model.getPassword());
		LOG.info("model hash pass " + CryptUtils.generateHashSHA1(model.getPassword()));
		LOG.info("user hash pass " + u.getPassword());
		// if
		// (u.getPassword().trim().equals(CryptUtils.generateHashSHA1(model.getPassword().trim())))
		// {
		if (u.getPassword().trim().equals(model.getPassword().trim())) {
			String token = tokenService.createJWT(u);
			TokenResponseModel respModel = new TokenResponseModel();
			respModel.setAccessToken(token);
			return new ResponseEntity<>(respModel,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		//ask Nazar
	}

	@PostMapping(value = "/register")
	public void register(@RequestBody CreateConsumerRequestModel model) {
		consumerService.createConsumer(model);
	}

	@PostMapping(value = "/register/publisher")
	public void register(@RequestBody CreatePublisherRequestModel model) {
		publisherService.createPublisher(model);
	}

	@GetMapping
	public ResponseEntity<UserBeanEntity> getLoggedUser() {
		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(u!=null){
			return new ResponseEntity<>(userService.userToUserEntity(u),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) {
		SecurityContextHolder.getContext().setAuthentication(null);
		//new SecurityContextLogoutHandler().logout(request,response,auth);
	}

}
