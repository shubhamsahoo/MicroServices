package com.nt.controller;

import java.util.LinkedHashMap;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {

	/*@RequestMapping("/user")
	@ResponseBody
	public String display() {
		return "<h1 style='color:red;text-align:center'>Home Page</h1>";
	}*/

	@SuppressWarnings("unchecked")
	@RequestMapping("/user")
	@ResponseBody
	public String user(OAuth2Authentication authentication) {
		LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) authentication
				.getUserAuthentication().getDetails();
		System.out.println(properties);
		return "<h1 align='center'>Welcome " + (String) properties.get("email") + "</h1>"
				+ "<h1 align='center'>Welcome " + (String) properties.get("given_name") + "</h1>"
				+ "<h1 align='center'>Welcome " + (String) properties.get("family_name") + "</h1>"
				+ "<img src="+properties.get("picture")+">";
	}

}
