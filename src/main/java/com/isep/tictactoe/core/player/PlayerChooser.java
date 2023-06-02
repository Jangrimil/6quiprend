package com.isep.tictactoe.core.player;

import com.isep.tictactoe.core.card.Card;
import com.isep.tictactoe.console.context.PlayContext;

/**
 * L'interface PlayerChooser. C'est le cerveau des joueurs. Vous devez
 * implémenter cette interface si vous souhaitez créer votre propre IA.
 */
public interface PlayerChooser {

	/**
	 * Choisit une carte à jouer. Cette méthode est appelée à chaque tour de la partie.
	 * Les cartes à jouer sont contenues dans l'objet Player dans le PlayContext.
	 * N'oubliez pas d'utiliser le simulateur. ;)
	 *
	 * @param playContext
	 *            le contexte de jeu
	 * @return la carte sélectionnée
	 */
	Card chooseCard(PlayContext playContext);

	/**
	 * Choisit une pile lorsque la carte jouée est inférieure à toutes les dernières cartes empilées.
	 * Cette méthode est appelée chaque fois que le joueur joue une carte qui ne peut pas être empilée sur une pile.
	 *
	 * Exemple :
	 * Les piles sont remplies comme suit :
	 * ... (descriptions des piles) ...
	 *
	 * @param playContext
	 *            le contexte de jeu
	 * @return l'index de la pile à prendre (commence à 1)
	 */
	int chooseStack(PlayContext playContext);

	/**
	 * Écrit une chaîne dans les sorties.
	 * @param playContext
	 * @param s la chaîne à écrire
	 */
	default void print(PlayContext playContext, String s){
		String template = "[" + playContext.getPlayer().getName() + "] ";
		s = s.trim().replaceAll("\n", "\n"+template);
		playContext.getGameContext().outputs.printDebug(template + s);
	}

	/**
	 * Écrit une ligne dans les sorties.
	 * @param playContext
	 * @param s la chaîne à écrire
	 */
	default void println(PlayContext playContext, String s){
		String template = "[" + playContext.getPlayer().getName() + "] ";
		s = s.trim().replaceAll("\n", "\n"+template);
		playContext.getGameContext().outputs.printlnDebug(template + s);
	}
}
