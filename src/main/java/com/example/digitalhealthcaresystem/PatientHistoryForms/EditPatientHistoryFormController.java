package com.example.digitalhealthcaresystem.PatientHistoryForms;

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

public class EditPatientHistoryFormController {

    private static final String FILE_PATH = "Data/patientHistories.txt";

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField genderField;

    @FXML
    private TextField admissionHistoryField;

    @FXML
    private TextField pastSymptomsField;

    @FXML
    private TextField majorComplaintsField;

    @FXML
    private TextField observationsField;

    @FXML
    private TextField treatmentCourseField;

    @FXML
    private Button saveButton;

    @FXML
    private Button goToCalenderButton;

    @FXML
    private Button goToDashboardButton;

    @FXML
    private Button exitButton;

    @FXML
    public void handleGoToDashboardButton(ActionEvent event) {
        // Display unsaved changes confirmation alert
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Unsaved Changes");
        alert.setContentText("Are you sure you want to exit without saving?");

        ButtonType exitButton = new ButtonType("Exit");
        ButtonType cancelButton = new ButtonType("Cancel");

        alert.getButtonTypes().setAll(exitButton, cancelButton);

        // Handle the user's response
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == exitButton) {
                // Create an instance of DashboardController and load the dashboard view
                DashboardController dashboardController = new DashboardController();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                dashboardController.loadDashboardView(stage);
            }
        });
    }

    @FXML
    public void handleGoToCalenderButton(ActionEvent event) {
        // Display exit confirmation alert
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Exit Confirmation");
        alert.setContentText("Are you sure you want to exit the app?");

        ButtonType exitButton = new ButtonType("Exit");
        ButtonType cancelButton = new ButtonType("Cancel");

        alert.getButtonTypes().setAll(exitButton, cancelButton);

        // Handle the user's response
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == exitButton) {
                // Create an instance of CalenderFormController and show the calendar form
                CalenderFormController calenderFormController = new CalenderFormController();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                calenderFormController.showCalenderForm(stage);
            }
        });
    }

    @FXML
    public void handleExitButton(ActionEvent event) {
        // Show exit confirmation dialog
        showExitConfirmation();
    }

    @FXML
    public void initialize() {
        // Update the ID choice box
        updateIDList();
    }



    public void handleSaveButton(ActionEvent event) {
        // Create an instance of DashboardController
        DashboardController dashboardController = new DashboardController();

        // Get the current stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Get the selected patient history ID from the choice box
        String patientHistoryID = choiceBox.getSelectionModel().getSelectedItem();

        // Read patient history list from the file
        List<String[]> patientHistories = readPatientHistoryList(FILE_PATH);

        // Find the patient history with the matching ID
        for (String[] patientHistory : patientHistories) {
            if (patientHistory[0].equals(patientHistoryID)) {
                // Retrieve patient information from the input fields
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = genderField.getText();
                String admissionHistory = admissionHistoryField.getText();
                String pastSymptoms = pastSymptomsField.getText();
                String majorComplaints = majorComplaintsField.getText();
                String observations = observationsField.getText();
                String treatmentCourse = treatmentCourseField.getText();

                // Validate input fields
                if (name == null || age == 0 || gender == null || admissionHistory == null ||
                        pastSymptoms == null || majorComplaints == null || observations == null || treatmentCourse == null) {
                    displayErrorMessage();
                }

                // Display details confirmation alert with patient details
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Details Confirmation");
                alert.setHeaderText("Please check before save.");
                alert.setContentText("Your History ID is: " + patientHistoryID + "\n" +
                        "Your Name is: " + name + "\n" +
                        "Your Age is: " + age + "\n" +
                        "Your Gender is: " + gender + "\n" +
                        "Your Admission History is: " + admissionHistory + "\n" +
                        "Your Past Symptoms is: " + pastSymptoms + "\n" +
                        "Your Major Complaints is: " + majorComplaints + "\n" +
                        "Your Observations is: " + observations + "\n" +
                        "Your Treatment Course is: " + treatmentCourse + "." + "\n" + "\n" +
                        "Are you sure you want to save these saving?");

                ButtonType yesButton = new ButtonType("Yes");
                ButtonType noButton = new ButtonType("No");
                alert.getButtonTypes().setAll(yesButton, noButton);

                // Handle the user's response
                alert.showAndWait().ifPresent(buttonType -> {
                    if (buttonType == yesButton) {
                        // Update patient history with new information and display success message
                        patientHistory[0] = patientHistoryID;
                        patientHistory[1] = name;
                        patientHistory[2] = String.valueOf(age);
                        patientHistory[3] = gender;
                        patientHistory[4] = admissionHistory;
                        patientHistory[5] = pastSymptoms;
                        patientHistory[6] = majorComplaints;
                        patientHistory[7] = observations;
                        patientHistory[8] = treatmentCourse;
                        displaySuccessMessage();
                    }
                });

                break;
            }
        }

        // Write the updated patient history list to the file
        writePatientHistoryList(patientHistories, FILE_PATH);

        // Load the dashboard view
        dashboardController.loadDashboardView(stage);
    }

    // Update the ID choice box by clearing it and adding new items from the file
    private void updateIDList() {
        choiceBox.getItems().clear();

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

    // Read the patient history list from the file and return it as a List of String arrays
    private List<String[]> readPatientHistoryList(String filename) {
        List<String[]> patientHistoryList = new ArrayList<>();

        File file = new File(filename);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    patientHistoryList.add(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return patientHistoryList;
    }

    // Write the patient history list to the file
    public void writePatientHistoryList(List<String[]> patients, String fileName) {
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);

            // Process each patient line by line and write to the file
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


    private void showExitConfirmation() {
        // Create an exit confirmation alert
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Unsaved Changes");
        alert.setContentText("Are you sure you want to exit without saving?");

        ButtonType exitButton = new ButtonType("Exit");
        ButtonType cancelButton = new ButtonType("Cancel");

        alert.getButtonTypes().setAll(exitButton, cancelButton);

        // Show the alert and handle the user's response
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == exitButton) {
                System.exit(0); // Replace this with your application's exit logic
            }
        });
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

    @FXML
    public void showEditPatientHistoryForm(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/digitalhealthcaresystem/PatientHistoryFormDocuments/EditPatientHistory.fxml"));
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
