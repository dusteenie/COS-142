
 /**
  * startBoard.java is an application which initalizes the battleship game board.
  * This class prompts the user for vital information used for the game.
  * Afterwards, this class calls on BattleshipBoard.java
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


public class startBoard extends JPanel 
{
    JFrame start,init;
    JPanel[][] panelHolder;
    int x,y;
    JButton newGame = new JButton("New Game");
    JButton customGame = new JButton("Custom");
    JButton launchGame = new JButton("Start Game");  
    JButton xTxtVal = new JButton("Set X");
    JButton yTxtVal = new JButton("Set Y");
    protected JTextField xTxt,yTxt;
    
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
            // Creates a new game
            if (e.getActionCommand().equals("New Game")) {
                finishStartUP(); 
            }
            // Creates a custom game
            if (e.getActionCommand().equals("Custom")){
                startConfig();
            }
            // Starts the game if the user created a custom game
            if (e.getActionCommand().equals("Start Game")){
                startBuild();
            }
            
            // Sets the game's x-length
            if (e.getActionCommand().equals("Set X")){
                try{
                    x = Integer.parseInt(xTxt.getText());
                    if(x>=15) {
                        xTxt.setText("Set!"); 
                    }
                    else{
                        x = -1;
                        xTxt.setText("Invalid Int");
                    }
                }
                catch(Exception exp){
                    x = -1;
                    xTxt.setText("Invalid Int");}
                }
            // Sets the game's y-length        
            if (e.getActionCommand().equals("Set Y")){
                try{
                    y = Integer.parseInt(yTxt.getText());
                    if(y>=15) {
                        yTxt.setText("Set!"); 
                    }
                    else{
                        y = -1;
                        yTxt.setText("Invalid Int");
                    }
                }
                catch(Exception exp){
                    y = -1;
                    yTxt.setText("Invalid Int");}
                }
        }
    }
        
    /**
     * Constructor for objects of class startBoard.
     */
    public startBoard()
    {
        x = -1;
        y = -1;
        
        initStartScreen();        
        panelHolder = new JPanel[4][3];
        
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 3; c++){
                panelHolder[r][c] = new JPanel();
                start.add(panelHolder[r][c]);
            }
        }
                
        panelHolder[1][1].add(new Label("BattleShip"));
        panelHolder[1][1].add(new Label("Select a Mode"));
        newGame.addActionListener(new ButtonListener());
        customGame.addActionListener(new ButtonListener());        
        
        panelHolder[2][1].add(newGame);
        panelHolder[2][1].add(customGame);
        
        start.setVisible(true);
    }
    
    /**
     * Initalizes the start JFrame, as well as adds a 3x3 grid layout to it. 
     * This screen is the "home" screen of the game.
     */     
    private void initStartScreen(){
        start = new JFrame("B A T T L E S H I P    -    S T A R T U P");
        start.setSize(450,250);
        start.setResizable(false);        
        start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        start.setLayout(new GridLayout(3,3));
    }
    
    /**
     * Initalizes the init JFrame, which is used to configure the game.
     * This method also adds a 4x3 grid layout to this JFrame. 
     */ 
    private void initConfigScreen(){
        init = new JFrame("B A T T L E S H I P    -    C O N F I G");
        init.setSize(450,250);
        init.setResizable(false);        
        init.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        init.setLayout(new GridLayout(4,3));
        
        
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 3; c++){
                panelHolder[r][c] = null;
                panelHolder[r][c] = new JPanel();
                init.add(panelHolder[r][c]);
            }
        }    
    }
    
    /**
     * This method disposes the start screen, loads the cofigure screen, and then adds 
     * user interface materials to it. 
     */
    private void startConfig(){
        start.dispose();
        initConfigScreen();
        
        panelHolder[0][1].add(new Label("Build Your Board!"));
        panelHolder[0][1].add(new Label("MINIMUM SIZE: 15 x 15"));
        
        panelHolder[1][0].add(new Label("X-Size"));
        xTxt = new JTextField("Enter an Int X.");
        panelHolder[1][1].add(xTxt);
        xTxtVal.addActionListener(new ButtonListener());
        panelHolder[1][2].add(xTxtVal);
        
        panelHolder[2][0].add(new Label("Y-Size")); 
        yTxt = new JTextField("Enter an Int Y.");
        panelHolder[2][1].add(yTxt); 
        yTxtVal.addActionListener(new ButtonListener());
        panelHolder[2][2].add(yTxtVal);      
        
        launchGame.addActionListener(new ButtonListener());
        panelHolder[3][1].add(launchGame);
        
        init.setVisible(true);
    }
    
    /**
     * This method disposes the configure screen, and then loads the game.
     * This method is called on within class ButtonListener()
     */
    private void startBuild(){
        init.dispose();
        loadGame();
    }
    
    /**
     * This method disposes the start screen, and then loads the game.
     * This method is called on within class ButtonListener()
     */
    private void finishStartUP(){ 
        start.dispose();
        loadGame();
    }
    
    /**
     * This method calls on class BattleshipBoard to create and play the game.
     * Depending on the settings the user had selected, the proper argument is passed 
     * for the sizee of the board.
     */
    private void loadGame(){
        if(x == -1 && y == -1)
            new BattleshipBoard();
        if(x!=-1 && y == -1)
            new BattleshipBoard(x);
        if(y!=-1 && x == -1)
            new BattleshipBoard(y);
        if(x!=-1 && y!=-1)
            new BattleshipBoard(x,y);

    }
}
