package com.mymoney.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment")
@Where(clause = "status = 'ACTIVE'")
public class Payment extends Persistence {

	private static final long serialVersionUID = 2362199967352265857L;
	
	private BigInteger creditWallet;
	
	private BigInteger debitWallet;

}
