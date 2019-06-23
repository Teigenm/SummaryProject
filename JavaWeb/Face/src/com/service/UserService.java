package com.service;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;

public class UserService {
    private static final String APP_ID = "XXXXXXX";
    private static final String API_KEY = "XXXXXXXXXXXXXXXXXXXXX";
    private static final String SECRET_KEY = "XXXXXXXXXXXXXXXXXXXXX";
	private static AipFace client = null;
	private static final String groupId = "group1";
	private static UserService userService = new UserService();
	static {
		init();
	}
	public static UserService getInstance() {
		return userService;
	}
	public static void init() {
		client = new AipFace(APP_ID,API_KEY,SECRET_KEY);
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
	}
	public String faceLogin(String username,String passBase64) {
		HashMap<String,String> options = new HashMap<String,String>();
		options.put("user_id", username);
		JSONObject res = client.search(passBase64,"BASE64",groupId,options);
		return res.toString(2);
	}
	
	public String faceRegister(String username,String passBase64) {
		HashMap<String,String> options = new HashMap<String,String>();
		options.put("user_info", "user's info");
		JSONObject res = client.addUser(passBase64, "BASE64", groupId, username, options);
		return res.toString(2);
	}
	
}
