package com.nt.controller;

import java.util.LinkedHashMap;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	 @SuppressWarnings("unchecked")
	@RequestMapping("/user")
	 @ResponseBody
	    public String user(OAuth2Authentication authentication) {
	        LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();
	        return "<h1 align='center'>Welcome "+(String)properties.get("name")+"</h1>";
	    }
}
