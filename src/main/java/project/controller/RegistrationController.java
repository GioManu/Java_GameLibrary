package main.java.project.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.project.model.User;
import main.java.project.service.Auth;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationController {

    final static Logger logger = Logger.getLogger(String.valueOf(RegistrationController.class));
    private final Stage thisStage;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @FXML
    ImageView img_view;
    @FXML
    Hyperlink img_pwd;
    @FXML
    Button btn_reg;
    @FXML
    TextField reg_username;
    @FXML
    TextField reg_pwd;
    @FXML
    TextField reg_confpwd;
    @FXML
    TextField reg_mail;
    @FXML
    Circle err_pwd_conf_circ;
    @FXML
    Circle err_email_circ;
    @FXML
    Circle err_pwd_circ;
    @FXML
    Circle err_username_circ;
    @FXML
    Label err_wrong_email;
    @FXML
    Label err_username;
    @FXML
    Label err_pwd;
    @FXML
    Label err_pwd_match;

    public RegistrationController() throws IOException {
        thisStage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/registrationForm.fxml"));

        loader.setController(this);

        thisStage.setScene(new Scene(loader.load()));
        thisStage.getIcons().add(new Image("main/java/project/img/logo.png"));
        thisStage.setResizable(false);
        thisStage.setTitle("Registration");

    }

    public void showStage() {
        thisStage.show();
    }

    @FXML
    public void initialize(){
        img_pwd.setOnAction(event -> {
//                openFileChooser();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Temporary unavailable");
                alert.setHeaderText("Ooops");
                alert.show();
        });
        btn_reg.setOnAction(event -> {
            if(validateFields()){
                User user = new User(reg_username.getText(),reg_pwd.getText(),reg_mail.getText());
                    try {
                        if(Auth.registerUser(user)){
                            logger.info("User registered, redirecting to Log In Form");
                            LoginFormController loginFormController = new LoginFormController();
                            loginFormController.showStage();
                            thisStage.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                logger.info("User already exists");
            }
        });
    }

    public void openFileChooser() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose picture");

        // Set extension filter
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG FILES (*.jpg)","*.jpg"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG FILES (*.png)","*.png"));

        File file = fileChooser.showOpenDialog(new Stage());

        if(file != null){
            Image image = new Image(file.toURI().toString());
            ImageAuthController imageAuthController = new ImageAuthController();
            imageAuthController.setImage(image);
            imageAuthController.setMode(0);

            imageAuthController.showStage();
        }
    }

    private void errMsg(Label label){
        label.setVisible(true);
    }

    private boolean validateFields(){
        Boolean validated = true;

        if(reg_username.getText().length() <= 3) {
            errMsg(err_username);
            err_username_circ.setVisible(true);
            validated = false;
        }
        if(reg_pwd.getText().length() == 0 ){
            errMsg(err_pwd);
            err_pwd_circ.setVisible(true);
            validated = false;
        }
        if(!reg_pwd.getText().equals(reg_confpwd.getText())){
            errMsg(err_pwd_match);
            err_pwd_conf_circ.setVisible(true);
            validated = false;
        }

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(reg_mail.getText());

        if(!matcher.find()){
            errMsg(err_wrong_email);
            err_email_circ.setVisible(true);
            validated = false;
        }
        return validated;
    }
}
