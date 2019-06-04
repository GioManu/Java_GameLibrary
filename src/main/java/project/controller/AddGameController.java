package main.java.project.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.java.project.model.Game;
import main.java.project.service.LibService;
import org.apache.log4j.Logger;

import java.io.IOException;


public class AddGameController {
    final static Logger logger = Logger.getLogger(String.valueOf(AddGameController.class));
    private final Stage thisStage;

    @FXML
    ComboBox<String> genre;
    @FXML
    ComboBox<String> age_rest;
    @FXML
    CheckBox sing_player;
    @FXML
    CheckBox mult_player;
    @FXML
    CheckBox coop;
    @FXML
    Button btn_add;
    @FXML
    TextField title;
    @FXML
    TextField developer;
    @FXML
    TextArea descript;

    public AddGameController() throws IOException {
        thisStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/gameAddForm.fxml"));

        loader.setController(this);

        thisStage.setScene(new Scene(loader.load(),522,450));
        thisStage.getIcons().add(new Image("main/java/project/img/logo.png"));
        thisStage.setResizable(false);
        thisStage.setTitle("New Game Form");

    }

    public void showStage(){
        thisStage.show();
    }

    public void refreshForm(){
        title.setText("");
        developer.setText("");
        descript.setText("");
        coop.setSelected(false);
        sing_player.setSelected(false);
        mult_player.setSelected(false);
    }

    public void successEvent(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Library");
        alert.setHeaderText("Information");
        alert.setContentText("Game added");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }

    @FXML
    public void initialize(){
        genre.setItems(FXCollections.observableArrayList("Simulations","Adventure","RTS","Puzzle","Action","Stealth","FPS","Sports","RPG","Educational","Horror"));
        age_rest.setItems(FXCollections.observableArrayList("0+","16+","18+"));

        btn_add.setOnAction(event -> {

            Game game = new Game();
            game.setTitle(title.getText());
            game.setDeveloper(developer.getText());
            game.setDescript(descript.getText());

            game.setAgeRest(age_rest.getSelectionModel().getSelectedItem().toString());
            game.setGenre(genre.getSelectionModel().getSelectedItem().toString());

            game.setCoop(coop.isSelected());
            game.setMulti(mult_player.isSelected());
            game.setSinglePlayer(sing_player.isSelected());

            if(LibService.addGame(game)){
                logger.info("Game Registered");
                refreshForm();
                successEvent();
            }else {
                logger.info("Game Registration FAILED");
                logger.error("Game Registration FAILED");
            }
        });
    }
}
