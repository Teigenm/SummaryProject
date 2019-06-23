package com.bean;

public class User {
	private String userId;
	private String facepass;
	
	public User() {
		super();
	}

	public User(String userId, String facepass) {
		super();
		this.userId = userId;
		this.facepass = facepass;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFacepass() {
		return facepass;
	}

	public void setFacepass(String facepass) {
		this.facepass = facepass;
	}
	
	
	
	
}
