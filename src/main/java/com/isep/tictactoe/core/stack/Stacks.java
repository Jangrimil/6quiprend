package com.isep.tictactoe.core.stack;

import com.isep.tictactoe.core.card.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The Class Stacks. It represents the stacks used to play the game. Each stack
 * is a set of cards played by players. This objects is handled by a
 * StackManager.
 */
public class Stacks extends HashMap<Integer, List<Card>> {

	/**
	 * Le constructeur copie de Stacks.
	 * Il crée un nouveau Stacks qui est une copie exacte d'un Stacks existant.
	 */
	public Stacks(Stacks stacks) {
		// Pour chaque pile dans les piles d'entrée
		for (Entry<Integer, List<Card>> e : stacks.entrySet()) {
			// Créer une nouvelle liste de cartes
			List<Card> cards = new ArrayList<>();
			// Pour chaque carte dans la pile actuelle
			for (Card c : e.getValue())
				// Ajouter une nouvelle carte avec le même nombre et le même score que la carte d'origine à la liste
				cards.add(new Card(c.getNumber(), c.getScore()));

			// Ajouter la nouvelle pile à la nouvelle instance de Stacks
			this.put(e.getKey(), cards);
		}
	}

	/**
	 * Le constructeur par défaut de Stacks.
	 * Il crée une nouvelle instance de Stacks sans aucune pile de cartes.
	 */
	public Stacks() {
		super();
	}


	/**
	 * Redéfinition de la méthode toString pour afficher les Stacks de manière plus lisible.
	 */
	@Override
	public String toString() {
		// Initialisation de la chaîne de caractères qui sera renvoyée
		String str = "";
		// Pour chaque pile dans les piles
		for (Entry<Integer, List<Card>> e : this.entrySet()) {
			// Ajouter à la chaîne le numéro de la pile et la dernière carte de la pile
			str += "{" + e.getKey().toString() + "} ";
			List<Card> value = e.getValue();
			// Ajouter à la chaîne le nombre de cartes dans la pile et le score total de la pile
			str += value.get(value.size() - 1).toString() + " (" + value.size() + " card(s) - total : "
					+ countScore(value) + "pt(s))";
			str += "\n";
		}
		// Renvoyer la chaîne
		return str;
	}

	/**
	 * Compte le score total d'une liste de cartes.
	 *
	 * @param cards la liste de cartes dont on veut calculer le score.
	 * @return le score total des cartes.
	 */
	private int countScore(List<Card> cards) {
		// Initialisation du score à 0
		int res = 0;
		// Pour chaque carte dans la liste de cartes
		for (Card c : cards)
			// Ajouter le score de la carte au score total
			res += c.getScore();
		// Renvoyer le score total
		return res;
	}

}
