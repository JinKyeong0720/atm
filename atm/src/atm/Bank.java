package atm;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Bank {
	
	private UserManager um;
	private AccountManager am;
	
	private static final int JOIN = 1;
	private static final int LEAVE = 2;
	private static final int MAKEACCOUNT = 3;
	private static final int DELETEACCOUNT = 4;
	private static final int LOGIN = 5;
	private static final int QUIT = 0;
	
	private Scanner scan;
	private Random ran;
	private int log;
	private String name;
	
	// Bank 생성자
	public Bank(String name) {
		um = new UserManager();
		am = new AccountManager();
		this.scan = new Scanner(System.in);
		this.ran = new Random();
		this.log = -1;
		this.name = name;
	}

	private void printMenu() {
		System.out.println("===" + this.name + "===");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원탈퇴");
		System.out.println("3. 계좌신청");
		System.out.println("4. 계좌철회");
		System.out.println("5. 로그인");
		System.out.println("0. 시스템 종료");
		System.out.println("메뉴 : ");
	}
	
	private int selectMenu() {
		int select = scan.nextInt();
		return select;
	}
	
	private void join() {  // 회원가입
		String id = input("id"); // id : 
		String password = input("password");
		String name = input("name");
		
		if(!checkDupl(id)) {
			User user = new User(id, password, name);
			um.addUser(user);			
			System.out.println("회원가입이 완료됐습니다.");
		} else {
			System.out.println("이미 사용중인 Id입니다.");
		}
	}
	
	private String input(String message) {
		System.out.print(message + " : ");
		String input = scan.next();
		return input;
	}
	
	private boolean checkDupl(String id) { // id 중복확인
		ArrayList<User> userList = um.getUserList();
		for(User UserManager : userList) {
			if(id.equals(UserManager.getId())){
				return true;
			}
		}
		return false;

	}
	
	private boolean isLoggedIn(int log) {
		if(this.log != -1) {
			return true;
		}
		return false;
	}
	
	private void leave() {
		if(isLoggedIn(this.log)) {
			String password = input("password");
			if() {
				
				System.out.println("회원탈퇴 되었습니다.");
			} else {
				System.out.println("비밀번호를 다시 확인하세요.");
			}
			
		} else {
			System.out.println("로그인 후 이용할 수 있습니다.");
		}
	}

	
	private void removeUser() {

	}
	
	private void makeAccount() {
		int accountCount = 0;
		
		if(!isLoggedIn(log)) {
				if(accountCount <= 3) {
					registerAccount();
					accountCount ++;
				} else {
					System.out.println("만들 수 있는 계좌 수(3)를 초과했습니다.");
				}
		} else {
			System.out.println("로그인 후 이용 가능합니다.");
		}
	}
	
	private void registerAccount() { // 로그인한 account[log]에 계좌 넣기

	}
	
	private String randomCode() {
		int ranCode1 = ran.nextInt(8999)+1000;
		int ranCode2 = ran.nextInt(8999)+1000;
		int ranCode3 = ran.nextInt(8999)+1000;
		
		String setRandomCode = ranCode1 + "-" + ranCode2 +"-"+ranCode3;
		return setRandomCode;
	}
	
	
	private void deleteAccount() {
		if(isLoggedIn(log)) {
//			if( > 0) { // 삭제할 계좌가 존재할 때
//				eraseAccount();
//			} else {
//				System.out.println("삭제할 계좌가 존재하지 않습니다;");
//			}
		} else {
			System.out.println("로그인 후 이용 가능합니다.");
		}
	}
	
	private void eraseAccount() {
		
		
	}
	
	private void logIn() {
		if(!isLoggedIn(this.log)) {
			String id = input("id");
			String password = input("password");
			
			ArrayList<User> userList = um.getUserList();
			int index = -1;
			for(User usermanager : userList) {
				if(id.equals(usermanager.getId()) && password.equals(usermanager.getPassword())) {
					index = findIndex(id);
					log = index;
					System.out.println("로그인 성공!");
				}
			}
			
		} else {
			System.out.println("이미 로그인 상태입니다.");
		}
	}
	
	private int findIndex(String id) {
		return this.um.indexOf(id);
	}
	
	public void run() {
		while(true) {
			printMenu();
			int menu = selectMenu();
			
			if (menu == JOIN) join();
			else if(menu == LEAVE) leave();
			else if(menu == MAKEACCOUNT) makeAccount();
			else if(menu == DELETEACCOUNT) deleteAccount();
			else if(menu == LOGIN) logIn();
			else if(menu == QUIT) break;
		}
		
	}
}