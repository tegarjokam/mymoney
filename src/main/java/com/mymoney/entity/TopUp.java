package com.mymoney.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "topup")
@Where(clause = "status = 'ACTIVE'")
public class TopUp extends Persistence {

	private static final long serialVersionUID = -3955441543801004222L;

	@Column
	@NotNull
	private BigInteger creditWallet;
}
