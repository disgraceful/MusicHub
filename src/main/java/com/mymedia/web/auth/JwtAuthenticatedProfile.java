package com.mymedia.web.auth;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.mymedia.web.mvc.model.User;

public class JwtAuthenticatedProfile implements Authentication {
	private final User minimalProfile;

	public JwtAuthenticatedProfile(User minimalProfile) {
		this.minimalProfile = minimalProfile;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
		//return Collections.singletonList(new SimpleGrantedAuthority(minimalProfile.getRole().getName()));
	}

	@Override
	public Object getCredentials() {
		return minimalProfile.getPassword();
	}

	@Override
	public Object getDetails() {
		return "";
	}

	@Override
	public User getPrincipal() {
		return minimalProfile;
	}

	@Override
	public boolean isAuthenticated() {
		return minimalProfile!=null?true:false;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		
	}

	@Override
	public String getName() {
		return minimalProfile.getUsername();
	}
}
