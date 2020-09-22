package com.mymoney.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import com.mymoney.entity.User;
import com.mymoney.entity.User.Role;
import com.mymoney.model.UserModel;
import com.mymoney.model.UserReqModel;
import com.mymoney.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserModel register(UserReqModel userReqModel) {
		if(userReqModel.getRole().equalsIgnoreCase("MERCHANT")) {
			User userByEmail = userRepository.findByEmail(userReqModel.getEmail());
			if (userByEmail != null && userByEmail.getEmail() != null) 
				throw new HttpServerErrorException(HttpStatus.CONFLICT, "Email already exists.");

			User userByPhone = userRepository.findByPhoneNumber(userReqModel.getPhoneNumber());
			if (userByPhone != null && userByPhone.getPhoneNumber() != null)
				throw new HttpServerErrorException(HttpStatus.CONFLICT, "Phone Number already exists.");
			
			User newUser = new User();
			newUser.setEmail(userReqModel.getEmail());
			newUser.setPassword(userReqModel.getPassword());
			newUser.setFullName(userReqModel.getFullName());
			newUser.setPhoneNumber(userReqModel.getPhoneNumber());
			newUser.setRole(Role.ROLE_MERCHANT);
			newUser = userRepository.save(newUser);
			
			UserModel userModel = new UserModel();
			BeanUtils.copyProperties(newUser, userModel);
			
			return userModel;
		} else if (userReqModel.getRole().equalsIgnoreCase("CUSTOMER")) {
			System.out.println("2");
			User userByEmail = userRepository.findByEmail(userReqModel.getEmail());
			if (userByEmail != null && userByEmail.getEmail() != null) 
				throw new HttpServerErrorException(HttpStatus.CONFLICT, "Email already exists.");

			User userByPhone = userRepository.findByPhoneNumber(userReqModel.getPhoneNumber());
			if (userByPhone != null && userByPhone.getPhoneNumber() != null)
				throw new HttpServerErrorException(HttpStatus.CONFLICT, "Phone Number already exists.");
			
			User newUser = new User();
			newUser.setEmail(userReqModel.getEmail());
			newUser.setPassword(userReqModel.getPassword());
			newUser.setFullName(userReqModel.getFullName());
			newUser.setPhoneNumber(userReqModel.getPhoneNumber());
			newUser.setRole(Role.ROLE_CUSTOMER);
			newUser = userRepository.save(newUser);
			
			UserModel userModel = new UserModel();
			BeanUtils.copyProperties(newUser, userModel);
			
			return userModel;
		} else if (userReqModel.getRole().equalsIgnoreCase("ADMIN")) {
			System.out.println("3");
			User userByEmail = userRepository.findByEmail(userReqModel.getEmail());
			if (userByEmail != null && userByEmail.getEmail() != null) 
				throw new HttpServerErrorException(HttpStatus.CONFLICT, "Email already exists.");

			User userByPhone = userRepository.findByPhoneNumber(userReqModel.getPhoneNumber());
			if (userByPhone != null && userByPhone.getPhoneNumber() != null)
				throw new HttpServerErrorException(HttpStatus.CONFLICT, "Phone Number already exists.");
			
			User newUser = new User();
			newUser.setEmail(userReqModel.getEmail());
			newUser.setPassword(userReqModel.getPassword());
			newUser.setFullName(userReqModel.getFullName());
			newUser.setPhoneNumber(userReqModel.getPhoneNumber());
			newUser.setRole(Role.ROLE_ADMIN);
			newUser = userRepository.save(newUser);
			
			UserModel userModel = new UserModel();
			BeanUtils.copyProperties(newUser, userModel);
			
			return userModel;
		} else {
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Your input doesn't recognize");
		}
	}

}
