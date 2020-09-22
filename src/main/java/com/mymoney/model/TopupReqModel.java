package com.mymoney.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TopupReqModel {
	
	@NotBlank
	private String referenceId;
	
	@NotNull
	private Long creditWallet;
	
	@NotBlank
	private String userId;
	
	@NotBlank
	private String typeTransaction;
	
	@NotBlank
	private String description;
}
