package com.techouts.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class NodeList {
	public Map<String, Integer> nodelist = new HashMap<String, Integer>();

	@Autowired
	private Environment env;
	
	public Map<String, Integer> getAllNodes() {
		nodelist.put("http://"+env.getProperty("tomcat.url1")+"/", 200);
		nodelist.put("http://"+env.getProperty("tomcat.url2")+"/", 200);

		return nodelist;
	}

	public int getStatus(String url) throws IOException {
		int code = 200;
		try {
			URL siteURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(3000);
			connection.connect();

			code = connection.getResponseCode();
			if (code == 200)
				return code;
			else
				return code;

		}
		catch (Exception e) {
			code = 000;

		}
		return code;
	}
}