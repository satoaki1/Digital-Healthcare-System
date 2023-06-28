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
    public void handleExitButton() {
        exitButton.setOnAction(event1 -> {
            System.exit(1);
        });
    }

    public void initialize() {
        List<String[]> patientHistories = readPatientHistoryList(FILE_PATH);
        updatePatientHistoryList();

        listView.setOnMouseClicked(mouseEvent -> {
            String patientInfo = listView.getSelectionModel().getSelectedItem();
            for (String[] patientHistory : patientHistories) {
                if ((patientHistory[1] + " - " + patientHistory[4]).equals(patientInfo)) {
                    String patientHistoryID = patientHistory[0];
                    String name = patientHistory[1];
                    String age = patientHistory[2];
                    String gender = patientHistory[3];
                    String admissionHistory = patientHistory[4];
                    String pastSymptoms = patientHistory[5];
                    String majorComplaints = patientHistory[6];
                    String observations = patientHistory[7];
                    String treatmentCourse = patientHistory[8];

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
                    alert.showAndWait().ifPresent(buttonType -> {
                        if (buttonType == yesButton) {
                            patientHistories.remove(patientHistory);
                            displaySuccessMessage();
                        } else {
                            alert.close();
                        }
                    });
                    break;
                }
            }
            writePatientHistoryList(patientHistories, FILE_PATH);
        });
    }

    // Updating the patient History list by clearing the current items in listview then opens patients.txt, reads each line, and adds each line as a new item in the listView.
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
