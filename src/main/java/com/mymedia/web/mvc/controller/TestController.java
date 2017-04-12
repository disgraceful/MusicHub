package com.mymedia.web.mvc.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.auth.TokenService;

@RestController
@RequestMapping("/test")
public class TestController {
	private static final Logger LOG = LogManager.getLogger(TestController.class);
	@RequestMapping(method = RequestMethod.GET)
	public void test(){
		String userName = "Username";
		String password = "pass123";
		//String token = TokenUtils.createJWT(userName, password);
		//TokenUtils.validateJWT(token);
		
	}
}
