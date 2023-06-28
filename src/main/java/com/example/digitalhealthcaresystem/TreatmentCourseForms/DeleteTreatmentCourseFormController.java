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
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteTreatmentCourseFormController {
    private static final String FILE_PATH = "Data/treatmentCourses.txt";

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
        List<String[]> treatmentCourses = readTreatmentCourseList(FILE_PATH);
        updateTreatmentCourseList();

        listView.setOnMouseClicked(mouseEvent -> {
            String treatmentCourseInfo = listView.getSelectionModel().getSelectedItem();
            for(String[] treatmentCourse : treatmentCourses) {
                if ((treatmentCourse[1] + " - " + treatmentCourse[6]).equals(treatmentCourseInfo)) {
                    String treatmentCourseID = treatmentCourse[0];
                    String name = treatmentCourse[1];
                    String age = treatmentCourse[2];
                    String gender = treatmentCourse[3];
                    String startDate = treatmentCourse[4];
                    String endDate = treatmentCourse[5];
                    String courseName = treatmentCourse[6];
                    String detailsOfPlan = treatmentCourse[7];
                    String progressNotes = treatmentCourse[8];

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Delete Treatment Course");
                    alert.setHeaderText("Are you sure you want to delete this treatment course?");
                    alert.setContentText("Your Treatment Course ID is: " + treatmentCourseID + "\n" +
                            "Your Name is: " + name + "\n" +
                            "Your Age is: " + age + "\n" +
                            "Your Gender is: " + gender + "\n" +
                            "Your Start Date is: " + startDate + "\n" +
                            "Your End Date is: " + endDate + "\n" +
                            "Your Course Name is: " + courseName + "\n" +
                            "Your Treatment Plan is: " + detailsOfPlan + "\n" +
                            "Your Progress Notes is: " + progressNotes + "." + "\n" + "\n" +
                            "Are you sure you want to delete this information?");

                    ButtonType yesButton = new ButtonType("Yes");
                    ButtonType noButton = new ButtonType("No");
                    alert.getButtonTypes().setAll(yesButton, noButton);
                    alert.showAndWait().ifPresent(buttonType -> {
                        if (buttonType == yesButton) {
                            treatmentCourses.remove(treatmentCourse);
                            displaySuccessMessage();
                        } else {
                            alert.close();
                        }
                    });
                    break;
                }
            }
            writeTreatmentCourseList(treatmentCourses, FILE_PATH);
        });
    }

    private void updateTreatmentCourseList() {
        listView.getItems().clear();

        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    listView.getItems().add(data[1] + " - " + data[6]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<String[]> readTreatmentCourseList(String filename) {
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
        alert.setHeaderText("Information Successfully Deleted");
        alert.setContentText("Information has been successfully deleted from the database.");
        alert.show();
    }

    public void showDeleteTreatmentCourse(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/digitalhealthcaresystem/TreatmentCourseFormDocuments/DeleteTreatmentCourseForm.fxml"));
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
}
