package com.mymedia.web.utils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class GoogleTokenVerifier {
	private static final String CLIENT_ID = "100485869370-cl89djdjsno00e1re6sfl6d4hua9d7p9.apps.googleusercontent.com";
	private static final HttpTransport TRANSPORT = new NetHttpTransport();
	private static final JacksonFactory JSON_FACTORY = new JacksonFactory();

	public static Payload verify(String idTokenString) throws GeneralSecurityException, IOException {
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(TRANSPORT, JSON_FACTORY)
				.setAudience(Collections.singletonList(CLIENT_ID)).build();
		System.out.println(idTokenString);
		GoogleIdToken idToken = verifier.verify(idTokenString);
		if (idToken != null) {
			Payload payload = idToken.getPayload();
			return payload;

		} else {
			System.out.println("Invalid ID token.");
			return null;
		}
	}
}
