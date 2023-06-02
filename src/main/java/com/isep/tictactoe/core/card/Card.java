package com.isep.tictactoe.core.card;

// Cette classe représente une carte du jeu. Une carte a un numéro unique et un score utilisé pour calculer le score des joueurs.
public class Card {

	// Le numéro de la carte.
	private int number;

	// Le score de la carte.
	private int score;

	// Constructeur de la classe Card.
	public Card(int number, int score) {
		super();
		this.number = number;
		this.score = score;
	}

	// Getters et setters pour le numéro et le score de la carte.
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	// Méthode toString pour afficher les informations de la carte.
	@Override
	public String toString() {
		return "[N°" + number + " (" + score + "p)]";
	}

	// Méthodes hashCode et equals pour vérifier l'égalité entre deux objets Card.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + score;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (number != other.number)
			return false;
		if (score != other.score)
			return false;
		return true;
	}
}
