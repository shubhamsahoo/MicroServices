package com.connector.rest.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connector.rest.bo.UserBO;
import com.connector.rest.dto.UserDTO;
import com.connector.rest.repository.UserRepository;
import com.connector.rest.service.UserService;

/**
 * 
 * @author Shubham Sahoo
 *
 */
@Service
public final class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserBO createUser(UserDTO dto) {
		UserBO bo = new UserBO();
		// convert DTO to BO
		BeanUtils.copyProperties(dto, bo);
		// convert util date to sql date
		bo.setDob(new java.sql.Date(dto.getDob().getTime()));
		// add the bo to data base
		return userRepository.save(bo);
	}

	@Override
	public boolean isUserPresentByMobileNumberAndPassword(long mobileNumber, String password) {
		return false;
	}

	@Override
	public boolean isUserPresentByEmailAndPassword(String emailAddress, String password) {
		return false;
	}

	@Override
	public UserDTO findUserByEmailAndPassword(String emailAddress, String password) {
		return null;
	}

	@Override
	public UserDTO findUserByMobileNoAndPassword(long mobileNumber, String password) {
		return null;
	}

}
