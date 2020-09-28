import java.util.Scanner;
/**
	This class implements a circular array.
	It expands if the queue becomes full.
**/
public class Queue {
	private QNode [] queue;		// Array that stores the queue elements.
	private int front;			// Index pointing to the front of the queue.
	private int end;			// Index pointing to the element that is one less than the end of the queue.
	private int numElements;	// The number of elements currently stored in the queue.
	private int size;			// The capacity of the allocated array. If the number of elements reaches this capacity, we need to expand.
	private boolean debug = true;
	/**
		Constructor: Allocates a queue with inital size of 1000.
	**/
	public Queue() {
		numElements = 0;
		size = 1000; // change to 1000
		front = size - 1;
		end = size - 1;
		queue = new QNode[size];
	}
	
	/**
		This function enqueues a new element p into the queue. 
		This also expands the array if it is full.
	**/
	public void enqueue(QNode p) {
		if (numElements == size) {
			
			// Expand the array, by first creating another one with twice the size and copying the contents of the old array
			QNode[] newQueue  = new QNode[size * 2];
			copyArray(newQueue);
		}
		//if (debug) System.out.println("DEBUG PRINT: Adding element to " + end + " " + p.getWord());
        //  Code for normal enqueue. Increment num elements
        if (numElements != 0) {
		for (int i = front; i <= end; i++)
			queue[i - 1] = queue[i];
        front--;
        }
		queue[end] = p;
		numElements++;
	}

	/**
		This function removes and returns the end front element in the queue.
	**/
	public QNode dequeue() {
		if (numElements == 0) {
			return null;
		}
		// Code to remove and return the front element and decrement num elements
		QNode removeNode = queue[front]; // save then remove.
		queue[front] = null;
		if (numElements > 1) front++;
		numElements--;;
		return removeNode; // return the removed Node.
	}

	/**
		This function returns true if the queue is empty, otherwise returns false.
	**/
	public boolean isEmpty() {
		if (numElements == 0) 
			return true;
		return false;
	}

	/**
		This function prints the contents of the queue.
	**/
	public void print() {
		// print the contents of the queue from front to end.
		for (int i = front; i <= end; i++)
		{	
			System.out.println(queue[i].getWord());
		}
	}

	/**
		This function copies the contents of the array passed in, into the queue.
		This is usually called when expanding the array size.
	**/
	private void copyArray(QNode [] array) {
		// Code to copy the array into queue. Make sure that the queue parameters -- front, end and numElements and size are all set correctly.
		for (int i = end, p = (this.size * 2) -1; i >= front; i--, p--) {
			array[p] = new QNode(queue[i]);
        if (i == front) front = p; 
        }
		queue = array;
		size = size * 2;
		end = size - 1;
	}
	// public static void main(String [] args)
	// {
	// 	Queue nq = new Queue();
	// 	Scanner kb = new Scanner(System.in);
	// 	int distance = 0; String input; 
	// 	if (nq.debug) System.out.println("Enter dist and word: ");
	// 	distance = kb.nextInt(); input = kb.nextLine();
	// 	QNode inputNode = new QNode(distance, input);
	// 	nq.enqueue(inputNode);
	// 	nq.enqueue(new QNode(1, "Hi"));
	// 	nq.enqueue(new QNode(2, "Hey"));
	// 	nq.enqueue(new QNode(3, "Hello"));
	// 	nq.enqueue(new QNode(4, "Howdy"));
	// 	nq.print();
	// 	if (nq.debug) System.out.println("front: " + nq.getFront() + " end: " + nq.getEnd() + " size: " + nq.size + " #elements: " + nq.numElements);
		
    // }
}
