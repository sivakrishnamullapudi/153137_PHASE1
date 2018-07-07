package com.cg.mypaymentapp.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.repo.WalletRepo;
import com.cg.mypaymentapp.repo.WalletRepoImpl;

public class WalletServiceImpl implements WalletService{
	private Map<String, Customer> info = new HashMap<String, Customer>();
	public WalletServiceImpl(Map<String, Customer> data)
	{
		//repo= new WalletRepoImpl(data);
	}
/*	public WalletServiceImpl(WalletRepo repo) {
		super();
		this.repo = repo;
	}*/
	WalletRepo repo;
	{
		repo=new WalletRepoImpl();
	}


	public WalletServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Customer createAccount(String name, String mobileno,
			BigDecimal amount) {
		if(validateNMobile(mobileno))
		{
			
				if(validateamount(amount))
			{
				Customer cu = new Customer(name, mobileno, new Wallet((amount)));
				repo.save(cu);
				info.put(mobileno, cu);
				return cu;
			}
			
			
		}
		else {
			System.out.println("Invalid mobile number");
			return null;
		}
		return null;
	}

	private boolean validateNMobile(String str) {
		String patterns="[1-9][0-9]{9}";
		if(str.matches(patterns))
			return true;
		else
		
		return false;
	}
	private boolean validateamount(BigDecimal amount) {
		//String patterns="[1-9][0-9]{9}";
		if(amount.equals(0))
			return false;
		else
		
		return true;
	}

	@Override
	public Customer showBalance(String mobileno){
		Customer customer=repo.findOne(mobileno);
		
			return customer;
	
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo,
			BigDecimal amount) {
		Customer cust1=info.get(sourceMobileNo);
		Customer cust2=info.get(targetMobileNo);
		BigDecimal bg1=amount;
		Wallet w=cust1.getWallet();
		Wallet w1=cust2.getWallet();
		BigDecimal balance=w.getBalance();
		BigDecimal balance1=w1.getBalance();
		cust1.setWallet(new Wallet(balance.subtract(amount)));
		cust2.setWallet(new Wallet(balance1.add(amount)));
		return cust1;
		}
	

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount1) {
		Customer cust1=info.get(mobileNo);
		BigDecimal bg1=amount1;
		Wallet w=cust1.getWallet();
		BigDecimal balance=w.getBalance();
		BigDecimal deposit=balance.add(amount1);	
 cust1.setWallet(new Wallet(deposit));
		return cust1;
	}

	@Override
	public Customer withdrawAmount(String mobileNo1, BigDecimal amount2) {
Customer cust2=info.get(mobileNo1);
		BigDecimal bg1=amount2;
		BigDecimal bg2=new BigDecimal(0);
		Wallet w=cust2.getWallet();
		BigDecimal balance=w.getBalance();
		BigDecimal withdraw=balance.subtract(amount2);	
		if(withdraw.compareTo(bg2)>0) {
 cust2.setWallet(new Wallet(withdraw));
		return cust2;
		}
		else return null;
	}
}
