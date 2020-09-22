package com.mymoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymoney.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	Transaction findByReferenceId(String referenceId);
}
