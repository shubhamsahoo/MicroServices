package com.connector.rest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Email;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.connector.rest.bo.UserBO;
import com.connector.rest.dto.UserDTO;
import com.connector.rest.service.UserService;
import com.connector.rest.util.Constants;
import com.connector.rest.util.PasswordEncoderUtil;

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
			user.setPassword(PasswordEncoderUtil.encodeToSha256((String) requestParam.get("password")));
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

	@GetMapping(value = "${server.mapping.fetch-user}", headers = { HttpHeaders.CONTENT_TYPE + "="
			+ MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getUserByMobileNoAndPassword(@RequestParam(name = "mobileNumber") String mobileNumber,
			@RequestParam(name = "emailId") String emailId,
			@RequestParam(name = "password", required = true) String password) {
		String localPassword = password.trim();
		Map<String, Object> response = new HashMap<>();
		if (StringUtils.isNotBlank(emailId)) {
			String localEmailId = emailId;
			UserDTO dto = userService.findUserByEmailAndPassword(localEmailId, localPassword);
			if (dto != null) {
				response.put(Constants.STATUS_CODE, 200);
				response.put(Constants.STATUS_MESSAGE, Constants.STATUS_SUCCESS);
				response.put(Constants.RESULT, dto);
				return response;
			}
			response.put(Constants.STATUS_CODE, 404);
			response.put(Constants.STATUS_MESSAGE, Constants.STATUS_NOT_FOUND);
			return response;

		} else if (StringUtils.isNotBlank(mobileNumber)) {
			long localMobileNumber = Long.parseLong(mobileNumber);
			UserDTO dto = userService.findUserByMobileNoAndPassword(localMobileNumber, localPassword);
			if (dto != null) {
				response.put(Constants.STATUS_CODE, 200);
				response.put(Constants.STATUS_MESSAGE, Constants.STATUS_SUCCESS);
				response.put(Constants.RESULT, dto);
				return response;
			}
			response.put(Constants.STATUS_CODE, 404);
			response.put(Constants.STATUS_MESSAGE, Constants.STATUS_NOT_FOUND);
			return response;
		} else {
			response.put(Constants.STATUS_CODE, 404);
			response.put(Constants.STATUS_MESSAGE, Constants.MUST_NOT_EMPTY);
			return response;
		}
	}

	@GetMapping(value = "${server.mapping.validate-email}/{" + Constants.EMAIL_ID + "}")
	@ResponseBody
	public Map<String, Object> verifyEmail(@PathVariable(Constants.EMAIL_ID)@Email String email) {
		return null;
	}
}
