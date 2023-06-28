package com.example.digitalhealthcaresystem;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) {
        // Begin the application by loading the login form
        LoginFormController loginFormController = new LoginFormController();
        loginFormController.loadSignInView(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
