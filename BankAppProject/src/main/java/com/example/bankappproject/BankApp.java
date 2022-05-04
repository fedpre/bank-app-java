package com.example.bankappproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Class that handles the start of the program
public class BankApp extends Application  {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file for the login page
        FXMLLoader fxmlLoader = new FXMLLoader(BankApp.class.getResource("login-view.fxml"));
        // Set the FXML file as the new scene
        Scene scene = new Scene(fxmlLoader.load(), 400, 375);
        // Set the title of the app
        stage.setTitle("Bank Account Application");
        // Set the new scene in the stage
        stage.setScene(scene);
        // Show the scene
        stage.show();
    }
    // Launch the app
    public static void main(String[] args) { launch(); }
}
