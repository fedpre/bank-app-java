package com.example.bankappproject;

import java.util.*;
// Class BankAccount to store all the information about transactions, balance, and history of an account owner.
public class BankAccount extends FileHandler implements Bank{
    private double balance;
    private String name;
    private ArrayList<String[]> transactions = new ArrayList<>();

    // Class constructor
    public BankAccount(String name) {
        if (csvExist(name)) {
            loadAccount(name);
        } else {
            createNewAccount(name);
        }
    }
    // Get the name of the owner of the bank account
    public String getName() {
        return this.name;
    }
    // Get the balance - value
    public double getBalance() {
        return this.balance;
    }
    // Get the list of transactions
    public ArrayList<String[]> getCurrentTransactions() { return this.transactions; }
    // Get history of all transactions
    public List<String[]> getHistoryTransactions(int skipLines) { return loadCSV(this.name, skipLines); }
    // Set the name of the bank account object
    public void setName(String name) { this.name = name; }
    // Set the balance of the bank account
    public void setBalance(double balance) { this.balance = balance; }
    // Set transaction history
    public void setTransactions(ArrayList<String[]> transactions) { this.transactions = transactions; }
    // Create a new bank account
    private void createNewAccount(String name) {
        // Set the name of the account to the name provided by the user
        this.name = name;
        // Set the balance of the account to the balance provided by the user
        this.balance = 0;
        // Create the CSV file to store all the information related to this account
        createCSV(this.name, this.balance);
    }
    private void loadAccount(String name) {
        // Proceed to load the CSV file and get all the useful information
        // Set the name of the account with the name provided by the user
        this.name = name;
        // Load all the data in the CSV file inside a List data structure
        List<String[]> loadedData = loadCSV(name, 0);
        // Set the balance as the balance in the CSV file
        this.balance = Double.parseDouble(loadedData.get(1)[1]);
        // Create an ArrayList data structure to load the transaction history only
        ArrayList<String[]> loadedTransactions = new ArrayList<>();
        // Loop through the CSV file to get all the transactions
        for (int i = 3; i < loadedData.size(); i++) {
            loadedTransactions.add(loadedData.get(i));
            this.balance = Double.parseDouble(loadedData.get(i)[2]);
        }
        // Set the transactions with the loaded transactions
        this.transactions = loadedTransactions;
    }
    // Method to deposit money
    public void deposit(double amount) {
        this.balance += amount;
        // Create a transaction string to add to the CSV
        String[] transaction = { "deposit", String.valueOf(amount), String.valueOf(this.balance), createCurrentDate()};
        // Add the transaction to the CSV file
        addRow(transaction);
        // Add the transaction to the ArrayList of the class to store them
        transactions.add(transaction);
    }
    // Method to withdraw money
    public boolean withdraw(double amount) {
        if (this.balance - amount < 0) {
            System.out.println("Not enough money in the bank account.");
            return false;
        }
        this.balance -= amount;
        String[] transaction = { "withdraw", String.valueOf(amount), String.valueOf(this.balance), createCurrentDate() };
        addRow(transaction);
        transactions.add(transaction);
        return true;
    }
    public String toString() {
        return "Account owner: " + this.name + " - Balance: $" + this.balance;
    }

}