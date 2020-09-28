
public class PriorityQueue {
	private Interval [] heap; // An array that encodes a max-heap data structure.
	private int size;	// The size of allocated buffer for the heap.
	private int numElements;	// The number of elements currently stored. 

	/**
		Constructor: s is the initial size of the heap.
	*/
	public PriorityQueue(int s) {
		size = s;
		heap = new Interval[size + 1];	// 1 extra element allows us to use 1-based indexing. The max heap stores intervals keyed on their lengths.
		numElements = 1;
	}

	/**
		Inserts a new Interval k into the heap. Automatically expands the heap if the buffer allocated is full.
	*/
	public void insert(Interval k) {
		if (numElements == size) {
			int newSize = this.size * 2;
			Interval[] heap2 = new Interval[newSize];
			// Expand the buffer allocated for the heap to another buffer that is twice as big.
			for (int i = 1; i < numElements; i++)
			{
				heap2[i] = heap[i];
			}
			this.heap = heap2;
			this.size = newSize;
		}
		// Insert without buffer expansion here.
		this.heap[numElements] = k;
		siftUp(numElements);
		numElements++;
		return;
	}

	// Sift up while heap property is violated
	public void siftUp(int i) {
	int parent = i / 2;
	if (parent <= 0 ) return;
	if (heap[i].compareTo(heap[parent]) > 0) {
		Interval temp = heap[i];
		heap[i] = heap[parent];
		heap[parent] = temp;
		siftUp(parent); // recursive call to continue up on the parent.
		}
	}

	// Sift down while heap property is violated
	public void siftDown(int i) {
	int left = 2 * i;
	int right = 2 * i + 1;
	if (left >= numElements) return; // if no left child, can't swap. (leaf)
	if (right >= numElements) { // if right is out of bounds, check left.
		if (heap[i].compareTo(heap[left]) < 0) // if left is ok, swap.
		{
			Interval temp = heap[i];
			heap[i] = heap[left];
			heap[left] = temp;
			siftDown(left); // see if child needs to swap with it's child.
		}
	}
	else
	{
		if(heap[left].compareTo(heap[right]) > 0)
		{
			if (heap[i].compareTo(heap[left]) < 0) // if left is ok, swap.
			{
			Interval temp = heap[i];
			heap[i] = heap[left];
			heap[left] = temp;
			siftDown(left); // see if child needs to swap with it's child.
			}
		}
		else
		{
			if (heap[i].compareTo(heap[right]) < 0) {
			Interval temp = heap[i];
			heap[i] = heap[right];
			heap[right] = temp;
			siftDown(right); // see if child needs to swap with it's child.
			}
		}
	}
}
	

	/**
		Returns the maximum Interval from the heap (usually the one with the largest length. See the compareTo function of Interval for more details on the comparison.
	TODO: Please complete this method.
	*/
	public Interval remove_max() {
		Interval root = heap[1];
		if (numElements == 1) return null; // Retuns null if heap is empty.
		// Remove_max code here.
		Interval temp = heap[numElements - 1];
		heap[numElements - 1] = heap[1];
		heap[1] = temp;
		numElements--;
		siftDown(1);
		return root; // Replace this statement with returning the max element (root) in the heap.
	}

	/**
		This function prints the contents of the array that encodes a heap.
	*/
	public void print() {
		System.out.println("Printing heap:");
		for (int i = 1; i < numElements; ++i)
			System.out.println(heap[i]);
	}
}
