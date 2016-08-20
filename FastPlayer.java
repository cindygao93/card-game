/** 
 FastPlayer Class
 COMP1406/1006 - Fall 2013
 Assignment 10 - Problem 1

Name: Cindy Gao
Student Number: 100861300
Date: December 9, 2013

 A subclass of player that creates a fast player. A fastplayer start their turn by taking a card from the pile,then 
 immediately places a card on the pile by giving it to the judge. The card they put on the pile is the one that 
 minimizes their score.
 
*/

public class FastPlayer extends Player{
  
  //attributes
  protected String type = "fast";
  
  //constructor 
  public FastPlayer(String name){
    super(name);
  }

  public String getType(){
    return type;
  }
  
  @Override
  public void play(Pile pile, Judge judge){
    
    // play a turn
    //
    // take a card from the pile
    //    - take more cards from the pile if needed
    //    - submit pairs to judge if applicable
    // give zero or more cards to the judge
    
    Card b = pile.remove();
    hand.add(b);
    sortHand();
    Card max = hand.get(0);
    
    for (int i=0; i<hand.size(); i=i+1){
      if (hand.get(i).getRank()>max.getRank()){
        max = hand.get(i);
      }
    }
    hand.remove(max);
    boolean end = false;
    judge.playerEnds(max, end, pile);
  }
}