package com.example.tictactoe;

import com.example.tictactoe.view.GamePresenter;
import com.example.tictactoe.view.GameView;
import com.isep.tictactoe.console.context.BasicContext;
import com.isep.tictactoe.console.context.GameContext;
import com.isep.tictactoe.console.game.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        this.stage.setTitle("6 qui prend");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 600);
        this.stage.setScene(scene);
        this.stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}