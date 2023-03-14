package atm;

public class Account {
	
	public static int LIMIT = 3; // 계좌 생성 시 최대 3개
	
	private String userId;
	private String accNum;
	private int money;
	private int code;
	
	public Account(String account, int money) {
		this.accNum = account;
		this.money = money;
	}
	
	public Account(int code, int money) {
		this.code = code;
		this.money = money;
	}
	
	public String account() {
		return this.accNum;
	}
	
	public int money() {
		return this.money;
	}
	
	public String getAccount(){
		return this.accNum;
	}
	
	public int getMoney() {
		return this.money;
	}
	
////	값 수정하기 : set
////	set(타겟인덱스, 수정할 값)
//	myVector.set(1, 100); // 1인덱스를 100인덱스로 변경
//	System.out.println(myVector.get(1));
	
	public String setAccount(Account account) {
		return null;
	}
	
	public int setMoney(Account account) {
		return 0;
	}
	

	
}