package com.connector.rest.bo;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Document(collection = "users")
public class UserBO {

	@Id
	private String userId;

	private String firstName;
	@Column(name = "middle_name", nullable = true)
	private String middleName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "mob_no", nullable = true)
	private long mobileNumber;
	@Column(name = "email_id", nullable = true)
	private String emailId;
	@Column(name = "gender", nullable = false)
	private String gender;
	@Column(name = "dob", nullable = false)
	private Date dob;
	@Column(name = "status", nullable = true)
	private String status;
	@Column(name = "current_address", nullable = true)
	private String currentAddress;
	@Column(name = "home_address", nullable = true)
	private String homeAddress;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "profile_pic", nullable = true)
	private String profilePicPath;
	@Column(name = "cover_pic", nullable = true)
	private String coverPicPath;
	@Column(name = "joining_date", nullable = false)
	private LocalDateTime userCreationDateAndTime;
}
