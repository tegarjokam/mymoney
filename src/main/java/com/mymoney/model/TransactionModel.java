package com.mymoney.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mymoney.entity.Payment;
import com.mymoney.entity.TopUp;
import com.mymoney.entity.User;
import com.mymoney.entity.Transaction.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionModel extends PersistenceModel {
	
	private String referenceId;
	
	private User user;

	private Date date;
	
	private String description;
	
	private Type type;

	private TopUp topupId;
	
	private Payment paymentId;
	
	private TopUpModel topup;
	
	private PaymentModel payment;

}
