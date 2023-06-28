package com.example.digitalhealthcaresystem.TreatmentCourseForms;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CreateTreatmentCourseFormController {
    private static final String FILE_PATH = "Data/treatmentCourses.txt";

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField genderField;

    @FXML
    private TextField startDateField;

    @FXML
    private TextField endDateField;

    @FXML
    private TextField courseNameField;

    @FXML
    private TextField treatmentPlanField;

    @FXML
    private TextField progressNotesField;

    @FXML
    private Button goToCalenderButton;

    @FXML
    private Button goToDashboardButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button submitButton;

    @FXML
    public void handleGoToDashboardButton() {
        goToDashboardButton.setOnAction(event -> {
            // Create an exit confirmation alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Exit Confirmation");
            alert.setHeaderText("Exit Confirmation");
            alert.setContentText("Are you sure you want to exit the app?");

            ButtonType exitButton = new ButtonType("Exit");
            ButtonType cancelButton = new ButtonType("Cancel");

            alert.getButtonTypes().setAll(exitButton, cancelButton);

            // Show the alert and handle the button response
            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == exitButton) {
                    // Create an instance of DashboardController and load the dashboard view
                    DashboardController dashboardController = new DashboardController();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    dashboardController.loadDashboardView(stage);
                }
            });
        });
    }

    @FXML
    public void handleGoToCalenderButton() {
        goToCalenderButton.setOnAction(event -> {
            // Create an exit confirmation alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Exit Confirmation");
            alert.setHeaderText("Exit Confirmation");
            alert.setContentText("Are you sure you want to exit the app?");

            ButtonType exitButton = new ButtonType("Exit");
            ButtonType cancelButton = new ButtonType("Cancel");

            alert.getButtonTypes().setAll(exitButton, cancelButton);

            // Show the alert and handle the button response
            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == exitButton) {
                    // Create an instance of CalenderFormController and show the calendar form
                    CalenderFormController calenderFormController = new CalenderFormController();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    calenderFormController.showCalenderForm(stage);
                }
            });
        });
    }

    @FXML
    public void handleExitButton() {
        exitButton.setOnAction(event1 -> {
            // Show exit confirmation
            showExitConfirmation();
        });
    }

    @FXML
    public void handleSubmitButton() {
        submitButton.setOnAction(event -> {
            // Retrieve input values
            String id = findExistingID();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = genderField.getText();
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();
            String courseName = courseNameField.getText();
            String treatmentPlan = treatmentPlanField.getText();
            String progressNotes = progressNotesField.getText();

            // Validate input values
            if (name == null || age == 0 || gender == null || startDate == null || endDate == null || courseName == null || treatmentPlan == null || progressNotes == null) {
                displayErrorMessage();
            }

            // Write the new record to the file and jump to the dashboard
            try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
                writer.write(id + "," + name + "," + age + "," + gender + "," + startDate + "," + endDate + "," + courseName + "," + treatmentPlan + "," + progressNotes + "\n");
                // Create an instance of DashboardController and load the dashboard view
                DashboardController dashboardController = new DashboardController();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                dashboardController.loadDashboardView(stage);
                displaySuccessMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public static String findExistingID() {

        Set<String> existingIDs = new HashSet<>();

        // Read the existing IDs from the text file
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
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
        String prefix = "T";
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
    public void showCreateTreatmentCourseForm(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/digitalhealthcaresystem/TreatmentCourseFormDocuments/CreateTreatmentCourseForm.fxml"));
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
