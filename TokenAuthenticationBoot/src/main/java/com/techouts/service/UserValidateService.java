package com.techouts.service;

import java.util.Optional;

import com.techouts.bo.UserBO;

public interface UserValidateService {
	public String login(String username, String password);

	public Optional<UserBO> findByToken(String token);
}
