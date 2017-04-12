package com.mymedia.web.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthFilter implements Filter {
	private static final Log LOG = LogFactory.getLog(JwtAuthFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		String authorization = servletRequest.getHeader("Authorization");
		if (authorization != null) {
			JwtAuthToken token = new JwtAuthToken(authorization.replaceAll("Bearer ", ""));
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}
