// 1.2.2 Creation of the CurrentAccount and SavingsAccount

package components;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "account")
public class CurrentAccount extends Account {

	public CurrentAccount() {
        super();
    }
	
	public CurrentAccount(Client client) {
		super("Current Account", client);
	}

}
