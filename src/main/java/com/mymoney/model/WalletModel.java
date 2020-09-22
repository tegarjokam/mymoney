package com.mymoney.model;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WalletModel extends PersistenceModel {

	private String userId;
	private BigInteger balance;
	
}
