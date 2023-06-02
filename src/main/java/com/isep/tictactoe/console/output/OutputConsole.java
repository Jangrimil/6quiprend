package com.isep.tictactoe.console.output;

// Cette classe représente une sortie vers la console.
public class OutputConsole  {

	// Symbole pour indiquer une saisie de l'utilisateur dans la console.
	public final static String CONSOLE_INPUT_SYMBOL = "$>";

	// Méthode pour écrire une chaîne de caractères sur la console.
	public void write(String s){
		if (s.trim().endsWith(CONSOLE_INPUT_SYMBOL))
			System.out.print(s);
		else
			System.out.println(s);
	}

	// Méthode pour fermer la sortie console. Ici, rien à faire car la console système ne nécessite pas de fermeture explicite.
	public void close() {
		// Tout code de nettoyage nécessaire pour fermer la console irait ici
	}
}