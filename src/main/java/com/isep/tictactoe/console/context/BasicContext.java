package com.isep.tictactoe.console.context;

// Cette classe représente le contexte standard de TakenSix :
// * 10 cartes distribuées aux joueurs
// * 5 piles pour jouer
// * 104 cartes uniques
// * 66 est le score de fin de partie
public class BasicContext extends GameContext {

	// Constructeur de la classe BasicContext.
	public BasicContext(int numberOfParty) {
		super(10, 4, 104, 66, numberOfParty);
	}
}