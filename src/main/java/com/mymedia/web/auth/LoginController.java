package com.mymedia.web.auth;

import java.security.Principal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mymedia.web.mvc.model.User;
import com.mymedia.web.requestmodel.LoginRequestModel;
import com.mymedia.web.responsemodel.TokenResponseModel;
import com.mymedia.web.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {

	private UserService userService;
	private TokenService tokenService;

	private static final Logger LOG = LogManager.getLogger(LoginController.class);

	@SuppressWarnings("unused")
	public LoginController() {
		this(null, null);
	}

	@Autowired
	public LoginController(UserService userService, TokenService tokenService) {
		this.userService = userService;
		this.tokenService = tokenService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public TokenResponseModel login(@RequestBody LoginRequestModel model) {
		User u = userService.getByUsername(model.getUsername());
		if(u.getPassword().trim().equals(model.getPassword().trim())){
			String token = tokenService.createJWT(u);
				
			TokenResponseModel respModel = new TokenResponseModel();
			respModel.setAccessToken(token);
			return respModel;
		}	
		return null;
	}
	
	@RequestMapping(value="/test",method = RequestMethod.GET)
	public String get(){
		
		
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}