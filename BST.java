public class BST {
	private Node root; // The root node of the tree.

	public BST() {
		root = null;
	}
	public static void main(String[] args)
	{
		// make a BST
BST bst = new BST();
// find the min
System.out.println("===MIN===");
System.out.println(bst.min());
// find the max
System.out.println("===MAX===");
System.out.println(bst.max());
// print tree
System.out.println("===PRINT===");
bst.print();
// insert testing data
System.out.println("===INSERTING===");
bst.insert(17, 0);
bst.insert(4, 0);
bst.insert(20, 0);
bst.insert(2, 0);
bst.insert(8, 0);
bst.insert(-1, 0);
bst.insert(6, 0);
bst.insert(12, 0);
bst.insert(11, 0);
bst.insert(15, 0);
bst.insert(25, 0);
bst.insert(21, 0);
bst.insert(23, 0);
// find the min
System.out.println("===MIN===");
System.out.println(bst.min());
// find the max
System.out.println("===MAX===");
System.out.println(bst.max());
// print tree
System.out.println("===PRINT===");
bst.print();
// find predecessors
for (int i=0; i <=26; i++) {
  System.out.println("===" + i + "'s Predecessor===");
  System.out.println(bst.pred(i));
}
// find successors
for (int i=-2; i <=26; i++) {
  System.out.println("===" + i + "'s Successor===");
  System.out.println(bst.succ(i));
}
// delete when not possible
System.out.println("===DELETE MISSING KEY (999)===");
bst.delete(999);
// print tree
System.out.println("===PRINT===");
bst.print();
// delete 11
System.out.println("===DELETE LEAF (11)===");
bst.delete(11);
// print tree
System.out.println("===PRINT===");
bst.print();
// delete 25
System.out.println("===DELETE LEFT CHILD (25)===");
bst.delete(25);
// print tree
System.out.println("===PRINT===");
bst.print();
// delete 21
System.out.println("===DELETE RIGHT CHILD (21)===");
bst.delete(21);
// print tree
System.out.println("===PRINT===");
bst.print();
// delete 17
System.out.println("===DELETE TWO CHILDREN (17)===");
bst.delete(17);
// print tree
System.out.println("===PRINT===");
bst.print();
	}
	//Note that the insert method should return the root node of the subtree in which we insert the key (and its value) 
	public void insert(int time, int req_index) {
	    root = insertNode(root, time, req_index);
	}
	private Node insertNode(Node rootSubtree, int time, int req_index) {
		if (rootSubtree == null)
			return new Node(time, req_index);
		else {
			if (rootSubtree.getTime() >= time) {
				// left subtree
				rootSubtree.setLeft(insertNode(rootSubtree.getLeft(), time, req_index));
				return rootSubtree;
			}
			else {
				// go down the right subtree
				rootSubtree.setRight(insertNode(rootSubtree.getRight(), time, req_index));
				return rootSubtree;
			}
		}
	}
	/**
		Returns a pointer to the Node that is the predecessor of time. The predecessor element is the largest
		element within the tree that is smaller or equal to time. This is the deepest ancestor with this property.
		Please return the predecessor element. You may return null if time does not have a predecessor.
	**/

	public Node pred(int time) {
        return predHelper(time, root, null);
        // this node will be the node that gets returned from every recursive call until the max node that is smaller then time is found.
	}
	private Node predHelper(int time, Node treeRoot, Node closest)
	{
		// base case or for empty tree
		if (treeRoot == null)
			return closest;
		// if time is found, the pred is max of left tree if it's there
		if (treeRoot.getTime() == time) 
			return treeRoot;
		else if (treeRoot.getLeft() != null)
				return findMax(treeRoot.getLeft());
		// if the key is less then, then we go down the left to find.
		else if (time <= treeRoot.getTime())
			return predHelper(time, treeRoot.getLeft(), closest);
		else {
		// or we go down the right if it's more, we set closest to current node we are recurring on
			closest = treeRoot;
			return predHelper(time, treeRoot.getRight(), closest);
		}
	}

	/**
		Returns a pointer to the Node that is the successor of time. The successor element is the largest
		element within the tree that is larger or equal to time. This is the deepest ancestor with this property.
		Please return the successor element. You may return null if time does not have a successor.
	**/
	public Node succ(int time) {
		return succHelper(time, root, null);
	}
	private Node succHelper(int time, Node treeRoot, Node closest)
	{
		// base case or for empty tree
		if (treeRoot == null)
			return closest;
		// if time is found, the succ is min of right tree if it's there
		if (treeRoot.getTime() == time) {
			if (treeRoot.getRight() != null)
				return findMin(treeRoot.getRight());
		}
		// if the key is less then, then we go down the RIGHT to find.
		else if (time > treeRoot.getTime())
			return succHelper(time, treeRoot.getRight(), closest);
		else {
		// or we go down the left if it's more, we set closest to current node we are recurring on
			closest = treeRoot;
			return succHelper(time, treeRoot.getLeft(), closest);
		}
		return closest;
	}
	public boolean isEmpty()
	{
		return this.root == null;
	}
	// Returns the minimum element in the binary search tree or null if the tree is empty.
	public Node min() {
        return findMin(this.root);
	}
	private Node findMin(Node rootSubtree)
	{
        // return null if BST is empty
        if (rootSubtree == null) {
            return null;
        }
        // find the deepest left node
		if (rootSubtree.getLeft() == null)
			return rootSubtree;
		else
			return findMin(rootSubtree.getLeft());
	}

	// Returns the maximum element in the binary search tree or null if the tree is empty.
	public Node max() { 
		return findMax(this.root);
	}
	private Node findMax(Node rootSubtree)
	{
        // check if empty
        if (rootSubtree == null) {
            return null;
        }
        // find the deepest right node
		if (rootSubtree.getRight() == null)
			return rootSubtree;
		else
			return findMax(rootSubtree.getRight());
	}

	// Remove the node that contains this time. If this time is not present in the structure, this method does nothing.
	public void delete(int time) {
	  	//System.out.println("Delete: " + deleteHelper(root, time));
	  	deleteHelper(root, time);
		return;
	}
	// PITA
	private Node deleteHelper(Node rootSubtree, int time) {
			// empty check
			if (rootSubtree == null) 
				return rootSubtree;
				// go find the node to delete on left side.
			else if (time < rootSubtree.getTime()) {
				rootSubtree.setLeft(deleteHelper(rootSubtree.getLeft(), time));
				return rootSubtree;
			}
				// else go right to find the node.
			else if (time > rootSubtree.getTime()) {
				rootSubtree.setRight(deleteHelper(rootSubtree.getRight(), time));
				return rootSubtree;
			}
			else // must handle the 3 cases since we found the Node.
			{
				// Node with no children
				if (rootSubtree.getLeft() == null && rootSubtree.getRight() == null) 
					return null;
				// Node with no left node
				else if (rootSubtree.getLeft() == null) 
					return rootSubtree.getRight();
				// node with no right node
				else if (rootSubtree.getRight() == null) 
					return rootSubtree.getLeft();
				// Node with two children
				else if (rootSubtree.getLeft() != null && rootSubtree.getRight() != null) 
				{
					Node Pred = pred(rootSubtree.getTime());
					// delete(Pred.getTime());
					// Node rootSubtreeLeft = rootSubtree.getLeft();
					// Node rootSubtreeRight = rootSubtree.getRight();
					// swapData(rootSubtree, Pred);
                    rootSubtree.setTime(Pred.getTime());
                    rootSubtree.setReq_index(Pred.getReq_index());
                    Node sl = deleteHelper(rootSubtree.getLeft(), Pred.getTime());
					rootSubtree.setLeft(sl);
 					return rootSubtree;
				}
			}
			return rootSubtree;
		}

	//helper method to swap time and req index
	public void swapData(Node a, Node b)
	{
		int tempTime = a.getTime();
		int tempIndex = a.getReq_index();
		a.setTime(b.getTime());
		a.setReq_index(b.getReq_index());
		b.setTime(tempTime);
		b.setReq_index(tempIndex);
	}
	//Prints the contents of the tree in sorted order.
	public void print() {
		// TODO: Code for print here.
		printTree(this.root);
	}
	// recursive function to print.
	private void printTree(Node treeRoot) {
		if (treeRoot == null)
		return;
		else {
			printTree(treeRoot.getLeft());
			System.out.println(treeRoot.toString());
			printTree(treeRoot.getRight());
		}
	}
}
