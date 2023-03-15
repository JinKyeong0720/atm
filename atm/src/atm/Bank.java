package atm;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
	
	private Scanner scan;
	
	private String brandName; // 은행 이름
	
	private UserManager um;
	private AccountManager am;
	
	private static final int JOIN = 1;
	private static final int LEAVE = 2;
	private static final int MAKEACCOUNT = 3;
	private static final int DELETEACCOUNT = 4;
	private static final int LOGIN = 5;
	private static final int LOGOUT = 6;
	private static final int BANKING = 7;
	private static final int QUIT = 0;
	
	private int log;
	private String name;
	
	// Bank 생성자
	public Bank(String brandName) {
		this.brandName = brandName;
		this.scan = new Scanner(System.in);
		this.um = new UserManager();
		this.am = new AccountManager();

		this.log = -1;
	}

	private void printMainMenu() {
		System.out.println("===" + this.brandName + "===");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원탈퇴");
		System.out.println("3. 계좌신청");
		System.out.println("4. 계좌철회");
		System.out.println("5. 로그인");
		System.out.println("6. 로그아웃");
		System.out.println("7. 뱅킹");
		System.out.println("0. 시스템 종료");
	}
	
	private int inputNumber() {
		int number = -1;
		
		System.out.println("input : ");
		String input = this.scan.next();
		
		try {
			number = Integer.parseInt(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return number;
	}
	
	public void run() {
		while(true) {
			printMainMenu();
			int menu = inputNumber();
			
			if (menu == 1) joinUser();
			else if(menu == 2) leaveUser();
			else if(menu == 3) createAccount();
			else if(menu == 4) deleteAccount();
			else if(menu == 5) logIn();
			else if(menu == 6) logOut();
			else if(menu == 7) banking();
			else if(menu == 0) break;
		}
		System.out.println("시스템이 종료되었습니다.");
	}

	
	private void joinUser() {  // 1. 회원가입 O
		if(!isLoggedIn(log)) {
			System.out.print("id : ");
			String id = this.scan.next();
			System.out.print("password : ");
			String password = this.scan.next();
			System.out.print("name : ");
			String name = this.scan.next();
			
			User user = new User(id, password, name, null);
			if(this.um.addUser(user) != null) {
				System.out.println("회원가입 성공!");
			} else {
				System.out.println("중복된 아이디가 존재합니다.");
			}
		} else {
			System.out.println("이미 로그인 되었습니다.");
		}
	}
	
	
//	private boolean checkDupl(String id) { // id 중복확인
//		ArrayList<User> userList = um.getUserList();
//		for(User UserManager : userList) {
//			if(id.equals(UserManager.getId())){
//				return true;
//			}
//		}
//		return false;
//
//	}
	
	private boolean isLoggedIn(int log) {
		if(this.log == -1) {
			return false;
		}
		return true;
	}
	
	private void leaveUser() { // 2. 회원탈퇴 
		if(isLoggedIn(this.log)) {
			System.out.println("password 재확인 : ");
			String password = this.scan.next();
			
			User user = this.um.getUserById(password);
			if(user != null) {
				if(user.getPassword().equals(password)) {
					um.deleteUser(log);
					logOut();
					System.out.println("회원 탈퇴가 완료됐습니다.");
				} else {
					System.out.println("비밀번호를 확인하세요.");
				}
			} else {
				System.out.println("회원정보를 다시 확인하세요.");
			}
		} else {
			System.out.println("로그인 후 이용할 수 있습니다.");
		}
	}

	

	private void createAccount() { // 3. 계좌 생성

		if (isLoggedIn(log)) {
			System.out.print("id 재확인: ");
			String id = this.scan.next();
			System.out.print("password 재확인 : ");
			String password = this.scan.next();

			// 복제본 반환받음
			User user = this.um.getUserById(id);

			if (user != null) {
				if (user.getPassword().equals(password)) {
					if (user.getAccountSize() < Account.LIMIT) {
						Account account = this.am.createAccount(new Account(id));
						this.um.setUser(user, account);
					} else {
						System.out.println("계좌는 최대 3개까지 개설 가능합니다.");
					}
				} else {
					System.out.println("비밀번호가 일치하지 않습니다.");
				}
			} else {
				System.out.println("회원정보를 다시 확인하세요.");
			}
		} else {
			System.out.println("로그인 후 이용 가능합니다.");
		}
	}
	
	private void deleteAccount() { // 4. 계좌 철회
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

	
	private void logIn() { // 5. 로그인 O
		if(!isLoggedIn(this.log)) {
			System.out.print("id : ");
			String id = this.scan.next();
			System.out.print("password : ");
			String password = this.scan.next();
			
//			ArrayList<User> userList = um.getUserList();
			int index = -1;
			for(User usermanager : um.getUserList()) {
				if(id.equals(usermanager.getId()) && password.equals(usermanager.getPassword())) {
					index = findIndex(id);
					log = index;
					System.out.println("로그인 성공!");
				} else {
					System.out.println("비밀번호를 다시 확인하세요");
				}
			}
		} else {
			System.out.println("이미 로그인 상태입니다.");
		}
	}
	
//	private boolean checkDupl(String id) { // id 중복확인
//		ArrayList<User> userList = um.getUserList();
//		for(User UserManager : userList) {
//			if(id.equals(UserManager.getId())){
//				return true;
//			}
//		}
//		return false;
//
//	}
	
	private int findIndex(String id) {
		return this.um.indexOfById(id);
	}

	private void logOut() { // 6. 로그아웃 O
		if(log != -1) {
			log = -1;
			System.out.println("로그아웃 되었습니다.");
		} else {
			System.out.println("로그인 상태가 아닙니다.");
		}
	}

	private void banking() { // 7. 뱅킹
		while (true) {
			printBankingMenu();
			int menu = inputNumber();

			if (menu == 1)
				inputMoney();
			else if (menu == 2)
				outputMoney();
			else if (menu == 3)
				infoAccount();
			else if (menu == 4)
				sendMoney();
			else if (menu == 0)
				break;
		}
	}

	private void printBankingMenu() {
		System.out.println("===Banking===");
		System.out.println("1. 입금");
		System.out.println("2. 출금");
		System.out.println("3. 조회");
		System.out.println("4. 이체");
		System.out.println("0. 뒤로가기");
	}

	private void inputMoney() { // 입금

	}
	
	private void outputMoney() { // 출금
		
	}
	
	private void infoAccount() { // 조회
		
	}
	
	private void sendMoney() { // 이체
		
	}
	
}