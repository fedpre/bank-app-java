package com.example.bankappproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountController {

    @FXML
    private BankAccount account;
    @FXML
    private Button getData;
    @FXML
    private Text accountName;
    @FXML
    private Label currentBalance;
    @FXML
    private TextField depositInput;
    @FXML
    private Label depositLabel;
    @FXML
    private Button depositBtn;
    @FXML
    private TextField withdrawInput;
    @FXML
    private Label withdrawLabel;
    @FXML
    private Button withdrawBtn;
    @FXML
    private TableView tableView;

    @FXML
    private TableColumn tranCol;
    @FXML
    private TableColumn amountCol;
    @FXML
    private TableColumn balCol;
    @FXML
    private TableColumn dateCol;

    @FXML
    private ObservableList<Transaction> transactions = FXCollections.observableArrayList();

    @FXML
    private void receiveData(ActionEvent event){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        this.account = (BankAccount) stage.getUserData();
        accountName.setText("Welcome " + this.account.getName());
        currentBalance.setText("Current balance: $" + this.account.getBalance());
        depositLabel.setStyle("visibility: visible;");
        depositInput.setStyle("visibility: visible;");
        depositBtn.setStyle("visibility: visible;");
        withdrawLabel.setStyle("visibility: visible;");
        withdrawInput.setStyle("visibility: visible;");
        withdrawBtn.setStyle("visibility: visible;");
        tableView.setStyle("visibility: visible;");
        getData.setText("Back");
        getData.setOnAction(e -> {
            try {
                backAction(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        setTransactionHistory(this.account);
    }
    @FXML
    private void depositAction(ActionEvent event) {
        double deposit = Double.parseDouble(depositInput.getText());
        this.account.deposit(deposit);
        currentBalance.setText("Current balance: $" + this.account.getBalance());
        depositInput.setText("");
        setTransactionHistory(this.account);
    }
    @FXML
    private void withdrawAction(ActionEvent event) {
        double withdraw = Double.parseDouble(withdrawInput.getText());
        this.account.withdraw(withdraw);
        currentBalance.setText("Current balance: $" + this.account.getBalance());
        withdrawInput.setText("");
        setTransactionHistory(this.account);
    }
    @FXML
    private void backAction(ActionEvent event) throws IOException {
        Parent accountView = FXMLLoader.load(getClass().getResource("login-view.fxml"));

        Scene accountViewScene = new Scene(accountView, 400, 375);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(accountViewScene);
        window.show();
    }

    protected void setTransactionHistory(BankAccount account) {
        ArrayList<String[]> transCSV = account.getCurrentTransactions();
        this.transactions.clear();

        for (String[] t : transCSV) {
            Transaction newTransaction = new Transaction();
            List<String> list = new ArrayList<String>(Arrays.asList(Arrays.toString(t).split(",")));
            newTransaction.setTransaction(list.get(0).replace("[", ""));
            newTransaction.setAmount(Double.parseDouble(list.get(1)));
            newTransaction.setNewBalance(Double.parseDouble(list.get(2)));
            newTransaction.setDate(list.get(3).replace(" ", ""));
            this.transactions.add(newTransaction);
        }

        tranCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("transaction"));
        amountCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amount"));
        balCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("newBalance"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date"));

        tableView.setItems(this.transactions);
    }
}



