package com.example.digitalhealthcaresystem.PatientForms;

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

public class DeletePatientInformationFormController {

    private static final String FILE_PATH = "Data/patients.txt";

    @FXML
    private ListView<String> listView;

    @FXML
    private Button goToCalenderButton;

    @FXML
    private Button goToDashboardButton;

    @FXML
    private Button exitButton;

    public void initialize() {
        // Read patient list from the file and update the UI
        List<String[]> patients = readPatientList(FILE_PATH);
        updatePatientList();

        // Handle mouse click event on the list view
        listView.setOnMouseClicked(mouseEvent -> {
            // Retrieve the selected patient's information
            String patientName = listView.getSelectionModel().getSelectedItem();
            for (String[] patient : patients) {
                if (patient[1].equals(patientName)) {
                    // Extract individual information from the patient array
                    String patientID = patient[0];
                    String age = patient[2];
                    String bloodType = patient[3];
                    String height = patient[4];
                    String weight = patient[5];
                    String gender = patient[6];
                    String bloodPressure = patient[7];
                    String occupation = patient[8];
                    String address = patient[9];
                    String contactNo = patient[10];

                    // Display a confirmation dialog for deletion
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Deletion Confirmation");
                    alert.setHeaderText("Please check before deleting.");
                    alert.setContentText("Your ID is: " + patientID + "\n" +
                            "Your Name is: " + patientName + "\n" +
                            "Your Age is: " + age + "\n" +
                            "Your Blood Type is: " + bloodType + "\n" +
                            "Your Height is: " + height + "\n" +
                            "Your Weight is: " + weight + "\n" +
                            "Your Gender is: " + gender + "\n" +
                            "Your Blood Pressure is: " + bloodPressure + "\n" +
                            "Your Occupation is: " + occupation + "\n" +
                            "Your Address is: " + address + "\n" +
                            "Your Contact No is: " + contactNo + "." + "\n" + "\n" +
                            "Are you sure you want to delete this information?");

                    ButtonType yesButton = new ButtonType("Yes");
                    ButtonType noButton = new ButtonType("No");

                    alert.getButtonTypes().setAll(yesButton, noButton);

                    // Handle the button actions
                    alert.showAndWait().ifPresent(buttonType -> {
                        if (buttonType == yesButton) {
                            patients.remove(patient);
                            displaySuccessMessage();
                        } else {
                            alert.close();
                        }
                    });
                    displaySuccessMessage();
                    break;
                }
            }
            writePatientList(patients, FILE_PATH);
        });
    }


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

    private void updatePatientList() {
        // Clear the list view
        listView.getItems().clear();

        // Read patient list from the file and add names to the list view
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


    public void writePatientList(List<String[]> patients, String fileName) {
        FileWriter fileWriter = null;
        try {
            // Create a file writer for the specified file
            File file = new File(fileName);
            fileWriter = new FileWriter(file);

            // Write each patient's information to the file
            for (String[] patient : patients) {
                fileWriter.append(String.join(",", patient));
                fileWriter.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the file writer
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private List<String[]> readPatientList(String filename) {
        List<String[]> patientList = new ArrayList<>();

        // Read patient information from the file and add to the patient list
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
    public void showDeletePatientInformationForm(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/digitalhealthcaresystem/PatientFormDocuments/DeletePatientInformationForm.fxml"));
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

    private void displaySuccessMessage() {
        // Display a success message dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Information Successfully Deleted");
        alert.setContentText("Information has been successfully deleted from the database.");
        alert.show();
    }
}
