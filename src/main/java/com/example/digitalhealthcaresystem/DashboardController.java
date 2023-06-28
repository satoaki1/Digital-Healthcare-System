package com.example.digitalhealthcaresystem;

import com.example.digitalhealthcaresystem.DataStorageControllers.DataStorageController;
import com.example.digitalhealthcaresystem.MedicalReviewForms.MedicalReviewFormController;
import com.example.digitalhealthcaresystem.PatientForms.ViewPatientInformationFormController;
import com.example.digitalhealthcaresystem.PatientHistoryForms.PatientHistoryFormController;
import com.example.digitalhealthcaresystem.TreatmentCourseForms.ViewTreatmentCourseFormController;
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
    private static final String FILE_PATH = "Data/patients.txt";
    private static final String PERSONAL_INFO = "Data/candidates.txt";

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
    private Button userSupportButton;

    @FXML
    private Button userGuideButton;

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
        ViewTreatmentCourseFormController viewTreatmentCourseFormController = new ViewTreatmentCourseFormController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewTreatmentCourseFormController.showViewTreatmentCourseForm(stage);
    }

    @FXML
    public void handleMedicalReviewLink(ActionEvent event) {
        MedicalReviewFormController medicalReviewFormController = new MedicalReviewFormController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        medicalReviewFormController.showMedicalReviewForm(stage);
    }

    @FXML
    public void handleDataStorageLink(ActionEvent event) {
        DataStorageController dataStorageController = new DataStorageController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        dataStorageController.showDataStorageView(stage);
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
    public void handleSearchButton(ActionEvent event) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String patientName = patientField.getText();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(patientName)) {
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

    @FXML
    public void handleUserSupportButton() {
        userSupportButton.setOnAction(event -> {
            // Print a message indicating that user support is coming soon
            System.out.println("Coming Soon...");
        });
    }

    @FXML
    public void handleUserGuideButton() {
        userGuideButton.setOnAction(event -> {
            // Print a message indicating that the user guide is coming soon
            System.out.println("Coming Soon...");
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
