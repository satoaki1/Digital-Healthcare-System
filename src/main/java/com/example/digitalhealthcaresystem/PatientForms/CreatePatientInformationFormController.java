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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CreatePatientInformationFormController {
    private static final String FILE_PATH = "patients.txt";

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
    private Hyperlink submitLink;

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
        showExitConfirmation();
    }

    @FXML
    public void handleSubmitLink(ActionEvent event) {
        String id = findExistingID();
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String bloodType = bloodTypeField.getText();
        int height = Integer.parseInt(heightField.getText());
        int weight = Integer.parseInt(weightField.getText());
        String gender = genderField.getText();
        int bloodPressure = Integer.parseInt(bloodPressureField.getText());
        String occupation = occupationField.getText();
        String address = addressField.getText();
        String contactNo = contactNoField.getText();

        if (name == null || age == 0 || bloodType == null || height == 0 || weight == 0 || gender == null || bloodPressure == 0 || occupation == null || address == null || contactNo == null) {
            displayErrorMessage();
        }

        String newRecord = String.format("%s,%s,%d,%s,%d,%d,%s,%d,%s,%s,%s",
                id, name, age, bloodType, height, weight, gender, bloodPressure, occupation, address, contactNo);

        // Write the new record to the file
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(newRecord + "\n");
            displaySuccessMessage();

            handleGoToDashboardButton(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showCreatePatientInformationForm(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/digitalhealthcaresystem/PatientFormDocuments/CreatePatientInformationForm.fxml"));
            Parent root = loader.load();
            CreatePatientInformationFormController controller = loader.getController();

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

    public static String findExistingID() {
        String filePath = "patients.txt"; // Replace with the actual file path

        Set<String> existingIDs = new HashSet<>();

        // Read the existing IDs from the text file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String id = line.split(",")[0]; // Assuming the ID is the first element in each line
                existingIDs.add(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Generate a new ID that is not present in the text file
        String newID = generateUniqueID(existingIDs);
        return newID;
    }

    public static String generateUniqueID(Set<String> existingIDs) {
        String prefix = "P";
        int count = 1;

        // Loop until a unique ID is found
        while (true) {
            String newID = prefix + String.format("%03d", count);
            if (!existingIDs.contains(newID)) {
                return newID;
            }
            count++;
        }
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
}
