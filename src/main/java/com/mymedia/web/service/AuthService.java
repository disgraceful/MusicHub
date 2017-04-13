package com.mymedia.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymedia.web.auth.TokenService;

@Service
public class AuthService {

	@Autowired
	private TokenService tokenService;
	
}
