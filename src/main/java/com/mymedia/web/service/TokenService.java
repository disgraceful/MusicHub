package com.mymedia.web.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.mvc.model.User;
import com.mymedia.web.requestmodel.GoogleLoginReqModel;
import com.mymedia.web.utils.GoogleTokenVerifier;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	private static final Logger LOG = LogManager.getLogger(TokenService.class);
	private static final String JWT_KEY = "secret";
	private static final String JWT_ENCODE = "UTF-8";
	private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

	static {
		
	}

	@Autowired
	private UserService userService;

	@Autowired
	private ConsumerService consumerService;

	public boolean verifyToken(String token) {
		boolean a = verifyGoogleToken(token);
		boolean b =verifyJWT(token);
		LOG.info("google " + a + " jwt " + b);
		return  a|| b;
	}

	public User retrieveUser(String token) {
		User user = getUserFromGoogleToken(token);
		if (user == null) {
			user = parseJWT(token);
		}
		if (user == null) {
			LOG.info("no user has been found");
		}
		return user;
	}

	public String createJWT(User user) {
		try {
			byte[] key = JWT_KEY.getBytes(JWT_ENCODE);
			JwtBuilder builder = Jwts.builder().claim("username", user.getUsername())
					.claim("password", user.getPassword()).claim("role", user.getRole().getName())
					.signWith(SIGNATURE_ALGORITHM, key);

			return builder.compact();
		} catch (Exception e) {
			throw new MusicHubGenericException("Failed to create token", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public User parseJWT(String jwt) {
		try {
			byte[] key = JWT_KEY.getBytes(JWT_ENCODE);
			Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
			User u = userService.getByUsername(claims.getBody().get("username").toString());
			return u;
		} catch (Exception e) {
			throw new MusicHubGenericException("Failed to parse token", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public boolean verifyJWT(String jwt) {
		return parseJWT(jwt) != null;
	}

	public boolean verifyGoogleToken(String idTokenString) {
		return getUserFromGoogleToken(idTokenString) != null;
	}

	public User getUserFromGoogleToken(String idTokenString) {
		try {
			Payload payload = GoogleTokenVerifier.verify(idTokenString);
			User user;
			if (userService.userExists(payload.getSubject())) {
				user = userService.getByGoogleId(payload.getSubject());
			} else {
				GoogleLoginReqModel model = new GoogleLoginReqModel(payload.getSubject(), (String) payload.get("name"),
						payload.getEmail(), (String) payload.get("picture"));
				user = userService
						.userEntityToUser(userService.getUser(consumerService.createConsumer(model).getUserId()));
			}
			LOG.info(user.getEmail());
			return user;

		} catch (GeneralSecurityException | IOException e) {
			throw new MusicHubGenericException("Failed to parse token " + idTokenString, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
