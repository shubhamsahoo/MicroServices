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
	UserRepository customerRepository;

	@Override
	public String login(String username, String password) {
		/*
		 * Optional<UserBO> user =
		 * customerRepository.findByUserNameAndPassword(username, password); if
		 * (user.isPresent()) { String token = UUID.randomUUID().toString(); UserBO
		 * custom = user.get(); custom.setAccessToken(token);
		 * customerRepository.save(custom); return token; }
		 */
		long count = customerRepository.countByUserNameAndPassword(username, password);
		return count + "";
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
