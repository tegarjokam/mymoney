package com.mymoney.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymoney.model.TopupReqModel;
import com.mymoney.model.TransactionModel;
import com.mymoney.service.TransactionService;

@RestController
@RequestMapping("/api/rest/v1/transaction/")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	/**
	 * TopUp Transaction with user id
	 * 
	 * @param topUpReqModel
	 * @param result
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@PostMapping("topup")
	public TransactionModel topup(@RequestBody @Valid TopupReqModel topUpReqModel,
			BindingResult result,
			HttpServletResponse response) throws IOException {
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return null;
		} else {
			return transactionService.topup(topUpReqModel);
		}
	}

}
