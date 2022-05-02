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


public class LoginController {

    BankAccount account;
    @FXML
    private Label username;
    @FXML
    private TextField usernameInput;
    @FXML
    private Label amount;
    @FXML
    private Button goTo;
    @FXML
    protected void onBtnClick(ActionEvent event) {
        String userInput = usernameInput.getText();
        this.account = new BankAccount(userInput);
        username.setText("Account name: " + this.account.getName());
        amount.setText("Account balance: $" + this.account.getBalance());
        goTo.setStyle("visibility: visible;");
        goTo.setText("Go to account");
    }
    @FXML
    protected void goToAction(ActionEvent event) throws IOException {
        Parent accountView = FXMLLoader.load(getClass().getResource("account-view.fxml"));

        Scene accountViewScene = new Scene(accountView, 500, 675);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setUserData(this.account);

        window.setScene(accountViewScene);
        window.show();
    }
}
