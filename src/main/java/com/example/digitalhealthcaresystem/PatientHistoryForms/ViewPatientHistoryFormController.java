package com.example.digitalhealthcaresystem.PatientHistoryForms;

import com.example.digitalhealthcaresystem.CalenderFormController;
import com.example.digitalhealthcaresystem.DashboardController;
import com.example.digitalhealthcaresystem.DataStorageControllers.PatientHistoryDataController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewPatientHistoryFormController {
    private static final String FILE_PATH = "Data/patientHistories.txt";

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label admissionHistoryLabel;

    @FXML
    private Label pastSymptomsLabel;

    @FXML
    private Label majorComplaintsLabel;

    @FXML
    private Label observationsLabel;

    @FXML
    private Label treatmentCourseLabel;

    @FXML
    private Button goToCalenderButton;

    @FXML
    private Button goToDashboardButton;

    @FXML
    private Button exitButton;

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
    public void handleListButton(ActionEvent event) {
        PatientHistoryFormController patientHistoryFormController = new PatientHistoryFormController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        patientHistoryFormController.showPatientHistoryForm(stage);
    }

    @FXML
    public void handleAddNewButton(ActionEvent event) {
        CreatePatientHistoryFormController createPatientHistoryFormController = new CreatePatientHistoryFormController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        createPatientHistoryFormController.showCreatePatientHistoryForm(stage);
    }

    @FXML
    public void handleEditButton(ActionEvent event) {
        EditPatientHistoryFormController editPatientHistoryFormController = new EditPatientHistoryFormController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        editPatientHistoryFormController.showEditPatientHistoryForm(stage);
    }

    @FXML
    public void handleDeleteButton(ActionEvent event) {
        DeletePatientHistoryFormController deletePatientHistoryFormController = new DeletePatientHistoryFormController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        deletePatientHistoryFormController.showDeletePatientHistory(stage);
    }

    public void searchPatientHistory(String patientRecords) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if ((data[1] + " - " + data[4]).equals(patientRecords)) {
                    admissionHistoryLabel.setWrapText(true);
                    pastSymptomsLabel.setWrapText(true);
                    majorComplaintsLabel.setWrapText(true);
                    observationsLabel.setWrapText(true);
                    treatmentCourseLabel.setWrapText(true);

                    String id = data[0];
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    String gender = data[3];
                    String admissionHistory = data[4];
                    String pastSymptoms = data[5];
                    String majorComplaints = data[6];
                    String observations = data[7];
                    String treatmentCourse = data[8];

                    idLabel.setText(id);
                    nameLabel.setText(name);
                    ageLabel.setText(Integer.toString(age));
                    genderLabel.setText(gender);
                    admissionHistoryLabel.setText(admissionHistory);
                    pastSymptomsLabel.setText(pastSymptoms);
                    majorComplaintsLabel.setText(majorComplaints);
                    observationsLabel.setText(observations);
                    treatmentCourseLabel.setText(treatmentCourse);

                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPatientHistoryForm(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientHistoryFormDocuments/PatientHistoryDetails.fxml"));
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
