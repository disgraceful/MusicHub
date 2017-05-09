package com.mymedia.web.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.mvc.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	private static final Logger LOG = LogManager.getLogger(TokenService.class);
	@Autowired
	private UserService userService;

	public String createJWT(User user) {
		try {
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
			byte[] key = "secret".getBytes("UTF-8");

			JwtBuilder builder = Jwts.builder().claim("username", user.getUsername())
					.claim("password", user.getPassword()).claim("role", user.getRole().getName())
					.signWith(signatureAlgorithm, key);

			return builder.compact();
		} catch (Exception e) {
			throw new MusicHubGenericException("Failed to create token", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public User parseJWT(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey("secret".getBytes("UTF-8")).parseClaimsJws(jwt);
			User u = userService.getByUsername(claims.getBody().get("username").toString());
			return u;
		} catch (Exception e) {
			throw new MusicHubGenericException("Failed to parse token", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public boolean validateJWT(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey("secret".getBytes("UTF-8")).parseClaimsJws(jwt);
			User u = userService.getByUsername(claims.getBody().get("username").toString());
			return u == null ? false : true;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to parse token", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
