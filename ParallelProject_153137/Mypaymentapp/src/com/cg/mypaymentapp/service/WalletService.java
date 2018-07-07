package com.cg.mypaymentapp.service;

import java.math.BigDecimal;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;

public interface WalletService {

	Customer createAccount(String name, String number, BigDecimal amount);

	Customer showBalance(String mobileno) ;

	Customer fundTransfer(String sourceMobileNo, String targetMobileNo,
			BigDecimal amount);

	Customer depositAmount(String mobileNo, BigDecimal amount1);

	Customer withdrawAmount(String mobileNo1, BigDecimal amount2);
	
}
