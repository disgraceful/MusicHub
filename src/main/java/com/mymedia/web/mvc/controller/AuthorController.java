package com.mymedia.web.mvc.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mymedia.web.dto.AuthorBeanEntity;
import com.mymedia.web.service.AuthorService;
import com.mymedia.web.utils.SpringBeanProvider;

@Controller
@RequestMapping("/authours")
public class AuthourController {

	private static final Logger LOG = LogManager.getLogger(AuthourController.class);

	private static AuthorService getService() {
		return SpringBeanProvider.getBean(AuthorService.class);
	}

	@RequestMapping(value = "/getauthour/{id}", method = RequestMethod.GET)
	public @ResponseBody
	AuthorBeanEntity getAuthourByID(@PathVariable int id) {
		AuthorBeanEntity a = getService().getAuthor(id);
		LOG.info(a.toString());
		return a;
	}

	@RequestMapping(value = "/listauthours", method = RequestMethod.GET)
	public @ResponseBody List<AuthorBeanEntity> listAuthours() {
		return getService().getAllAuthours();
	}

	@RequestMapping(value="/addauthour",method=RequestMethod.POST)
	public @ResponseBody
	AuthorBeanEntity postUser(@RequestBody AuthorBeanEntity authour) {
		return getService().addAuthor(authour);
	}
}
