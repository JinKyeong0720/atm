package atm;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {

	private static ArrayList<Account> list = new ArrayList<Account>();
	
	// Account에 대한 
	// Create
	public void addAccount(Account account) {
		this.list.add(account);
	}
	
	// Read
	public Account getAccount(int index) {
		Account account = this.list.get(index);
		
		Account reqObj = new Account(null, index);
		return reqObj;
	}
	
	public Account getAccountBy(String account) {
		int index = -1;
		return getAccount(index);
	}
	
	// Update
	public void setAccount(int index, Account account) {
		this.list.set(index, account);
	}
	
	// Delete 관련 메서드가 AccountManager 안에서 실행
	public void deleteAccount(int index) {
		this.list.remove(index);
	}
	
	public void deelteAccountBy(String account) {
		
	}
	
}
