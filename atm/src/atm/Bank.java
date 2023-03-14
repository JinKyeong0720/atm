package atm;

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
	
	Scanner scan = new Scanner(System.in);
	Random ran = new Random();
	String[][] idpw; // {id, password}, {id, password}, {id, password}
	int[] money;
	Account[] account;
	int log = -1;
	int code;
	int count = 0;
	
	public Bank(String bank) {
		
	}

	private void printMenu() {
		System.out.println("1. 회원가입");
		System.out.println("2. 회원탈퇴");
		System.out.println("3. 계좌신청");
		System.out.println("4. 계좌철회");
		System.out.println("5. 로그인");
	}
	
	private int selectMenu() {
		System.out.print("메뉴 : ");
		int select = scan.nextInt();
		return select;
	}
	
	private void join() {
		String id = input("id");
		String password = input("password");
		
		if(checkDupl(id)) {
			addUser(id, password);
			System.out.println("회원가입이 완료됐습니다.");
		} else {
			System.out.println("이미 사용중인 Id입니다.");
		}
	}
	
	private void printAllUsers() {
		for(int i=0; i<this.count; i++) {
			String id = this.idpw[i][0];
			String password = this.idpw[i][1];
			int currentMoney = this.money[i];
			
			System.out.printf("%s:%s / %d원\n", id, password, currentMoney);
		}
	}
	
	private void addUser(String id, String password) {
		String[][] tempidpw = this.idpw;
		this.idpw = new String[this.count+1][2];
		
		for(int i=0; i<this.count; i++) {
			this.idpw[i] = tempidpw[i];
		}
		this.idpw[this.count][0] = id;
		this.idpw[this.count][1] = password;
		
		this.count++;
	}
	
	private String input(String message) {
		System.out.print(message + " : ");
		String id = scan.next();
		return id;
	}
	
	private boolean checkDupl(String id) {
		boolean check = true;
		
		for(int i=0; i<count; i++) {
			if(id.equals(this.idpw[i][0])) {
				check = false;
			}
		}
		return check;
	}
	
	private boolean isLoggedIn() {
		if(this.log != -1) {
			return false;
		}
		return true;
	}
	
	private void leave() {
		if(isLoggedIn()) {
			String password = input("password");
			
			if(this.idpw[this.log][1].equals(password)) {
				removeUser();
				logOut();
				System.out.println("회원 탈퇴가 완료됐습니다.");
			} else {
				System.out.println("비밀번호를 다시 확인해주세요.");
			}
		}
	}
	
	private void removeUser() {
		String[][] tempidpw = this.idpw;
		int[] tempMoney = this.money;
		
		this.idpw = new String[this.count-1][2];
		this.money = new int[this.count-1];
		
		int index = 0;
		for(int i=0; i<this.count; i++) {
			if(i!=this.log) {
				this.idpw[index] = tempidpw[i];
				this.money[index] = tempMoney[i];
				index++;
			}
		}
		this.count--;
	}
	
	private void makeAccount() {
		int accountCount = 0;
		
		if(isLoggedIn()) {
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
		int code = randomCode();
		int money = 0;
		Account account = new Account(code, money);
		
		
	}
	
	private int randomCode() {
		Random ran = new Random();
		int code = 0;
		
		while(true) {
			code = ran.nextInt(8999)+1000;
			boolean checkDuplCode = true;
			for(int i=0; i<this.count; i++) {
				
				Account account = this.account[i];
				
			}
			if(checkDuplCode)
				break;
		}		
		return code;
	}
	
	
	private void deleteAccount() {
		if(isLoggedIn()) {
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
		// 계좌 들어있는 배열 하나 줄이기
		
	}
	
	private void logIn() {
		String id = input("id");
		String password = input("password");
		
		int index = getIndexById(id);
		
		if(index != -1) {
			String dataId = this.idpw[index][0];
			String dataPassword = this.idpw[index][1];
			
			if(id.equals(dataId) && password.equals(dataPassword)) {
				this.log = index;
				System.out.println("로그인 성공");
			} else {
				System.out.println("회원정보를 다시 확인해주세요.");
			}
		}
	}
	
	private int getIndexById(String id) {
		int index = -1;
		
		for(int i=0; i<this.count; i++) {
			if(id.equals(this.idpw[i][0])) {
				index = i;
			}
		}
		return index;
	}
	
	private void logOut() {
		
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