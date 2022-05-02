package com.example.bankappproject;

import java.util.ArrayList;
import java.util.List;

public interface Bank {
    String getName();
    double getBalance();
    void setName(String name);
    void setBalance(double balance);
    void setTransactions(ArrayList<String[]> transactions);
    void deposit(double amount);
    boolean withdraw(double amount);
    ArrayList<String[]> getCurrentTransactions();
    List<String[]> getHistoryTransactions(int skipLines);
}
