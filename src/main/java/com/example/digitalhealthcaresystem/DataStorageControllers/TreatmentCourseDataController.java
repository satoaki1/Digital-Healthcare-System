package com.example.digitalhealthcaresystem.DataStorageControllers;

import com.example.digitalhealthcaresystem.CalenderFormController;
import com.example.digitalhealthcaresystem.DashboardController;
import com.example.digitalhealthcaresystem.TreatmentCourseForms.CreateTreatmentCourseFormController;
import com.example.digitalhealthcaresystem.TreatmentCourseForms.ViewTreatmentCourseFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TreatmentCourseDataController {

    private static final String FILE_PATH = "Data/treatmentCourses.txt";

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

    @FXML
    public void openAddNewPage(ActionEvent event) {
        // Instantiate the CreateTreatmentCourseFormController
        CreateTreatmentCourseFormController createTreatmentCourseFormController = new CreateTreatmentCourseFormController();
        // Get the stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Call the showCreateTreatmentCourseForm method to display the create treatment course form
        createTreatmentCourseFormController.showCreateTreatmentCourseForm(stage);
    }

    public void initialize() {
        // Initialize the controller
        updateTreatmentCoursePatientList();

        listView.setOnMouseClicked(mouseEvent -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String patientName = listView.getSelectionModel().getSelectedItem();
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if ((data[1]).equals(patientName)) {
                        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

                        try {
                            // Correctly point to the FXML file
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/digitalhealthcaresystem/TreatmentCourseFormDocuments/ViewTreatmentCourseForm.fxml"));
                            Parent root = loader.load();

                            // Create a new scene with the root parent
                            Scene scene = new Scene(root);

                            // Set the scene on the primary stage
                            stage.setScene(scene);
                            stage.setTitle("Digital Healthcare System");

                            // Show the primary stage
                            stage.show();

                            // Get the controller
                            ViewTreatmentCourseFormController viewTreatmentCourseFormController = loader.getController();
                            // Call the searchPatient method to display the patient's treatment course
                            viewTreatmentCourseFormController.searchPatient(patientName);
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

    // Updating the patient list by clearing the current items in the list view
    // then opening treatmentCourses.txt, reading each line, and adding each line as a new item in the listView.
    private void updateTreatmentCoursePatientList() {
        listView.getItems().clear();

        File file = new File("Data/treatmentCourses.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    listView.getItems().add(data[1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void showTreatmentCourseDataForm(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/digitalhealthcaresystem/DataStorageDocuments/TreatmentCourseDataForm.fxml"));
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
