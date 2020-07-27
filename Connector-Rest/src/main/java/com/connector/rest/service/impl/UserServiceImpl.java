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
		// add the bo to data base
		return userRepository.save(bo);
	}

	@Override
	public boolean isUserPresentByMobileNumberAndPassword(long mobileNumber, String password) {
		int result = userRepository.countByMobileNumberAndPassword(mobileNumber, password);
		return result > 0;
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
		UserDTO dto = new UserDTO();
		UserBO bo = null;
		int result = userRepository.countByMobileNumberAndPassword(mobileNumber, password);
		if (result > 0) {
			bo = userRepository.findByMobileNumberAndPassword(mobileNumber, password);
			BeanUtils.copyProperties(bo, dto);
			return dto;
		}
		return null;
	}

}
