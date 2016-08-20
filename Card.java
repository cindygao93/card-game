/** 
 Card Class
 COMP1406/1006 - Fall 2013
 Assignment 10 - Problem 1

Name: Cindy Gao
Student Number: 100861300
Date: December 9, 2013

 A class that creates a card object and its asociated methods.
 
*/

public class Card implements Comparable<Card>{
  /* these are the valid ranks and suits (both represented as Strings) */
  protected final static String[] RANKS = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
  protected final static String[] SUITS = {"Diamond", "Club", "Heart", "Spade" };
  
  protected int rank;
  protected String suit;
  
  /* required methods for all cards to have */
  public int getRank(){
    return rank;
  }
  
  public String getSuit(){
    return suit;
  }

  /* override Object's toString() */
  public final String toString(){
    return String.valueOf(this.getRank()) + String.valueOf(this.getSuit().charAt(0)); 
  }
  
  public Card(int rank, String suit){
    this.rank = rank;
    this.suit = suit;
  }
  
  public Card(String rank, String suit){
    for(int r = 0; r < RANKS.length; r+=1){
      if( rank.equals(RANKS[r]) ){
        this.rank = r+1;
      }
    }
    this.suit = suit;
  }
  
  public int compareTo(Card other){
    if (this.rank>other.rank){
      return 1;
    }
    else if (this.rank<other.rank){
      return -1;
    }
    else{
      return 0;
    }
  }
}