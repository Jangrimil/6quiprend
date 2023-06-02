package com.isep.tictactoe.core.player;

import com.isep.tictactoe.core.card.Card;
import com.isep.tictactoe.console.context.PlayContext;
import com.isep.tictactoe.core.player.callable.PlayerPlayCardCallable;
import com.isep.tictactoe.core.player.callable.PlayerSelectStackCallable;
import com.isep.tictactoe.console.utils.Randomizer;
import com.isep.tictactoe.console.utils.StringMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * La classe Player représente un joueur dans le jeu TakenSix. Un joueur a un nom,
 * une liste de cartes à jouer, un score et détermine chaque mouvement grâce à un PlayerChooser.
 * Vous devez implémenter un nouveau PlayerChooser si vous souhaitez créer votre propre IA.
 */
public class Player {
	public final static int IA_TIMEOUT = 300;
	// Le nom du joueur.
	private String name;

	// Les cartes à jouer du joueur.
	private List<Card> cards;

	// Le score du joueur.
	private int score;

	// Le cerveau du joueur.
	private PlayerChooser chooser;

	// Les temps de réflexion.
	private List<Long> playTimes;

	// Le délai d'attente pour jouer.
	private int playTimeout;


	/*
	 * Instancie un nouveau joueur.
	 */
	public Player(String name, PlayerChooser chooser) {
		this.name = name;
		this.cards = new ArrayList<>();
		this.chooser = chooser;
		this.score = 0;
		this.playTimeout = IA_TIMEOUT;
		this.playTimes = new ArrayList<>();
	}


	/*
	 * Instancie un nouveau joueur à partir d'un autre joueur.
	 *
	 */
	public Player(Player player) {
		this.name = player.getName();
		this.cards = new ArrayList<>(player.getCards());
		this.chooser = player.getChooser();
		this.score = player.getScore();
		this.playTimeout = player.getPlayTimeout();
		this.playTimes = player.getPlayTimes();
	}

	/*
	 * Demande au cerveau de sélectionner une carte à jouer en fonction des différentes
	 * informations contenues dans le PlayContext. La carte sélectionnée est ensuite retirée
	 * de la liste des cartes à jouer.
	 */

	public final Card playCard(PlayContext playContext) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Card> future = executor.submit(new PlayerPlayCardCallable(chooser, playContext));
		Card playedCard = null;


		try {
			playedCard = future.get(playTimeout, TimeUnit.SECONDS);

		} catch (TimeoutException | InterruptedException | ExecutionException e) {

			playedCard = this.cards.get(Randomizer.nextInt(cards.size()));
			print(playContext, StringMaker.playsCardTimeout(this, playedCard));
		}

		executor.shutdownNow();
		this.cards.remove(playedCard);

		return playedCard;
	}

	// Demande au cerveau de sélectionner une pile lorsque la carte jouée ne peut pas être empilée.

	public int chooseStack(PlayContext playContext) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Integer> future = executor.submit(new PlayerSelectStackCallable(chooser, playContext));
		int stackIndex = -1;
		try {
			stackIndex = future.get(playTimeout, TimeUnit.SECONDS);
		} catch (TimeoutException | InterruptedException | ExecutionException e) {
			stackIndex = Randomizer.nextInt(1, playContext.getGameContext().numberOfStacks);
			print(playContext, StringMaker.selectStackTimeout(this, stackIndex));
		}
		
		return stackIndex;
	}

	//Ajoute le score au score actuel du joueur.

	public final void addScore(int score) {
		this.score += score;
	}

	// Obtient le score du joueur.

	public final int getScore() {
		return this.score;
	}

	// Obtient le nom du joueur.

	public final String getName() {
		return this.name.toUpperCase();
	}

	// Ajoute une carte à la liste des cartes du joueur.

	public final void receiveCard(Card card) {
		this.cards.add(card);
		this.sortCards();
	}

	// Obtient le cerveau du joueur.

	public final PlayerChooser getChooser() {
		return chooser;
	}

	// Obtient la liste des cartes du joueur.

	public final List<Card> getCards() {
		return cards;
	}

	// Trie les cartes par ordre croissant.

	private final void sortCards() {
		List<Card> sortedList = new ArrayList<>();

		while (!this.cards.isEmpty())
			sortedList.add(getLowerCard());

		this.cards = sortedList;
	}

	//Obtient la carte la plus basse en fonction du numéro de la carte.
	//Cette méthode est utilisée pour le tri.
	private final Card getLowerCard() {
		Card minimumCard = null;
		for (Card c : this.cards) {
			if (minimumCard == null || minimumCard.getNumber() > c.getNumber())
				minimumCard = c;
		}

		this.cards.remove(minimumCard);
		return minimumCard;
	}

	//Réinitialise le score du joueur.

	public final void resetScore() {
		this.score = 0;
	}


	// Obtient le délai d'attente pour jouer.
	public List<Long> getPlayTimes() {
		return this.playTimes;
	}
	
	// Le temps de jeu.

	public int getPlayTimeout() {
		return playTimeout;
	}
	
	private void print(PlayContext playContext, String s) {
		playContext.getGameContext().outputs.print(s);
	}

	@Override
	public String toString() {
		String str = name.toUpperCase() + " [" + score + "] ";
		// for (Card c : cards)
		// str += c.toString() + " ";
		return str;
	}
}
