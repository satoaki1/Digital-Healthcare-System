package com.example.digitalhealthcaresystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginFormController {

    private static final String FILE_PATH = "Data/candidates.txt";

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
            // Retrieve the entered username and password
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Check if either username or password is empty
            if (username == null || password == null) {
                // Display an error message indicating missing username or password
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter your username and password.");
                alert.showAndWait();
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[1].equals(username) && data[2].equals(password)) {
                        // If the username and password match, load the dashboard view
                        DashboardController dashboardController = new DashboardController();
                        Stage stage = (Stage) signInButton.getScene().getWindow();
                        dashboardController.loadDashboardView(stage);
                        displaySuccessMessage();

                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void handleLoginWithGoogleButton() {
        loginWithGoogleButton.setOnAction(event -> {
            // Print a message indicating that login with Google is coming soon
            System.out.println("Coming Soon");
        });
    }

    @FXML
    public void handleLoginWithFacebookButton() {
        loginWithFacebookButton.setOnAction(event -> {
            // Print a message indicating that login with Facebook is coming soon
            System.out.println("Coming Soon");
        });
    }

    @FXML
    public void handleLoginWithInstagramButton() {
        loginWithInstagramButton.setOnAction(event -> {
            // Print a message indicating that login with Instagram is coming soon
            System.out.println("Coming Soon");
        });
    }

    @FXML
    public void handleUserSupportButton() {
        userSupportButton.setOnAction(event -> {
            // Print a message indicating that user support is coming soon
            System.out.println("Coming Soon");
        });
    }

    private void displaySuccessMessage() {
        // Display a success message indicating successful login
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Successfully Logged In");
        alert.setContentText("The information was valid and logged in now.");
        alert.show();
    }


    public void loadSignInView(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
            Parent root = loader.load();
            loader.getController();

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
