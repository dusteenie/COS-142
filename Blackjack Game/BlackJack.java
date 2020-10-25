/*
 * Name: Dusty Stepak
 * Date: 03.25.2019
 */
package dusty_BlueJ;
import java.util.Scanner;
public class BlackJack
{
    // initalizes number products 
    protected static int player1HandVal = 0, player2HandVal = 0, dealerHandVal = 0;
    protected static int player1Money = (int)(Math.random()*100+1);
    protected static int player2Money = (int)(Math.random()*100+1);
    protected static int tableMoney = (int)(Math.random()*100+1);;
    
    // initalizes field objects
    protected static Scanner input = new Scanner(System.in);    
    protected static Hand hand1 = new Hand(); // val = 0
    protected static Hand dealerHand = new Hand(); // val = 1  
    protected static Hand hand2 = new Hand(); // val = 2;
    protected static Deck deck = new Deck();
    protected static Card p1Card, p2Card, dealerCard; 
    
    // initalizes booleans and strings
    protected static String playerCards = "";
    protected static boolean gameOver = false;
    protected static boolean bust = false;
    protected static boolean isStanding = false;
    protected static boolean isHard = false;
    
    public static void main(String[] args)
    {      
        // Begins the program
        int bet = 0;
        String userInput = ""; 
        System.out.println("\n\n");
        printGreeting();
        System.out.println("Let's begin!\n\n");
        System.out.println("----    ----    ----    ----    ----    ----    ----    ----    ----");
        
        //shuffles the deck
        deck.shuffle();
        deck.shuffle();
        // Deals the starting cards
        hand1.addCard(deck.dealCard());
        dealerHand.addCard(deck.dealCard());
        hand2.addCard(deck.dealCard());
        hand1.addCard(deck.dealCard());
        dealerHand.addCard(deck.dealCard());
        hand2.addCard(deck.dealCard());
        
        // Initial betting
        System.out.print("\n\nThere is $"+tableMoney+" on the table.\nYou don't have any change, so you can only bet dollar values.\nYou have $" + player1Money + "; How much do you want to bet? ");
        // Player 1 bets
        try { 
            bet = input.nextInt();
            if(bet > player1Money || bet < 0)
                System.out.println("You tried to bet an invalid amount.\nThe dealer is merciful this time.");
            else {
             player1Money = player1Money - bet;
             tableMoney += bet; 
             System.out.println("You bet " + bet + " dollars!"); }}
        
        catch(Exception e){System.out.print("You decide not to bet.");}
            
        // Player 2 bets 
        int temp1 = (int)(Math.random()*player2Money+1);
        System.out.println("Player 2 bets " + temp1 + " dollars!");
        tableMoney+=temp1;
        player2Money-=temp1;
            
        // Initial delt cards 
        deal(2);        
            
        int temp =0;    
        // Prints avalible moves and allows the user to make a move.
        printPlayerMoves();
        // Checks to see if there's a bust
        bust();
        // Makes the NPC's move
        NPCMoves();              
                                  
        System.out.println("\n\n----    ----    ----    ----    ----    ----    ----    ----    ----\n");
            
        //play game
        while(gameOver == false) {
            if(gameOver == true){break;}
            deal(1);  
            // Prints avalible moves and allows the user to make a move.
            printPlayerMoves();       
            if(gameOver == true){break;}
            NPCMoves();
            if(gameOver == true){break;}
            System.out.println("\n\n----    ----    ----    ----    ----    ----    ----    ----    ----\n"); }
        printWin();
    }
    
