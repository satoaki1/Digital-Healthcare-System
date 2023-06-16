package com.example.digitalhealthcaresystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Button loginWithGoogleButton;

    @FXML
    private Button loginWithFacebookButton;

    @FXML
    private Button loginWithInstagramButton;

    @FXML
    private Button userSupportButton;

    @FXML
    public void handleSignInButton() {
        signInButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

//            if (username == null || password == null) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error");
//                alert.setHeaderText(null);
//                alert.setContentText("Please enter your username and password.");
//                alert.showAndWait();
//            }
//
//            System.out.println(username + " " + password);
        });
    }

    @FXML
    public void handleLoginWithGoogleButton() {
        loginWithGoogleButton.setOnAction(event -> {
//            System.out.println("Google");
        });
    }

    @FXML
    public void handleLoginWithFacebookButton() {
        loginWithFacebookButton.setOnAction(event -> {
//            System.out.println("Facebook");
        });
    }

    @FXML
    public void handleLoginWithInstagramButton() {
        loginWithInstagramButton.setOnAction(event -> {
//            System.out.println("Instagram");
        });
    }

    @FXML
    public void handleUserSupportButton() {
        userSupportButton.setOnAction(event -> {
//            System.out.println("User Support");
        });
    }

    public void loadSignInView(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
            Parent root = loader.load();

            // Create a new scene with the root parent
            Scene scene = new Scene(root);

            // Set the scene on the primary stage
            stage.setScene(scene);
            stage.setTitle("Digital Healthcare System");

            // Show the primary stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
