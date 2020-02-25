package com.techouts.service.Impl;

import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techouts.bo.UserBO;
import com.techouts.security.UserRepository;
import com.techouts.service.UserValidateService;

@Service
public class UserValidateServiceImpl implements UserValidateService {
	@Autowired
	UserRepository userRepository;

	@Override
	public String login(String username, String password) {

		long count = userRepository.countByUserNameAndPassword(username, password);
		if (count > 0) {
			String token = UUID.randomUUID().toString();
			UserBO custom = userRepository.getUserByUserNameAndPassword(username, password);
			custom.setAccessToken(token);
			custom.setLoginTime(new java.sql.Date(new java.util.Date().getTime()));
			userRepository.save(custom);
			return token;
		}
		return StringUtils.EMPTY;
	}

	@Override
	public String logout(String accessToken) {
		UserBO user = userRepository.getUserByAccessToken(accessToken);
		user.setAccessToken("");
		user.setLoginTime(null);
		userRepository.save(user);
		return StringUtils.EMPTY;
	}

	@Override
	public Optional<UserBO> findByToken(String token) {
		/*
		 * Optional<UserBO> customer = customerRepository.findByAccessToken(token); if
		 * (customer.isPresent()) { UserBO customer1 = customer.get(); UserBO user = new
		 * UserBO(); return Optional.of(user); }
		 */
		return Optional.empty();
	}
}