    /**
     * Deals cards to all players. Adds their value and updates the  point system. 
     * Also updates the players card string if the player is on easy.
     */
    public static void deal(int xTimes)
    {
        int temp = 0;
        if(gameOver == false) {
            for(int i = 0; i<xTimes; i++) {
                //get next cards from the users hand and adds to Player 1's points
                p1Card = hand1.getNextCard();
                temp = 0;
                if(p1Card.toString().indexOf("A") != 0)
                    if(p1Card.getRank() >= 10)
                        temp = 10;
                    else
                        temp = p1Card.getRank();
                else
                    temp = aceVal(0);
                playerCards = p1Card.toString() + " " + playerCards;            
                player1HandVal = player1HandVal + temp;  }}
            
        for(int i = 0; i<xTimes; i++) {
            //get next cards from the users hand and adds to Player 2's points
            p2Card = hand2.getNextCard();
            temp = 0;
            if(player2HandVal < 22)
                try{
                    if(p2Card.toString().indexOf("A") != 0)
                        if(p2Card.getRank() >= 10)
                            temp = 10;
                        else
                            temp = p2Card.getRank();
                    else
                        temp = aceVal(2);
                    player2HandVal = player2HandVal + temp; }
                catch(Exception e){gameOver = true;}
            else {bust();} }
             
            
        for(int i = 0; i<xTimes; i++) {
            //get next cards from the Dealers hand and adds to the Dealers points
            dealerCard = dealerHand.getNextCard();
            temp = 0;
            if(dealerHandVal < 22)
            try{if(dealerCard.toString().indexOf("A") != 0)
                if(dealerCard.getRank() >= 10)
                    temp = 10;
                else
                    temp = dealerCard.getRank();
                dealerHandVal = dealerHandVal + temp; }
            catch (Exception e) {gameOver = true;}
            else {bust();} }

    }

    /**
     * Checks to see what value the players (val) ace should be.
     * In blackjack, if you have more than 12 points, it's worth 1.
     * Otherwise it's worth 11.
     */
    public static int aceVal( int val )
    {
        if (val == 0)
            if(player1HandVal >= 12)
                return 1;
        else if (val == 1)
            if(dealerHandVal >= 12)
                return 1;
        else if(val == 2)
            if(player2HandVal >= 12)
                return 1;
        
        return 11;
    }   
    
    /**
     * Paramaters: Bets and val. Makes bet transactions on the table
     * And does NOT allow for any invalid amounts.
     */
    public static void bet(int bets, int val)
    {
        try{
            if(bets > player1Money || bets < 0 && val == 0)
                System.out.print("You tried to bet an invalid amount./nThe dealer is merciful this time.");
            else
            if(val == 0){
                player1Money = player1Money - bets;
                tableMoney += bets;
                System.out.println("You betted " + bets + " dollars!");
                printPlayerMoves();}}
        catch(Exception e){
            System.out.println("The dealer shakes their head at the amount you tried to bet.");}
    }
    
    /**
     * Checks to see if this is a bust. 
     * A bust occurs if a player/dealer exceeds 21.
     */
    public static void bust()
    {         
        if(player1HandVal > 21){bust =  true;}
        if(player2HandVal > 21) {bust =  true;}
        if( dealerHandVal > 21) {bust =  true;}
        
        if(bust == true){
            gameOver = true; }
    }
    
    /**
     * Prints which player (val) decided to stand. 
     * Proceeds to break the game loop.
     */
    public static void stand( int val )
    {
        if( val == 0) {System.out.println("\nPlayer 1 choses to stand!");}
        else if(val == 1) {System.out.println("\nThe Dealer choses to stand!");} 
        else if(val == 2) {System.out.println("\nPlayer 2 choses to stand!");}  
        isStanding = true;
        gameOver = true;
    } 
    
    /**
     * Gives the corresponding player (val) another card.
     */
    public static void hit(int val)
    {
        if(val==0) {
            System.out.println("You hit!");
            hand1.addCard(deck.dealCard()); }
        else if (val == 1) {
            System.out.println("The Dealer decides to hit!");
            dealerHand.addCard(deck.dealCard());}
        else {
            System.out.println("Player 2 decides to hit!");
            hand2.addCard(deck.dealCard());}
    }
    
     /**
     * Prints the greeting shown at the beggining of the game
     */
    public static void printGreeting()
    {
        System.out.println("\n    ____   _       ____    __  __  _   ____   ____    __  __  _");
        System.out.println("   |    \\ | |     /    |  /  ]|  |/ ] |    | /    |  /  ]|  |/ ]");
        System.out.println("   |  o  )| |    |  o  | /  / |  ' /  |__  ||  o  | /  / |  ' /");
        System.out.println("   |     || |___ |     |/  /  |    \\  __|  ||     |/  /  |    \\");
        System.out.println("   |  O  ||     ||  _  //   \\_ |    \\/  |  ||  _  /   \\_ |     \\");
        System.out.println("   |     ||     ||  |  \\     ||  .  |\\  `  ||  |  \\     ||  .  |");
        System.out.println("   |_____||_____||__|__|\\____||__|\\_| \\____||__|__|\\____||__|\\_|\n");
        System.out.println("----    ----    ----    ----    ----    ----    ----    ----    ----");
        
        // Prints the rules
        printRules();
    }
    
