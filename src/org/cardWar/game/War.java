package org.cardWar.game;


import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Ordering;
import com.google.common.collect.TreeMultimap;


import java.util.*;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class War {

	    // use of 'static' here is not ideal, but ok for demo purposes
	    private static List<Player> activePlayers;

	    public void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
	        Deck deck = new CardDeck();
	        deck.create(numberOfSuits, numberOfRanks);
	        deck.shuffle();
	        List < Player > winners = playWar(numberOfPlayers, deck);
	        System.out.println(winners.get(0).getPlayerNumber()+"  "+ winners.get(0).getNumberOfStackCards());
	    }

	    List<Player> playWar(int numberOfPlayers, Deck deck) {
	        List<Player> players = initializePlayers(numberOfPlayers);
	        dealAllCardsToPlayers(players, deck);
	        while (activePlayers.size() > 0) {
	            Pile pile = new Pile();
	            playBattle(activePlayers, pile);
	            if (pile.getWinner() != null) {
	                pile.getWinner().addCardsToStack(pile.getCards());
	            }
	        }
	        return determineWinnerOfGame(players);
	    }

	    void dealAllCardsToPlayers(List<Player> players, Deck deck) {
	        for (int cardNumber = 0; cardNumber < deck.getRealDeckSize() / players.size(); cardNumber++) {
	            for (Player player : players) {
	                player.addCardToHand(deck.deal());
	            }
	        }
	    }

	    Map<Player, Optional<Card>> playSingleCards(List<Player> players) {
	        return players.stream().collect(toMap(player -> player, Player::playCard));
	    }

	    /*
	        A "battle" consists of each player playing a single card.
	        battles continue recursively until a player wins the pile.
	     */
	    void playBattle(List<Player> players, Pile pile) {
	        Map<Player, Optional<Card>> nextPlayerCards = playSingleCards(players);
	        pile.addPlayedCards(nextPlayerCards.values().stream().collect(toList()));

	        List<Player> rankingPlayers = determineWinnerOfBattle(nextPlayerCards);

	        if (rankingPlayers.size() > 1) {
	            playBattle(rankingPlayers, pile);
	        } else if (rankingPlayers.size() == 1) {
	            pile.setWinner(rankingPlayers.get(0));
	        }
	        List<Player> checkPlayers = new ArrayList<>(players);
	        checkPlayers.stream()
	                .filter(player -> !player.hasHandCards())
	                .forEach(players::remove);
	    }

	    @VisibleForTesting
	    List<Player> determineWinnerOfBattle(Map<Player, Optional<Card>> playedCards) {
	        TreeMultimap<Integer, Player> rankingTree = TreeMultimap.create(Ordering.natural().reverse(), Ordering.usingToString());

	        // stream the cards into the tree for sorting purposes
	        playedCards.keySet().stream()
	                .filter(player -> playedCards.get(player).isPresent())
	                .forEach(player -> rankingTree.put(playedCards.get(player).get().getRank(), player));

	        // highest rank always wins (and avoids 'war') even when lesser ranks might be tied
	        Optional<Integer> highestRank = rankingTree.keySet().iterator().hasNext() ? Optional.of(rankingTree.keySet().iterator().next()) : Optional.empty();

	        // multiple players are returned when the highest rank is duplicated in another suit
	        return highestRank.isPresent() ? rankingTree.get(highestRank.get()).stream().collect(toList()) : Collections.<Player>emptyList();
	    }

	    @VisibleForTesting
	    List<Player> determineWinnerOfGame(List<Player> players) {
	        TreeMultimap<Integer, Player> rankingTree = TreeMultimap.create(Ordering.natural().reverse(), Ordering.usingToString());

	        for (Player player : players) {
	            rankingTree.put(player.getNumberOfStackCards(), player);
	        }
	        int highestRank = rankingTree.keySet().iterator().next();
	        List<Player> winners = rankingTree.get(highestRank).stream().collect(toList());

	        return winners;
	    }


	    private List<Player> initializePlayers(int numberOfPlayers) {
	        activePlayers = new ArrayList<>(numberOfPlayers);
	        List<Player> players = new ArrayList<>(numberOfPlayers);
	        for (int i = 0; i < numberOfPlayers; i++) {
	            players.add(new Player(i));
	        }
	        activePlayers.addAll(players);
	        return players;
	    }

	    public static void main (String[] args) {

	        War war = new War();
	        int suits = 4;
	        int ranks = 13;
	        int players = 4;
	        war.play(suits, ranks, players);
	    }
	
}
