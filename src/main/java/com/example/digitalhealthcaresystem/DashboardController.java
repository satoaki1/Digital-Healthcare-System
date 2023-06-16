package com.example.digitalhealthcaresystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {
    @FXML
    private Hyperlink medicalHistoryLink;

    @FXML
    private Hyperlink treatmentCourseLink;

    @FXML
    private Hyperlink medicalReviewLink;

    @FXML
    private Hyperlink dispensaryLink;

    @FXML
    private Hyperlink dataStorageSystemLink;

    @FXML
    private Button goToHomeButton;

    @FXML
    private Button goToCalenderButton;

    @FXML
    private Button goToDashboardButton;

    @FXML
    private Button exitButton;

    @FXML
    public void handleMedicalHistoryLink() {
        PatientHistoryFormController patientHistoryFormController = new PatientHistoryFormController();
        medicalHistoryLink.setOnAction(event -> {
            patientHistoryFormController.showPatientHistoryForm(new Stage());
        });
    }

    @FXML
    public void handleTreatmentCourseLink() {
        TreatmentCourseFormController treatmentCourseFormController = new TreatmentCourseFormController();
        treatmentCourseLink.setOnAction(event -> {
            treatmentCourseFormController.showTreatmentCourseForm(new Stage());
        });
    }

    @FXML
    public void handleMedicalReviewLink() {
        MedicalReviewFormController medicalReviewFormController = new MedicalReviewFormController();
        medicalReviewLink.setOnAction(event -> {
            medicalReviewFormController.showMedicalReviewForm(new Stage());
        });
    }

    @FXML
    public void handleDispensaryLink() {
        DispensaryFormController dispensaryFormController = new DispensaryFormController();
        dispensaryLink.setOnAction(event -> {
            dispensaryFormController.showDispensaryForm(new Stage());
        });
    }

    @FXML
    public void handleDataStorageSystemLink() {
        DataStorageFormController dataStorageFormController = new DataStorageFormController();
        dataStorageSystemLink.setOnAction(event -> {
            dataStorageFormController.showDataStorageView(new Stage());
        });
    }

    @FXML
    public void handleGoToHomeButton() {
        goToHomeButton.setOnAction(event -> System.out.println("goToHomeButton"));
    }

    @FXML
    public void handleGoToDashboardButton() {
        goToDashboardButton.setOnAction(event -> System.out.println("goToDashboardButton"));
    }

    @FXML
    public void handleGoToCalenderButton() {
        goToCalenderButton.setOnAction(event -> System.out.println("goToCalenderButton"));
    }

    @FXML
    public void handleExitButton() {
        exitButton.setOnAction(event1 -> System.exit(1));
    }

    @FXML
    public void loadDashboardView(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
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
