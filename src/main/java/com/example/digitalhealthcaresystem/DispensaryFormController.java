package com.example.digitalhealthcaresystem;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DispensaryFormController {
    @FXML
    private Button patientReviewButton;

    @FXML
    private Button treatmentCourseButton;

    @FXML
    private Button requiredMedicationButton;

    @FXML
    private Button requiredDosageButton;

    @FXML
    private Button medicationFrequencyButton;

    @FXML
    private Button followUpDateButton;

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
    public void handlePatientReviewButton() {
        patientReviewButton.setOnAction(event1 -> System.out.println("patientReviewButton"));
    }

    @FXML
    public void handleTreatmentCourseButton() {
        treatmentCourseButton.setOnAction(event1 -> System.out.println("treatmentCourseButton"));
    }

    @FXML
    public void handleRequiredMedicationButton() {
        requiredMedicationButton.setOnAction(event1 -> System.out.println("requiredMedicationButton"));
    }

    @FXML
    public void handleRequiredDosageButton() {
        requiredDosageButton.setOnAction(event1 -> System.out.println("requiredDosageButton"));
    }

    @FXML
    public void handleMedicationFrequencyButton() {
        medicationFrequencyButton.setOnAction(event1 -> System.out.println("medicationFrequencyButton"));
    }

    @FXML
    public void handleFollowUpDateButton() {
        followUpDateButton.setOnAction(event1 -> System.out.println("followUpDateButton"));
    }

    @FXML
    public void handleViewButton() {
        viewButton.setOnAction(event1 -> System.out.println("viewButton"));
    }

    @FXML
    public void handleEditButton() {
        editButton.setOnAction(event1 -> System.out.println("editButton"));
    }

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
    public void showDispensaryForm(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DispensaryForm.fxml"));
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
