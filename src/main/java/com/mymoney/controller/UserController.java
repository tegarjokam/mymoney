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

import com.mymoney.model.UserModel;
import com.mymoney.model.UserReqModel;
import com.mymoney.service.UserService;

@RestController
@RequestMapping("/api/rest/v1/user/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * Create user account (admin, merchant, and customer)
	 * 
	 * @param userReqModel
	 * @param result
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@PostMapping("register")
	public UserModel create(@RequestBody @Valid UserReqModel userReqModel,
			BindingResult result,
			HttpServletResponse response) throws IOException {
		UserModel userModel = new UserModel();
		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return userModel;
		} else {
			return userService.register(userReqModel);
		}
	}
}
