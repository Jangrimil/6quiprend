package com.isep.tictactoe.console;

import java.util.ArrayList;
import java.util.List;

import com.isep.tictactoe.console.context.BasicContext;

import com.isep.tictactoe.console.game.GameManager;
import com.isep.tictactoe.core.player.Player;
import com.isep.tictactoe.core.player.human.PlayerChooserHumanConsole;
import com.isep.tictactoe.core.player.ia.PlayerChooserSimple;

public class Main {

    public static void main(String[] args) {

        // ------------------------------------------------------------ CONFIGURE

        int numberOfParty = 100;

        // -------------------------------------------------------------- PREPARE

        List<Player> players = new ArrayList<>();

        players.add(new Player("Human", new PlayerChooserHumanConsole()));
        players.add(new Player("Simple", new PlayerChooserSimple()));


        BasicContext context = new BasicContext(numberOfParty);
        new GameManager(context, players).launch();
    }

}
