package com.isep.tictactoe.console.output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Cette classe représente une collection de sorties, par exemple, pour imprimer des informations sur la console.
public class OutputCollection {

	// Une liste d'objets OutputConsole.
	private List<OutputConsole> outputs;

	// Constructeur de la classe OutputCollection.
	public OutputCollection() {
		this.outputs = new ArrayList<>();
	}

	// Méthode pour ajouter un ou plusieurs objets OutputConsole à la collection.
	public void add(OutputConsole... outputs) {
		this.outputs.addAll(Arrays.asList(outputs));
	}

	// Méthode pour fermer toutes les sorties dans la collection.
	public void close() {
		for (OutputConsole o : outputs)
			o.close();
	}

	// Méthode pour imprimer une chaîne de caractères sur toutes les sorties de la collection.
	public void print(String s) {
		for (OutputConsole o : outputs)
			o.write(s);
	}

	// Méthode pour imprimer une chaîne de caractères à des fins de débogage sur toutes les sorties de la collection.
	public void printDebug(String s) {
		for (OutputConsole o : outputs)
			o.write(s);
	}

	// Méthode pour imprimer une chaîne de caractères suivie d'un saut de ligne à des fins de débogage sur toutes les sorties de la collection.
	public void printlnDebug(String s) {
		for (OutputConsole o : outputs)
			o.write(s + "\n");
	}
}
