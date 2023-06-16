package com.example.digitalhealthcaresystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DataStorageFormController {
    @FXML
    private Button patientInformationDataButton;

    @FXML
    private Button medicalHistoryDataButton;

    @FXML
    private Button treatmentCourseDataButton;

    @FXML
    private Button medicalReviewDataButton;

    @FXML
    private Button dispensaryDataButton;
    
    @FXML
    private Button viewButton;

    @FXML
    private Button editButton;

    @FXML
    private Button goToHomeButton;

    @FXML
    private Button goToCalenderButton;

    @FXML
    private Button goToDashboardButton;

    @FXML
    private Button exitButton;
    
    @FXML
    public void handlePatientInformationDataButton() {
        patientInformationDataButton.setOnAction(event -> System.out.println("patientInformationDataButton"));
    }

    @FXML
    public void handleMedicalHistoryDataButton() {
        medicalHistoryDataButton.setOnAction(event -> System.out.println("medicalHistoryDataButton"));
    }

    @FXML
    public void handleTreatmentCourseDataButton() {
        treatmentCourseDataButton.setOnAction(event -> System.out.println("treatmentCourseDataButton"));
    }

    @FXML
    public void handleMedicalReviewDataButton() {
        medicalReviewDataButton.setOnAction(event -> System.out.println("medicalReviewDataButton"));
    }

    @FXML
    public void handleDispensaryDataButton() {
        dispensaryDataButton.setOnAction(event -> System.out.println("dispensaryDataButton"));
    }
    @FXML
    public void handleViewButton() {
        viewButton.setOnAction(event -> System.out.println("viewButton"));
    }

    @FXML
    public void handleEditButton() {
        editButton.setOnAction(event -> System.out.println("editButton"));
    }

    @FXML
    public void handleGoToHomeButton() {
        goToHomeButton.setOnAction(event -> System.out.println("goToHomeButton"));
    }

    @FXML
    public void handleGoToDashboardButton(ActionEvent event) {
        DashboardController dashboardController = new DashboardController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        dashboardController.loadDashboardView(stage);
    }

    @FXML
    public void handleGoToCalenderButton() {
        goToCalenderButton.setOnAction(event -> System.out.println("goToCalenderButton"));
    }

    @FXML
    public void handleExitButton() {
        exitButton.setOnAction(event1 -> {
            System.exit(1);
        });
    }

    @FXML
    public void showDataStorageView(Stage stage) {
        DashboardController dashboardController = new DashboardController();
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DataStorageForm.fxml"));
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
