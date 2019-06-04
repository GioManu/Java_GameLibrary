package main.java.project.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.java.project.service.Auth;
import org.apache.log4j.Logger;

import java.io.IOException;

public class LoginFormController {
    final static Logger logger = Logger.getLogger(String.valueOf(LoginFormController.class));

    private final Stage thisStage;

    @FXML
    PasswordField user_pwd;
    @FXML
    TextField user_name;
    @FXML
    Button btn_logIn;
    @FXML
    Hyperlink clk_reg;

    public LoginFormController() throws IOException {
        thisStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/loginForm.fxml"));

        loader.setController(this);

        thisStage.setScene(new Scene(loader.load(),300,400));
        thisStage.getIcons().add(new Image("main/java/project/img/logo.png"));
        thisStage.setResizable(false);
        thisStage.setTitle("Login");
    }

    public void showStage(){
        thisStage.show();
    }

    @FXML
    public void initialize(){
        clk_reg.setOnAction(event -> {
            RegistrationController registrationController = null;
            try {
                registrationController = new RegistrationController();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert registrationController != null;
            registrationController.showStage();

            ((Node) (event.getSource())).getScene().getWindow().hide();
        });

        btn_logIn.setOnAction(event ->{
            if(user_name.getText().length() > 0 && user_pwd.getText().length() > 0){
                try {
                    if(Auth.checkUser(user_name.getText().toLowerCase(),user_pwd.getText())){

                        AddGameController addGameController = new AddGameController();
                        addGameController.showStage();

                        thisStage.close();

                        logger.info("User Loged In");
                    }else{
                        logger.info("wrong password or username");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
