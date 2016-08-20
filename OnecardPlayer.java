/** 
 OneCard Player Class
 COMP1406/1006 - Fall 2013
 Assignment 10 - Problem 1


 A subclass of player that creates a onecard player. A onecardplayer starts their turn by taking a card from 
 the pile, submits a pair(s) if they have it, then immediately places a card on the pile by giving it
 to the judge. The card they put on the pile is the one that minimizes their score.
 
*/


public class OnecardPlayer extends Player{
  
  protected String type = "onecard";
  
  public OnecardPlayer(String name){
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
   
   Card b =  pile.remove();
   hand.add(b);
   sortHand();
   
   for (int i=1; i<hand.size(); i=i+1){
     if(hand.get(i-1).getRank() == hand.get(i).getRank()){
       judge.takePair(hand.get(i-1), hand.get(i), this);
       hand.remove(hand.get(i));
       hand.remove(hand.get(i-1));
     }
   }
   
   boolean end;
   Card a;
   
   if (hand.size() == 0){
     a = null;
     end = true;
   }
   else{
     a = hand.get(hand.size()-1);
     hand.remove(a);
     
     if(hand.size() == 0){
       end = true;
     }
     else{
       end = false;
     }
   }
   judge.playerEnds(a, end, pile);  
  }
}
