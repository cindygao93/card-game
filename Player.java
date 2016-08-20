/** 
 Player Class
 COMP1406/1006 - Fall 2013
 Assignment 10 - Problem 1

Name: Cindy Gao
Student Number: 100861300
Date: December 9, 2013

 A class that creates a card game player and contains the associated methods of that player.
 
*/

import java.util.Collections;
import java.util.ArrayList;

public class Player{
  
  //attributes
  protected String name;
  protected java.util.ArrayList<Card> hand = new ArrayList<Card>();
  protected String type;
  
  //gets the type of the player
  public String getType(){
    return type;
  }
  
  //gets the name of the player
  public String getName(){ return name; }
  
  
  public void addCard(Card c){
    // adds the card to the players hand
    hand.add(c);
  }
  
  public java.util.ArrayList<Card> showHand(){
    // display the player's hand to standard output
    return hand;
  }
  
  public void play(Pile pile, Judge judge){
    // play a turn
    //
    // take a card from the pile
    //    - take more cards from the pile if needed
    //    - submit pairs to judge if applicable
    // give zero or more cards to the judge
  }
  
  //sorts the cards in a players hand from lowest ranked to highest ranked
  public void sortHand(){
    Collections.sort(hand);
  }
  
  //constructor for the player class
  public Player(String name){
    this.name = name;
  }
}
