package com.isep.tictactoe.console.game;

import com.isep.tictactoe.core.card.Card;
import com.isep.tictactoe.core.card.CardGame;
import com.isep.tictactoe.core.card.PlayedCardCollection;
import com.isep.tictactoe.console.context.GameContext;
import com.isep.tictactoe.core.player.Player;
import com.isep.tictactoe.core.stack.StackManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Game {
	// Liste des joueurs dans le jeu.
	private List<Player> players;

	// Les cartes utilisées dans le jeu.
	private CardGame cards;

	// Le gestionnaire de piles, contient et gère les piles.
	private StackManager stackManager;

	// Une liste des cartes jouées par les joueurs à un certain moment.
	private Map<Player, Card> playedCardList;

	// Un historique des cartes jouées.
	private PlayedCardCollection playedCardsHistory;

	// Constructeur pour la classe Game.
	public Game(GameContext context) {
		this.players = new ArrayList<>();  // Initialisation de la liste des joueurs.
		this.stackManager = new StackManager(context.numberOfStacks);  // Initialisation du gestionnaire de piles.
		this.cards = new CardGame(context.numberOfCards);  // Initialisation des cartes.
		this.playedCardList = new HashMap<>();  // Initialisation de la liste des cartes jouées.
		this.playedCardsHistory = new PlayedCardCollection();  // Initialisation de l'historique des cartes jouées.
	}


	// Trouve la carte jouée la plus basse et la supprime de la liste des cartes jouées.
	public Entry<Player, Card> getLowerPlayedCard() {
		Entry<Player, Card> minimumCard = null;  // Initialise la carte minimale à null.

		// Boucle sur toutes les entrées de la liste des cartes jouées.
		for (Entry<Player, Card> entry : this.playedCardList.entrySet()) {
			// Si la carte est plus petite que la carte minimale actuelle, met à jour la carte minimale.
			if (minimumCard == null || minimumCard.getValue().getNumber() > entry.getValue().getNumber())
				minimumCard = entry;
		}

		// Met à jour l'historique des cartes jouées et supprime la carte minimale de la liste des cartes jouées.
		this.playedCardsHistory.updatePlayedCardsHistory(minimumCard);
		playedCardList.remove(minimumCard.getKey());

		// Renvoie la carte minimale.
		return minimumCard;
	}

	// Getter pour les cartes.
	public CardGame getCards() {
		return cards;
	}

	// Setter pour les cartes.
	public void setCards(CardGame cards) {
		this.cards = cards;
	}
	// Getter pour la liste des cartes jouées.
	public Map<Player, Card> getPlayedCardList() {
		return playedCardList;
	}

	// Ajoute un joueur à la liste des joueurs.
	public void addPlayer(Player p) {
		this.players.add(p);
	}
	// Getter pour la liste des joueurs.
	public List<Player> getPlayers() {
		return players;
	}


	// Getter pour le gestionnaire de piles.
	public StackManager getStackManager() {
		return stackManager;
	}


	// Ajoute une carte jouée à la liste des cartes jouées.
	public void addPlayedCard(Player player, Card card) {
		this.playedCardList.put(player, card);
	}

	// Getter pour l'historique des cartes jouées.
	public PlayedCardCollection getPlayedCardsHistory() {
		return playedCardsHistory;
	}


}
