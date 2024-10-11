package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import components.Account;
import components.Client;
import components.Credit;
import components.CurrentAccount;
import components.Debit;
import components.Flow;
import components.SavingsAccount;
import components.Transfert;

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
		accountHashtable.values().stream()
				.sorted((a1, a2) -> Double.compare(a1.getBalance(), a2.getBalance()))
		        .forEach(System.out::println);
	}
	
	
	
	// 1.3.4 Creation of the flow array
	
	public static List<Flow> generateFlows(List<Account> accounts){
		
		List<Flow> flows = new ArrayList<>();
		
		// Flow 1
		flows.add(new Debit("Débit de 50e", 50, 1, true));
		
		// Flow 2
		for (Account account : accounts) {
			if (account instanceof CurrentAccount) {
				flows.add(new Credit("Credit de 100.50e", 100.50, account.getAccountNumber(), true));
			}
		}
		
		// Flow 3
		for (Account account : accounts) {
			if (account instanceof SavingsAccount) {
				flows.add(new Credit("Credit de 1500e", 1500, account.getAccountNumber(), true));
			}
		}
		
		// Flow 4
		flows.add(new Transfert("Transfert de compte 1 à compte 2 de 50e", 50, 2, 1, true));
		
		return flows;
	}
	
	
	// 1.3.5 Updating accounts
	
	public static void applyFlows(List<Flow> flows, Hashtable<Integer, Account> accountHashtable) {
		
		// Appliquer les flows
		for (Flow flow : flows) {
			Account account = accountHashtable.get(flow.getTargetAccountNumber());
			account.setBalance(flow);
		}
		
		// Verifier les comptes négatifs
		Optional<Account> negativeBalanceAccount = accountHashtable.values().stream()
		        .filter(account -> account.getBalance() < 0)
		        .findFirst();
		
		if (negativeBalanceAccount.isPresent()) {
			System.out.println("Il y a au moins un compte avec un solde négatif");
		}

		// Afficher tous les comptes triés
		accountHashtable.values().stream()
		    .sorted((acc1, acc2) -> Double.compare(acc1.getBalance(), acc2.getBalance()))
		    .forEach(System.out::println);
	}
	
	
	public static List<Flow> loadFlowsFromJson() {
        List<Flow> flows = new ArrayList<>();
        Path jsonFilePath = Paths.get("src/resources/flows.json");

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(jsonFilePath.toString())) {
            // Parse le fichier JSON
            Object obj = jsonParser.parse(reader);

            // Convertir en JSONArray
            JSONArray flowList = (JSONArray) obj;

            // Parcourir la liste de flux
            for (Object flowObj : flowList) {
                JSONObject flowJson = (JSONObject) flowObj;

                // Lire les propriétés communes
                String type = (String) flowJson.get("type");
                String comment = (String) flowJson.get("comment");
                double amount = (double) flowJson.get("amount");
                int targetAccountNumber = (int) flowJson.get("targetAccountNumber");
                int transferAccountNumber = (int) flowJson.get("transferAccountNumber");
                boolean effect = (boolean) flowJson.get("effect");

                // Vérifier le type de flux et créer les objets correspondants
                switch (type) {
                    case "Credit":
                        flows.add(new Credit(comment, amount, targetAccountNumber, effect));
                        break;
                    case "Debit":
                        flows.add(new Debit(comment, amount, targetAccountNumber, effect));
                        break;
                    case "Transfer":
                        long issuingAccountNumber = (long) flowJson.get("issuingAccountNumber");
                        flows.add(new Transfert(comment, amount, targetAccountNumber, transferAccountNumber, effect));
                        break;
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return flows;
    }
	
	
	// Main

	public static void main(String[] args) throws IOException {
		List<Client> clients = generateClients(3);

		displayClients(clients);
		
		List<Account> accounts = generateAccounts(clients);
		
		displayAccounts(accounts);
		
		System.out.println("Hashtable :");
		
		Hashtable<Integer, Account> accountHashtable = convertHashtable(accounts);
		
		displayAccountSorted(accountHashtable);
		
		System.out.println("Post flows :");
		
		List<Flow> flows = loadFlowsFromJson();
		
		applyFlows(flows, accountHashtable);
	}

}
