package atm;

import java.util.ArrayList;

public class User {
	private String id, password, name;
	
	// new 객체가 아님 -> AccountManager.list 안에 있는 인스턴스
	// 원본은 static list 안에 있음
	// 
	private ArrayList<Account> accs;
	
	public User(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getName() {
		return this.name;
	}
	
////	값 수정하기 : set
////	set(타겟인덱스, 수정할 값)
//	myVector.set(1, 100); // 1인덱스를 100인덱스로 변경
//	System.out.println(myVector.get(1));
	
	public String setId(User user) {
		
		return null;
	}
	
	public String setPassword(User user) {
		return null;
	}
	
	public String setName(User user) {
		return null;
	}
	
	
}