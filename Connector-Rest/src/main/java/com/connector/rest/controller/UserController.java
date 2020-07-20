package com.connector.rest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.connector.rest.bo.UserBO;
import com.connector.rest.dto.UserDTO;
import com.connector.rest.service.UserService;
import com.connector.rest.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("${server.mapping.user-controller}")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "${server.mapping.create-user}", headers = { HttpHeaders.CONTENT_TYPE + "="
			+ MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> createUser(@RequestBody(required = false) Map<String, Object> requestParam) {
		Map<String, Object> response = new HashMap<>();
		String strDob = (String) requestParam.get("dob");
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		UserDTO user = new UserDTO();
		try {
			user.setFirstName((String) requestParam.get("firstName"));
			user.setMiddleName((String) requestParam.get("middleName"));
			user.setLastName((String) requestParam.get("lastName"));
			user.setDob(formatDate.parse(strDob));
			user.setMobileNumber(Long.parseLong((String) requestParam.get("mobileNumber")));
			user.setEmailId((String) requestParam.get("emailId"));
			user.setGender((String) requestParam.get("gender"));
			user.setStatus((String) requestParam.get("status"));
			user.setCurrentAddress((String) requestParam.get("currentAddress"));
			user.setHomeAddress((String) requestParam.get("homeAddress"));
			user.setPassword((String) requestParam.get("password"));
			user.setProfilePicPath((String) requestParam.get("profilePicPath"));
			user.setCoverPicPath((String) requestParam.get("coverPicPath"));
			user.setUserCreationDateAndTime(LocalDateTime.now());
			UserBO bo = userService.createUser(user);
			response.put(Constants.STATUS_CODE, 201);
			response.put(Constants.STATUS_MESSAGE, Constants.STATUS_CREATED);
			response.put(Constants.ADDED_OBJECT, bo);
		} catch (ParseException e) {
			log.error("Error While parsing date:", e);
			response.put(Constants.STATUS_CODE, 500);
			response.put(Constants.STATUS_MESSAGE, Constants.ERROR_MESSAGE);
		}
		return response;
	}

}
