package com.example.digitalhealthcaresystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientInformationFormController {
    @FXML
    private Button goToHomeButton;

    @FXML
    private Button goToCalenderButton;

    @FXML
    private Button goToDashboardButton;

    @FXML
    private Button exitButton;

    @FXML
    public void handleGoToHomeButton() {
        goToHomeButton.setOnAction(event1 -> System.out.println("goToHomeButton"));
    }

    @FXML
    public void handleGoToDashboardButton() {
        goToDashboardButton.setOnAction(event1 -> {
            DashboardController dashboardController = new DashboardController();
            dashboardController.loadDashboardView(new Stage());
        });
    }

    @FXML
    public void handleGoToCalenderButton() {
        goToCalenderButton.setOnAction(event1 -> System.out.println("goToCalenderButton"));
    }

    @FXML
    public void handleExitButton() {
        exitButton.setOnAction(event1 -> {
            System.exit(1);
        });
    }

    @FXML
    public void showPatientInformationForm(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientInformationForm.fxml"));
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
