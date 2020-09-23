package com.mymoney.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentReqModel {

	@NotNull
	private Long creditWallet;
	
	@NotNull
	private Long debitWallet;
	
	@NotBlank
	private String referenceId;
	
	@NotBlank
	private String userId;
	
	@NotBlank
	private String merchantId;
	
	@NotBlank
	private String typeTransaction;
	
	private String description;
}
