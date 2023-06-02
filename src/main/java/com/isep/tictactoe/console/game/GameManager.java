/*
 * 
 */
package com.isep.tictactoe.console.game;

import com.isep.tictactoe.core.card.Card;
import com.isep.tictactoe.console.context.GameContext;
import com.isep.tictactoe.console.context.PlayContext;
import com.isep.tictactoe.core.player.Player;
import com.isep.tictactoe.core.stack.Stacks;
import com.isep.tictactoe.console.utils.StringMaker;

import java.util.List;
import java.util.Map.Entry;

/**
 * The Class GameManager. This class handles a Game and ensure its process
 * rules.
 * 
 * While no player has reached the score limit :
 * * generates the card game
 * * initializes the stacks with one card
 * * cards are given to each player
 * * While players have at least one card :
 * * * asks each player to choose one card they have
 * * * serves stacks with played cards by ascending order (card n�1 is process before card n�2)
 * * * updates scores according to each move
 */
public class GameManager {

	// Le jeu actuel qui est géré par ce gestionnaire de jeu.
	Game game;

	// Le contexte du jeu contenant les paramètres du jeu.
	GameContext context;

	// Les joueurs participant au jeu.
	List<Player> players;

	// Constructeur pour GameManager. Initialise le gestionnaire de jeu avec un contexte de jeu et une liste de joueurs.
	public GameManager(GameContext context, List<Player> players) {
		this.context = context;
		this.context.numberOfPlayers = players.size();
		this.players = players;
	}

	// Lance un ensemble de parties.
	public void launch() {
		// Boucle sur chaque partie.
		for (int i = 1; i <= context.numberOfParty; i++)
		{
			// Affiche l'information de la partie.
			this.print(StringMaker.party(i));

			// Lance un jeu jusqu'à ce qu'une partie soit terminée.
			while (!isPartyFinished()) {
				this.launchOneGame();
			}

			// Réinitialise le score des joueurs.
			this.resetPlayersScore();

			// Affiche les scores des joueurs.
			this.print(StringMaker.scores(players, context));

		}

		// Ferme les sorties.
		context.outputs.close();
	}

	// Lance une partie de jeu.
	private void launchOneGame() {
		// Crée un nouveau jeu et ajoute les joueurs.
		this.game = new Game(context);
		this.addPlayers(players);

		// Sert des cartes aux piles et aux joueurs.
		this.serveCardsToStacks();
		this.serveCardsToPlayers();

		// Boucle sur chaque tour de jeu.
		for (int i = 0; i < context.numberOfGivenCards; i++) {
			// Les joueurs jouent leurs cartes.
			this.playCards();

			// Affiche les cartes jouées.
			this.print(StringMaker.playedCards(game.getPlayedCardList()));

			// Traite les cartes jouées.
			this.processPlayedCards();
		}
	}

	// Traite les cartes jouées par les joueurs.
	private void processPlayedCards() {
		// Boucle sur chaque joueur.
		for (int i = 0; i < game.getPlayers().size(); i++) {
			// Obtient la carte jouée la plus basse.
			Entry<Player, Card> playedCard = game.getLowerPlayedCard();
			Player player = playedCard.getKey();
			Card card = playedCard.getValue();
			Stacks stacks = game.getStackManager().getStacks();

			// Affiche la carte jouée par le joueur.
			this.print(StringMaker.playsCard(player, card));

			// Traite la carte sur les piles et calcule le score du coup.
			int score = game.getStackManager().receiveCard(card,
					new PlayContext(context, game.getPlayedCardsHistory(), player, stacks));

			// Affiche les piles.
			this.print(StringMaker.stacks(stacks));

			// Met à jour le score du joueur.
			if (score > 0) {
				player.addScore(score);
				this.print(StringMaker.getsPoints(player, score));
			}

			// Affiche les joueurs.
			this.print(StringMaker.players(players));

		}
	}

	// Initialise les piles avec une carte.
	private void serveCardsToStacks() {
		this.print(StringMaker.serving());
		for (int i = 0; i < context.numberOfStacks; i++) {
			Card card = game.getCards().getOneCard();
			this.game.getStackManager().receiveCard(card, null);
		}
	}

	//Donne  les cartes aux joueurs
	private void serveCardsToPlayers() {
		for (Player p : game.getPlayers()) {
			for (int i = 0; i < context.numberOfGivenCards; i++)
				p.receiveCard(game.getCards().getOneCard());
		}
	}

	//Demande à un joueur de jouer une carte
	private void playCards() {
		for (Player currentPlayer : game.getPlayers()) {
			Card playedCard = currentPlayer.playCard(
					new PlayContext(context, game.getPlayedCardsHistory(), currentPlayer, game.getStackManager().getStacks()));
			game.addPlayedCard(currentPlayer, playedCard);
		}
	}
	
	//Verifie si le jeu est finis
	private boolean isPartyFinished() {
		if (this.game == null || this.players == null)
			return false;

		for (Player p : this.game.getPlayers())
			if (p.getScore() >= context.partyEndScore)
				return true;
		return false;
	}	

	//Reset le score du joueur
	private void resetPlayersScore() {
		for (Player p : players)
			p.resetScore();
	}


	//Ajoute un joueur au jeu

	public void addPlayers(List<Player> players) {
		for (Player p : players)
			this.game.addPlayer(p);
	}
	
	private void print(String s) {
		this.context.outputs.print(s);
	}
}
