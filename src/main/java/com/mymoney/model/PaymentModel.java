package com.mymoney.model;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentModel extends PersistenceModel{

	private BigInteger creditWallet;
	
	private BigInteger debitWallet;
}
