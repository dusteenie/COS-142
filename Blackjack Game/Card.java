package dusty_BlueJ;

public class Card {

    private int rank;
    private int suit;
    public static final int DIAMOND=1;
    public static final int CLUB=2;
    public static final int HEART=3;
    public static final int SPADE=4;


    //default constructor
    public Card() 
    {
        //set rank to default value
        rank = 2;      

        //set suit to default value
        suit = CLUB;
    }

    //constructor where rank and suit are passed in
    public Card(int r, int s) {
        //check to see if r and s are valid
        //  if they are, set rank/suit
        //  else set them to default values
        if (r >= 2 && r <=14 && s >= 1 && s <= 4)
        { 
            rank = r;
            suit = s;
        }
    }


    public int getSuit() 
    {
      return suit;
    }

    public int getRank() 
    {
      return rank;
    }

    
    public void setRank(int r) 
    {
      rank = r;
    }

    public void setSuit(int s) 
    {
      suit = s;
    }

    public String toString() 
    {
        String s="";

        if (rank == 14) 
        {
            s = s + "A";
        }
        else if(rank == 13) 
        {
            s += "K";
        }
        else if(rank == 12) 
        {
            s += "Q";
        }
        else if(rank == 11) 
        {
            s += "J";
        }
        else 
        {
            s += rank;
        }
        
        if(suit == DIAMOND) 
        {
            s += "D";
        }
        else if(suit == CLUB) 
        {
            s += "C";
        }
        else if(suit == SPADE) 
        {
            s += "S";
        }
        else if(suit == HEART) 
        {
            s += "H";
        }

        return s;
    }
    
    public int compareTo(Card c) 
    {

      return this.rank - c.getRank(); 
    }

}
