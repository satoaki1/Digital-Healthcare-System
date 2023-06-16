package com.example.digitalhealthcaresystem.Archives;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;

public class HelloController {
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField genderField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField contactNumberField;

    @FXML
    private TextField occupationField;

    @FXML
    private TextArea notesField;

    public HelloController(){
        initialize();
    }

    private void initialize() {
        // Set the prompt text for each text field
        firstNameField.setPromptText("Enter national ID");
        lastNameField.setPromptText("Enter name");
        ageField.setPromptText("Enter age");
        genderField.setPromptText("Enter gender");
        addressField.setPromptText("Enter address");
        contactNumberField.setPromptText("Enter contact number");
        occupationField.setPromptText("Enter workplace");
        notesField.setPromptText("Enter notes");

        // Set the text limit for the text fields
        firstNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 10) {
                firstNameField.setText(oldValue);
            }
        });

        lastNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 50) {
                lastNameField.setText(oldValue);
            }
        });

        ageField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 3) {
                ageField.setText(oldValue);
            }
        });

        genderField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 10) {
                genderField.setText(oldValue);
            }
        });

        addressField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 50) {
                addressField.setText(oldValue);
            }
        });

        contactNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 15) {
                contactNumberField.setText(oldValue);
            }
        });

        occupationField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 100) {
                occupationField.setText(oldValue);
            }
        });

        notesField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 1000) {
                notesField.setText(oldValue);
            }
        });
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        initialize();

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String age = ageField.getText();
        String gender = genderField.getText();
        String address = addressField.getText();
        String contactNumber = contactNumberField.getText();
        String occupation = occupationField.getText();
        String notes = notesField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || age.isEmpty() || gender.isEmpty() || address.isEmpty() || contactNumber.isEmpty() || occupation.isEmpty() || notes.isEmpty()) {
            // If any field is empty, display an error message to the user
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill out all fields.");
            alert.showAndWait();
            return;
        }

        // TODO: Process the form data (e.g., save to a database, send an email, etc.)
        try {
            FileWriter writer = new FileWriter("patients.txt", true);
            writer.write(firstName + "," + lastName + "," + age + "," + gender + "," + address + "," + contactNumber + "," + occupation + "," + notes + "\n");
            writer.close();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Could not write an information successfully.");
            alert.showAndWait();
            return;
        }

        // If form data is successfully processed, display a success message to the user
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Form data submitted successfully.");
        alert.showAndWait();

        // Clear all fields after form submission
        firstNameField.clear();
        lastNameField.clear();
        ageField.clear();
        genderField.clear();
        addressField.clear();
        contactNumberField.clear();
        occupationField.clear();
        notesField.clear();
    }
}
