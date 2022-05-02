package com.example.bankappproject;

import javafx.beans.property.SimpleStringProperty;

public class Transaction {
    private SimpleStringProperty transaction;
    private SimpleStringProperty amount;
    private SimpleStringProperty newBalance;
    private SimpleStringProperty dateCreated;

    public Transaction(){
        this.transaction = new SimpleStringProperty("");
        this.amount = new SimpleStringProperty("");
        this.newBalance = new SimpleStringProperty("");
        this.dateCreated = new SimpleStringProperty("");
    }

    public Transaction(String transactionName, Double amount, Double newBalance, String dateCreated) {
        this.transaction = new SimpleStringProperty(transactionName);
        this.amount = new SimpleStringProperty(String.valueOf(amount));
        this.newBalance = new SimpleStringProperty(String.valueOf(newBalance));
        this.dateCreated = new SimpleStringProperty(dateCreated);
    }

    public String getTransaction() {
        return transaction.get();
    }

    public void setTransaction(String transaction) {
        this.transaction.set(transaction);
    }

    public String getAmount() {
        return amount.get();
    }

    public void setAmount(Double amount) {
        this.amount.set("$" + amount);
    }

    public String getNewBalance() {
        return newBalance.get();
    }

    public void setNewBalance(Double newBalance) {
        this.newBalance.set("$" + newBalance);
    }

    public String getDate() {
        return dateCreated.get();
    }

    public void setDate(String date) {
        this.dateCreated.set(date);
    }

}
