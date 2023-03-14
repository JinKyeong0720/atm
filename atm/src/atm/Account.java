package atm;

public class Account {
	
	public static int LIMIT = 3; // 계좌 생성 시 최대 3개
	
	private String userId;
	private String accNum;
	private int money;
	private int code;
	
	public Account(String userId, String account, int money) {
		this.userId = userId;
		this.accNum = account;
		this.money = money;
	}

	public String userId() {
		return this.userId;
	}
	
	public String account() {
		return this.accNum;
	}
	
	public int money() {
		return this.money;
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public String getAccount(){
		return this.accNum;
	}
	
	public int getMoney() {
		return this.money;
	}

	
	
	public String setAccount(Account account) {
		return null;
	}
	
	public int setMoney(Account account) {
		return 0;
	}
	

	
}