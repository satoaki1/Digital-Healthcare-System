package com.example.digitalhealthcaresystem.PatientHistoryForms;

import com.example.digitalhealthcaresystem.CalenderFormController;
import com.example.digitalhealthcaresystem.DashboardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DeletePatientHistoryFormController {
    private static final String FILE_PATH = "Data/patientHistories.txt";

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
        // Read patient history list from the file and update the patient history list view
        List<String[]> patientHistories = readPatientHistoryList(FILE_PATH);
        updatePatientHistoryList();

        // Handle mouse click event on the list view
        listView.setOnMouseClicked(mouseEvent -> {
            // Get the selected patient info from the list view
            String patientInfo = listView.getSelectionModel().getSelectedItem();

            // Find the corresponding patient history
            for (String[] patientHistory : patientHistories) {
                if ((patientHistory[1] + " - " + patientHistory[4]).equals(patientInfo)) {
                    // Retrieve patient information
                    String patientHistoryID = patientHistory[0];
                    String name = patientHistory[1];
                    String age = patientHistory[2];
                    String gender = patientHistory[3];
                    String admissionHistory = patientHistory[4];
                    String pastSymptoms = patientHistory[5];
                    String majorComplaints = patientHistory[6];
                    String observations = patientHistory[7];
                    String treatmentCourse = patientHistory[8];

                    // Display deletion confirmation alert with patient details
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Deletion Confirmation");
                    alert.setHeaderText("Please check before delete.");
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
                            // Remove the selected patient history and display success message
                            patientHistories.remove(patientHistory);
                            displaySuccessMessage();
                        } else {
                            alert.close();
                        }
                    });
                    break;
                }
            }

            // Write the updated patient history list to the file
            writePatientHistoryList(patientHistories, FILE_PATH);
        });
    }

    // Update the patient history list view by clearing it and adding new items from the file
    private void updatePatientHistoryList() {
        listView.getItems().clear();

        // Read each line from the file and add it as a new item in the list view
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

    // Read the patient history list from the file and return it as a list of string arrays
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


    public void showDeletePatientHistory(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/digitalhealthcaresystem/PatientHistoryFormDocuments/DeletePatientHistory.fxml"));
            Parent root = loader.load();
            loader.getController();

            // Create a new scene with the root parent
            Scene scene = new Scene(root);

            // Set the scene on the primary stage
            stage.setScene(scene);
            stage.setTitle("Digital Healthcare System");

            // Show the primary stage
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displaySuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Information Successfully Deleted");
        alert.setContentText("Information has been successfully deleted from the database.");
        alert.show();
    }
}
