package main.java.project.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Inet4Address;

public class ImageAuthController {

    private final Stage thisStage;
    private Integer mode;

    @FXML
    public ImageView img_pwd;

    public ImageAuthController() throws IOException {
        thisStage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/imageAuth.fxml"));

        loader.setController(this);

        thisStage.setScene(new Scene(loader.load()));
        thisStage.setResizable(false);
        thisStage.setTitle("Image Authentication");

    }

    public void showStage(){
        thisStage.show();
    }

    public void setImage(Image img){
        img_pwd.setImage(img);
    }

    public void setMode(Integer mode){
        this.mode = mode;
    }
}
