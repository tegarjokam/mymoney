package com.mymoney.service;

import com.mymoney.model.TopupReqModel;
import com.mymoney.model.TransactionModel;

public interface TransactionService {
	
	TransactionModel topup(TopupReqModel topUpReqModel);

}
