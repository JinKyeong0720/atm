package atm;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {

	private static ArrayList<Account> list = new ArrayList<Account>();
	
	// Create
	public void createAccount(Account account) {
		list.add(account);
	}
	
	// Read
	public Account getAccount(int index) {
		// 사본 제공해줘야 함
		Account account = list.get(index);
		
		Account reqObj = new Account(null, index);
		return reqObj;
	}
	
	// Update
	public void setAccount(int index, Account account) {
		list.set(index, account);
	}
	
	// Delete 
	// 관련 메서드가 AccountManager 안에서 실행
	public void deleteAccount(int index) {
		list.remove(index);
	}

	
}
