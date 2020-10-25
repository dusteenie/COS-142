package dusty_BlueJ;

import java.util.Random;

public class Deck 
{

    private Card [] deck;
    private int top;
    private static Random rdm = new Random();

   //set up all member variables
   //initialize or instantiate your instance variables
    public Deck() 
    {
        //instantiate Card array
        deck = new Card[52];

        //fill array with actual cards
        int i=0;
        for(int r = 2; r <= 14; r++) {
            for(int s = 1; s<=4 ; s++) {
                deck[i++] = new Card(r,s);
            }
        }

        //set "top" card index to 0
        top = 0;
    }



    public Card dealCard() {
        //take next card off of "top" of deck
        //  if there are no more cards left, shuffle
      Card temp = deck[top];
      //adjust "top" index
      top++;
      
      if(top >= deck.length) {
         shuffle();
      }   
      return temp;
    }

    public void shuffle() {
        //lots of ways to do this
        //what would you do?
      
      for(int i=0;i<deck.length*4;i++) {

         int x = rdm.nextInt(deck.length);
         int y = rdm.nextInt(deck.length);
         Card temp = deck[x];
         deck[x] = deck[y];
         deck[y] = temp;
      }
      top = 0;

    }

    public String toString() {
        String str = "";
      for(int i=0;i<deck.length;i++) {
         str += deck[i].toString() + " ";
      }
        return str;
    }

}
