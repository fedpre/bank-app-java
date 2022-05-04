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
// Controller class for the Account page. All the actions are processed in this class
public class AccountController {
    // Connect the variable in the FXML file with the controller
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
    private final ObservableList<Transaction> transactions = FXCollections.observableArrayList();

    // Class that receives and process the data from the login page
    // the action is controller by the "Get data" button in the Account page
    @FXML
    private void receiveData(ActionEvent event){
        // Create a node from the event source
        Node node = (Node) event.getSource();
        // Get the stage object from the event node
        Stage stage = (Stage) node.getScene().getWindow();
        // Get the userData passed from the other page
        // In this case, the BankAccount was passed down to perform additional actions on it
        this.account = (BankAccount) stage.getUserData();
        // Dynamically add the information of the BankAccount to the related objects
        // The objects were invisible until the information are received so the actions can be properly performed
        accountName.setText("Welcome " + this.account.getName());
        currentBalance.setText("Current balance: $" + this.account.getBalance());
        depositLabel.setStyle("visibility: visible;");
        depositInput.setStyle("visibility: visible;");
        depositBtn.setStyle("visibility: visible;");
        withdrawLabel.setStyle("visibility: visible;");
        withdrawInput.setStyle("visibility: visible;");
        withdrawBtn.setStyle("visibility: visible;");
        tableView.setStyle("visibility: visible;");
        // Set the "Get data" button to "Back" to allow the user to navigate to the previous page and open another account
        getData.setText("Back");
        getData.setOnAction(e -> {
            try {
                // Method to process the action of moving back to the login page
                backAction(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        // Method that sets the transaction history from the CSV file
        setTransactionHistory(this.account);
    }
    // Method that performs the action of creating an entry into the CSV file connected to the account
    // In this case, the action is "Deposit"
    @FXML
    private void depositAction(ActionEvent event) {
        // Get the input from the input field on the screen
        double deposit = Double.parseDouble(depositInput.getText());
        // Call the "deposit" method from the BankAccount class
        this.account.deposit(deposit);
        // Update the text of the current balance
        currentBalance.setText("Current balance: $" + this.account.getBalance());
        // Set the input text to blank
        depositInput.setText("");
        // Update the transaction history to show the new transaction
        setTransactionHistory(this.account);
    }
    // Method that performs the action of creating an entry into the CSV file connected to the account
    // In this case, the action is "withdraw"
    @FXML
    private void withdrawAction(ActionEvent event) {
        // Get the input from the input field on the screen
        double withdraw = Double.parseDouble(withdrawInput.getText());
        // Call the "withdraw" method from the BankAccount class
        this.account.withdraw(withdraw);
        // Update the text of the current balance
        currentBalance.setText("Current balance: $" + this.account.getBalance());
        // Set the input text to blank
        withdrawInput.setText("");
        // Update the transaction history to show the new transaction
        setTransactionHistory(this.account);
    }
    // Process the request to go to a different page and load the associated FXML file
    @FXML
    private void backAction(ActionEvent event) throws IOException {
        // Load the FXML file
        Parent accountView = FXMLLoader.load(getClass().getResource("login-view.fxml"));
        // Create the new scene with the loaded FXML file
        Scene accountViewScene = new Scene(accountView, 400, 375);
        // Get the Stage from the ActionEvent
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        // Set the new scene in the stage
        window.setScene(accountViewScene);
        // Show the new scene in the stage
        window.show();
    }
    // Method to set the transaction history inside the TableView
    protected void setTransactionHistory(BankAccount account) {
        // Get all the current transactions
        ArrayList<String[]> transCSV = account.getCurrentTransactions();
        // Clear the transactions ObservableList from the previous transactions
        this.transactions.clear();
        // Iterate over the transactions
        for (String[] t : transCSV) {
            // For each new transaction, create a Transaction object
            Transaction newTransaction = new Transaction();
            // Split the row into separete fields and store them into a List<>
            List<String> list = new ArrayList<String>(Arrays.asList(Arrays.toString(t).split(",")));
            // Set the appropriate fields in the Transaction object to populate the object
            newTransaction.setTransaction(list.get(0).replace("[", ""));
            newTransaction.setAmount(Double.parseDouble(list.get(1)));
            newTransaction.setNewBalance(Double.parseDouble(list.get(2)));
            newTransaction.setDate(list.get(3).replace(" ", ""));
            // Add the transaction to the ObservableList<>
            this.transactions.add(newTransaction);
        }
        // Connect the property value to the column in the TableView
        tranCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("transaction"));
        amountCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amount"));
        balCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("newBalance"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date"));

        // Set the ObservableList in the TableView to be showed to the user
        tableView.setItems(this.transactions);
    }
}



