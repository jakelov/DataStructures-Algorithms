
public class PrintDepth {
	private static int printdepth; // The depth to print all the elements.


	public static void main(String [] args) {
		MyTree T = new MyTree(); // Gets a Tree object.
		TreeNode root = T.getRoot(); // Gets the root of the tree.
		printdepth = 111; // sets the tree to depth 111.
	    printTreeDepth(root, 0);
	}
	// TODO: Please write a recursive function to print all nodes at level (or depth) 111, all sorted from right to left.
	public static void printTreeDepth(TreeNode root, int printd)
    { 
        if (root == null) 
            return; 
        if (printd == printdepth)  
        { 
            System.out.println(root.key); 
        }  
        else 
        { 
            printTreeDepth(root.right, printd + 1);
            printTreeDepth(root.left, printd + 1); 
        } 
    } 
}
