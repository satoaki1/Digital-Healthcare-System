package com.example.digitalhealthcaresystem.DataStorageControllers;

import com.example.digitalhealthcaresystem.CalenderFormController;
import com.example.digitalhealthcaresystem.DashboardController;
import com.example.digitalhealthcaresystem.MedicalReviewForms.MedicalReviewFormController;
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
    private Button goToCalenderButton;

    @FXML
    private Button goToDashboardButton;

    @FXML
    private Button exitButton;

    @FXML
    public void handlePatientInformationDataButton() {
        patientInformationDataButton.setOnAction(event -> {
            // Instantiate the PatientInformationDataController
            PatientInformationDataController patientInformationDataController = new PatientInformationDataController();
            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Call the showPatientInformationDataForm method to display the patient information data form
            patientInformationDataController.showPatientInformationDataForm(stage);
        });
    }

    @FXML
    public void handlePatientHistoryDataButton() {
        patientHistoryDataButton.setOnAction(event -> {
            // Instantiate the PatientHistoryDataController
            PatientHistoryDataController patientHistoryDataController = new PatientHistoryDataController();
            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Call the showPatientHistoryDataForm method to display the patient history data form
            patientHistoryDataController.showPatientHistoryDataForm(stage);
        });
    }

    @FXML
    public void handleTreatmentCourseDataButton() {
        treatmentCourseDataButton.setOnAction(event -> {
            // Instantiate the TreatmentCourseDataController
            TreatmentCourseDataController treatmentCourseDataController = new TreatmentCourseDataController();
            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Call the showTreatmentCourseDataForm method to display the treatment course data form
            treatmentCourseDataController.showTreatmentCourseDataForm(stage);
        });
    }

    @FXML
    public void handleMedicalReviewDataButton() {
        medicalReviewDataButton.setOnAction(event -> {
            // Instantiate the MedicalReviewFormController
            MedicalReviewFormController medicalReviewFormController = new MedicalReviewFormController();
            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Call the showMedicalReviewForm method to display the medical review form
            medicalReviewFormController.showMedicalReviewForm(stage);
        });
    }

    @FXML
    public void handleGoToDashboardButton(ActionEvent event) {
        // Instantiate the DashboardController
        DashboardController dashboardController = new DashboardController();
        // Get the stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Call the loadDashboardView method to go to the dashboard view
        dashboardController.loadDashboardView(stage);
    }

    @FXML
    public void handleGoToCalenderButton(ActionEvent event) {
        // Instantiate the CalenderFormController
        CalenderFormController calenderFormController = new CalenderFormController();
        // Get the stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Call the showCalenderForm method to display the calendar form
        calenderFormController.showCalenderForm(stage);
    }

    @FXML
    public void handleExitButton(ActionEvent event) {
        // Exit the application
        System.exit(1);
    }

    @FXML
    public void showDataStorageView(Stage stage) {
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
