/** 
 Judge Class
 COMP1406/1006 - Fall 2013
 Assignment 10 - Bonus 1


 A test class that opens a test file called players.data.txt, intakes the data from the text file 
 and plays a number of games with the players inside the text files to keep track of the stats of 
 the players such as how many games they have won and the total number of games played.
 
*/

import java.io.*;
import java.util.ArrayList;

public class PlayPairsiWithFiles{
  
  public static void main (String [] args){
    int playGames = Integer.parseInt(args[0]);
    String fileName = "players.data.txt";
    String [] names = new String [3];
    String [] type = new String [3];
    int [] gamesWon = new int [3];
    int totalGames = 0;
      
    
    try{
      
      // FileReader reads text files in the default encoding.
      FileReader fileReader = new FileReader(fileName);

      // wrap FileReader in BufferedReader.
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      
      int count = 0;
      int nameCount = 0;
      int gameNum = 0;
      int typenum = 0;
      String line;
      
      
      //saves the information in the text file
      while((line = bufferedReader.readLine())!= null) {
        if (count == 0 || count == 3 || count == 6){
          names[nameCount] = line;
          count = count + 1;
          nameCount = nameCount + 1;
        }
        else if (count == 1 || count == 4 || count == 7){
          type[typenum] = line;
          count = count + 1;
          typenum = typenum + 1;
        }
        else if (count == 2 || count == 5 || count == 8){
          gamesWon[gameNum] = (Integer.parseInt(line));
          count = count + 1;
          gameNum = gameNum + 1;
        }
        else{
          totalGames = Integer.parseInt(line);
        }
      }
    bufferedReader.close();
    }
    
    catch(Exception ex) {
      ex.printStackTrace();
    }

     //creates the players for the game
    java.util.ArrayList<Player> players = new java.util.ArrayList<Player>();
  
    for (int i = 0; i<type.length; i=i+1){
      if (type[i].equals("fast")){
        players.add(new FastPlayer(names[i]));
      }
      else if (type[i].equals("onecard")){
        players.add(new OnecardPlayer(names[i]));
      }
      else if (type[i].equals("pairs")){
        players.add(new PairsPlayer(names[i]));
      }
      else{
        players.add(new SmartPlayer(names[i]));
      } 
    }
    
    
    //keeps tracks of the number of wins for eahc game
    int fastwins = 0;
    int onecardwins = 0;
    int pairswins = 0;
    int smartwins = 0;
  
    for(int i=0; i<playGames; i=i+1){
      // deck of cards to play the game with
      Deck deck = new Deck();
      deck.shuffle();
      
      // judge to run the game 
      Judge judy = new Judge();
      
      // the pile in the game
      Pile pile = new Pile();
      
      judy.startGame(deck, players, pile);
      
      while( judy.keepPlaying() == true){
        Player currentPlayer = judy.currentPlayer();
        currentPlayer.play( pile, judy );
      }
      
      Player[] winners = judy.declareWinner();
    
    for (i=0; i<winners.length; i=i+1){
      if (winners[i] instanceof FastPlayer){
        fastwins = fastwins + 1;
      }
      else if (winners[i] instanceof OnecardPlayer){
        onecardwins = onecardwins + 1;
      }
      else if (winners[i] instanceof PairsPlayer){
        pairswins = pairswins + 1;
      }
      else{
        smartwins = smartwins + 1;
      }
    }
  }
    
  int totalfastwins = fastwins;
  int totalonecardwins = gamesWon[1] + onecardwins;
  int totalpairswins = gamesWon[2] + pairswins;
  int totalsmartwins = gamesWon[0] + smartwins;
    
  
  //write the results of the games to a file
  try{
    FileWriter output = new FileWriter("players.data.txt");
    PrintWriter out = new PrintWriter(output);
    
    for(int i = 0; i<3; i=i+1){
      out.println(players.get(i).getName());
      out.println(players.get(i).getType());
      if (players.get(i).getType() == "smart"){
        out.println(totalsmartwins);
      }
      else if (players.get(i).getType() == "onecard"){
        out.println(totalonecardwins);
      }
      else if (players.get(i).getType() == "pairs"){
        out.println(totalpairswins);
      }
    }
    out.println(totalGames + playGames);
    
   out.close();
  }
  catch (Exception e){
    e.printStackTrace();
  }
}
}
