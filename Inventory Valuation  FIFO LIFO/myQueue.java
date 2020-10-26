
/**
 * MyQueue is a queue which follows the FIFO accounting method.
 * This class creates and manages the queue, and implements the
 * Queue interface. 
 *
 * @author      Dusty Stepak
 * @version     05.02.2019
 */

import java.util.LinkedList;
public class myQueue <E> implements Queue <E>
{
    // Creates the queue
    LinkedList<E> queue;
    
    /**
     * myQueue() is the constructor for myQueue.java
     * This constructor initalizes the linked list.
     * 
     * @param    None.
     */
    public myQueue(){
        queue = new LinkedList<E>();
    }
    
    /**
     * enqueue(param) is a method which adds items to the queue.
     * 
     * @param   a   This paramater is of type E. The element is then added to the queue.
     * @return      void.
     */
    public void enqueue(E a){
        queue.addLast(a);
    }
    
    /**
     * dequeue() is a method which removes and returns the first 
     * element within the queue.
     * 
     * @param                               None, see description.
     * @return                              Returns the first element in the queue by removing it.
     *                                      This return is of type E.
     * @exception   EmptyQueueException     This exception is thrown if the queue is empty
     */
    public E dequeue() throws EmptyQueueException{
        if(isEmpty()==true)
            throw new EmptyQueueException("ERROR: The Queue is empty! :-(");
        return queue.removeFirst();    
    }
    
    /**
     * front() is a method which returns the first element within the queue, without
     * removing it.
     * 
     * @param                               None, see description.
     * @return                              Returns the first element in the queue and does
     *                                      NOT remove it. This return is of type E.
     * @exception   EmptyQueueException     This exception is thrown if the queue is empty
     */
    public E front() throws EmptyQueueException{
        if(isEmpty()==true)
            throw new EmptyQueueException("ERROR: The Queue is empty! :-(");
        return queue.getFirst();
    }
    
    /**
     * size() is a method which returns the size of the queue.
     * 
     * @param       None, see description.
     * @return      Returns the size of the queue. This return is of type int.
     */
    public int size(){
        return queue.size();
    }

    /**
     * isEmpty() is a method which returns T/F if the queue is empty.
     * 
     * @param       None, see description.
     * @return      Returns whether or not if the queue is empty.
     *              This return is of type boolean.
     */
    public boolean isEmpty(){ 
        return (queue.size() == 0);
    }

}
