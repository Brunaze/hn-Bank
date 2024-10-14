// 1.1.1 Creation of the client class

package components;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "client")
@XmlType(propOrder = {"name", "firstName"})
public class Client {
	
	private String name;
	
	private String firstName;
	private int clientNumber;
	
	private static int nextClientNumber = 1;
	
	
	public Client() {
    }
	
	public Client(String name, String firstName) {
		this.name = name;
		this.firstName = firstName;
		this.clientNumber = nextClientNumber++;
	}


	@XmlElement(name = "name")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@XmlElement(name = "firstName")
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public int getClientNumber() {
		return clientNumber;
	}


	public void setClientNumber(int clientNumber) {
		this.clientNumber = clientNumber;
	}


	@Override
	public String toString() {
		return "Client [name=" + name + ", firstName=" + firstName + ", clientNumber=" + clientNumber + "]";
	}
	
	
	
	
	

}
