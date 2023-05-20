package com.app.banking;

import java.time.LocalDate;
import custom_exceptions.AccountOverdrawnException;

import static utils.BankvalidationRules.validateBalance;

public class BankAccount{
	

	private int accNumber;
	private AccountType type;
	private double balance;
	private LocalDate creationDate;
	private String customerName;
	
	public BankAccount(int accNumber, AccountType type, double balance, LocalDate creationDate, String customerName) {
		super();
		this.accNumber = accNumber;
		this.type = type;
		this.balance = balance;
		this.creationDate = creationDate;
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "BankAccount [accNumber=" + accNumber + ", type=" + type + ", balance=" + balance + ", creationDate="
				+ creationDate + ", customerName=" + customerName + "]";
	}
	
	//Deposit
	public void deposit(double amount) {
		balance+=amount;
	}
	//Withdraw money
	public void withdraw(double amount) throws AccountOverdrawnException {
			validateBalance(balance-amount); //we are validating (bal-amt) so..
			balance-=amount;
	}
	
	//Funds transfer
	public void transferFunds(BankAccount dest,double transferAmount) throws AccountOverdrawnException {
		//withdraw from source account
		this.withdraw(transferAmount);  
		//if withdraw success then only
		dest.deposit(transferAmount);
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

}
