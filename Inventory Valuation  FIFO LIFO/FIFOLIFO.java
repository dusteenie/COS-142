
/**
 * FIFOLIFO is a driver class which usees my stack and queue structures to store and retrieve inventory,
 * according to the entries in the inventory data file. This driver will process all of the files 
 * provided as command line arguments.
 *
 * @author Dusty Stepak
 * @version 05.02.2019
 */
import java.util.Scanner;
import java.io.*;
public class FIFOLIFO
{
    public static Scanner sc;          // Used for User-Input
    private static Scanner scanData;    //Used tp read the data file
    
    /**
    * This is the main method of the driver class. This method prompts the user for
    * input, and then uses that data to access the neccessary file for information.
    * Afterwards, this method will proccess the information, and print both
    * FIFO and LIFO accounting information.
    *
    * @param        None, this is the main method.
    * @return       None, this is the main method; see description.
    */
    public static void main(String[] args) 
    { 
        String rawDataFile; 
        sc = new Scanner(System.in);
        
        // Prompts the user for input
        System.out.println("  ----    ----    ----    ----    ----    ----\n"+
                           "NOTE: Please have all .dat files located\n" + 
                           "      within the same folder as this program.\n"+
                           "  ----    ----    ----    ----    ----    ----\n"+
                           "PROPER FILE NAME INPUT EXAMPLE:\n"+
                           "Example: data1.dat\n"+
                           "Please Input File Name: data1.dat\n"+
                           "  ----    ----    ----    ----    ----    ----");                         
        
        System.out.print("Please Input File Name: ");
        
        // Checks to see if the user input is a valid file name.
        // if so, then the system calls for processData(), and processess the given file.
        try{            
            rawDataFile = sc.nextLine();
            File data = new File(rawDataFile);
            scanData = new Scanner(data);
            System.out.println("\n\n----    ----    ----    ----    ----    ----");
            processData();
        }
        catch(Exception e){System.out.println("ERROR: Invalid File Name!");} 
    }
    
    /**
    * processData() is a method which proccesses all the lines within a given file.
    * This method determins if the user has brought / sold an item,
    * how much profit they made for that individual sale, as well as the net profit.
    * Afterwards, this method calls on report(param,param,param,param) to print the results.
    *
    * @param                                None.
    * @return                               void, see description.
    * @exception    EmptyQueueException     This exception is thrown when an empty queue is being minipulated.
    * @exception    EmptyStackException     This exception is thrown when an empty stack is being minipulated.
    * 
    */
    private static void processData() throws EmptyStackException,EmptyQueueException
    { 
        // Creates a queue as well as a stack 
        myQueue fifo = new myQueue();
        myStack lifo = new myStack();
        
        // Variables which will hold net-sales
        int fifoNet = 0; int lifoNet = 0;
        double total = 0;
        
        // Proccesses each line within the data file
        while(scanData.hasNextLine()){
            // Proccesses lines
            String history[] = scanData.nextLine().split(":");  
            String type = history[0];
            int limit = Integer.parseInt(history[1]);
            int initCost;
            
            // Checks to see if the user has brought inventory. If not, then the user has sold something.
            if(type.charAt(0)=='B'){   
                // Updates both the queue and stack with the new inventory.
                for(int i = 0; i<limit;i++){
                    fifo.enqueue(history[2]);
                    lifo.push(history[2]); 
                }
                
            }
            else{
                
                for(int i = 0; i<limit;i++){
                    // Updates average information
                    total++;
                    
                    // Udates FIFO accounting information
                    String temp = "";
                    temp = "" + fifo.dequeue();
                    initCost = Integer.parseInt(temp);
                    temp = "";
                    temp = "" + history[2];
                    fifoNet += (( Integer.parseInt(temp)) - initCost);
                     
                    // Updates LIFO accounting information
                    temp = "";
                    temp = lifo.pop() + "";
                    initCost = Integer.parseInt(temp);
                    temp = "";
                    temp = "" + history[2];
                    lifoNet += (( Integer.parseInt(temp)) - initCost);
                }
                
            }
        }
        
        // Generates the resulting report.
        report(fifoNet,lifoNet,((1.0*fifoNet)/total),((1.0*lifoNet)/total));
    }
    
    /**
    * Decides which accounting method will maximize profit, and prints a generated report 
    * based on the found data.
    *
    * @param    fifoNet     an int containing the net profit for the fifo accounting method.
    * @param    lifoNet     an int containing the net profit for the lifo accounting method.
    * @param    fAverage    A double which contains the average profit generated for the 
    *                       fifo accounting method. This is calculated within the method call.
    * @param    lAverage    A double which contains the average profit generated for the 
    *                       lifo accounting method. This is calculated within the method call.
    * @return               void, see description.
    */
    private static void report(int fifoNet, int lifoNet, double fAverage, double lAverage){
        // Reccomends an accounting method for maximum profit.
        String choice = "";        
        if(fifoNet==lifoNet){
            choice = "Either.";
        }
        else if(fifoNet > lifoNet){
            choice = "FIFO.";
        }
        else{
            choice = "LIFO.";
        }
        
        // Prints the "report."
        System.out.println("----    ----    ----    ----    ----    ----\n"+
                           "ACCOUNT METHOD: FIFO\n\tNet-Sales: $" + fifoNet + "\n" +
                           "\tAverage-Income: $" + fAverage + "\n" +
                           "ACCOUNT METHOD: LIFO\n\tNet-Sales: $" + lifoNet + "\n" +
                           "\tAverage-Income: $" + lAverage + "\n" +
                           "RECCOMENDED METHOD: " + choice);
    }
}
