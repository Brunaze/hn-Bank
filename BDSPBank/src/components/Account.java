// 1.2.1 Creation of the account class

package components;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


@XmlSeeAlso({CurrentAccount.class, SavingsAccount.class})
public abstract class Account {
	
	
	protected String label;
	
	
	protected double balance;
	
	
	protected int accountNumber;
	
	private static int nextAccountNumber = 1;
	
	protected Client client;
	
	
	public Account() {
    }
	

	public Account(String label, Client client) {
		this.label = label;
		this.client = client;
		this.accountNumber = nextAccountNumber++;
	}

	
	@XmlElement(name = "label")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@XmlElement(name = "balance")
	public double getBalance() {
		return balance;
	}
	
	// 1.3.3 Creation of the Transfert, Credit, Debit classes

	public void setBalance(Flow flow) {
		// Si c'est un debit
		if (flow instanceof Debit) {
			this.balance -= flow.getAmount();
		} 
		
		// Si c'est un credit
		else if (flow instanceof Credit) {
			this.balance += flow.getAmount();
		} 
		
		// Si c'est un transfert
		else if (flow instanceof Transfert) {
			Transfert transfert = (Transfert) flow;
			// Si ce compte recoit
			if (this.accountNumber == transfert.getTargetAccountNumber()) {
				this.balance += transfert.getAmount();
			}
			// Si ce compte envoie
			else if (this.accountNumber == transfert.getTransferAccountNumber()){
				this.balance -= transfert.getAmount();
			}
			
		}
	}

	@XmlElement(name = "accountNumber")
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	@XmlElement(name = "client")
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
