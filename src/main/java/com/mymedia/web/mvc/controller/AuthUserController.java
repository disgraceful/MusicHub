package com.mymedia.web.mvc.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mymedia.web.mvc.model.AuthUser;
import com.mymedia.web.service.AuthUserService;
import com.mymedia.web.utils.SpringBeanProvider;

@Controller
@RequestMapping("/authusers")
public class AuthUserController {

	private static final Logger LOG = LogManager.getLogger(AuthUserController.class);
	
	private static AuthUserService getService(){
		return SpringBeanProvider.getBean(AuthUserService.class);
	}
	
	@RequestMapping(value = "/getuser/{id}", method = RequestMethod.GET)
	public @ResponseBody AuthUser getAuthUserByID(@PathVariable int id) {
		AuthUser a = getService().getAuthUser(id);
		LOG.info(a.toString());
						
		return a;
	}

	@RequestMapping(value = "/listusers", method = RequestMethod.GET)
	public @ResponseBody List<AuthUser> listAuthUsers() {
		return getService().getAllAuthUsers();
	}

	
}
