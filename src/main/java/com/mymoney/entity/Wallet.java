package com.mymoney.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wallet")
@Where(clause = "status = 'ACTIVE'")
public class Wallet extends Persistence {

	private static final long serialVersionUID = 1322108369091031669L;
	
	@Column
	@NotNull
	private BigInteger balance;
	
	@JoinColumn(name = "user_id")
	@OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	private User user;
}
