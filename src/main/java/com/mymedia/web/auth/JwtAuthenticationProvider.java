package com.mymedia.web.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.mymedia.web.mvc.model.User;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    
	private final TokenService tokenService;

    @SuppressWarnings("unused")
    public JwtAuthenticationProvider() {
        this(null);
    }

    @Autowired
    public JwtAuthenticationProvider(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            User possibleProfile = tokenService.validateJWT((String)authentication.getCredentials());
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
