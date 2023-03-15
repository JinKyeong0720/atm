package atm;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AccountManager {

	private static ArrayList<Account> list = new ArrayList<Account>();
	
	// Create
	public Account createAccount(Account account) {
		String accountNum = accNumGenerator();
		account.setAccNum(accountNum);
		list.add(account);
		return account;
	}
	
	// Read
	public Account getAccount(int index) {
		Account account = list.get(index);
		
		Account reqObj = new Account(account.getUserId(), account.getAccNum(), account.getMoney());
		return reqObj;
	}
	
	public Account getAccountByNum(String accountNum) {
		Account account = null;
		
		for(Account object : list) {
			if(object.getAccNum().equals(accountNum))
				account = object;
		}
		return account;
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
	
	public String accNumGenerator() {
		// ####-####
		String num = "";
		
		Random ran = new Random();
		while(true) {
			int first = ran.nextInt(8999)+1000;
			int second = ran.nextInt(8999)+1000;
			num = first + "-" + second;
			
			Account account = getAccountByNum(num);
			
			if(account == null)
				break;
		}
		return num;
	}
	
	@Override
	public String toString() { // 계좌 생성 시 오버라이드
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}