package com.example.digitalhealthcaresystem.DataStorageControllers;

import com.example.digitalhealthcaresystem.CalenderFormController;
import com.example.digitalhealthcaresystem.DashboardController;
import com.example.digitalhealthcaresystem.PatientForms.CreatePatientInformationFormController;
import com.example.digitalhealthcaresystem.PatientForms.ViewPatientInformationFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.*;

public class PatientInformationDataController {

    private static final String FILE_PATH = "Data/patients.txt";

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
        // Instantiate the CreatePatientInformationFormController
        CreatePatientInformationFormController controller = new CreatePatientInformationFormController();
        // Get the stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Call the showCreatePatientInformationForm method to display the create patient information form
        controller.showCreatePatientInformationForm(stage);
    }

    public void initialize() {
        // Initialize the controller
        updatePatientList();

        listView.setOnMouseClicked(mouseEvent -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String patientName = listView.getSelectionModel().getSelectedItem();
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[1].equals(patientName)) {
                        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

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

                            // Call the searchPatient method to display the patient's information
                            viewPatientInformationFormController.searchPatient(patientName);
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

    // Updating the patient list by clearing the current items in the ListView and adding each line from patients.txt as a new item in the listView.
    private void updatePatientList() {
        listView.getItems().clear();

        File file = new File(FILE_PATH);
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
    public void showPatientInformationDataForm(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/digitalhealthcaresystem/DataStorageDocuments/PatientInformationDataForm.fxml"));
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
