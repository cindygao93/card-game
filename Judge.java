/** 
 Judge Class
 COMP1406/1006 - Fall 2013
 Assignment 10 - Problem 1

Name: Cindy Gao
Student Number: 100861300
Date: December 9, 2013

 A class called Judge which starts and end the game, keeps track of the submitted pairs from 
 all players in a card game, keeps track of each players scores and declares the winner at 
 the end of the game.
 
*/

import java.util.HashMap;
import java.util.Collections;

public class Judge{
  
  //attributes
  protected boolean keepPlaying = false;
  protected Player currentPlayer;
  protected java.util.ArrayList<Player> players;
  protected int position;
  protected HashMap<Player, java.util.ArrayList<Card>> playerpairs = new HashMap<Player, java.util.ArrayList<Card>>();
  protected int turns;
  protected java.util.ArrayList<Card> insertedCards = new java.util.ArrayList<Card>();
  
  public boolean keepPlaying(){
    // return true if the current game has not ended
    // return false if the current game has ended
    
    if (turns >=5000){
      keepPlaying = false;
    }
    return keepPlaying;
  }
  
  public void startGame(Deck deck, java.util.ArrayList<Player> players, Pile pile){
    //
    // Do not change this method
    //
    // starts game by dealing out 8 cards from the deck to each player
    // and placing the remaining cards in the pile
    int numPlayers = players.size();
    int c = 0;
    for(int i=0; i<8; i+=1){
      for(int p=0; p<numPlayers; p+=1){
        players.get(p).addCard(deck.getCard(c));
        c += 1;
      }
    }
    while( c < deck.numCards() ){
      pile.add( deck.getCard(c) );
      c+=1;
    }
    
    this.players = players;
    position = 0;
    turns = 0;
    currentPlayer = players.get(position);
    this.keepPlaying = true;
    
    HashMap<Player, java.util.ArrayList<Card>> playerpairs = new HashMap<Player, java.util.ArrayList<Card>>();
    
    for (int i=0; i<players.size(); i=i+1){
      playerpairs.put(players.get(i), new java.util.ArrayList<Card>());
    }
  }
  
  public void takePair(Card c1, Card c2, Player p) /*throws CheaterException*/{
    // take a pair of cards from player p
    //throw an exception if the submit9ted pairs are not 
    if (c1.getRank() == c2.getRank()){
      
      java.util.ArrayList<Card> pairs;
      
      if (playerpairs.get(p) == null){
        pairs = new java.util.ArrayList<Card>();
      }
      else{ 
        pairs = playerpairs.get(p);
      }
      pairs.add(c1);
      pairs.add(c2);
      playerpairs.put(p, pairs);
    }
  }
  
  public void playerEnds(Card c, boolean end, Pile pile) /*throws CheaterException*/{
    // a player ends their turn by calling this method
    // and telling the judge what to do
    //
    // there are three possibilities
    // c == null
    //   player has no more cards left and wants to end the game
    // c != null, end == true
    //   player gives their last card and wants to end the game
    // c != null, end == false
    //   players gives a card and the next player takes their turn
    //
    // pile is the current pile in the game
    // 
    // judge should determine if the game has ended or not
    
    
    //can throw an exception!
    
    if (c == null){
      keepPlaying = false;
    }
    else if (c != null && end == true){
      pile.add(c);
      keepPlaying = false;
      insertedCards.add(c);
    }
    else{
      pile.add(c);
      keepPlaying = true;
      
      if (position == players.size()-1){
        position = 0;
        currentPlayer = players.get(position);
      }
      else{
        position = position + 1;
        currentPlayer = players.get(position);
      }
    insertedCards.add(c);
    }
    turns = turns + 1;
  }
  
  public void displayResults(){
    //prints the results of the game such as the scores of each player, the number of turns 
    //it took to finish the game and the pairs submitted by each player and at the 
    //end what each player has in their hand.
    
    for (int i = 0; i<players.size(); i=i+1){
      System.out.println("Player: " + players.get(i).getName());
      System.out.println("Score: " + getScore(players.get(i)));
      System.out.println("The pairs this player submitted during the game are:");
      System.out.println(playerpairs.get(players.get(i)));
      System.out.println("The cards in his hands are: " + players.get(i).showHand());
      System.out.println("\n");
     
    }
    System.out.println("The person who ended the game was " + currentPlayer.getName());
    System.out.println("The total number of turns it took to end the game: " + turns);
    
    System.out.println("Summary of game: ");
    for (int i=0; i<turns; i=i+1){
      System.out.println("For turn " + (i+1) + " the card put on the pile was " + insertedCards.get(i));
    }
      
  }
  
  public Player[] declareWinner(){
    // returns the player (or players) that win the game
    // all players with the highest score are returned
    
    java.util.ArrayList<Player> winners = new java.util.ArrayList<Player>();
    Player winner = players.get(0);
    for (int i=0; i<players.size(); i=i+1){
      if (getScore(players.get(i))>getScore(winner)){
        winner = players.get(i);
        winners.clear();
        winners.add(winner);
      }
      else if (getScore(players.get(i)) == getScore(winner)){
        winners.add(players.get(i));
      }
      else{
      }
    }
    Player [] arrayWinners = new Player [winners.size()];
    for (int i=0; i<arrayWinners.length; i=i+1){
      arrayWinners[i] = winners.get(i);
    }
    return arrayWinners;
  }
  
  public Player currentPlayer(){
    // returns a player in the game
    // returns the player whose turn it currently is
    //
    // Order of play is determined by the order of the players in the 
    // arraylist originally passed into the startGame method.  player
    // at position 0 has the first turn, player at positoin 1 has second
    // turn, etc.
    //
    return currentPlayer;
  }
  
  
  //a method that computes the score of a given inputed player
  public int getScore(Player p){
    
    int posScore = 0;
    int negScore = 0;
    int count = 1;

    java.util.ArrayList<Card> pairs = playerpairs.get(p);
    java.util.ArrayList<Card> hand = p.showHand();
    
    if (pairs != null){
      Collections.sort(pairs);
      for (int i=1; i<pairs.size(); i=i+1){
        if (pairs.get(i-1).getRank() == pairs.get(i).getRank()){
          count = count + 1;
          if (count%2 == 0){
            if (pairs.get(i).getRank() == 1){
              posScore = posScore + 30;
            }
            else if (pairs.get(i).getRank()>1 && pairs.get(i).getRank()<11){
              posScore = posScore + 10;
            }
            else{
              posScore = posScore + 20;
            }
          }
          if (count == 4){
            if (pairs.get(i).getRank() == 1){
              posScore = posScore + 30;
            }
            else if (pairs.get(i).getRank()>1 && pairs.get(i).getRank()<11){
              posScore = posScore + 10;
            }
            else{
              posScore = posScore + 20;
            }
          }
        }
        else{
          count = 1;
        }
      }
    }
    
    if (hand.size() != 0){
      Collections.sort(hand);
      for (int i=0; i<hand.size(); i=i+1){
        if (hand.get(i).getRank() == 1){
          negScore = negScore + 20;
        }
        else if (hand.get(i).getRank()>1 && hand.get(i).getRank()<11){
          negScore = negScore + hand.get(i).getRank();
        }
        else{
          negScore = negScore + 15;
        }
      }
    }
    int score = posScore - negScore;
    return score;
  }
}