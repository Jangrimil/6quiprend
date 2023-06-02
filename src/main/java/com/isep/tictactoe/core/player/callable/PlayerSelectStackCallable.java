package com.isep.tictactoe.core.player.callable;

import com.isep.tictactoe.console.context.PlayContext;
import com.isep.tictactoe.core.player.PlayerChooser;

import java.util.concurrent.Callable;

// Cette classe implémente l'interface Callable pour permettre de sélectionner une pile dans un contexte de jeu
public class PlayerSelectStackCallable implements Callable<Integer> {

	private PlayerChooser chooser;
	private PlayContext context;

	// Le constructeur prend un PlayerChooser et un PlayContext en argument
	public PlayerSelectStackCallable(PlayerChooser pc, PlayContext context) {
		this.chooser = pc;
		this.context = context;
	}

	// La méthode call retourne le choix de pile par l'IA à partir du contexte de jeu
	@Override
	public Integer call() throws Exception {
		return chooser.chooseStack(context);
	}

}