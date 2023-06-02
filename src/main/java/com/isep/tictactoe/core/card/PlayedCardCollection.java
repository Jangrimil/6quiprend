package com.isep.tictactoe.core.card;

import com.isep.tictactoe.core.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Cette classe étend HashMap et stocke une collection de cartes jouées, organisée par le nom du joueur.
public class PlayedCardCollection extends HashMap<String, List<Card>>{

	// Constructeur qui prend en entrée une autre collection de cartes jouées et crée une copie profonde.
	public PlayedCardCollection(PlayedCardCollection playedCardCollection){
		// Pour chaque entrée dans la collection de cartes jouées donnée...
		for (Entry<String, List<Card>> entry : playedCardCollection.entrySet()){
			List<Card> cards = new ArrayList<>();

			// ...créer une nouvelle liste de cartes...
			for (Card c : entry.getValue())
				cards.add(new Card(c.getNumber(), c.getScore()));

			// ...et ajouter cette liste à la nouvelle collection de cartes jouées.
			this.put(entry.getKey(), cards);
		}
	}

	// Constructeur par défaut.
	public PlayedCardCollection(){
		super();
	}

	// Méthode pour mettre à jour l'historique des cartes jouées.
	public void updatePlayedCardsHistory(Entry<Player, Card> playedCard) {
		// Récupère le nom du joueur.
		String playerName = playedCard.getKey().getName();
		// Récupère la carte jouée.
		Card c = playedCard.getValue();

		List<Card> cards = null;
		// Si le joueur n'a pas encore joué de carte...
		if (!this.containsKey(playerName))
			// ...créer une nouvelle liste de cartes...
			cards = new ArrayList<>();
		else
			// ...sinon, récupère la liste de cartes déjà jouées par le joueur.
			cards = this.get(playerName);

		// Ajouter la carte à la liste de cartes du joueur.
		cards.add(c);
		// Mettre à jour la collection de cartes jouées avec la nouvelle carte du joueur.
		this.put(playerName, cards);
	}
}
