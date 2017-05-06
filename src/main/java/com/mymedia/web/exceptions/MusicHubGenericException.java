package com.mymedia.web.exceptions;

import org.springframework.http.HttpStatus;

public class MusicHubGenericException extends RuntimeException {

	HttpStatus code;

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public MusicHubGenericException(String message, HttpStatus code) {
		super(message);
		this.code = code;
	}

}
