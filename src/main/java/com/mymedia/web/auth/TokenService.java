package com.mymedia.web.auth;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymedia.web.mvc.model.User;
import com.mymedia.web.service.UserService;

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

			long nowMillis = System.currentTimeMillis();
			Date now = new Date(nowMillis);

			JwtBuilder builder = Jwts.builder().claim("username", user.getUsername())
					.claim("password", user.getPassword()).claim("role", user.getRole().getName()).claim("date", now)
					.signWith(signatureAlgorithm, key);

			return builder.compact();
		} catch (UnsupportedEncodingException e) {
			LOG.error("Error while building a Token Key Encoder", e);
		}

		return "";
	}

	public User validateJWT(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey("secret".getBytes("UTF-8")).parseClaimsJws(jwt);
			LOG.info("UserName : " + claims.getBody().get("username"));
			LOG.info("Password : " + claims.getBody().get("password"));
			LOG.info("JWT Send Date : " + claims.getBody().get("date"));
			return userService.getByUsername(claims.getBody().get("username").toString());
		} catch (Exception e) {
			LOG.error("Error while parsing a JWT", e);
			return null;
		}

	}
}
