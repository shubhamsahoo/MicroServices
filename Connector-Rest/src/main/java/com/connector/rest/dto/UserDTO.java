package com.connector.rest.dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class UserDTO {

	private String userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private long mobileNumber;
	private String emailId;
	private String gender;
	private Date dob;
	private String status;
	private String currentAddress;
	private String homeAddress;
	private String password;
	private String profilePicPath;
	private String coverPicPath;
	private LocalDateTime userCreationDateAndTime;

}
