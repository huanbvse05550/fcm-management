package com.capstone.administrator.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.capstone.administrator.entity.UserDto;

@Service
public class NotificationService {

	@Autowired
	private CommonApiService commonApiService;

	@Autowired
	private Environment env;

	private String getDomain() {
		String path = env.getProperty("api.domain");
		return path;
	}

	private String getuserApi() {
		String path = env.getProperty("api.user");
		return path;
	}

	private String getNotificationSendApi() {
		String path = env.getProperty("api.notification.send");
		return path;
	}

	private String getResendNotificationApi() {
		String path = env.getProperty("api.notification.resend");
		return path;
	}

	public List<UserDto> getAllUser() {
		return null;
	}

	public List<UserDto> getUserByRole(String role) {
		try {
			String userApi = getDomain() + getuserApi();
			String jsonData = commonApiService.sendGET(userApi);
			JSONArray jsonArray = new JSONArray(jsonData);
			List<UserDto> listUser = new ArrayList<>();
			for (Object x : jsonArray) {
				JSONObject obj = new JSONObject(x.toString());
				String userId = obj.getString("userId");
				String fullName = obj.getString("fullName");
				UserDto user = new UserDto();
				user.setUserId(userId);
				user.setFullName(fullName);
				listUser.add(user);
			}
			return listUser;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int sendNotification(String target, String title, String body, String imageurl) {
		String notificationApi = getDomain() + getNotificationSendApi();
		JSONObject obj = new JSONObject();
		JSONArray targetArr = new JSONArray(target);
		obj.put("target", targetArr);
		obj.put("title", title);
		obj.put("body", body);
		commonApiService.sendPost(notificationApi, obj.toString());
		return -1;
	}

	public int resendNotification(String messageId) {
		String resendApi = getDomain()+getResendNotificationApi();
		JSONObject obj = new JSONObject();
		obj.put("messageId", messageId);
		commonApiService.sendPost(resendApi, obj.toString());
		return -1;
	}
}
