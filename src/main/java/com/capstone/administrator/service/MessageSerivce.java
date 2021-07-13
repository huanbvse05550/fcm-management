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

import com.capstone.administrator.entity.MessageDto;

@Service
public class MessageSerivce {

	@Autowired
	private Environment env;

	private String getDomain() {
		String path = env.getProperty("api.domain");
		return path;
	}

	private String getMessageApi() {
		String path = env.getProperty("api.message");
		return path;
	}

	@Autowired
	private CommonApiService commonApiService;

	public List<MessageDto> getMessages() {
		try {
			String api = getDomain() + getMessageApi();
			String rs = commonApiService.sendGET(api);
			List<MessageDto> listMessage = new ArrayList<>();
			JSONArray arr = new JSONArray(rs);
			int count = 1;
			for (Object x : arr) {
				JSONObject obj = new JSONObject(x.toString());
				MessageDto message = new MessageDto();
				message.setId(obj.get("id") == null ? 0 : obj.getInt("id"));
				message.setTitle(obj.get("title") == null ? "" : obj.getString("title"));
				message.setBody(obj.get("body") == null ? "" : obj.getString("body"));
				if (obj.get("imageUrl") != "null") {
					message.setImageUrl(obj.get("imageUrl") == null ? "" : obj.get("imageUrl") + "");
				}
				message.setDate(obj.get("date") == null ? "" : obj.getString("date"));
				message.setNo(count);
				count++;
				listMessage.add(message);
			}
			return listMessage;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
