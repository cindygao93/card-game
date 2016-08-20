/** 
 Pile Class
 COMP1406/1006 - Fall 2013
 Assignment 10 - Problem 1


 A class that creates a pile of cards and its associated methods.
 
*/


public class Pile{
  
  //try to use a stack for this
  
  //attribute
  private int size;
  private Card [] cardPile = new Card[36];
  
  public int getSize(){
    return size;
  }

  public void add(Card c){
    // add a card to the top of the pile
      cardPile[size] = c;
      size = size + 1;
  }
  
  public Card remove(){
    // removes the card on the top of the pile
    Card removed = cardPile[size-1];
    cardPile[size-1] = null;
    size = size-1;
    
    return removed;
  }
  
  public int size(){
    // returns the size of the pile
    // (how many Cards are in the pile)
    return size;
  }
  
  @Override
  public String toString(){
    // return a string representation of the pile
    // this method does not change the current pile
    // (this method does not need to be efficient)
    
    String pileString = "";
    
    for (int i=0; i<this.size(); i=i+1){
      pileString = pileString + cardPile[i].toString() + " ";
    }
    return pileString;
  }
  
  public Pile(){
    size = 0;
  }
}
