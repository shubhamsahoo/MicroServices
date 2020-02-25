package com.techouts.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techouts.service.UserValidateService;

@Controller
//@RequestMapping("/Authenticate")
public class AuthenticateController {

	@Autowired
	private UserValidateService service;

	@GetMapping("/user")
	public String login(final HttpServletRequest req, final Map<String, Object> map) {
		String accessToken = service.login(req.getParameter("username"), req.getParameter("password"));
		HttpSession session = req.getSession(true);
		session.setAttribute("accessToken", accessToken);
		return "welcome";
	}

	@GetMapping("/logoutuser")
	public String logout(HttpServletRequest req) {
		service.logout((String) req.getSession(false).getAttribute("accessToken"));
		req.getSession(false).removeAttribute("accessToken");
		return "logout";
	}

	@GetMapping("/Authenticate")
	@ResponseBody
	public Map<String, String> authenticate(final HttpServletRequest req, final Map<String, Object> map) {
		Map<String, String> response = new HashMap<>();
		login(req, map);
		response.put("accessToken", (String) req.getSession(false).getAttribute("accessToken"));
		return response;
	}
}
