package com.isep.tictactoe.console.utils;

import java.util.Random;

public class Randomizer {
	static Random random;
	
	static {
		random = new Random();
	}

	// Méthode pour générer un nombre aléatoire entre minIncluded et maxIncluded.
	public static int nextInt(int minIncluded, int maxIncluded) {
		return random.nextInt(maxIncluded - minIncluded + 1) + minIncluded;
	}

	// Méthode pour générer un nombre aléatoire entre 0 (inclus) et maxExcluded (exclus).
	public static int nextInt(int maxExcluded) {
		return random.nextInt(maxExcluded);
	}
}
