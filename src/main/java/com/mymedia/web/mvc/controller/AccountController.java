package com.mymedia.web.mvc.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mymedia.web.mvc.model.User;
import com.mymedia.web.requestmodel.CreateConsumerRequestModel;
import com.mymedia.web.requestmodel.CreatePublisherRequestModel;
import com.mymedia.web.requestmodel.LoginRequestModel;
import com.mymedia.web.responsemodel.TokenResponseModel;
import com.mymedia.web.service.ConsumerService;
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

	@RequestMapping(value="/login",method = RequestMethod.POST)
	public TokenResponseModel login(@RequestBody LoginRequestModel model) {
		LOG.info(model.getUsername() + " " + model.getPassword());
		User u = userService.getByUsername(model.getUsername());
		if(u.getPassword().trim().equals(model.getPassword().trim())){
			String token = tokenService.createJWT(u);
				
			TokenResponseModel respModel = new TokenResponseModel();
			respModel.setAccessToken(token);
			return respModel;
		}	
		return null;
	}
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public void register(@RequestBody CreateConsumerRequestModel model){
		LOG.info("username : " + model.getUsername());
		LOG.info("pass : " + model.getPassword());
		LOG.info("confirm : " + model.getConfirmPassword());
		consumerService.createConsumer(model);
	}
	
	@RequestMapping(value="/register/upload",method = RequestMethod.POST)
	public void upload(@RequestBody MultipartFile file){
		LOG.info("we got file name! " + file.getName());
		LOG.info("we got file org name! " + file.getOriginalFilename());
		
	}
	
	@RequestMapping(value="/register/publisher",method = RequestMethod.POST)
	public void register(@RequestBody CreatePublisherRequestModel model){
		publisherService.createPublisher(model);
	}
	
	@RequestMapping(value="/test",method = RequestMethod.GET)
	public String get(){
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	
}