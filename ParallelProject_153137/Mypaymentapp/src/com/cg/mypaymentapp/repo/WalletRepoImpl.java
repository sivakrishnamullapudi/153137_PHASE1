package com.cg.mypaymentapp.repo;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;

public class WalletRepoImpl implements WalletRepo {
	private Map<String, Customer> info=new HashMap<String, Customer>(); 
	public WalletRepoImpl(Map<String, Customer> data) {
		super();
		this.info = data;
	}

	public WalletRepoImpl() {
		// TODO Auto-generated constructor stub
	}

	public boolean save(Customer customer) {
		info.put(customer.getMobileNo(), customer);
		return true;
	}

	public Customer findOne(String mobileNo) {
		Customer cust=info.get(mobileNo);
		return cust;
		
		
	}

}
