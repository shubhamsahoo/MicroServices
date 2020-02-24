package com.techouts.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techouts.service.UserValidateService;

@Controller
//@RequestMapping("/Authenticate")
public class AuthenticateController {

	@Autowired
	private UserValidateService service;

	@GetMapping("/user")
	public String login(final HttpServletRequest req, final Map<String, Object> map) {
		service.login(req.getParameter("username"), req.getParameter("password"));
		return "welcome";
	}

	@RequestMapping("/logoutuser")
	public String logout() {
		return "logout";
	}
}
