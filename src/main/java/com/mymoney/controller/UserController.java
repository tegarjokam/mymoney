package com.mymoney.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping("register")
	public UserModel create(@RequestBody @Valid UserReqModel userReqModel) {
		UserModel userModel = new UserModel();
		return userService.register(userReqModel);
	}

}
