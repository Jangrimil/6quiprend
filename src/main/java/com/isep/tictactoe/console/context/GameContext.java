package com.isep.tictactoe.console.context;

import com.isep.tictactoe.console.output.OutputCollection;
import com.isep.tictactoe.console.output.OutputConsole;

// Cette classe contient divers paramètres pour l'initialisation du jeu.
public class GameContext {

	// Le nombre de cartes distribuées aux joueurs.
	public int numberOfGivenCards;

	// Le nombre de piles.
	public int numberOfStacks;

	// Le nombre de cartes pour le jeu de cartes.
	public int numberOfCards;

	// Le score limite qui met fin à une partie.
	public int partyEndScore;

	// Le nombre de parties.
	public int numberOfParty;

	// Le nombre de joueurs.
	public int numberOfPlayers;

	// Les sorties.
	public OutputCollection outputs;

	// Constructeur de la classe GameContext.
	public GameContext(int numberOfGivenCards, int numberOfStacks, int numberOfCard, int partyEndScore, int numberOfParty) {
		this.numberOfGivenCards = numberOfGivenCards;
		this.numberOfStacks = numberOfStacks;
		this.numberOfCards = numberOfCard;
		this.partyEndScore = partyEndScore;
		this.numberOfParty = numberOfParty;

		// Initialisation des sorties.
		this.initOutputs();
	}
	public GameContext(GameContext gameContext) {
		this.numberOfGivenCards = gameContext.numberOfGivenCards;
		this.numberOfStacks = gameContext.numberOfStacks;
		this.numberOfCards = gameContext.numberOfCards;
		this.partyEndScore = gameContext.partyEndScore;
		this.numberOfParty = gameContext.numberOfParty;
		this.numberOfPlayers = gameContext.numberOfPlayers;
		this.outputs = gameContext.outputs;
	}

	// Initialisation des sorties.
	private void initOutputs(){
		outputs = new OutputCollection();
		outputs.add(new OutputConsole());
	}
}