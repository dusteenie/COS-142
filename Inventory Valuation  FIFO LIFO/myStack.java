
/**
 * MyStack is a queue which follows the LIFO accounting method.
 * This class creates and manages the queue, and implements the
 * Stack interface. 
 *
 * @author      Dusty Stepak
 * @version     05.02.2019
 */

import java.util.LinkedList;
public class myStack <E> implements Stack<E>
{
    // Creates the stack
    LinkedList<E> stack;
    
    /**
     * myStack() is the constructor for myStack.java
     * This constructor initalizes the linked list.
     * 
     * @param    None.
     */
    public myStack(){
        stack = new LinkedList<E>();
    }
    
    /**
     * enqueue(param) is a method which adds items to the stack.
     * 
     * @param   a   This paramater is of type E. The element is then added to the stack.
     * @return      void.
     */
    public void push(E a){        
        stack.addFirst(a);
    }
    
    /**
     * pop() is a method which removes and returns the first element within the stack.
     * 
     * @param                               None, see description.
     * @return                              Returns the first element in the stack by removing it.
     *                                      This return is of type E.
     * @exception   EmptyStackException     This exception is thrown if the stack is empty
     */
    public E pop() throws EmptyStackException{
        if(isEmpty()==true)
            throw new EmptyStackException("ERROR: The stack is empty! :-(");
        return stack.removeFirst();
    }
    
    /**
     * top() is a method which returns the first element within the stack, without
     * removing it.
     * 
     * @param                               None, see description.
     * @return                              Returns the first element in the stack and does
     *                                      NOT remove it. This return is of type E.
     * @exception   EmptyStackException     This exception is thrown if the stack is empty
     */
    public E top() throws EmptyStackException{
        if(isEmpty()==true)
            throw new EmptyStackException("ERROR: The stack is empty! :-(");
        return stack.getFirst();
    }
    
    /**
     * size() is a method which returns the size of the stack.
     * 
     * @param       None, see description.
     * @return      Returns the size of the stack. This return is of type int.
     */
    public int size(){
        return stack.size();
    }

    /**
     * isEmpty() is a method which returns T/F if the stack is empty.
     * 
     * @param       None, see description.
     * @return      Returns whether or not if the stack is empty. This return is of type boolean.
     */
    public boolean isEmpty(){
        return stack.size() == 0;
    }
    
}
