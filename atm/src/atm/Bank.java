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
	private static final int LOGIN = 3;
	private static final int LOGOUT = 4;
	private static final int BANKING = 5;
	private static final int FILE = 6;
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
		System.out.println();
		System.out.println("=== " + this.brandName + " ===");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원탈퇴");
		System.out.println("3. 로그인");
		System.out.println("4. 로그아웃");
		System.out.println("5. 뱅킹 시스템");
		System.out.println("6. 파일 시스템");
		System.out.println("0. 종료");
	}
	
	public void run() {
		while(true) {
			printMainMenu();
			int menu = inputNumber();
			
			if (menu == 1) joinUser();
			else if(menu == 2) leaveUser();
			else if(menu == 3) logIn();
			else if(menu == 4) logOut();
			else if(menu == 5) banking();
//			else if(menu == 6) file();
			else if(menu == 0) break;
		}
		System.out.println("시스템이 종료되었습니다.");
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
	
	private boolean isLoggedIn(int log) {
		if(this.log == -1) {
			return false;
		}
		return true;
	}
	
	
	private void joinUser() {  // 1. 회원가입
		if(!isLoggedIn(log)) {
			System.out.print("가입할 id : ");
			String id = this.scan.next();
			System.out.print("가입할 password : ");
			String password = this.scan.next();
			System.out.print("가입할 name : ");
			String name = this.scan.next();
			
			User user = new User(id, password, name);
			if(this.um.addUser(user) != null) {
				System.out.println("회원가입 성공!");
			} else {
				System.out.println("중복된 아이디가 존재합니다.");
			}
		} else {
			System.out.println("이미 로그인 되었습니다.");
		}
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
	
	private void logIn() { // 3. 로그인
		if(!isLoggedIn(this.log)) {
			System.out.print("id : ");
			String id = this.scan.next();
			System.out.print("password : ");
			String password = this.scan.next();
			
			int index = -1;
			for(int i=0; i<um.getUserSize(); i++) {
				if(id.equals(um.getUser(i).getId()) && password.equals(um.getUser(i).getPassword())) {
					index = findIndex(id);
					log = index;
					System.out.println("로그인 성공!");
				} else {
					System.out.println("비밀번호를 다시 확인하세요.");
				}
			}
		} else {
			System.out.println("이미 로그인 상태입니다.");
		}
	}
	
	private int findIndex(String id) {
		return this.um.indexOfById(id);
	}

	private void logOut() { // 4. 로그아웃
		if(log != -1) {
			log = -1;
			System.out.println("로그아웃 되었습니다.");
		} else {
			System.out.println("로그인 상태가 아닙니다.");
		}
	}

	private void banking() { // 5. 뱅킹
		while (true) {
			printBankingMenu();
			int menu = inputNumber();

			if(menu == 1) // 계좌 신청
				createAccount();
			else if(menu == 2) // 계좌 철회
				deleteAccount();
			else if (menu == 3) // 입금
				inputMoney();
			else if (menu == 4) // 출금
				outputMoney();
			else if (menu == 5) // 조회
				infoAccount();
			else if (menu == 6) // 이체
				sendMoney();
			else if (menu == 0) // 뒤로가기
				break;
		}
	}

	private void printBankingMenu() {
		System.out.println("=== Banking ===");
		System.out.println("1. 계좌 생성");
		System.out.println("2. 계좌 철회");
		System.out.println("3. 입금");
		System.out.println("4. 출금");
		System.out.println("5. 조회");
		System.out.println("6. 이체");
		System.out.println("0. 뒤로가기");
	}

	private void createAccount() {
		if (isLoggedIn(log)) {
			// 복제본 반환 받음
			User user = this.um.getUserById(um.getUser(log).getId());

			if (user.getAccountSize() < Account.LIMIT) {
				Account account = this.am.createAccount(new Account(um.getUser(log).getId()));
				this.um.setUser(user, account);
				
				System.out.println("계좌 : " + account.getAccNum()); 
				System.out.println("계좌 생성 완료!");
			} else {
				System.out.println("계좌 최대 생성 개수는 3개입니다.");
			}
		} else {
			System.out.println("로그인 후 이용 가능합니다");
		}
	}
	
	
	private void deleteAccount() { // 2. 계좌 삭제
		if (isLoggedIn(log)) {
			User user = um.getUser(log);
			if (user.getAccountSize() > 0) { // 1개 이상 등록됐을 떄
				for(int i=0; i<user.getAccountSize(); i++) {
					System.out.printf("계좌 : %s\n", am.getAccount(i).getAccNum());
				}
				System.out.print("삭제할 계좌 선택 : ");
				int selectDeleteAcc = scan.nextInt()-1;
				am.deleteAccount(selectDeleteAcc);
				um.deleteUserAccount(selectDeleteAcc, this.log);
				
				System.out.println("계좌가 삭제되었습니다.");
			} else {
				System.out.println("삭제할 계좌가 존재하지 않습니다;");
			}
		} else {
			System.out.println("로그인 후 이용 가능합니다.");
		}
	}

	private void inputMoney() { // 입금
		if(isLoggedIn(log)) {
			System.out.println("입금할 금액 : ");
			// 로그인한 계정 log -> 
		} else {
			System.out.println("로그인 후 이용 가능합니다.");
		}
	}
	
	private void outputMoney() { // 출금
		if(isLoggedIn(log)) {
			System.out.println("출금할 금액 : ");
//			if() { // 입금할 금액 > 0
//				
//			} else {
//				System.out.println("1원 이상부터 입금할 수 있습니다.");
//			}
		} else {
			System.out.println("로그인 후 이용가능합니다.");
		}
		
	}
	
	private void infoAccount() { // 조회
		if(isLoggedIn(log)) {
			
		} else {
			System.out.println("로그인 후 이용가능합니다.");
		}
	}
	
	private void sendMoney() { // 이체
		if(isLoggedIn(log)) {
			System.out.println("이체할 계좌번호 : ");
			// if 유효한 계좌번호일 때
			System.out.println("이체할 금액 : ");
			// else 
			System.out.println("계좌번호를 다시 확인하세요"); 
		} else {
			System.out.println("로그인 후 이용가능합니다.");
		}
	}
}