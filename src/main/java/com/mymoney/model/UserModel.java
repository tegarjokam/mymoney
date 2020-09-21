package com.mymoney.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel extends PersistenceModel {
	
	private String email;
	private String fullName;
	private String phoneNumber;
	private String role;

}
