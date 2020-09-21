package com.mymoney.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user")
@Where(clause = "status = 'ACTIVE'")
public class User extends Persistence {
	
	private static final long serialVersionUID = 623186140292910224L;

	public enum Role {
		ADMIN, MERCHANT, CUSTOMER
	}
	
	@Size(min = 5, max = 50)
	@Column(length = 50, unique = true) 
	private String email;
	
	@JsonIgnore
	@NotNull
	@Size(min = 8)
	@Column
	private String password;
	
	@NotNull
	private String fullName;
	
	@NotNull
	@Column(length = 25, unique = true)
	private String phoneNumber;
	
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private Role role;

}
