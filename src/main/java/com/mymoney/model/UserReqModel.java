package com.mymoney.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mymoney.util.FieldValueMatch;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldValueMatch.List({
	@FieldValueMatch(
			field = "password",
			fieldMatch = "verifyPassword",
			message = "password must match"
	)
})
public class UserReqModel {
	
	@NotBlank
	@Email(message = "email invalid")
	private String email;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String verifyPassword;
	
	@NotBlank
	private String fullName;

	@NotBlank
	@Pattern(regexp = "(^[0-9]+$|^$)", message = "number only")
	private String phoneNumber;
	
	@NotBlank
	private String role;

}
