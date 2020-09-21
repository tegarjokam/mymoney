package com.mymoney.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="transaction")
@Where(clause = "status = 'ACTIVE'")
public class Transaction extends Persistence {

	private static final long serialVersionUID = -5684302019457576082L;
	
	public enum Type {
		TOPUP, PAYMENT
	}
	
	@Column(length = 255)
	private String referenceId;
	
	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	private User user;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column(length = 150)
	private String description;
	
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@JoinColumn(name = "topup_id")
	@OneToOne(targetEntity = TopUp.class)
	private TopUp topupId;
	
	@JoinColumn(name = "payment_id")
	@OneToOne(targetEntity = Payment.class)
	private Payment paymentId;
}
