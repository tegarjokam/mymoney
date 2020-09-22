package com.mymoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymoney.entity.TopUp;

@Repository
public interface TopUpRepository extends JpaRepository<TopUp, Integer>{

}
