package StudentInformationSystem.entity;

public class Fee {

	private int idfee;
	private String feename;
	private int money;
	
	public Fee(int idfee, String feename, int money) {
		super();
		this.idfee = idfee;
		this.feename = feename;
		this.money = money;
	}
	
	public Fee() {
		super();
	}

	public int getIdfee() {
		return idfee;
	}

	public void setIdfee(int idfee) {
		this.idfee = idfee;
	}

	public String getFeename() {
		return feename;
	}

	public void setFeename(String feename) {
		this.feename = feename;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	
}
