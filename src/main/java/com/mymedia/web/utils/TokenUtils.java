package com.mymedia.web.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtils {

	private static final Logger LOG = LogManager.getLogger(TokenUtils.class);

	private TokenUtils() {

	}

	public static String createJWT(String userName, String password) {
		try {
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
			byte[] key = "secret".getBytes("UTF-8");

			long nowMillis = System.currentTimeMillis();
			Date now = new Date(nowMillis);

			JwtBuilder builder = Jwts.builder().claim("username", userName).claim("password", password)
					.claim("date", now).signWith(signatureAlgorithm, key);

			return builder.compact();
		} catch (UnsupportedEncodingException e) {
			LOG.error("Error while building a Token Key Encoder", e);
		}

		return "";
	}

	public static void validateJWT(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey("secret".getBytes("UTF-8")).parseClaimsJws(jwt);

			LOG.info("UserName : " + claims.getBody().get("username"));
			LOG.info("Password : " + claims.getBody().get("password"));
			LOG.info("JWT Send Date : " + claims.getBody().get("date"));
		} catch (Exception e) {
			LOG.error("Error while parsing a JWT", e);
		}
	}
}
