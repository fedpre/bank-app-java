package com.example.bankappproject;

import javafx.beans.property.SimpleStringProperty;
// Class to instantiate a transaction object
public class Transaction {
    // Create the class variables
    private SimpleStringProperty transaction;
    private SimpleStringProperty amount;
    private SimpleStringProperty newBalance;
    private SimpleStringProperty dateCreated;
    // Class constructor
    public Transaction(){
        this.transaction = new SimpleStringProperty("");
        this.amount = new SimpleStringProperty("");
        this.newBalance = new SimpleStringProperty("");
        this.dateCreated = new SimpleStringProperty("");
    }
    // Second class constructor
    public Transaction(String transactionName, Double amount, Double newBalance, String dateCreated) {
        this.transaction = new SimpleStringProperty(transactionName);
        this.amount = new SimpleStringProperty(String.valueOf(amount));
        this.newBalance = new SimpleStringProperty(String.valueOf(newBalance));
        this.dateCreated = new SimpleStringProperty(dateCreated);
    }
    // Method to return the transaction type
    public String getTransaction() {
        return transaction.get();
    }
    // Method to set the transaction type
    public void setTransaction(String transaction) {
        this.transaction.set(transaction);
    }
    // Method to get the amount of the transaction
    public String getAmount() {
        return amount.get();
    }
    // Method to set the amount of the transaction
    public void setAmount(Double amount) {
        this.amount.set("$" + amount);
    }
    // Method to get the new balance
    public String getNewBalance() {
        return newBalance.get();
    }
    // Method to set the new balance
    public void setNewBalance(Double newBalance) {
        this.newBalance.set("$" + newBalance);
    }
    // Method to get the date
    public String getDate() {
        return dateCreated.get();
    }
    // Method to set the date
    public void setDate(String date) {
        this.dateCreated.set(date);
    }
}