     /**
     * Prints the rules
     */
    public static void printRules()
    {
        System.out.println("Welcome to BLACKJACK!\nThe objective of blackjack is:\n" +
            "to beat the dealers hand by either\n\thaving a total over 16 that exceeds the dealers total,\n\t" + 
            "by not going over 21 when the dealer does.\n\nYou can either hit or stand.\n" + 
            "\tHitting gives you another card.\n\tStanding means you're ready to play your hand.\n" +
            "Cards are worth their face values.\nKings, Queens, and Jacks are worth 10 points\n" +
            "Aces are worth 11 points when the players hand\nis worth less than 12 points. 1 otherwise.\n" +
            "\nWatch out though!\nThe Dealer is impatient and will assume\nyou want to hit if you give an invalid input!" + 
            "\nNow that you know the rules,\nPlease press  [ 1 ]  for Hard Mode, and  [ 2 ] for Easy Mode.");
            
        int mode = 0;
        try {
            mode = input.nextInt(); 
            if(mode == 2){
                isHard = true; 
                System.out.println("You chose Easy Mode!"); } 
            else if(mode == 1){
                System.out.println("You chose Hard Mode!");}}
        catch(Exception e){System.out.println("You chose Hard Mode!");}
    }
    
     /**
     * Gives the player the chance to make a decision and play.
     */
    public static void printPlayerMoves()
    {
        try {
            if(isHard == true) {   
                // Prints point systems // CHANGE INTO ONLY APPEARS ON EASY
                System.out.println("\nPlayer 1's points: " + player1HandVal);
                System.out.println("Player 2's points: " + player2HandVal);
                System.out.println("The Dealer's points: " + dealerHandVal + "\n"); }
            System.out.println("Your Cards:\n"+playerCards);
            if(isHard == true) {System.out.println("\nThey total out to be worth " + player1HandVal +" points.");}
            
            int printNext;
            System.out.println("There is " + tableMoney + " dollars on the table.");
            System.out.println("You have " + player1Money + " dollars left to bet.");
            System.out.println("What would you like to do?");
            System.out.println("[1] hit   [2] stand   [3] bet");
            printNext = input.nextInt();
            
            if(printNext == 1)
                hit(0);
            if(printNext == 2)
                stand(0);
            if(printNext == 3){
                int betVal;
                try{
                System.out.print("How much do you want to bet?");
                betVal = input.nextInt();
                bet(betVal,0); }
                catch(Exception e)
                {System.out.println("You decide not to bet."); hit(0);}}  }
        catch(Exception e) {
            System.out.println("Next time enter a valid input. The dealer is impatient.");
            hit(0); }
    }
    
    /**
     * NPC AI
     */
    public static void NPCMoves()
    {
        // Player 2 Moves
        if(!isStanding && gameOver == false){
            if(player2HandVal >= 17 && player2HandVal < 21) {
                stand(2); }
            else if(player2HandVal == 21) {stand(2);}
            else if(player2HandVal > 21) {bust();}
            else {hit(2);}}
            
        // Dealer Moves
        if(!isStanding && gameOver == false){
            if(dealerHandVal >= 17 && dealerHandVal < 21) {
                stand(1); }
            else if(dealerHandVal == 21) {stand(1);}
            else if(dealerHandVal > 21) {bust();}
            else {hit(1);}}  
    }
    
