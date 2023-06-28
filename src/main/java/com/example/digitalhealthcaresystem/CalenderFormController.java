package com.example.digitalhealthcaresystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class CalenderFormController {

    @FXML
    private Button goToCalenderButton;

    @FXML
    private Button goToDashboardButton;

    @FXML
    private Button exitButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ImageView calenderView;

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
        // Set an event listener for the DatePicker value change event
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateCalendarImage(newValue.getMonthValue());
        });
    }

    @FXML
    public void updateCalendarImage(int month) {
        // Clear the current image
        calenderView.setImage(null);

        // Set the initial image based on the month
        switch (month) {
            case 1:
                Image image = new Image("January-2023-Calendar.jpg");
                calenderView.setImage(image);
                break;
            case 2:
                image = new Image("February-2023-Calendar.jpg");
                calenderView.setImage(image);
                break;
            case 3:
                image = new Image("March-2023-Calendar.jpg");
                calenderView.setImage(image);
                break;
            case 4:
                image = new Image("April-2023-Calendar.jpg");
                calenderView.setImage(image);
                break;
            case 5:
                image = new Image("May-2023-Calendar.jpg");
                calenderView.setImage(image);
                break;
            case 6:
                image = new Image("June-2023-Calendar.jpg");
                calenderView.setImage(image);
                break;
            case 7:
                image = new Image("July-2023-Calendar.jpg");
                calenderView.setImage(image);
                break;
            case 8:
                image = new Image("August-2023-Calendar.jpg");
                calenderView.setImage(image);
                break;
            case 9:
                image = new Image("September-2023-Calendar.jpg");
                calenderView.setImage(image);
                break;
            case 10:
                image = new Image("October-2023-Calendar.jpg");
                calenderView.setImage(image);
                break;
            case 11:
                image = new Image("November-2023-Calendar.jpg");
                calenderView.setImage(image);
                break;
            case 12:
                image = new Image("December-2023-Calendar.jpg");
                calenderView.setImage(image);
                break;
        }
    }

    @FXML
    public void showCalenderForm(Stage stage) {
        try {
            // Load the FXML file and create a root parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Calender.fxml"));
            Parent root = loader.load();
            CalenderFormController calenderFormController = loader.getController();

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
