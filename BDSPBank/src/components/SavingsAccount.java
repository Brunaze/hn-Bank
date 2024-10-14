// 1.2.2 Creation of the CurrentAccount and SavingsAccount

package components;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "account")
public class SavingsAccount extends Account {
	
	public SavingsAccount() {
        super();
    }

	public SavingsAccount(Client client) {
		super("Savings Account", client);
	}

}
