package org.cardWar.game;

public class Card implements Comparable {
	
	public static final int SPADES = 0;
	public static final int CLUBS = 1;
	public static final int DIAMONDS = 2;
	public static final int HEARTS = 3;
	
	
	private int rank;
	private int suit;


	int getRank()
	{
		return rank;
	}
	int getSuit()
	{
		return suit;
	}
	
	
	public void setSuit(int newSuit) {
		if (newSuit >= 0 && newSuit <= 3) {
			this.suit = newSuit;
		}
	}


	public void setRank(int r) {
		this.rank = r;
	}


	
	public void setSuitFromString(String suit) {
		if (suit.equals("SPADES")) {
			this.suit = Card.SPADES;
		}
		else if (suit.equals("CLUBS")) {
			this.suit = Card.CLUBS;
		}
		else if (suit.equals("DIAMONDS")) {
			this.suit = Card.DIAMONDS;
		}
		else if (suit.equals("HEARTS")) {
			this.suit = Card.HEARTS;
		}
		else {
			System.out.println("Illegal suit!");
		}
	}

	public String getSuitAsString() {
		if (this.suit == Card.SPADES) {
			return "SPADES";
		}
		else if (this.suit == Card.CLUBS) {
			return "CLUBS";
		}
		else if (this.suit == Card.HEARTS) {
			return "DIAMONDS";
		}
		else { 
			return "HEARTS";
		}
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public String toString() {
		return "Card [rank=" + rank + ", suit=" + suit + "]";
	}	
	
	
	
}
