package com.connector.rest.service;

import com.connector.rest.bo.UserBO;
import com.connector.rest.dto.UserDTO;

/**
 * 
 * @author Shubham Sahoo 
 * This service will used to perform some operations on a User.
 *
 */

public interface UserService {
	/**
	 * This method will be used to create an user. In the Parameter you have to pass
	 * the User data contain user object.
	 * 
	 * @param dto
	 * @return An User object, that stored in DB.
	 */
	UserBO createUser(final UserDTO dto);

	/**
	 * This method will be used to know whether user is present in data base or not.
	 * Based on the given mobile number and password.
	 * 
	 * @param mobileNumber
	 * @param password
	 * @return true if user is present in DB, else false.
	 */
	boolean isUserPresentByMobileNumberAndPassword(final long mobileNumber, final String password);

	/**
	 * This method will be used to know whether user is present in data base or not.
	 * Based on the given email address and password.
	 * 
	 * @param emailAddress
	 * @param password
	 * @return true if user is present in DB, else false.
	 */
	boolean isUserPresentByEmailAndPassword(final String emailAddress, String password);

	/**
	 * This method will be used to fetch/get the user information/data based on the
	 * given Email and password.
	 * 
	 * @param emailAddress
	 * @param password
	 * @return user object.
	 */
	UserDTO findUserByEmailAndPassword(final String emailAddress, String password);

	/**
	 * This method will be used to fetch/get the user information/data based on the
	 * given mobile number and password.
	 * 
	 * @param mobileNumber
	 * @param password
	 * @return user object.
	 */
	UserDTO findUserByMobileNoAndPassword(final long mobileNumber, final String password);
}
