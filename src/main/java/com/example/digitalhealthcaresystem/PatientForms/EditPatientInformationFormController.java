package com.example.digitalhealthcaresystem.PatientForms;

import com.example.digitalhealthcaresystem.CalenderFormController;
import com.example.digitalhealthcaresystem.DashboardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EditPatientInformationFormController {
    private static final String FILE_PATH = "Data/patients.txt";

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField bloodTypeField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField genderField;

    @FXML
    private TextField bloodPressureField;

    @FXML
    private TextField occupationField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField contactNoField;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Hyperlink saveLink;

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
        showExitConfirmation();
    }

    @FXML
    public void initialize() {
        // Initialize the controller
        updateIDList();
    }
    @FXML
    public void handleSaveLink(ActionEvent event) {
        DashboardController dashboardController = new DashboardController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String patientID = choiceBox.getSelectionModel().getSelectedItem();

        List<String[]> patients = readPatientList(FILE_PATH);
        for (String[] patient : patients) {
            if (patient[0].equals(patientID)) {
                String name = nameField.getText();
                String age = ageField.getText();
                String bloodType = bloodTypeField.getText();
                String height = heightField.getText();
                String weight = weightField.getText();
                String gender = genderField.getText();
                String bloodPressure = bloodPressureField.getText();
                String occupation = occupationField.getText();
                String address = addressField.getText();
                String contactNo = contactNoField.getText();

                if (name == null || age == null || bloodType == null || height == null || weight == null || gender == null || bloodPressure == null || occupation == null || address == null || contactNo == null) {
                    displayErrorMessage();
                }

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Details Confirmation");
                alert.setHeaderText("Please check before save.");
                alert.setContentText("Your ID is: " + patientID + "\n" +
                        "Your Name is: " + name + "\n" +
                        "Your Age is: " + age + "\n" +
                        "Your Blood Type is: " + bloodType + "\n" +
                        "Your Height is: " + height + "\n" +
                        "Your Weight is: " + weight + "\n" +
                        "Your Gender is: " + gender + "\n" +
                        "Your Blood Pressure is: " + bloodPressure + "\n" +
                        "Your Occupation is: " + occupation + "\n" +
                        "Your Address is: " + address + "\n" +
                        "Your Contact No is: " + contactNo + "." + "\n" + "\n" +
                        "Are you sure you want to save these saving?");

                ButtonType yesButton = new ButtonType("Yes");
                ButtonType noButton = new ButtonType("No");

                alert.getButtonTypes().setAll(yesButton, noButton);
                alert.showAndWait().ifPresent(buttonType -> {
                    if (buttonType == yesButton) {
                        patient[0] = patientID;
                        patient[1] = name;
                        patient[2] = age;
                        patient[3] = bloodType;
                        patient[4] = height;
                        patient[5] = weight;
                        patient[6] = gender;
                        patient[7] = bloodPressure;
                        patient[8] = occupation;
                        patient[9] = address;
                        patient[10] = contactNo;
                        displaySuccessMessage();
                    }

                });
                break;
            }
        }
        writePatientList(patients, FILE_PATH);
        dashboardController.loadDashboardView(stage);
    }

    private void updateIDList() {
        // Clear the choice box items
        choiceBox.getItems().clear();

        // Update the choice box items with patient IDs from the file
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    choiceBox.getItems().add(data[0]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private List<String[]> readPatientList(String filename) {
        List<String[]> patientList = new ArrayList<>();

        // Read the patient list from the file
        File file = new File(filename);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    patientList.add(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return patientList;
    }

    @FXML
    public void showEditPatientInformationForm(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/digitalhealthcaresystem/PatientFormDocuments/EditPatientInformationForm.fxml"));
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

    private void showExitConfirmation() {
        // Create a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Unsaved Changes");
        alert.setContentText("Are you sure you want to exit without saving?");

        // Create exit and cancel buttons
        ButtonType exitButton = new ButtonType("Exit");
        ButtonType cancelButton = new ButtonType("Cancel");

        // Set the buttons for the dialog
        alert.getButtonTypes().setAll(exitButton, cancelButton);

        // Show the dialog and handle the button actions
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == exitButton) {
                // Replace this with your application's exit logic
                System.exit(0);
            }
        });
    }


    public void writePatientList(List<String[]> patients, String fileName) {
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);

            // Write each patient's information to the file
            for (String[] patient : patients) {
                fileWriter.append(String.join(",", patient));
                fileWriter.append("\n");
            }
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        } finally {
            try {
                // Flush and close the file writer
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                // Handle exception
                e.printStackTrace();
            }
        }
    }

    private void displaySuccessMessage() {
        // Display a success message dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Information Saved");
        alert.setContentText("Information has been successfully saved.");
        alert.show();
    }


    private void displayErrorMessage() {
        // Display an error message dialog
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Empty Information");
        alert.setContentText("Please fill in all information before saving.");
        alert.show();
    }
}
