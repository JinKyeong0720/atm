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
		User user = list.get(index);
		
		String id = user.getId();
		String password = user.getPassword();
		String name = user.getName();
		
		// 사본 제공
		User reqObj = new User(id, password, name);
		return reqObj;
	}
	
	public User getUserById(String id) {
		int index = -1; // ?
		return getUser(index);
	}
	
	public ArrayList<User> getUserList(){
		return list;
	}
	
	// 인덱스 찾는 클래스
	
	// Update
	public void setUser(int index, User user) {
		list.set(index, user);
	}
	
	// Delete 
	public void deleteUser(int index) {
		list.remove(index);
	}
	
	public void deleteUserById(String id) {
	
	}

	public int indexOf(String id) {
		int index = -1;
		
		for(int i=0; i<list.size(); i++) {
			if(id.equals(list.get(i).getId())) {
				index = i;
			}
		}
		return index;
	}
	


}
 