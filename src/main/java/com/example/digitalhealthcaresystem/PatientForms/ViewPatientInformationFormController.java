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
        PatientInformationDataController patientInformationDataController = new PatientInformationDataController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        patientInformationDataController.showPatientInformationDataForm(stage);
    }

    @FXML
    public void handleEditLink(ActionEvent event) {
        EditPatientInformationFormController editPatientInformationFormController = new EditPatientInformationFormController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        editPatientInformationFormController.showEditPatientInformationForm(stage);
    }

    @FXML
    public void handleDeleteLink(ActionEvent event) {
        DeletePatientInformationFormController deletePatientInformationFormController = new DeletePatientInformationFormController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        deletePatientInformationFormController.showDeletePatientInformationForm(stage);
    }

    @FXML
    public void handleCreateLink(ActionEvent event) {
        CreatePatientInformationFormController controller = new CreatePatientInformationFormController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
