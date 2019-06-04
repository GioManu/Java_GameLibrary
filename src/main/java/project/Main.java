package main.java.project;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.project.controller.LoginFormController;
import main.java.project.controller.RegistrationController;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        LoginFormController loginForm = new LoginFormController();
        loginForm.showStage();
    }

    public static void main(String[] args) throws SQLException {
        launch(args);
    }

}
