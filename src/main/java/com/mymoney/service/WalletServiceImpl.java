package com.mymoney.service;

import java.math.BigInteger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymoney.entity.User;
import com.mymoney.entity.Wallet;
import com.mymoney.exception.ServiceException;
import com.mymoney.model.WalletModel;
import com.mymoney.repository.UserRepository;
import com.mymoney.repository.WalletRepository;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public WalletModel create(String userId) {
		//cek user id, apakah ada dalam database ?
		User userById = userRepository.findById(userId);
		if (userById == null)
			throw new ServiceException(404, "user id is not found.");
		//kalau ada buat wallet berdasarkan userId tersebut
		
		Wallet walletByUserId = walletRepository.findByUserId(userById.getId());
		if (walletByUserId != null)
			throw new ServiceException(409, "Wallet is already exists.");
		
		Wallet wallet = new Wallet();
		wallet.setUser(userById);
		wallet.setBalance(BigInteger.valueOf(0));
		wallet = walletRepository.save(wallet);
		
		WalletModel walletModel = new WalletModel();
		BeanUtils.copyProperties(wallet, walletModel);
		
		return walletModel;
	}

}
