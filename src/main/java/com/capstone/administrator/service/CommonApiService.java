package com.capstone.administrator.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

@Service
public class CommonApiService {


	public CloseableHttpResponse sendPost(String url, String jsonString) {
		try {

			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			StringEntity entity = new StringEntity(jsonString);
			httpPost.setEntity(entity);
			httpPost.setHeader("Content-type", "application/json");

			CloseableHttpResponse response = client.execute(httpPost);

			client.close();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			System.exit(1);
		}
		return null;
	}

	public CloseableHttpResponse get(String url, Map<String, String> params) {

		HttpGet httpGet = new HttpGet(url);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			response = client.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

	public String sendGET(String api) throws IOException {
		URL obj = new URL(api);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			return response.toString();
		} else {
			System.out.println("GET request not worked");
		}
		return "";
	}

}
