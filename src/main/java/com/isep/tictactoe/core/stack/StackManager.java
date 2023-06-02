package com.isep.tictactoe.core.stack;

import com.isep.tictactoe.core.card.Card;
import com.isep.tictactoe.console.context.PlayContext;
import com.isep.tictactoe.core.player.Player;
import com.isep.tictactoe.console.utils.StringMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * The Class StackManager. It handles the game's stacks.
 */
public class StackManager {

	// Le nombre de piles.
	private Stacks stacks;
	// Le nombre de piles.
	int numberOfStacks;
	public final static int MAX_CARD_PER_STACK = 5;
	// Le nombre de piles initialisées. Une pile est initialisée avec une carte.
	int numberOfInitializedStacks;

	// Constructeur qui initialise le nombre de piles.
	public StackManager(int numberOfStacks) {
		this.numberOfStacks = numberOfStacks;
		this.init();
	}
	// Constructeur qui initialise avec des piles existantes.
	public StackManager(Stacks stacks) {
		this.stacks = stacks;
	}

	// Cette méthode reçoit une carte jouée, détermine sur quelle pile elle doit être placée et calcule le score du mouvement.
	public int receiveCard(Card card, PlayContext playContext) {
		int destinationStackIndex;
		int penalty = 0;

		// First received cards are for initialization (one card per stack)
		// Others will be place on the right stack
		if (numberOfInitializedStacks < numberOfStacks) {
			destinationStackIndex = getStackIndexToInitialize();
			numberOfInitializedStacks++;
		} else {
			destinationStackIndex = getStackIndexForCard(card);
		}

		// Asks the player to clean a stack if the played card can't be stacked
		if (destinationStackIndex < 0) {
			destinationStackIndex = playContext.getPlayer().chooseStack(playContext);

			playContext.getGameContext().outputs
					.print(StringMaker.choosesStack(playContext.getPlayer(), destinationStackIndex));

			penalty += cleanStack(this.stacks.get(destinationStackIndex));
		}

		// Process move and compute score
		penalty += this.addCardToStack(card, destinationStackIndex);
		return penalty;
	}

	// Cette méthode simule un mouvement et retourne le contexte de jeu après le traitement du mouvement. Elle est utilisée par le simulateur.
	public PlayContext simulate(PlayContext playContext, Card card) {
		int penalty = 0;
		int destinationStackIndex = getStackIndexForCard(card);

		if (destinationStackIndex < 0) {
			destinationStackIndex = playContext.getPlayer().chooseStack(playContext);
			penalty += cleanStack(this.stacks.get(destinationStackIndex));
		}

		penalty += this.addCardToStack(card, destinationStackIndex);

		Player player = playContext.getPlayer();
		player.addScore(penalty);
		player.getCards().remove(card);

		return playContext;
	}

	// Cette méthode ajoute une carte à une pile et retourne le score du mouvement.
	private int addCardToStack(Card card, int destinationStackIndex) {
		if (destinationStackIndex < 0)
			return 0;

		List<Card> stack = this.stacks.get(destinationStackIndex);

		int returnedScore = 0;

		if (stack.size() >= MAX_CARD_PER_STACK)
			returnedScore = cleanStack(stack);

		stack.add(card);
		this.stacks.put(destinationStackIndex, stack);

		return returnedScore;
	}

	// Cette méthode nettoie une pile et retourne le score total de la pile.
	private int cleanStack(List<Card> stack) {
		int totalScore = 0;

		for (Card c : stack)
			totalScore += c.getScore();

		stack.clear();

		return totalScore;
	}

	// Cette méthode retourne l'index de la pile à initialiser.
	private int getStackIndexToInitialize() {
		for (Entry<Integer, List<Card>> stack : stacks.entrySet()) {
			if (stack.getValue().isEmpty())
				return stack.getKey();
		}

		return -1;
	}

	// Cette méthode détermine la pile qui recevra la carte jouée.
	public int getStackIndexForCard(Card card) {
		int bestStackIndex = -1;
		int bestStackCardNumber = -100;

		int cardNumber = card.getNumber();

		for (Entry<Integer, List<Card>> entry : stacks.entrySet()) {
			List<Card> stack = entry.getValue();
			int maxStackNumber = stack.get(stack.size() - 1).getNumber();

			if (cardNumber < maxStackNumber)
				continue;

			if (maxStackNumber > bestStackCardNumber) {
				bestStackIndex = entry.getKey();
				bestStackCardNumber = maxStackNumber;
			}
		}

		return bestStackIndex;
	}

	// Cette méthode prépare les piles.
	private void init() {
		this.numberOfInitializedStacks = 0;

		this.stacks = new Stacks();
		for (int i = 1; i <= numberOfStacks; i++) {
			this.stacks.put(i, new ArrayList<Card>());
		}
	}

	// Getter pour les piles.
	public Stacks getStacks() {
		return stacks;
	}

}
