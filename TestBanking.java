package tester;
import static utils.CollectionUtils.populateMap;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import static utils.CollectionUtils.findByAccountNo;
import com.app.banking.AccountType;
import com.app.banking.BankAccount;
import static java.time.LocalDate.parse;
import static utils.BankvalidationRules.validateBalance;

import custom_exceptions.AccountHandlingException;
import custom_exceptions.DupAccountException;
public class TestBanking {

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
		//invoke populate map
		HashMap<Integer,BankAccount> accounts =  populateMap();
		
		boolean exit = false;
		while(!exit) {
			System.out.println("Options 1.Create New Account 2.Display all Accounts 3.Account summary "
					+ "4.Fund transfer 5.Close account "
					+ "6.Dispaly account type "
					+ " 7.Seacrh account by balance "
					+ "8.Remove all loan accounts 9.Sort Account in Ascending "
					+ "10.sort account number in desc 100.EXIT ");
			System.out.println("Enter your options");
			try {
				switch(sc.nextInt()) {
				case 1://Create account
					System.out.println("Enter account number"); //since account num is needed
					int accNo = sc.nextInt();
					if(accounts.containsKey(accNo)) //int-->autoboxing-->Integer-->Object
						//=Dup A/c key
						throw new DupAccountException("Account number already exist");
						// new a/c number
					System.out.println("ENter a.c type, balance, creation Date,customerName");
					accounts.put(accNo, new BankAccount(accNo,AccountType.valueOf(sc.next().toUpperCase()),
							validateBalance(sc.nextDouble()),parse(sc.next()),sc.next()));
					System.out.println("Account created");
					break;
				
				case 2://Display of all accounts
					//values will be used since we want to see complete acc details(Values) not account number(KEY) only
					System.out.println("All Account details");
					for(BankAccount a: accounts.values())
						System.out.println(a);
					break;
					
				case 3: //Account summary
					
					System.out.println("Enter account number");
					System.out.println("Bank Account Summary"+findByAccountNo(sc.nextInt(),accounts));
					break;
					
				case 4: //fund transfer
						System.out.println("Enter source account number");
						BankAccount src = findByAccountNo(sc.nextInt(), accounts);
						
						System.out.println("Enter destination account number");
						BankAccount dest = findByAccountNo(sc.nextInt(), accounts);
						
						System.out.println("Enter amount");
						
						src.transferFunds(dest, sc.nextDouble());
						System.out.println("Funds Transfer");
						break;
						
				case 5: //Close account
					//input account number-->o/p details of closed acc or err mesg via exceptions
					System.out.println("Enter account number you want to close");
					 BankAccount a = accounts.remove(sc.nextInt());
					 if(a==null)
						 throw new AccountHandlingException("Account closing fail:invalid accNum");
					 System.out.println("Closed account"+a);
					 break;
					 
				case 6: //Display all account details of specific account type
					//searching criteria is value based so had to convert to map -->collection view
					System.out.println("Enter account type");
					AccountType actype = AccountType.valueOf(sc.next().toUpperCase());
					System.out.println("Account details for a/c type"+actype);
					for(BankAccount a1:accounts.values()) 
						if(a1.getType()==actype) 
							System.out.println(a1);
					break;
					
	
				case 7: //Search account by balance > spacific balnace, dislay therir details
						System.out.println("Enter balance");
						double bal = sc.nextDouble();
						//searching criteria is value based so convert map into collection view
						for(BankAccount a1:accounts.values())
							if(a1.getBalance()>bal)
								System.out.println(a1);
						break;
						
				case 8://Remove all loan accounts
					Collection<BankAccount> acctCollection = accounts.values();
//					 for(BankAccount b1: accounts.values())
//						 if(b1.getType()==AccountType.LOAN) //it will give castclassModifi
//							 acctCollection.remove(b1);
					Iterator<BankAccount> itr = acctCollection.iterator();
					while(itr.hasNext()) {
						if(itr.next().getType()==AccountType.LOAN)
							itr.remove();
					}
					 break;
					 
				case 9: //sort Account as per account number
					//since sorting criteria is key based(acc no):can be done using TM
					TreeMap<Integer,BankAccount> sorted = new TreeMap<>(accounts);
//					TreeMap<Integer,BankAccount> sorted = new TreeMap<>();
//					sorted.putAll(accounts);
//					
					//JVM invokes for sorting Integers compareTo
					System.out.println("Sorting acount number in Ascending order");
					for(BankAccount b : sorted.values())
						System.out.println(b);				
					break;	 
				
				case 10: //sort account number as per desc
					//using anonymous inner class
					
					TreeMap<Integer, BankAccount> sortedAcc = new TreeMap<>(new Comparator<Integer>(){
						@Override
						public int compare(Integer o1,Integer o2) {
							return o2.compareTo(o1);
						}
					});
					sortedAcc.putAll(accounts); //copies all entries from HM to TM,
					//JVM will invoke anon inner class method
					System.out.println("Sorting acount number in Descending order");
					for(BankAccount b : sortedAcc.values())
						System.out.println(b);	
					break;
					
				case 11: //Sort account as per account creation date
					//date : value based => so convert into collecton view
					Collection<BankAccount> coll = accounts.values();
					//Collection to AL using Collections.sort(list,comparator)
					ArrayList<BankAccount> list = new ArrayList<>(coll);
//					Collections.sort(list, new Comparator<BankAccount>() {
//
//						@Override
//						public int compare(BankAccount o1, BankAccount o2) {
//							// TODO Auto-generated method stub
//							return o1.getCreationDate().compareTo(o2.getCreationDate());
//						}	
//					});
//					System.out.println("Accounts as per creation date");
//					for(BankAccount b:list) {
//						System.out.println(b);
//					}
					Collections.sort(list,(o1,o2)->(o1.getCreationDate().compareTo(o2.getCreationDate())));
					break;
				case 100:
					exit=true;
					break;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			//read off pending inputs from scanner
			//sc.nextLine();
		}
		}
	}

}
