package com.mymoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymoney.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
	
	Wallet findByUserId(String userId);
	
}
