

public interface Stack <E> {

	public void push(E a);
	public E pop() throws EmptyStackException;
	public E top() throws EmptyStackException;
	public int size();
	public boolean isEmpty();

}
