package com.example.digitalhealthcaresystem.TreatmentCourseForms;

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

public class EditTreatmentCourseFormController {
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
    private ChoiceBox<String> choiceBox;

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

        // Get the current stage from the event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Get the selected treatment course ID from the choice box
        String treatmentCourseID = choiceBox.getSelectionModel().getSelectedItem();

        // Read the treatment course list from the file
        List<String[]> treatmentCourses = readTreatmentCourseList(FILE_PATH);

        // Iterate over the treatment courses to find the matching ID
        for (String[] treatmentCourse : treatmentCourses) {
            if (treatmentCourse[0].equals(treatmentCourseID)) {
                // Retrieve the input field values
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = genderField.getText();
                String startDate = startDateField.getText();
                String endDate = endDateField.getText();
                String courseName = courseNameField.getText();
                String treatmentPlan = treatmentPlanField.getText();
                String progressNotes = progressNotesField.getText();

                // Check if any required fields are null or age is 0
                if (name == null || age == 0 || gender == null || startDate == null || endDate == null || courseName == null || treatmentPlan == null || progressNotes == null) {
                    displayErrorMessage();
                }

                // Create a confirmation alert for saving the details
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Details Confirmation");
                alert.setHeaderText("Please check before save.");
                alert.setContentText("Your Treatment Course ID is: " + treatmentCourseID + "\n" +
                        "Your Name is: " + name + "\n" +
                        "Your Age is: " + age + "\n" +
                        "Your Gender is: " + gender + "\n" +
                        "Your Start Date is: " + startDate + "\n" +
                        "Your End Date is: " + endDate + "\n" +
                        "Your Course Name is: " + courseName + "\n" +
                        "Your Treatment Plan is: " + treatmentPlan + "\n" +
                        "Your Progress Notes is: " + progressNotes + "." + "\n" + "\n" +
                        "Are you sure you want to save these details?");

                ButtonType yesButton = new ButtonType("Yes");
                ButtonType noButton = new ButtonType("No");
                alert.getButtonTypes().setAll(yesButton, noButton);

                // Show the alert and handle the button response
                alert.showAndWait().ifPresent(buttonType -> {
                    if (buttonType == yesButton) {
                        // Update the treatment course with the new details and display a success message
                        treatmentCourse[0] = treatmentCourseID;
                        treatmentCourse[1] = name;
                        treatmentCourse[2] = String.valueOf(age);
                        treatmentCourse[3] = gender;
                        treatmentCourse[4] = startDate;
                        treatmentCourse[5] = endDate;
                        treatmentCourse[6] = courseName;
                        treatmentCourse[7] = treatmentPlan;
                        treatmentCourse[8] = progressNotes;
                        displaySuccessMessage();
                    }
                });
                break;
            }
        }

        // Write the updated treatment course list to the file
        writeTreatmentCourseList(treatmentCourses, FILE_PATH);

        // Load the dashboard view
        dashboardController.loadDashboardView(stage);
    }

    private void updateIDList() {
        // Clear the choice box
        choiceBox.getItems().clear();

        // Read the treatment course data from the file and update the choice box
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

    private List<String[]> readTreatmentCourseList(String filename) {
        // Read the treatment course data from the file and return a list of treatment courses
        List<String[]> treatmentCourseList = new ArrayList<>();

        File file = new File(filename);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    treatmentCourseList.add(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return treatmentCourseList;
    }

    public void writeTreatmentCourseList(List<String[]> patients, String fileName) {
        // Write the treatment course list to the file
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);

            // Process content line by line
            for (String[] patient : patients) {
                fileWriter.append(String.join(",", patient));
                fileWriter.append("\n");
            }
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        } finally {
            try {
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
    public void showEditTreatmentCourseForm(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/digitalhealthcaresystem/TreatmentCourseFormDocuments/EditTreatmentCourseForm.fxml"));
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
