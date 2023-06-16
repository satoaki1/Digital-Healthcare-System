package com.example.digitalhealthcaresystem;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        LoginFormController loginFormController = new LoginFormController();
        loginFormController.loadSignInView(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
