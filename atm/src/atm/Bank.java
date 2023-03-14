package atm;

import java.util.Scanner;

public class Bank {
	
	private UserManager um;
	private AccountManager am;
	
	final int JOIN = 1;
	final int LEAVE = 2;
	final int MAKEACCOUNT = 3;
	final int DELETEACCOUNT = 4;
	final int LOGIN = 5;
	
	Scanner scan = new Scanner(System.in);
	String[][] idpw;
	int log = -1;
	int count = 0;
	
	void printMainMenu() {
		System.out.println("회원가입");
		System.out.println("회원탈퇴");
		System.out.println("계좌신청");
		System.out.println("계좌철회");
		System.out.println("로그인");
	}
	
	int selectMenu() {
		int select = scan.nextInt();
		return select;
	}
	
	void join() {
		String id = input("id");
		String password = input("password");
		
		if(checkDupl(id)) {
			
		}
	}
	
	String input(String message) {
		System.out.print(message + " : ");
		String id = scan.next();
		return id;
	}
	
	boolean checkDupl(String id) {
		boolean check = true;
		
		for(int i=0; i<count; i++) {
			if(id.equals(this.idpw[i][0])) {
				check = false;
			}
		}
		return check;
	}
	
	boolean isLoggedIn() {
		if(this.log != -1) {
			return false;
		}
		return true;
	}
	
	void leave() {
		
	}
	
	void makeAccount() {
		
	}
	
	void deleteAccount() {
		
	}
	
	void logIn() {
		
	}
	
	// Banking 관련 메소드
	public void run() {
		printMainMenu();
		int menu = selectMenu();
		
		if (menu == JOIN) join();
		else if(menu == LEAVE) leave();
		else if(menu == MAKEACCOUNT) makeAccount();
		else if(menu == DELETEACCOUNT) deleteAccount();
		else if(menu == LOGIN) logIn();
	}
}
