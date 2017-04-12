package com.mymedia.web.auth;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.mvc.model.User;
import com.mymedia.web.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {

	private UserService userService;
	private TokenService tokenService;
	
	@SuppressWarnings("unused")
	public LoginController() {
		this(null,null);
	}

	@Autowired
	public LoginController(UserService userService,TokenService tokenService) {
		this.userService = userService;
		this.tokenService = tokenService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public User login(@RequestBody String username, String password, HttpServletResponse response) {
		User u = userService.getByUsername(username);
		if(u.getPassword().trim().equals(password)){
			response.setHeader("Token", tokenService.createJWT(u));	
		}
		return u;
	}
}