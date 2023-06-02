package com.isep.tictactoe.console.utils;

import com.isep.tictactoe.core.card.Card;
import com.isep.tictactoe.core.card.PlayedCardCollection;
import com.isep.tictactoe.console.context.GameContext;
import com.isep.tictactoe.core.player.Player;
import com.isep.tictactoe.core.stack.Stacks;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StringMaker {
	public final static String SEPARATOR = "------------------------\n";
	public final static String CONSOLE_INPUT_SYMBOL = "$>";


	// Méthode pour générer une chaîne de caractères représentant la liste des joueurs.
	public static String players(List<Player> players) {
		String str = "[PLAYERS & SCORES]\n";
		for (Player p : players)
			str += p.toString() + "\n";
		return separate(str);
	}

	// Méthode pour générer une chaîne de caractères représentant les scores des joueurs.
	public static String scores(List<Player> players, GameContext gameContext) {
		String str = "[SCORES]\n\n";
		return separate(str);
	}


	public static String playedCards(Map<Player, Card> playedCards) {
		String str = "[PLAYED CARDS]\n";
		for (Entry<Player, Card> e : playedCards.entrySet())
			str += e.getKey().getName() + ": " + e.getValue().toString() + "\n";
		return separate(str);
	}
	
	public static String playsCard(Player player, Card card) {
		String str = player.getName() + " PLAYS " + card.toString() + '\n';
		return separate(str);
	}
	
	public static String playsCardTimeout(Player player, Card playedCard) {
		String str = "/!\\ " + player.getName() + " DIDN'T PLAY IN TIME (" + player.getPlayTimeout()
		+ "s max).\nSO HE RAMDOMLY PLAYED : " + playedCard;
		return str;
	}
	
	public static String selectStackTimeout(Player player, int stackIndex) {
		String str = "/!\\ " + player.getName() + " DIDN'T PLAY IN TIME (" + player.getPlayTimeout()
		+ "s max).\nSO HE RAMDOMLY SELECTED : {" + stackIndex + "}";
		return str;
	}
	
	public static String getsPoints(Player player, int points){
		return player.getName() + " GETS " + points + " POINTS";
	}
	
	public static String choosesStack(Player player, int stackIndex){
		return player.getName() + " CHOOSES STACK {" + stackIndex + "}\n";
	}
	
	public static String stacks(Stacks stacks) {
		String str = "[STACKS]\n";
		str += stacks.toString() + "\n";
		return separate(str);
	}
	
	public static String askCard(List<Card> playerCards) {
		String str = "[YOUR CARDS]\n";
		int size = playerCards.size();
		for (int i = 1; i <= size; i++)
			str += i + ": " + playerCards.get(i-1).toString() + "\n";
		str += "Choose a card ? [1-"+size+"]\n";
		return str + CONSOLE_INPUT_SYMBOL;
	}


	// Méthode pour ajouter un séparateur autour d'une chaîne de caractères.
	public static String separate(String s) {
		return SEPARATOR + s + SEPARATOR + "\n";
	}

	// Méthode pour ajouter un séparateur personnalisé autour d'une chaîne de caractères un certain nombre de fois.
	public static String separate(String s, String separator, int nbOccurence) {
		String sep = "";
		for (int i = 0; i < nbOccurence; i++)
			sep += separator;
		sep += "\n";
		return sep + s + sep;
	}

	public static String party(int i) {
		return separate("\tPARTY " + i + "\n", "*", 23) + "\n";
	}

	public static String serving() {
		return separate("SERVING CARDS\n");
	}



	public static String askStack(Stacks stacks, int suggestion) {
		String str = StringMaker.stacks(stacks);
		str = str.replace("{", "").replace("}", ":");
		str += "Which stack do you want to get ? [1-"+ stacks.size() + "] (stack n�"+suggestion+ " suggested)\n";
		return str + CONSOLE_INPUT_SYMBOL;
	}

	public static String playedCards(PlayedCardCollection playedCardHistory) {
		String str = "[PLAYED CARDS HISTORY]\n";
		for (Entry<String, List<Card>> entry : playedCardHistory.entrySet()){
			str += entry.getKey() + " |";
			for (Card c : entry.getValue())
				str += " " + c.toString();
			str += "\n";
		}
		return separate(str);
	}
	

}
