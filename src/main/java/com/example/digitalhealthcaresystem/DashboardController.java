package com.example.digitalhealthcaresystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    public void handleMedicalHistoryLink(ActionEvent event) {
        PatientHistoryFormController patientHistoryFormController = new PatientHistoryFormController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        patientHistoryFormController.showPatientHistoryForm(stage);
    }

    @FXML
    public void handleTreatmentCourseLink(ActionEvent event) {
        TreatmentCourseFormController treatmentCourseFormController = new TreatmentCourseFormController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        treatmentCourseFormController.showTreatmentCourseForm(stage);
    }

    @FXML
    public void handleMedicalReviewLink(ActionEvent event) {
        MedicalReviewFormController medicalReviewFormController = new MedicalReviewFormController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        medicalReviewFormController.showMedicalReviewForm(stage);
    }

    @FXML
    public void handleDispensaryLink(ActionEvent event) {
        DispensaryFormController dispensaryFormController = new DispensaryFormController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        dispensaryFormController.showDispensaryForm(stage);
    }

    @FXML
    public void handleDataStorageSystemLink(ActionEvent event) {
        DataStorageFormController dataStorageFormController = new DataStorageFormController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        dataStorageFormController.showDataStorageView(stage);
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
