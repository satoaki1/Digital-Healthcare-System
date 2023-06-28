package com.example.digitalhealthcaresystem.PatientHistoryForms;

import com.example.digitalhealthcaresystem.CalenderFormController;
import com.example.digitalhealthcaresystem.DashboardController;
import com.example.digitalhealthcaresystem.PatientForms.ViewPatientInformationFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PatientHistoryFormController {

    private static final String FILE_PATH = "Data/patientHistories.txt";

    @FXML
    private Button addNewButton;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button goToCalenderButton;

    @FXML
    private Button goToDashboardButton;

    @FXML
    private Button exitButton;

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

    public void initialize() {
        // Update the patient history list in the ListView
        updatePatientHistoryList();

        // Handle mouse click event on the ListView items
        listView.setOnMouseClicked(mouseEvent -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                // Get the selected patient record from the ListView
                String patientRecords = listView.getSelectionModel().getSelectedItem();
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if ((data[1] + " - " + data[4]).equals(patientRecords)) {
                        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

                        try {
                            // Load the FXML file for patient history details view
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/digitalhealthcaresystem/PatientHistoryFormDocuments/PatientHistoryDetails.fxml"));
                            Parent root = loader.load();

                            // Create a new scene with the loaded root parent
                            Scene scene = new Scene(root);

                            // Set the scene on the primary stage
                            stage.setScene(scene);
                            stage.setTitle("Digital Healthcare System");

                            // Show the primary stage
                            stage.show();

                            // Get the controller for the patient history details view
                            ViewPatientHistoryFormController viewPatientHistoryFormController = loader.getController();

                            // Search and display the selected patient history
                            viewPatientHistoryFormController.searchPatientHistory(patientRecords);
                            break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Updating the patient history list by clearing the current items in the ListView,
    // then opening patients.txt, reading each line, and adding each line as a new item in the ListView.
    private void updatePatientHistoryList() {
        listView.getItems().clear();

        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    listView.getItems().add(data[1] + " - " + data[4]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void openAddNewPage(ActionEvent event) {
        // Create an instance of CreatePatientHistoryFormController and show the create patient history form
        CreatePatientHistoryFormController createPatientHistoryFormController = new CreatePatientHistoryFormController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        createPatientHistoryFormController.showCreatePatientHistoryForm(stage);
    }


    @FXML
    public void showPatientHistoryForm(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/digitalhealthcaresystem/PatientHistoryFormDocuments/PatientHistoryFormList.fxml"));
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
