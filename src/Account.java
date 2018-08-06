public class Account {
	private String name; // instance variable
	private Double balance; // instance variable

	public Account()
	{
		name = null;
		balance = 0.0;
	}

	// Account constructor that receives two parameters
	public Account(String name, Double balance) {
		this.name = name; // assign name to instance variable name

		// validate that the balance is greater than 0.0; if it's not,
		// instance variable balance keeps its default initial value of 0.0
		if (balance > 0.0) { // if the balance is valid
			this.balance = balance; // assign it to instance variable balance
		}
	}

	// method that deposits (adds) only a valid amount to the balance
	public void deposit(Double depositAmount) {
		if (depositAmount > 0.0) { // if the depositAmount is valid
			balance = balance + depositAmount; // add it to the balance
		}
	}

	public void setBalance(Double name) {
		this.balance = name;
	}

	// method returns the account balance
	public Double getBalance() {
		return balance;
	}

	// method that sets the name
	public void setName(String name) {
		this.name = name;
	}

	// method that returns the name
	public String getName() {
		return name;
	}
}
