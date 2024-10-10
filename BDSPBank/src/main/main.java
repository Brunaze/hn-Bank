package main;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import components.Account;
import components.Client;
import components.CurrentAccount;
import components.SavingsAccount;

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
		clients.stream()
        		.map(Client::toString)
        		.forEach(System.out::println);
	}
	
	
	// 1.2.3 Creation of the table account
	
	public static List<Account> generateAccounts(List<Client> clients){
		
		List<Account> accounts = new ArrayList<>();
		
		for (Client client : clients) {
			accounts.add(new CurrentAccount(client));
			accounts.add(new SavingsAccount(client));
		}
		
		return accounts;
	}
	
	
	public static void displayAccounts(List<Account> accounts) {
        accounts.stream()
                .map(Account::toString)
                .forEach(System.out::println);
    }
	
	
	
	// 1.3.1 Adaptation of the table of accounts
	
	public static Hashtable<Integer, Account> convertHashtable(List<Account> accounts){
		
		Hashtable<Integer, Account> accountHashtable = new Hashtable<>();
		for (Account account: accounts) {
			accountHashtable.put(account.getAccountNumber(), account);
		}
		
		return accountHashtable;
	}
	
	
	public static void displayAccountSorted(Hashtable<Integer, Account> accountHashtable) {
		accountHashtable.values()
				.stream()
				.sorted((a1, a2) -> Double.compare(a1.getBalance(), a2.getBalance()))
		        .forEach(System.out::println);
	}
	
	
	// Main

	public static void main(String[] args) {
		List<Client> clients = generateClients(3);

		displayClients(clients);
		
		List<Account> accounts = generateAccounts(clients);
		
		displayAccounts(accounts);
		
		System.out.println("Hashtable :");
		
		Hashtable<Integer, Account> accountHashtable = convertHashtable(accounts);
		
		displayAccountSorted(accountHashtable);
	}

}
