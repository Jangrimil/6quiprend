
package com.isep.tictactoe.core.player.human;

import com.isep.tictactoe.core.card.Card;
import com.isep.tictactoe.console.context.PlayContext;
import com.isep.tictactoe.core.player.PlayerChooser;
import com.isep.tictactoe.core.stack.Stacks;
import com.isep.tictactoe.console.utils.StringMaker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map.Entry;

public class PlayerChooserHumanConsole implements PlayerChooser {
	static BufferedReader scan;

	static {
		scan = new BufferedReader(new InputStreamReader(System.in));
	}

	// Méthode utilitaire pour lire un entier depuis la console
	public static final int nextInt(){
		int selected = -1;
		try {
			selected = Integer.parseInt(scan.readLine());
		} catch (NumberFormatException | IOException e) {
		}
		return selected;
	}

	// Demande au joueur de choisir une pile
	public static String askStack(Stacks stacks, int suggestion) {
		String str = StringMaker.stacks(stacks);
		str = str.replace("{", "").replace("}", ":");
		str += "Quelle pile souhaitez-vous récupérer ? [1-"+ stacks.size() + "] (pile n°"+suggestion+ " suggérée)\n";
		return str + "$>";
	}

	// Demande au joueur de choisir une carte parmi celles qu'il possède
	public static String askCard(List<Card> playerCards) {
		String str = "[VOS CARTES]\n";
		int size = playerCards.size();
		for (int i = 1; i <= size; i++)
			str += i + ": " + playerCards.get(i-1).toString() + "\n";
		str += "Choisissez une carte ? [1-"+size+"]\n";
		return str + "$>";
	}

	// Obtient la suggestion de pile en fonction du score des cartes dans chaque pile
	private int getStackSuggestion(Stacks stacks) {
		int bestKey = -1;
		int bestValue = 1000;

		for (Entry<Integer, List<Card>> e : stacks.entrySet()) {
			int score = 0;
			for (Card c : e.getValue())
				score += c.getScore();

			if (score < bestValue) {
				bestValue = score;
				bestKey = e.getKey();
			}
		}
		return bestKey;
	}

	@Override
	public Card chooseCard(PlayContext playContext) {
		this.print(playContext, StringMaker.stacks(playContext.getStacks()));

		int selected = -1;
		List<Card> cards = playContext.getPlayer().getCards();
		while (selected < 1 || selected > cards.size()) {
			this.print(playContext, askCard(cards));
			selected = nextInt();
		}

		return cards.get(selected - 1);
	}

	@Override
	public int chooseStack(PlayContext playContext) {
		Stacks stacks = playContext.getStacks();

		int suggestion = getStackSuggestion(stacks);

		int selected = -1;
		int numberOfStacks = stacks.size();
		while (selected < 1 || selected > numberOfStacks) {
			this.print(playContext, askStack(stacks, suggestion));
			selected = nextInt();
		}

		return selected;
	}

}
