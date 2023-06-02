
package com.isep.tictactoe.console.context;

import com.isep.tictactoe.core.card.Card;
import com.isep.tictactoe.core.card.PlayedCardCollection;
import com.isep.tictactoe.core.player.Player;
import com.isep.tictactoe.core.stack.Stacks;

import java.util.List;

// Cette classe représente le contexte de jeu pour un joueur à un tour donné.
public class PlayContext {

	// Le joueur qui doit jouer.
	private Player player;

	// L'état des piles pour un tour donné.
	private Stacks stacks;

	// Le contexte du jeu.
	private GameContext gameContext;

	// L'historique des cartes jouées.
	private PlayedCardCollection playedCardHistory;

	public PlayContext(GameContext gameContext, PlayedCardCollection playedCardCollection, Player player, Stacks stacks) {
		this.gameContext = new GameContext(gameContext);
		this.playedCardHistory = new PlayedCardCollection(playedCardCollection);
		this.player = new Player(player);
		this.stacks = new Stacks(stacks);
	}


	public PlayContext(PlayContext playContext) {
		this.gameContext = new GameContext(playContext.getGameContext());
		this.playedCardHistory = new PlayedCardCollection(playContext.getPlayedCardHistory());
		this.player = new Player(playContext.getPlayer());
		this.stacks = new Stacks(playContext.getStacks());
	}

	// Les cartes que le joueur peut jouer.
	public List<Card> getMyPlayableCards() {
		return this.player.getCards();
	}

	// Le score du joueur.
	public int getMyScore() {
		return this.player.getScore();
	}

	// Getter et setter pour le joueur.
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}

	// Getter et setter pour les piles.
	public Stacks getStacks() {
		return stacks;
	}


	// Getter pour le contexte du jeu.
	public GameContext getGameContext() {
		return gameContext;
	}
 //Getter pour l'historique des cartes jouées.
	public PlayedCardCollection getPlayedCardHistory() {
		this.playedCardHistory = playedCardHistory;
		return playedCardHistory;
	}
}