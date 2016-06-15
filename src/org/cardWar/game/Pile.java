package org.cardWar.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


public class Pile {

	private List<Card> cards = new ArrayList<>();
    private Player winner;

    public void addPlayedCards(List<Optional<Card>> newCards) {
        cards.addAll(newCards.stream().filter(Optional::isPresent).map(Optional::get).collect(toList()));
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getWinner() {
        return winner;
    }
	
}
