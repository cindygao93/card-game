/** 
 Play7 Class
 COMP1406/1006 - Fall 2013
 Assignment 10 - Problem 1


 A testing class in which a fast player, an onecard player play, a pairs player, and a smart player play the card game.
 At the end of the game, a summary of the results is results of the game is printed.
 
*/



//A four player game involving one of each type of player (fast, onecard, pairs and smart).

public class Play7{
  
    public static void main(String[] args){
   
    // deck of cards to play the game with
    Deck deck = new Deck();
    deck.shuffle();    //deck.printDeck();
    
    // judge to run the game 
    Judge judy = new Judge();
    
    // some players to play the game
    Player p1=null, p2=null, p3=null, p4=null;
    
    
    // create the players...
    // ...
    p1 = new FastPlayer("Samuel the speedy");
    p2 = new OnecardPlayer("One Trick Unicorn");
    p3 = new PairsPlayer("Double the trouble");
    p4 = new SmartPlayer("Smartie McSmartPants");
    
    
    java.util.ArrayList<Player> players = new java.util.ArrayList<Player>();
    players.add(p1);
    players.add(p2);
    players.add(p3);
    players.add(p4);
    
    // the pile in the game
    Pile pile = new Pile();
    
    
    // judge deals 8 cards to each layer and places remaining cards on the pile
    judy.startGame(deck, players, pile);
    
    while( judy.keepPlaying() == true){
      Player currentPlayer = judy.currentPlayer();
      //System.out.println(currentPlayer.showHand());
      currentPlayer.play( pile, judy );
    }
      
    judy.displayResults();
    
    Player[] winners = judy.declareWinner();
    for(Player p : winners){
      System.out.println(p.getName() + " is a winner!");
    }
    
  }
}
