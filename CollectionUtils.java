package utils;

import java.util.HashMap;

import static com.app.banking.AccountType.*;
import com.app.banking.BankAccount;

import custom_exceptions.AccountHandlingException;

import static java.time.LocalDate.parse;
public class CollectionUtils {
	//add a static method to return populated map of bank a/c
	
	public static HashMap<Integer,BankAccount> populateMap(){
		
		//Create empty map
		HashMap<Integer,BankAccount> map = new HashMap<>();
		//int accNumber, AccountType type, double balance, LocalDate creationDate, String customerName
		map.put(10, new BankAccount(10, SAVING, 5000, parse("2010-04-23"), "Reema"));
		map.put(101, new BankAccount(101, CURRENT, 3500, parse("2011-06-21"), "Sameer"));
		map.put(76, new BankAccount(76, FD, 150000, parse("2009-04-01"), "Mohan"));
		map.put(56, new BankAccount(56, SAVING, 7800, parse("2020-04-07"), "Meera"));
		map.put(10, new BankAccount(10, LOAN, 9500, parse("2015-11-23"), "Riya"));
		
		//System.out.println(map);
		return map;
	}
	
	//add a static method to return ac no or throw exc
	public static BankAccount findByAccountNo(int accNo,HashMap<Integer, BankAccount> map) throws AccountHandlingException{
		BankAccount a = map.get(accNo);
		if(a==null) 
			throw new AccountHandlingException("Invalid Acount number");
		return a;
		}
	}

