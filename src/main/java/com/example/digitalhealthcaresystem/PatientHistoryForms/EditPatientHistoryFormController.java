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
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Unsaved Changes");
        alert.setContentText("Are you sure you want to exit without saving?");

        ButtonType exitButton = new ButtonType("Exit");
        ButtonType cancelButton = new ButtonType("Cancel");

        alert.getButtonTypes().setAll(exitButton, cancelButton);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == exitButton) {
                DashboardController dashboardController = new DashboardController();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                dashboardController.loadDashboardView(stage);
            }
        });
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
    public void initialize() {
        updateIDList();
    }


    public void handleSaveButton(ActionEvent event) {
        DashboardController dashboardController = new DashboardController();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String patientHistoryID = choiceBox.getSelectionModel().getSelectedItem();

        List<String[]> patientHistories = readPatientHistoryList(FILE_PATH);
        for (String[] patientHistory : patientHistories) {
            if (patientHistory[0].equals(patientHistoryID)) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = genderField.getText();
                String admissionHistory = admissionHistoryField.getText();
                String pastSymptoms = pastSymptomsField.getText();
                String majorComplaints = majorComplaintsField.getText();
                String observations = observationsField.getText();
                String treatmentCourse = treatmentCourseField.getText();

                if (name == null || age == 0 || gender == null || admissionHistory == null || pastSymptoms == null || majorComplaints == null || observations == null || treatmentCourse == null) {
                    displayErrorMessage();
                }

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
                alert.showAndWait().ifPresent(buttonType -> {
                    if (buttonType == yesButton) {
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
        writePatientHistoryList(patientHistories, FILE_PATH);
        dashboardController.loadDashboardView(stage);
    }

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

    public void writePatientHistoryList(List<String[]> patients, String fileName) {
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);

            // process content line by line
            for (String[] patient : patients) {
                fileWriter.append(String.join(",", patient));
                fileWriter.append("\n");
            }
        } catch (Exception e) {

            // handle exception
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                // handle exception
                e.printStackTrace();
            }
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

    private void displaySuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Information Saved");
        alert.setContentText("Information has been successfully saved.");
        alert.show();
    }

    private void displayErrorMessage() {
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
