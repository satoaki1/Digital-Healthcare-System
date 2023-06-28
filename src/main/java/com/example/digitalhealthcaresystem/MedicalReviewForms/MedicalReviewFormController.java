package com.example.digitalhealthcaresystem.MedicalReviewForms;

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
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class MedicalReviewFormController {

    private static final String PATIENTS_TXT = "Data/patients.txt";
    private static final String MEDICAL_REVIEWS = "Data/medicalReviews.txt";

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private Label bloodTypeLabel;

    @FXML
    private Label heightLabel;

    @FXML
    private Label weightLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label bloodPressureLabel;

    @FXML
    private Label occupationLabel;

    @FXML
    private Label diagnosisLabel;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField patientInfoField;

    @FXML
    private TextArea newDiagnosisArea;

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

    @FXML
    public void initialize() {
        // Initialize the controller
        updateIDList();
    }


    public void handleSaveButton(ActionEvent event) {
        // Get the selected patient ID from the choice box
        String patientId = choiceBox.getSelectionModel().getSelectedItem();
        // Read the patient list and medical reviews from their respective files
        List<String[]> patients = readPatientList(PATIENTS_TXT);
        List<String[]> patientsMedicalReviews = readPatientList(MEDICAL_REVIEWS);
        boolean patientExists = false;

        // Check if the selected patient ID exists in the patient list
        for (String[] patient : patients) {
            if (patient[0].equals(patientId)) {
                patientExists = true;
                break;
            }
        }

        if (patientExists) {
            // Check if a medical review already exists for the patient
            boolean reviewExists = reviewExists(patientsMedicalReviews, patientId);

            if (reviewExists) {
                // Update the existing medical review with new diagnosis information
                for (String[] review : patientsMedicalReviews) {
                    if (review[0].equals(patientId)) {
                        review[1] = getPatientName(patientId);
                        review[2] = newDiagnosisArea.getText();
                        break;
                    }
                }
                // Write the updated medical review list to the file
                writePatientList(patientsMedicalReviews, MEDICAL_REVIEWS);
            } else {
                // Create a new medical review for the patient
                String name = getPatientName(patientId);
                String newDiagnosis = newDiagnosisArea.getText();
                try (FileWriter writer = new FileWriter(MEDICAL_REVIEWS, true)) {
                    writer.write(patientId + "," + name + "," + newDiagnosis + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Show an error message if the selected patient ID does not exist
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Patient does not exist");
            alert.setContentText("The ID you have chosen did not match with any ID in patients.txt.");
            alert.show();
        }
    }


    public void handleSearchButton(ActionEvent event) {
        // Call the searchMedicalReview method to search for a medical review
        searchMedicalReview();
    }


    public String getPatientName(String patientId) {
        // Retrieve the patient name associated with the given patient ID
        String patientName = "";
        List<String[]> patientList = readPatientList(PATIENTS_TXT);
        for (String[] patient : patientList) {
            if (patient[0].equals(patientId)) {
                patientName = patient[1];
                break;
            }
        }
        return patientName;
    }


    public boolean reviewExists(List<String[]> patientsMedicalReviews, String patientId) {
        // Check if a medical review exists for the given patient ID
        boolean reviewExists = false;
        for (String[] review : patientsMedicalReviews) {
            if (review[0].equals(patientId)) {
                reviewExists = true;
                break;
            }
        }
        return reviewExists;
    }


    public void writePatientList(List<String[]> patients, String fileName) {
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);

            // Write each patient's information to the file
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


    private List<String[]> readPatientList(String filename) {
        List<String[]> patientList = new ArrayList<>();

        // Read the patient list from the file
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


    private void updateIDList() {
        // Clear the choice box items
        choiceBox.getItems().clear();

        // Update the choice box items with patient IDs from the file
        File file = new File(PATIENTS_TXT);
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

    public void searchMedicalReview() {
        // Read the medical reviews list
        List<String[]> patientsMedicalReviews = readPatientList(MEDICAL_REVIEWS);
        try (BufferedReader reader = new BufferedReader(new FileReader(PATIENTS_TXT))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String patientInfo = patientInfoField.getText();
                if (data[0].equals(patientInfo)) {
                    // Retrieve the patient information
                    String id = data[0];
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    String bloodType = data[3];
                    int height = Integer.parseInt(data[4]);
                    int weight = Integer.parseInt(data[5]);
                    String gender = data[6];
                    int bloodPressure = Integer.parseInt(data[7]);
                    String occupation = data[8];

                    // Update the labels with patient information
                    idLabel.setText(id);
                    nameLabel.setText(name);
                    ageLabel.setText(Integer.toString(age));
                    bloodTypeLabel.setText(bloodType);
                    heightLabel.setText(Integer.toString(height));
                    weightLabel.setText(Integer.toString(weight));
                    genderLabel.setText(gender);
                    bloodPressureLabel.setText(Integer.toString(bloodPressure));
                    occupationLabel.setText(occupation);

                    // Check if a medical review exists for the patient and update the diagnosis label
                    boolean reviewExists = reviewExists(patientsMedicalReviews, patientInfo);
                    if (reviewExists) {
                        for (String[] review : patientsMedicalReviews) {
                            if (review[0].equals(patientInfo)) {
                                String diagnosis = review[2];
                                diagnosisLabel.setText(diagnosis);
                            }
                        }
                    } else {
                        diagnosisLabel.setText("No diagnosis");
                    }

                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void showMedicalReviewForm(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/digitalhealthcaresystem/MedicalReviewFormDocuments/MedicalReviewForm.fxml"));
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
