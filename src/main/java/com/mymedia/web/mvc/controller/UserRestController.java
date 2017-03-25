package com.mymedia.web.mvc.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.mvc.model.AuthUser;
import com.mymedia.web.service.AuthUserService;

@RestController
public class UserRestController {

	private static final Logger LOG = LogManager.getLogger(AuthUserController.class);

	@Autowired
	AuthUserService userService;

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public @ResponseBody AuthUser getAuthUserByID(@PathVariable int id) {
		AuthUser a = userService.getAuthUser(id);
		LOG.info(a.toString());
		return a;
	}
	
	@RequestMapping(value="/adduser",method=RequestMethod.POST)
	public @ResponseBody AuthUser postUser(@RequestBody AuthUser user) {
		return userService.addAuthUser(user);
	}
}
