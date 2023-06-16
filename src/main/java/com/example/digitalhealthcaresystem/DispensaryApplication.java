package com.example.digitalhealthcaresystem;

import com.example.digitalhealthcaresystem.DispensaryFormController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DispensaryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        try {
//            // Load the FXML file and create a root parent
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("DispensaryForm.fxml"));
//            Parent root = loader.load();
//
//            // Create a new scene with the root parent
//            Scene scene = new Scene(root);
//
//            // Set the scene on the primary stage
//            stage.setScene(scene);
//            stage.setTitle("Digital Healthcare System");
//
//            // Show the primary stage
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        DispensaryFormController dispensaryFormController = new DispensaryFormController();
        dispensaryFormController.showDispensaryForm(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}