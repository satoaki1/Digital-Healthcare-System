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
    public void initialize() {
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

                ButtonType exitButton = new ButtonType("Yes");
                ButtonType cancelButton = new ButtonType("No");

                alert.getButtonTypes().setAll(exitButton, cancelButton);
                alert.showAndWait().ifPresent(buttonType -> {
                    if (buttonType == exitButton) {
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

    private List<String[]> readPatientList(String filename) {
        List<String[]> patientList = new ArrayList<>();

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

    public void writePatientList(List<String[]> patients, String fileName) {
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
