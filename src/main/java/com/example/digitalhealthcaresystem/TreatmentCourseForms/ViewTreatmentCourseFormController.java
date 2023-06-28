package com.example.digitalhealthcaresystem.TreatmentCourseForms;

import com.example.digitalhealthcaresystem.CalenderFormController;
import com.example.digitalhealthcaresystem.DashboardController;
import com.example.digitalhealthcaresystem.DataStorageControllers.TreatmentCourseDataController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ViewTreatmentCourseFormController {
    private static final String FILE_PATH = "Data/treatmentCourses.txt";

    @FXML
    private TextField patientNameField;

    @FXML
    private Button searchButton;

    @FXML
    private Button addNewButton;

    @FXML
    private Button listButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Label patientAgeLabel;

    @FXML
    private Label patientGenderLabel;

    @FXML
    private Label startDateLabel;

    @FXML
    private Label endDateLabel;

    @FXML
    private Label detailsOfPlanLabel;

    @FXML
    private Label notesLabel;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

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

    public void handleBackButton(ActionEvent event) {
        
    }


    @FXML
    public void handleExitButton() {
        exitButton.setOnAction(event1 -> {
            System.exit(1);
        });
    }

    @FXML
    public void handleSearchButton(ActionEvent event) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String patientName = patientNameField.getText();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(patientName)) {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    try {
                        // Correctly point to the FXML file
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/digitalhealthcaresystem/TreatmentCourseFormDocuments/ViewTreatmentCourseForm.fxml"));
                        Parent root = loader.load();

                        // Create a new scene with the root parent
                        Scene scene = new Scene(root);

                        // Set the scene on the primary stage
                        stage.setScene(scene);
                        stage.setTitle("Digital Healthcare System");

                        // Show the primary stage
                        stage.show();

                        // Get the controller
                        ViewTreatmentCourseFormController controller = loader.getController();
                        controller.initializeView();
                        controller.searchPatient(patientName);
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddNewButton() {
        addNewButton.setOnAction(event -> {
            CreateTreatmentCourseFormController createTreatmentCourseFormController = new CreateTreatmentCourseFormController();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            createTreatmentCourseFormController.showCreateTreatmentCourseForm(stage);
        });
    }

    @FXML
    public void handleListButton() {
        listButton.setOnAction(event -> {
            TreatmentCourseDataController treatmentCourseDataController = new TreatmentCourseDataController();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            treatmentCourseDataController.showTreatmentCourseDataForm(stage);
        });
    }

    @FXML
    public void handleEditButton() {
        editButton.setOnAction(event -> {
            EditTreatmentCourseFormController editTreatmentCourseFormController = new EditTreatmentCourseFormController();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            editTreatmentCourseFormController.showEditTreatmentCourseForm(stage);
        });
    }

    @FXML
    public void handleDeleteButton() {
        deleteButton.setOnAction(event -> {
            DeleteTreatmentCourseFormController deleteTreatmentCourseFormController = new DeleteTreatmentCourseFormController();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            deleteTreatmentCourseFormController.showDeleteTreatmentCourse(stage);
        });
    }

    public void initializeView() {
        idLabel.setText("");
        nameLabel.setText("");
        patientAgeLabel.setText("");
        patientGenderLabel.setText("");
        startDateLabel.setText("");
        endDateLabel.setText("");
        courseNameLabel.setText("");
        detailsOfPlanLabel.setText("");
        notesLabel.setText("");
    }

    public void searchPatient(String patientName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(patientName)) {
                    String id = data[0];
                    String name = data[1];
                    String age = data[2];
                    String gender = data[3];
                    String startDate = data[4];
                    String endDate = data[5];
                    String courseName = data[6];
                    String detailsOfPlan = data[7];
                    String notes = data[8];

                    idLabel.setText(id);
                    nameLabel.setText(name);
                    patientAgeLabel.setText(age);
                    patientGenderLabel.setText(gender);
                    startDateLabel.setText(startDate);
                    endDateLabel.setText(endDate);
                    courseNameLabel.setText(courseName);
                    detailsOfPlanLabel.setText(detailsOfPlan);
                    notesLabel.setText(notes);

                    break; // Exit the method once a match is found
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showViewTreatmentCourseForm(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/digitalhealthcaresystem/TreatmentCourseFormDocuments/ViewTreatmentCourseForm.fxml"));
            Parent root = loader.load();
            ViewTreatmentCourseFormController controller = loader.getController();

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
