package com.example.bankappproject;

import java.util.ArrayList;
import java.util.List;
// Bank interface to show all the methods that are necessary to instantiate a new BankAccount class
public interface Bank {
    // Method to get the name of the account holder
    String getName();
    // Method to get the current balance of the account
    double getBalance();
    // Method to set the name of the account holder in case we need to change it
    void setName(String name);
    // Method to set the balance if there is a need to manually change it
    void setBalance(double balance);
    // Method to set the transactions inside an ArrayList<String[]>
    void setTransactions(ArrayList<String[]> transactions);
    // Method to process the request of depositing money in the account
    void deposit(double amount);
    // Method to process the request of withdrawing money from the account
    boolean withdraw(double amount);
    // Method that returns the current transactions
    ArrayList<String[]> getCurrentTransactions();
    // Method that return the transaction history from the CSV file
    List<String[]> getHistoryTransactions(int skipLines);
}
