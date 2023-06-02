package com.example.tictactoe;
import com.isep.tictactoe.console.game.GameManager;
import com.isep.tictactoe.core.card.Card;
import com.isep.tictactoe.core.card.CardGame;
import com.isep.tictactoe.console.game.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.*;
import javafx.stage.*;
import java.io.IOException;

public class HelloController {
    private Stage primaryStage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void switchSceneall(String fxml) throws IOException{
        FXMLLoader sce = new FXMLLoader(getClass().getResource(fxml));
        sce.setLocation(HelloApplication.class.getResource(fxml));
        root = sce.load();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void switchScene(ActionEvent event) throws IOException {
        primaryStage =(Stage)((Node)event.getSource()).getScene().getWindow();
        switchSceneall("joueur.fxml");
    }

    public void valider(ActionEvent event) throws IOException {
        primaryStage =(Stage)((Node)event.getSource()).getScene().getWindow();
        switchSceneall("jeu.fxml");
        GameManager GameManager;
    }


}