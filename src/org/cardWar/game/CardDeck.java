package org.cardWar.game;

import java.util.ArrayList;
import java.util.Random;

import java.util.ArrayList;
import java.util.Random;
 
public class CardDeck implements Deck{
 
            public static final int DECK_SIZE = 52;
//         private Card[] entireDeck = new Card[DECK_SIZE];
            private ArrayList<Card> deckOfCards;
            private static int realDeckSize = 0;
 
            @Override
            public void create(int numberOfSuits, int numberOfRanks) {
                        int index = 0;
                        deckOfCards = new ArrayList<Card>();
 
                        for (int i = 0; i < numberOfSuits; i++)
                        {
 
                                    for (int j = 0; j < numberOfRanks; j++)
                                    {
                                                Card tempCard = new Card();
                                                tempCard.setRank(j);
                                                tempCard.setSuit(i);
                                                //entireDeck[index] = tempCard;
                                                this.deckOfCards.add(tempCard);
                                                index++;
                                    }
                        }
            }
 
            @Override
            public void shuffle() {
                        for (int i = 0; i < DECK_SIZE; i++) {
                                    int rand = new Random().nextInt(51);
                                    Card temp = deckOfCards.get(i);
                                    Card tempSecond = deckOfCards.get(rand);
                                    deckOfCards.set(i,tempSecond);
                                    deckOfCards.set(rand, temp);
                        }
                        realDeckSize = 52;
            }
 
            @Override
            public Card deal() {
                       
                        if(deckOfCards.size()!=0){
                                    Card tempCard = deckOfCards.get(0);
                                    deckOfCards.remove(0);
                                    return tempCard;
                        }
                        return null;
                       
//                     Optional<Card> nextCard = deckOfCards.stream().findFirst();
//                    
//                     if(this.deckOfCards.size()==0) return null;
//                     else{
//                                 realDeckSize--;
//        
//                     return entireDeck[realDeckSize];
//                     }
            }
 
            public ArrayList<Card> getDeck() {
                        return this.deckOfCards;
            }
            public int getRealDeckSize(){
                        return deckOfCards.size();
                       
             }
}