 /**
  * BattleshipBoard.java is an application which creates the battleship game board.
  * This class creates and controls the board. Not only that, however it also creates
  * any other screen neccesary to play the game.
  * 
  * @author  Sarah Stepak
  * @version 6.0
  * @since   04.19.2019
  */
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer; 
import java.util.*;

public class BattleshipBoard extends JPanel implements ActionListener, MouseListener
{
    Timer t = new Timer(5, this);
    
    public static final int HIT = 1;
    public static final int MISS = -1;    
    public static final int WATER = 0;
    public static final int END = 3;
    public static final int SHIP = 2;
    
    public static int xSize,ySize,curSize;
    
    int[][]board;
    int[]shipsToBuild = new int[5]; 
    int numHit;   
   
    JButton[][] button;
    JButton newGame = new JButton("New Game");
    JButton quit = new JButton("Show the Solution and End the Game"); 
    
    JFrame screen,pause,end;
    JLabel label;
    
    /**
     * ButtonListener is an embedded class which handles game changing  button events.
     * This class is called on specific buttons, which carry out their intened/noted purpose.
     * 
     * @author  Dusty Stepak
     * @version 2.0
     * @since 04.10.2019
     */
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("New Game")) {
                try{
                    end.dispose();
                }
                catch(Exception except){}
                reset();                 
            }
            if (e.getActionCommand().equals("Show the Solution and End the Game")){                
                quit.setVisible(false);
                pause.remove(quit);
                pause.setLayout(new GridLayout(0,1));
                showSolution();
            }
        }
    }
    
    /**
     * Constructor for objects of class BattleshipBoard.
     * This constructor defaults the game size to be 15 x 15.
     */
    public BattleshipBoard() {
        // initalizes enemy ship sizes
        shipsToBuild[0] = 2; shipsToBuild[1] = 3; 
        shipsToBuild[2] = 3; shipsToBuild[3] = 4; shipsToBuild[4] = 5; 
        // initalizes the current size of the current boat to be built.
        curSize = 1;
        // initalizes end game condition
        numHit = 0;
        // initalizes JFrame screen
        initScreen();        
        screen.setLayout(new GridLayout(15,15)); 
        // initalizes 2D arraylists
        board = new int[15][15];
        button = new JButton[15][15];
        //initalizes the size of the board
        xSize = 15; 
        ySize = 15; 
        // initalizes the game board
        initBoard();
        // shows the screen
        screen.setVisible(true); 
        // starts the timer
        t.start();         
        // sets the focus to JFrame screen.
        screen.setFocusable(true);
        screen.addMouseListener(this);         
    }        
    
    /**
     * Constructor for objects of class BattleshipBoard.
     * This constructor creates the game size to be size x times x.
     */
    public BattleshipBoard(int x){
        // initalizes enemy ship sizes
        shipsToBuild[0] = 2; shipsToBuild[1] = 3; 
        shipsToBuild[2] = 3; shipsToBuild[3] = 4; shipsToBuild[4] = 5; 
        // initalizes the current size of the current boat to be built.
        curSize = 1;
        // initalizes end game condition
        numHit = 0; 
        // initalizes JFrame screen
        initScreen();        
        screen.setLayout(new GridLayout(x,x));  
        // initalizes 2D arraylists
        board = new int[x][x];  
        button = new JButton[x][x];
        //initalizes the size of the board
        xSize = x; 
        ySize = x; 
        // initalizes the game board
        initBoard();
        // shows the screen
        screen.setVisible(true); 
        // starts the timer
        t.start();         
        // sets the focus to JFrame screen.
        screen.setFocusable(true);
        screen.addMouseListener(this);                    
    }
        
    /**
     * Constructor for objects of class BattleshipBoard.
     * This constructor creates the game size to be size x times y.
     */
    public BattleshipBoard(int x, int y){
        // initalizes enemy ship sizes
        shipsToBuild[0] = 2; shipsToBuild[1] = 3; 
        shipsToBuild[2] = 3; shipsToBuild[3] = 4; shipsToBuild[4] = 5; 
        // initalizes the current size of the current boat to be built.
        curSize = 1;
        // initalizes end game condition
        numHit = 0;
        initScreen();        
        screen.setLayout(new GridLayout(x,y));  
        // initalizes 2D arraylists
        board = new int[x][y]; 
        button = new JButton[x][y];
        // initalizes the size of the board
        xSize = x; 
        ySize = y; 
        // initalizes the game board
        initBoard();
        // shows the screen
        screen.setVisible(true); 
        // starts the timer
        t.start();    
        // sets the focus to JFrame screen.
        screen.setFocusable(true);
        screen.addMouseListener(this);         
    }
        
    /** 
     * Creates the playable game screen by creating and initalizing the screen JFrame
     */
    private void initScreen(){
        screen = new JFrame("B A T T L E S H I P");
        screen.setSize(800,450);
        screen.setResizable(false);        
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        initPause();
    }
    
    /** 
     * Creates the options screen by creating and initalizing the pause JFrame
     */
    private void initPause(){
        pause = new JFrame("O P T I O N S");
        pause.setSize(450,450);
        pause.setResizable(false);        
        pause.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        pause.setLayout(new GridLayout(2,1));
        
        newGame.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        quit.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        newGame.addActionListener(new ButtonListener());
        quit.addActionListener(new ButtonListener());
        
        pause.add(newGame);
        pause.add(quit);
        pause.setVisible(true);
    } 
    
    /** 
     * Creates the win end game screen by creating and initalizing the end JFrame
     */    
    private void initWin(){
        end = new JFrame("Y O U      W I N");
        end.setSize(450,250);
        end.setResizable(false);       
        end.setLayout(new GridLayout(1,2));
        label = new JLabel("YOU  WIN!!!!!!");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 28));
        end.add(label);
        end.add(newGame);
        end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        screen.dispose();
        pause.dispose();
        end.setVisible(true);
    }
    
    /**
     * This method initializes the board. This method is responsible for randomly placing five ships. 
     * These ships are placed anywhere on the board, and they have the 
     * equal chance of being oriented horizontally or vertically. 
     * There is one ship of length 2, 2 ships of length 3, one ship of length 4, 
     * and one ship of length 5.
     */
    protected void initBoard(){
        for(int r = 0; r<xSize; r++){
            for(int c = 0; c<ySize; c++){
                button[r][c] = new JButton();
                button[r][c].setFont(new Font("Times New Roman",Font.PLAIN,10));
                button[r][c].setText("~"); 
                button[r][c].setBackground(new Color(114,195,249));
                
                /**
                 * Each of the buttons within the game board have the mouse listener.
                 * This is responsible for handling UI when the button is clicked,
                 * as well as handling game events when a ship or water is hit.
                 * Finally, it also handles the win conditional.
                 */
                button[r][c].addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e)
                    {
                        for (int r = 0; r<xSize; r++){
                            for(int c = 0; c < ySize; c++){
                                if(button[r][c] == e.getSource()){
                                    if(board[r][c] == SHIP){
                                        board[r][c] = HIT;
                                        numHit++;
                                        button[r][c].setText("H");
                                        button[r][c].setBackground(Color.red);
                                    }
                                    
                                    if(board[r][c] == WATER){
                                        board[r][c] = MISS;
                                        button[r][c].setBackground(new Color(45,68,98)); 
                                        button[r][c].setText("M");
                                    }
                                    
                                    if(checkForWin() == true){
                                        initWin();
                                    }
                                }
                            }
                        }
                    }
                });
                
                screen.add(button[r][c]);
                board[r][c] = WATER; 
                }
            }
        newPos(0);newPos(1);newPos(2); newPos(3); newPos(4);
        }
    
    public void buildBoat(int posX, int posY, int i){
        int face = (int)(Math.random()*2);
        int rowAdd, colAdd;
        if(face!=1){rowAdd=0; colAdd=1;} 
        else{rowAdd=1; colAdd=0;}
        int pos = i, r=posX, c=posY;
        
        boolean isBuilt = false;
        while(isBuilt==false){
            try{
                
                if(board[r][c] == WATER){
                    board[r][c] = SHIP; 
                    r+=rowAdd; c+=colAdd;curSize++;}
                else if(board[r][c] == SHIP){
                    while(curSize != 1){
                        board[r][c] = WATER; 
                        r-=rowAdd; c-=colAdd;curSize--;}
                    isBuilt = false; break;}
                else{                
                    isBuilt=true;}
                if(curSize>shipsToBuild[pos]){isBuilt = true;}}
            catch(Exception e){
                r-=rowAdd; c-=colAdd;
                while(curSize != 1){
                    board[r][c] = WATER; 
                    button[r][c].setBackground(new Color(114,195,249));
                    r-=rowAdd; c-=colAdd;curSize--;}
                    isBuilt = false; break;}
        }
        if(isBuilt ==false){curSize=1; newPos(pos);}
        curSize=1;
    }     
            
    /**
     * This method creates a new position on the game board
     * where the enemy ship shall be placed.
     */
    public void newPos(int i){
        int xPos = (int)(Math.random()*(xSize)+1);
        int yPos = (int)(Math.random()*(ySize)+1);
        
        buildBoat(xPos,yPos, i);        
    }
   
    
    /**
     * Reveals the solution instead of the active game. 
     * That is, it returns a visual representation of the board revealing where the boats are. 
     * It will be called if the user wishes to quit before the game is over. 
     */
    protected void showSolution(){
        for(int r = 0; r < xSize; r++){
            for(int c = 0; c < ySize; c++){
                if(board[r][c] == SHIP){
                    button[r][c].setBackground(Color.red);
                    }
                board[r][c] = END;
            }
        }
    }
        
    /**
     * This method is called everytime the timer goes off.
     * It makes sure the options window is right next to 
     * the game board.
     */
    public void actionPerformed(ActionEvent e) {
        try{
            Rectangle bounds = screen.getBounds();
            pause.setLocation(bounds.x + bounds.width + 10, bounds.y);
        }
        catch(Exception exept){
            Rectangle bounds = end.getBounds();
            pause.setLocation(bounds.x + bounds.width + 10, bounds.y);            
        }
    }
       
    
    /**
     * The following methods override the mouse listener methods
     */
    public void keyPressed(KeyEvent e) {/**nothing yet! :-)*/}
    public void mouseClicked(MouseEvent e){/**nothing yet! :-)*/}
    public void mouseReleased(MouseEvent e) {/**nothing yet! :-)*/}
    public void mouseExited(MouseEvent e) {/**nothing yet! :-)*/}
    public void mouseEntered(MouseEvent e) {/**nothing yet! :-)*/}
    public void mousePressed(MouseEvent e){/**nothing yet! :-)*/}
    
    /**
     * Checks to see if all enemy ships were hit.
     * Returns true or false.
     */
    protected boolean checkForWin(){
        return numHit >= 16;
    }
    
    /**
     * Resets the game; disposes the game and the options menu, 
     * and returns to the "home" screen.
     */
    public void reset(){        
        screen.dispose();
        pause.dispose();
        new startBoard();
    } 
        
}