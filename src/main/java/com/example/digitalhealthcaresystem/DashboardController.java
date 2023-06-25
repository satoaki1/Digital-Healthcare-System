package com.example.digitalhealthcaresystem;

import com.example.digitalhealthcaresystem.PatientForms.ViewPatientInformationFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DashboardController {
    private static final String FILE_PATH = "patients.txt";

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
    private Button goToCalenderButton;

    @FXML
    private Button goToDashboardButton;

    @FXML
    private Button exitButton;

    @FXML
    private TextField patientField;

    @FXML
    private Button submitButton;

    @FXML
    public void handlePatientHistoryLink(ActionEvent event) {
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
        showExitConfirmation();
    }

    @FXML
    public void handleSearchButton(ActionEvent event) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String patientName = patientField.getText();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[1].equals(patientName)) {
                    continue;
                } else {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    try {
                        // Correctly point to the FXML file
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/digitalhealthcaresystem/PatientFormDocuments/ViewPatientInformationForm.fxml"));
                        Parent root = loader.load();

                        // Create a new scene with the root parent
                        Scene scene = new Scene(root);

                        // Set the scene on the primary stage
                        stage.setScene(scene);
                        stage.setTitle("Digital Healthcare System");

                        // Show the primary stage
                        stage.show();

                        // Get the controller
                        ViewPatientInformationFormController viewPatientInformationFormController = loader.getController();

                        viewPatientInformationFormController.searchPatient(patientName);
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showExitConfirmation() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Unsaved Changes");
        alert.setContentText("Are you sure you want to exit without saving?");

        ButtonType exitButton = new ButtonType("Exit");
        ButtonType cancelButton = new ButtonType("Cancel");

        alert.getButtonTypes().setAll(exitButton, cancelButton);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == exitButton) {
                System.exit(0); // Replace this with your application's exit logic
            }
        });
    }

    @FXML
    public void loadDashboardView(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();
            DashboardController dashboardController = loader.getController();

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
