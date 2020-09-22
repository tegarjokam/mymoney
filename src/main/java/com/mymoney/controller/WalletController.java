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

import com.mymoney.model.WalletModel;
import com.mymoney.service.WalletService;
import com.mymoney.model.WalletCreateModel;

@RestController
@RequestMapping("/api/rest/v1/wallet/")
public class WalletController {
	
	@Autowired
	private WalletService walletService;
	
	/**
	 * Create wallet based on user id
	 * 
	 * @param walletCreateModel
	 * @param result
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@PostMapping("create")
	public WalletModel create(@RequestBody @Valid WalletCreateModel walletCreateModel,
			BindingResult result,
			HttpServletResponse response) throws IOException {
		WalletModel walletModel = new WalletModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return walletModel;
		} else {
			return walletService.create(walletCreateModel.getUserId());
		}
	}

}
