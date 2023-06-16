package com.example.digitalhealthcaresystem;

import javafx.application.Application;
import javafx.stage.Stage;

public class DashboardApplication extends Application {
    @Override
    public void start(Stage stage) {
        DashboardController dashboardController = new DashboardController();
        dashboardController.loadDashboardView(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
