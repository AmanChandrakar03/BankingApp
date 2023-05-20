package tester;

import java.util.HashMap;
import static utils.CollectionUtils.populateMap;
import com.app.banking.BankAccount;
import static java.util.Map.Entry; 
public class TestCollView {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			HashMap<Integer, BankAccount> map = populateMap();
			//display only keys
			System.out.println("Keys");
			for(int i: map.keySet()) //auto unbox
				System.out.println(i+" ");
			
			//extract and display only values
			System.out.println("Values: ");
			for(BankAccount a: map.values())
				System.out.println(a);
			
			System.out.println("Entries");
			//display both key and value in single iteration: Set<Map.entry>
			for(Entry<Integer,BankAccount>e:map.entrySet())
				System.out.println(e.getKey()+" "+e.getValue());
			
	}

}
