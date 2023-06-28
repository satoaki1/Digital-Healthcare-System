package com.example.digitalhealthcaresystem.PatientForms;

import com.example.digitalhealthcaresystem.CalenderFormController;
import com.example.digitalhealthcaresystem.DashboardController;
import com.example.digitalhealthcaresystem.DataStorageControllers.PatientInformationDataController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewPatientInformationFormController {
    private static final String FILE_PATH = "Data/patients.txt";

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private Label bloodTypeLabel;

    @FXML
    private Label heightLabel;

    @FXML
    private Label weightLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label bpLabel;

    @FXML
    private Label occupationLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label contactNoLabel;

    @FXML
    private Hyperlink listLink;

    @FXML
    private Hyperlink editLink;

    @FXML
    private Hyperlink deleteLink;

    @FXML
    private Hyperlink createLink;

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

    // read the patients.txt and get the data
    // go through line by line, search the first column data matches with the patientName parameter
    // if matches, assign each column data into each field, set text for labels.
    public void searchPatient(String patientName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(patientName)) {
                    addressLabel.setWrapText(true);

                    String id = data[0];
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    String bloodType = data[3];
                    int height = Integer.parseInt(data[4]);
                    int weight = Integer.parseInt(data[5]);
                    String gender = data[6];
                    int bloodPressure = Integer.parseInt(data[7]);
                    String occupation = data[8];
                    String address = data[9];
                    String contactNo = data[10];

                    idLabel.setText(id);
                    nameLabel.setText(name);
                    ageLabel.setText(Integer.toString(age));
                    bloodTypeLabel.setText(bloodType);
                    heightLabel.setText(Integer.toString(height));
                    weightLabel.setText(Integer.toString(weight));
                    genderLabel.setText(gender);
                    bpLabel.setText(Integer.toString(bloodPressure));
                    occupationLabel.setText(occupation);
                    addressLabel.setText(address);
                    contactNoLabel.setText(contactNo);

                    break; // Exit the method once a match is found
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleListLink(ActionEvent event) {
        // Create an instance of PatientInformationDataController
        PatientInformationDataController patientInformationDataController = new PatientInformationDataController();

        // Get the current stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Show the patient information data form using the controller
        patientInformationDataController.showPatientInformationDataForm(stage);
    }

    @FXML
    public void handleEditLink(ActionEvent event) {
        // Create an instance of EditPatientInformationFormController
        EditPatientInformationFormController editPatientInformationFormController = new EditPatientInformationFormController();

        // Get the current stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Show the edit patient information form using the controller
        editPatientInformationFormController.showEditPatientInformationForm(stage);
    }

    @FXML
    public void handleDeleteLink(ActionEvent event) {
        // Create an instance of DeletePatientInformationFormController
        DeletePatientInformationFormController deletePatientInformationFormController = new DeletePatientInformationFormController();

        // Get the current stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Show the delete patient information form using the controller
        deletePatientInformationFormController.showDeletePatientInformationForm(stage);
    }

    @FXML
    public void handleCreateLink(ActionEvent event) {
        // Create an instance of CreatePatientInformationFormController
        CreatePatientInformationFormController controller = new CreatePatientInformationFormController();

        // Get the current stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Show the create patient information form using the controller
        controller.showCreatePatientInformationForm(stage);
    }


    @FXML
    public void showPatientInformationForm(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientFormDocuments/ViewPatientInformationForm.fxml"));
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
