package com.mymoney.service;

import com.mymoney.model.UserModel;
import com.mymoney.model.UserReqModel;

public interface UserService {
	
	UserModel register(UserReqModel userReqModel);

}
