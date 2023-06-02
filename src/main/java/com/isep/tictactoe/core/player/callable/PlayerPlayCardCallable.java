
package com.isep.tictactoe.core.player.callable;

import com.isep.tictactoe.core.card.Card;
import com.isep.tictactoe.console.context.PlayContext;
import com.isep.tictactoe.core.player.PlayerChooser;

import java.util.concurrent.Callable;

// Cette classe implémente l'interface Callable pour permettre de jouer une carte dans un contexte de jeu
public class PlayerPlayCardCallable implements Callable<Card> {

	private PlayerChooser chooser;
	private PlayContext context;

	// Le constructeur prend un PlayerChooser et un PlayContext en argument
	public PlayerPlayCardCallable(PlayerChooser pc, PlayContext context) {
		this.chooser = pc;
		this.context = context;
	}

	// La méthode call retourne la carte choisie par l'IA à partir du contexte de jeu
	@Override
	public Card call() throws Exception {
		return chooser.chooseCard(context);
	}

}