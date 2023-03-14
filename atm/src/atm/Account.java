package atm;

public class Account {
	
	public static int LIMIT = 3; // 계좌 생성 시 최대 3개
	
	private String account;
	private int money;
	
	public Account(String account, int money) {
		this.account = account;
		this.money = money;
	}
	
	public String account() {
		return this.account;
	}
	
	public int money() {
		return this.money;
	}
	
	public String getAccount(){
		return this.account;
	}
	
	public int getMoney() {
		return this.money;
	}

	
}
