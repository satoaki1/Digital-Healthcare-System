package com.example.digitalhealthcaresystem.DataStorageControllers;

import com.example.digitalhealthcaresystem.CalenderFormController;
import com.example.digitalhealthcaresystem.DashboardController;
import com.example.digitalhealthcaresystem.DispensaryForms.DispensaryFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DataStorageController {
    @FXML
    private Button patientInformationDataButton;

    @FXML
    private Button patientHistoryDataButton;

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
    private Button goToCalenderButton;

    @FXML
    private Button goToDashboardButton;

    @FXML
    private Button exitButton;
    
    @FXML
    public void handlePatientInformationDataButton() {
        patientInformationDataButton.setOnAction(event -> {
            PatientInformationDataController patientInformationDataController = new PatientInformationDataController();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            patientInformationDataController.showPatientInformationDataForm(stage);
        });
    }

    @FXML
    public void handlePatientHistoryDataButton() {
        patientHistoryDataButton.setOnAction(event -> {
            PatientHistoryDataController patientHistoryDataController = new PatientHistoryDataController();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            patientHistoryDataController.showPatientHistoryDataForm(stage);
        });
    }

    @FXML
    public void handleTreatmentCourseDataButton() {
        treatmentCourseDataButton.setOnAction(event -> {
            TreatmentCourseDataController treatmentCourseDataController = new TreatmentCourseDataController();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            treatmentCourseDataController.showTreatmentCourseDataForm(stage);
        });
    }

    @FXML
    public void handleMedicalReviewDataButton() {
        medicalReviewDataButton.setOnAction(event -> {
            System.out.println("medicalReviewDataButton");
        });
    }

    @FXML
    public void handleDispensaryDataButton() {
        dispensaryDataButton.setOnAction(event -> {
            DispensaryFormController dispensaryFormController = new DispensaryFormController();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            dispensaryFormController.showDispensaryForm(stage);
        });
    }

    @FXML
    public void handleGoToDashboardButton(ActionEvent event) {
        DashboardController dashboardController = new DashboardController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        dashboardController.loadDashboardView(stage);
    }

    @FXML
    public void handleGoToCalenderButton(ActionEvent event) {
        CalenderFormController calenderFormController = new CalenderFormController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        calenderFormController.showCalenderForm(stage);
    }

    @FXML
    public void handleExitButton(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    public void showDataStorageView(Stage stage) {
        DashboardController dashboardController = new DashboardController();
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/digitalhealthcaresystem/DataStorageDocuments/DataStorage.fxml"));
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
