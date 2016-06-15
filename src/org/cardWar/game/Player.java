package org.cardWar.game;

import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player {

    private final int playerNumber;
    private final List<Card> handCards = new ArrayList<>();

    private final List<Card> stackCards = new ArrayList<>();

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void addCardsToStack(List<Card> cards) {
        stackCards.addAll(cards);
    }

    public void addCardToHand(Card card) {
        handCards.add(card);
    }

    public Optional<Card> playCard() {
        // last cards dealt are played first
    	if(handCards.isEmpty()) return null;
        Optional<Card> card = Optional.ofNullable(handCards.get(handCards.size() - 1));
        Iterables.getLast(handCards);
        if (card.isPresent()) {
            handCards.remove(card.get());
        }
        return card;
    }

    public boolean hasHandCards() {
        return (!handCards.isEmpty());
    }

    public int getPlayerNumber () {
        return playerNumber;
    }

 

    public int getNumberOfStackCards() {
        return stackCards.size();
    }
	
}
