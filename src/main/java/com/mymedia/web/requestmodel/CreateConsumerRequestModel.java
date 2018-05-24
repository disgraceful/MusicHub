package com.mymedia.web.requestmodel;

public class CreateConsumerRequestModel extends CreateUserRequestModel{
	private String imgPath;

	public CreateConsumerRequestModel(String email, String username, String password, String confirmPassword,String imgPath) {
		super(email,username,password,confirmPassword);
		this.imgPath = imgPath;
	}
	public CreateConsumerRequestModel() {
		super();
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	
}
