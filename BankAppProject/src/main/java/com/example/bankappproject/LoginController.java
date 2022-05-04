package com.example.bankappproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

// Class to handle the functions on the login page
public class LoginController {
    // Get the objects connected with the FXML page
    BankAccount account;
    @FXML
    private Label username;
    @FXML
    private TextField usernameInput;
    @FXML
    private Label amount;
    @FXML
    private Button goTo;
    // Process the event of clicking the button to get the account connected to the name input
    // If no name is found, a new CSV will be created
    @FXML
    protected void onBtnClick(ActionEvent event) {
        // Get the input from the input text
        String userInput = usernameInput.getText();
        // Create a new bank account object with the provided name
        this.account = new BankAccount(userInput);
        // Set the labels to show the data of the bank account
        username.setText("Account name: " + this.account.getName());
        amount.setText("Account balance: $" + this.account.getBalance());
        goTo.setStyle("visibility: visible;");
        goTo.setText("Go to account");
    }
    // Method that handles the button to go to the Account page
    @FXML
    protected void goToAction(ActionEvent event) throws IOException {
        // Load the FXML file
        Parent accountView = FXMLLoader.load(getClass().getResource("account-view.fxml"));
        // Create the new scene with the account page
        Scene accountViewScene = new Scene(accountView, 500, 675);
        // Set the scene on the stage
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        // Pass the account object to the userDate
        window.setUserData(this.account);
        // Set the scene in the window
        window.setScene(accountViewScene);
        // Show the window
        window.show();
    }
}
