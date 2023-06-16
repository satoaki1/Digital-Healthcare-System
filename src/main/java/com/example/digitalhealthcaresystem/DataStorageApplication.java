package com.example.digitalhealthcaresystem;

import com.example.digitalhealthcaresystem.DataStorageFormController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DataStorageApplication extends Application {
    @Override
    public void start(Stage stage) {
//        try {
//            // Load the FXML file and create a root parent
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("DataStorageForm.fxml"));
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
        DataStorageFormController dataStorageFormController = new DataStorageFormController();
        dataStorageFormController.showDataStorageView(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}