     /**
     * Prints the corresponding win conditional.
     */
    public static void printWin()
    {
        int terminationCondition = 0;
        // 3 player wins
            // All Players Win (21-Points)
        if(terminationCondition == 0 && player1HandVal == 21 && player2HandVal == 21 && dealerHandVal == 21) {
            terminationCondition = 1;  
            System.out.print("Your game was legendary. Everybody wins!\nYou all split the money, you earn $" + tableMoney + "!"); }            
            // All Players win (< 21-Points)
        if (terminationCondition == 0 && player1HandVal > 16 && player1HandVal == player2HandVal && player2HandVal > 16 && 
            player2HandVal == dealerHandVal && dealerHandVal > 16 && terminationCondition == 0) {
                terminationCondition = 1;  
                System.out.print("The game was legendary! Everybody wins!\nYou all split the money, you earn $" + tableMoney + "!");}
            
        // Since not everybody won, there is a chance a player busted. The following prints that.
        if(player1HandVal > 21)
            System.out.println("Player 1 busts!");
        if(player2HandVal > 21)
            System.out.println("Player 2 busts!");
        if(dealerHandVal > 21)
            System.out.println("The Dealer busts!");
            
        // The following checks to see if everybody busted, and determines who won based off of that information.
        if(terminationCondition == 0 && player1HandVal > 21 && player2HandVal > 21 && dealerHandVal > 21 )
        {
            player1HandVal = player1HandVal - 21;
            player2HandVal = player2HandVal - 21;
            dealerHandVal = dealerHandVal - 21;
            
            if(player1HandVal < player2HandVal){
                if(player1HandVal < dealerHandVal){                    
                    terminationCondition = 1;   System.out.print("Player 1 wins!\nYou earn $" + tableMoney + "!"); }
                else{
                    terminationCondition = 1;  System.out.print("The Dealer wins!\nThey earn $" + tableMoney + "!"); } }
            else {
                if(player2HandVal < dealerHandVal){
                    terminationCondition = 1;  System.out.print("Player 2 wins!\nThey earn $" + tableMoney + "!");}
                else{
                    terminationCondition = 1;  System.out.print("The Dealer wins!\nThey earn $" + tableMoney + "!"); }}}
                    
        // 2 player wins
            // Two Players Win (21-Points)
        if(terminationCondition == 0 && player1HandVal == 21 && player2HandVal == 21 && terminationCondition == 0) {
            terminationCondition = 1;  
            System.out.print("Both Players 1 & 2 win!\nYou split the money. You earn $" + (tableMoney / 2) + " dollars!");
            player1Money += (tableMoney / 2);
            player2Money += (tableMoney / 2);}
        if(terminationCondition == 0 && player1HandVal == 21 && dealerHandVal == 21 && terminationCondition == 0) {
            terminationCondition = 1;  
            System.out.print("Both Player 1 & the Dealer win!\nYou split the money. You earn $" + (tableMoney) + " dollars!");
            player1Money += (tableMoney / 2);} 
        if(terminationCondition == 0 && player2HandVal == 21 && dealerHandVal == 21 && terminationCondition == 0) {
            terminationCondition = 1;  
            System.out.print("Both Player 2 & the Dealer win!\nYou lose. They earn $"+ (tableMoney) + " dollars!"); 
            player2Money += (tableMoney / 2);} 
            // Two Player Wins (< 21-Points)
        if(terminationCondition == 0 && player1HandVal > 16 && (player1HandVal < player2HandVal) 
            && player2HandVal > 16 && (player2HandVal > dealerHandVal)) {
            terminationCondition = 1;  
            System.out.print("Player 2 wins the game!\nYou lose. They earn $" + tableMoney +" dollars!");
            player2Money += (tableMoney / 2);}
        if(player1HandVal > 16 && player2HandVal > 16 && player1HandVal == player2HandVal && terminationCondition == 0) {
            terminationCondition = 1;  
            System.out.print("Both Players 1 & 2 win!\nYou split the money. You earn $" + (tableMoney / 2) + " dollars!");
            player1Money += (tableMoney / 2);
            player2Money += (tableMoney / 2); }
        
        // 1 player win
            // One Player Win (21-Points)   
        if(terminationCondition == 0 && player1HandVal == 21) {
            terminationCondition = 1;  
            System.out.print("Player 1 wins the game!\nYou earn $" + tableMoney + "!");
            player1Money += tableMoney;}
        if(terminationCondition == 0 && player2HandVal == 21) {
            terminationCondition = 1;  
            System.out.print("Player 2 wins the game!\nYou lose. They earn $" + tableMoney + "!");
            player2Money += tableMoney;}
        if(terminationCondition == 0 && dealerHandVal == 21) {
            terminationCondition = 1;  
            System.out.println("The Dealer wins the game!\nYou lose. They earn $" + tableMoney + "!"); }
            // One Player Wins (< 21-Points)
        if(terminationCondition == 0 && player1HandVal > 16 && player1HandVal < 22) {
            if (player1HandVal > player2HandVal && player1HandVal > dealerHandVal) {
                terminationCondition = 1;  
                System.out.print("Player 1 wins the game!\nYou earn $" + tableMoney + "!");
                player1Money += tableMoney;} }
        if((terminationCondition == 0 && player2HandVal > 16 && player2HandVal < 22)) {
            if(player2HandVal > player1HandVal && player2HandVal > dealerHandVal) {
                terminationCondition = 1;  
                System.out.print("Player 2 wins the game!\nYou lose. They earn $" + tableMoney + "!"); } 
                player2Money += tableMoney;}
        if(terminationCondition == 0 && dealerHandVal > 16 && dealerHandVal < 22) {
            if(dealerHandVal > player1HandVal && dealerHandVal > player2HandVal) {
                terminationCondition = 1;  
                System.out.println("The Dealer wins the game!\nYou lose.They earn $" + tableMoney + "!"); } }
                
        // Bad Stand resulting in a loss   
        if (terminationCondition == 0 && player1HandVal < 17 || player2HandVal < 17 && terminationCondition == 0) {
            terminationCondition = 1;  
            System.out.println("The Dealer wins the game!\nYou lose. They earn $" + tableMoney + "!"); }
            
        // Someone busted and now we godda see who's the closest to 21- whoever is under 21.
        if (terminationCondition == 0 && (player1HandVal > 21 || player2HandVal > 21 || dealerHandVal > 21)){
            // One player busted
            if(player1HandVal > 21 && player2HandVal < 22 && dealerHandVal < 22){
                player2HandVal = player2HandVal - 21;
                dealerHandVal = dealerHandVal - 21;
                if(player2HandVal < dealerHandVal){
                    System.out.print("Player 2 wins the game!\nYou lose. They earn $" + tableMoney + "!"); 
                    player2Money += tableMoney;}
                else{
                    System.out.println("The Dealer wins the game!\nYou lose.They earn $" + tableMoney + "!"); }}
            if(player2HandVal > 21 && player1HandVal < 22 && dealerHandVal < 22){
                player1HandVal = player1HandVal - 21;
                dealerHandVal = dealerHandVal - 21;
                if(player1HandVal < dealerHandVal){
                    System.out.print("Player 1 wins the game!\nYou earn $" + tableMoney + "!"); 
                    player1Money += tableMoney;}
                else{
                    System.out.println("The Dealer wins the game!\nYou lose.They earn $" + tableMoney + "!"); }}
            if(dealerHandVal > 21 && player2HandVal < 22 && player1HandVal < 22){
                player2HandVal = player2HandVal - 21;
                player1HandVal = player1HandVal - 21;
                if(player2HandVal < player1HandVal){
                    System.out.print("Player 2 wins the game!\nYou lose. They earn $" + tableMoney + "!"); 
                    player2Money += tableMoney;}
                else{
                    System.out.print("Player 1 wins the game!\nYou earn $" + tableMoney + "!"); 
                    player1Money += tableMoney;}}
            // Two players busted
            if(player1HandVal > 21 && player2HandVal > 21 && dealerHandVal < 22){
                System.out.println("The Dealer wins the game!\nYou lose.They earn $" + tableMoney + "!");}
            if(player1HandVal > 21 && player2HandVal < 22 && dealerHandVal > 21){
                System.out.print("Player 2 wins the game!\nYou lose. They earn $" + tableMoney + "!"); 
                player2Money += tableMoney;}
            if(player1HandVal < 22 && player2HandVal > 21 && dealerHandVal > 21){
                System.out.print("Player 1 wins the game!\nYou earn $" + tableMoney + "!"); 
                player1Money += tableMoney;} }
    
      }
}
