package com.connector.rest.bo;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

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
@Entity
public class UserBO {

	@MongoId
	private String userId;

	@NotNull(message = "First name could not be null")
	@Size(min = 2, message = "First Name Should have at least two characters")
	private String firstName;
	private String middleName;
	@NotNull(message = "Last name could not be null")
	private String lastName;
	private long mobileNumber;
	@NotEmpty
	@Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
	private String emailId;
	@NotEmpty
	private String gender;
	private Date dob;
	private String status;
	private String currentAddress;
	private String homeAddress;
	@NotEmpty
	private String password;
	private String profilePicPath;
	private String coverPicPath;
	private LocalDateTime userCreationDateAndTime;
}
