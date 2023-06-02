
package com.isep.tictactoe.core.player.ia;

import com.isep.tictactoe.core.card.Card;
import com.isep.tictactoe.console.context.PlayContext;
import com.isep.tictactoe.core.player.PlayerChooser;
import com.isep.tictactoe.core.stack.StackManager;
import com.isep.tictactoe.core.stack.Stacks;

import java.util.List;
import java.util.Map.Entry;

public class PlayerChooserSimple implements PlayerChooser {

	// Choisit la carte à jouer en évaluant chaque carte jouable.
	@Override
	public Card chooseCard(PlayContext playContext) {
		Card bestCard = null; // Initialise la meilleure carte à null
		double bestScore = Double.MAX_VALUE; // Initialise le meilleur score à la valeur maximale possible

		// Boucle sur toutes les cartes jouables
		for (Card c : playContext.getMyPlayableCards()) {
			// Évalue le score de la carte
			double score = eval(new PlayContext(playContext), c);

			// Si le score est meilleur que le meilleur score actuel, met à jour la meilleure carte et le meilleur score
			if (score < bestScore) {
				bestCard = c;
				bestScore = score;
			}
		}

		// Renvoie la meilleure carte
		return bestCard;
	}

	// Choisit la pile à nettoyer en fonction du score total minimum de chaque pile
	@Override
	public int chooseStack(PlayContext playContext) {
		int bestKey = -1; // Initialise la meilleure clé à -1
		int bestValue = Integer.MAX_VALUE; // Initialise la meilleure valeur à la valeur maximale possible

		// Boucle sur toutes les piles
		for (Entry<Integer, List<Card>> e : playContext.getStacks().entrySet()) {
			int score = 0; // Initialise le score de la pile à 0

			// Calcule le score total de la pile
			for (Card c : e.getValue())
				score += c.getScore();

			// Si le score de la pile est inférieur au meilleur score actuel, met à jour la meilleure clé et le meilleur score
			if (bestKey < 0 || score < bestValue) {
				bestValue = score;
				bestKey = e.getKey();
			}
		}

		// Renvoie la clé de la meilleure pile
		return bestKey;
	}


	// Simule un mouvement en créant un nouveau gestionnaire de piles et en jouant une carte
	public static PlayContext simulate(PlayContext playContext, Card card) {
		return new StackManager(new Stacks(playContext.getStacks())).simulate(new PlayContext(playContext), card);
	}


	// Cette méthode est utilisée pour calculer la distance entre la carte jouée et la dernière carte de la pile choisie.
	public static int computeDistance(PlayContext playContext, Card card) {
		int stackIndex = new StackManager(new Stacks(playContext.getStacks())).getStackIndexForCard(card);

		if (stackIndex < 0)
			return -1;

		List<Card> stack = playContext.getStacks().get(stackIndex);
		Card lastCard = stack.get(stack.size() - 1);
		return card.getNumber() - lastCard.getNumber();
	}


	// Évalue le score d'un mouvement en jouant une carte. Plus le score est bas, meilleur est le mouvement.
	private double eval(PlayContext playContext, Card c) {
		double evalScore = 0; // Initialise le score d'évaluation à 0

		// Calcule la distance entre la carte à jouer et la dernière carte de la pile
		double distance = (double) computeDistance(playContext, c);

		// Calcule la différence de score avant et après avoir joué la carte
		double scoreDiff = -playContext.getMyScore();
		PlayContext playContextAfter = simulate(playContext, c);
		scoreDiff += playContextAfter.getMyScore();

		// Si le score a augmenté (c'est-à-dire que le joueur a pris une pile), ajoute 100 au score d'évaluation
		if (scoreDiff > 0)
			evalScore += 100;

		// Ajoute la distance au score d'évaluation
		evalScore += distance;

		// Renvoie le score d'évaluation
		return evalScore;
	}
}
