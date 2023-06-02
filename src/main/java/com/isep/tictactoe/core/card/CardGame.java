package com.isep.tictactoe.core.card;

import com.isep.tictactoe.console.utils.Randomizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Cette classe représente l'ensemble des cartes du jeu. Chaque carte est unique.
public class CardGame {

	// Toutes les cartes du jeu.
	private List<Card> cards;

	// Constructeur de la classe CardGame.
	public CardGame(int numberOfCards) {
		this.cards = new ArrayList<>();
		this.populate(numberOfCards);
		this.shuffle();
	}

	// Méthode pour mélanger les cartes du jeu.
	public void shuffle() {
		Collections.shuffle(cards);
	}

	// Méthode pour remplir le jeu avec le nombre de cartes indiqué.
	private void populate(int numberOfCards) {
		for (int i = 1; i <= numberOfCards; i++) {
			int cardScore = getScore(i);
			this.cards.add(new Card(i, cardScore));
		}
	}

	// Méthode pour obtenir une carte du jeu et la retirer du set.
	public Card getOneCard() {
		return this.cards.remove(0);
	}

	// Méthode pour générer un score aléatoire pour une carte.
	private int getScore(int cardNumber) {
		return Randomizer.nextInt(1,7);
	}
}