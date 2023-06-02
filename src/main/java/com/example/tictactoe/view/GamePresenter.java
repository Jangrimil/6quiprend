package com.example.tictactoe.view;

import com.example.tictactoe.HelloApplication;
import com.isep.tictactoe.console.game.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class GamePresenter {

    HelloApplication application;
    GameView gameView;
    Game game;

    public GamePresenter(GameView gameView, Game game, HelloApplication application) {
        this.gameView = gameView;
        this.game = game;
        this.application = application;
    }
}
