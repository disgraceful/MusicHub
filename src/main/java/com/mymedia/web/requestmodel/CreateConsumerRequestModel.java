package com.mymedia.web.requestmodel;

public class CreateConsumerRequestModel extends CreateUserRequestModel{


	public CreateConsumerRequestModel(String email, String username, String password, String confirmPassword) {
		super(email,username,password,confirmPassword);
	
	}

	public CreateConsumerRequestModel() {
		
	}
	
	
	
}
