package tester;

import java.util.ArrayList;
import java.util.HashMap;

import static utils.CollectionUtils.populateMap;
import com.app.banking.BankAccount;

public class Test3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<Integer, BankAccount> map = populateMap();
		map.forEach((k,v)->System.out.println(k+" "+ v));
	}

}
