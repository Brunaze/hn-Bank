// 1.2.1 Creation of the account class

package components;

public abstract class Account {
	
	protected String label;
	protected double balance;
	protected int accountNumber;
	
	private static int nextAccountNumber = 1;
	
	protected Client client;
	
	

	public Account(String label, Client client) {
		this.label = label;
		this.client = client;
		this.accountNumber = nextAccountNumber++;
	}

	
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double amount) {
		balance = this.balance + amount;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Account [label=" + label + ", Balance=" + balance + ", accountNumber=" + accountNumber + ", client="
				+ client + "]";
	}
	
	
	

}
