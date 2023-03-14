package atm;

import java.util.ArrayList;

public class UserManager {

	private static ArrayList<User> list = new ArrayList<User>();

	// User에 대한 CRUD : 관련 메서드가 UserManager 안에서 실행
	// Create
	public void addUser(User user) {
		this.list.add(user);
	}
	
	// Read
	public User getUser(int index) {
		User user = this.list.get(index);
		
		// 사본 제공
		User reqObj = new User(null, null, null);
		return reqObj;
	}
	
	public User getUserById(String id) {
		int index = -1; // ?
		return getUser(index);
	}
	
	
	// Update
	public void setUser(int index, User user) {
		this.list.set(index, user);
	}
	
	// Delete 
	public void deleteUser(int index) {
		this.list.remove(index);
	}
	
	public void deleteUserById(String id) {
		// 
	}
	
	// getter : USER, ACCOUNT에서 만들기
	
	


//	

//	

//	
//	void addUser() {
//		
//	}
//	
//	void leave() {
//		
//	}
//	
//	void logIn() {
//		
//	}
}
 