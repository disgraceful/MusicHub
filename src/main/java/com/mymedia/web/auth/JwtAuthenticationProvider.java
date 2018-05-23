package com.mymedia.web.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.mymedia.web.mvc.model.User;
import com.mymedia.web.service.TokenService;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
	private static final Logger LOG = LogManager.getLogger(JwtAuthenticationProvider.class);
	@Autowired
	private TokenService tokenService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			
			String token = (String) authentication.getCredentials();
			LOG.info("SOME TOKENM " + token);
			User possibleProfile = tokenService.retrieveUser(token);
			return new JwtAuthenticatedProfile(possibleProfile);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthToken.class.equals(authentication);
	}

}
