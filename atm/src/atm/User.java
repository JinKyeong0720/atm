package atm;

import java.util.ArrayList;

public class User {
	private String id, password, name;
	
	// new 객체가 아님 --> AccountManager.list 안에 있는 인스턴스
	// 원본은 static list 안에 있음
	private ArrayList<Account> accs;

	public User(String id, String password, String name, ArrayList<Account> acc) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.accs = new ArrayList<Account>();
	}
	
	
	public String getId() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAccountSize() {
		return this.accs.size();
	}
	
	public void addAccount(Account account) {
		this.accs.add(account);
	}
	
	public Account getAccount(int index) {
		return this.accs.get(index);
	}
	
	public ArrayList<Account> getAccountList(){
		return (ArrayList<Account>) this.accs.clone();
	}
	
	
}