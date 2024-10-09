package main;

import java.util.ArrayList;
import java.util.List;

import components.Client;

public class main {
	
	// 1.1.2 Creation of main class for tests
	
	public static List<Client> generateClients(int nbClients){
		
		List<Client> clients = new ArrayList<>();
		
		for (int i = 1; i <= nbClients; i++) {
			clients.add(new Client("name"+i, "firstname"+i));
		}
		
		return clients;
	}
	
	
	public static void displayClients(List<Client> clients) {
		System.out.println(clients.toString());
	}
	

	public static void main(String[] args) {
		List<Client> clients = generateClients(3);

		displayClients(clients);
	}

}
