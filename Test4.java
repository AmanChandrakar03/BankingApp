package tester;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import static utils.CollectionUtils.populateMap;
import com.app.banking.BankAccount;

public class Test4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<Integer, BankAccount> map = populateMap();
		Collection<BankAccount> coll = map.values();
		ArrayList<BankAccount> list = new ArrayList<>(coll);
		Collections.sort(list, (o1,o2)->((Double)o1.getBalance()).compareTo(o2.getBalance()));
		list.forEach(a->System.out.println(a));
	}

}
