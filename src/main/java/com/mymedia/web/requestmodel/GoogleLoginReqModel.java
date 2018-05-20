package com.mymedia.web.requestmodel;

public class GoogleLoginReqModel {

	private String id;
	private String username;
	private String email;
	private String avatarPath;

	public GoogleLoginReqModel(String id, String username, String email, String avatarPath) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.avatarPath = avatarPath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

}
