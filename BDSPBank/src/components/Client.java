// 1.1.1 Creation of the client class

package components;

public class Client {
	
	private String name;
	private String firstName;
	private int clientNumber;
	
	private static int nextClientNumber = 1;
	
	
	public Client(String name, String firstName) {
		this.name = name;
		this.firstName = firstName;
		this.clientNumber = nextClientNumber++;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


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
