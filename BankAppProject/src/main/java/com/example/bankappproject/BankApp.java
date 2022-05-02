package com.example.bankappproject;

import java.io.IOException;
import java.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class BankApp extends Application  {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BankApp.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 375);
        stage.setTitle("Bank Account Application");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) { launch(); }
}
