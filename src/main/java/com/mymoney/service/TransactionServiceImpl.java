package com.mymoney.service;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymoney.entity.Payment;
import com.mymoney.entity.TopUp;
import com.mymoney.entity.Transaction;
import com.mymoney.entity.Transaction.Type;
import com.mymoney.entity.User;
import com.mymoney.entity.Wallet;
import com.mymoney.exception.ServiceException;
import com.mymoney.model.PaymentReqModel;
import com.mymoney.model.TopupReqModel;
import com.mymoney.model.TransactionModel;
import com.mymoney.repository.PaymentRepository;
import com.mymoney.repository.TopUpRepository;
import com.mymoney.repository.TransactionRepository;
import com.mymoney.repository.UserRepository;
import com.mymoney.repository.WalletRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private TopUpRepository topUpRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Override
	public TransactionModel topup(TopupReqModel topUpReqModel) {
		//1. find userId, jika tidak ada send error
		User userById = userRepository.findById(topUpReqModel.getUserId());
		if (userById == null) 
			throw new ServiceException(404, "user id not found.");
		
		//2. find reference_id
		Transaction transactionByReferenceId = transactionRepository.findByReferenceId(topUpReqModel.getReferenceId());
		if (transactionByReferenceId != null)
			throw new ServiceException(409, "Transaction failed, because the reference id was found in the database.");
		
		//3. find wallet by user id
		Wallet wallet = walletRepository.findByUserId(userById.getId());
		if (wallet == null)
			throw new ServiceException(404, "wallet not found.");
		BigInteger balance = wallet.getBalance();
		BigInteger newBalance = balance.add(BigInteger.valueOf(topUpReqModel.getCreditWallet()));
		wallet.setBalance(newBalance);
		wallet = walletRepository.save(wallet);
		
		//4. create topup and save on db
		TopUp topUp = new TopUp();
		topUp.setCreditWallet(BigInteger.valueOf(topUpReqModel.getCreditWallet()));
		topUp = topUpRepository.save(topUp);
		
		//5. create transaction and save on db (with var topup & user)
		Transaction transaction = new Transaction();
		transaction.setUser(userById);
		transaction.setReferenceId(topUpReqModel.getReferenceId());
		transaction.setDate(new Date());
		transaction.setDescription(topUpReqModel.getDescription());
		transaction.setType(Type.TOPUP);
		transaction.setTopupId(topUp);
		transaction = transactionRepository.save(transaction);
		
		TransactionModel transactionModel = new TransactionModel();
		BeanUtils.copyProperties(transaction, transactionModel);

		return transactionModel;
	}

	@Override
	public TransactionModel payment(PaymentReqModel paymentReqModel) {
		//1. cek userId apakah ada ?
		User userById = userRepository.findById(paymentReqModel.getUserId());
		if (userById == null) 
			throw new ServiceException(404, "user id not found.");
		//2. cek merchantId apakah ada ?
		User userMerhantById = userRepository.findById(paymentReqModel.getMerchantId());
		if (userMerhantById == null) 
			throw new ServiceException(404, "merchant id not found.");
		//3. cek referenceId apakah ada ?
		Transaction transactionByReferenceId = transactionRepository.findByReferenceId(paymentReqModel.getReferenceId());
		if (transactionByReferenceId != null)
			throw new ServiceException(409, "Transaction failed, because the reference id was found in the database.");
		//4. cek wallet userId&merchantId apakah ada ?
		Wallet walletUserId = walletRepository.findByUserId(userById.getId());
		if (walletUserId == null)
			throw new ServiceException(404, "User wallet not found.");
		Wallet walletMerchantId = walletRepository.findByUserId(userMerhantById.getId());
		if (walletMerchantId == null)
			throw new ServiceException(404, "Merchant wallet not found.");
		//5. cek jumlah wallet userId apakah mencukupi dengan jumlah payment yg diminta
		if (walletUserId.getBalance().compareTo(BigInteger.valueOf(paymentReqModel.getDebitWallet())) == -1)
			throw new ServiceException(400, "Your balance is not enough");
		//6. kurangi jumlah balance userId wallet dan tambahkan jumlah tersebut ke merchant wallet
		walletUserId.setBalance(walletUserId.getBalance().subtract(BigInteger.valueOf(paymentReqModel.getDebitWallet())));
		walletUserId = walletRepository.save(walletUserId);
		walletMerchantId.setBalance(walletMerchantId.getBalance().add(BigInteger.valueOf(paymentReqModel.getCreditWallet())));
		walletMerchantId = walletRepository.save(walletMerchantId);
		
		//7. catat dan simpan kedalam transaksi
		Payment payment = new Payment();
		payment.setCreditWallet(BigInteger.valueOf(paymentReqModel.getCreditWallet()));
		payment.setDebitWallet(BigInteger.valueOf(paymentReqModel.getDebitWallet()));
		payment = paymentRepository.save(payment);
		
		Transaction transaction = new Transaction();
		transaction.setUser(userById);
		transaction.setReferenceId(paymentReqModel.getReferenceId());
		transaction.setDate(new Date());
		transaction.setDescription(paymentReqModel.getDescription());
		transaction.setType(Type.PAYMENT);
		transaction.setPaymentId(payment);
		transaction = transactionRepository.save(transaction);
		
		//8. bikin TransactionModelnya dan kirim respons ke client
		TransactionModel transactionModel = new TransactionModel();
		BeanUtils.copyProperties(transaction, transactionModel);

		return transactionModel;
	}

}
