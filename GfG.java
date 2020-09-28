import java.util.Scanner;
public class GfG
{ 
  
public int n = 0;  
public int k = 0; 
public int[][] board;
public int count = 0;

public GfG(int size)
{
    this.n = size;
    this.k = 1;
}

/* A helper function to print solution */
public void printBoard()  
{  
    System.out.printf("%d-\n", this.k++);  
    for (int i = 0; i < n; i++)  
    {  
        for (int j = 0; j < n; j++)  
            System.out.printf(" %d ", this.board[i][j]);  
        System.out.printf("\n");  
    }  
    System.out.printf("\n");  
}  
  
/* Helper function to check if a queen can  
be placed on board[row][col]. This  
function is called when "col" queens are  
already placed in columns from 0 to col -1.  */

public boolean checkValid(int row, int col)  
{  
    int i, j;  
  
    // Check this row on left side, goes >>>>>>
    for (i = 0; i < col; i++)  
        if (this.board[row][i] == 1)  
            return false;  
  
    // Check upper diagonal on left side, goes <^
    for (i = row, j = col; i >= 0 && j >= 0; i--, j--)  
        if (this.board[i][j] == 1)  
            return false;  
  
    // Check lower diagonal on left side, goes <down
    for (i = row, j = col; j >= 0 && i < n; i++, j--)  
        if (this.board[i][j] == 1)  
            return false;  
    return true;  
}  
  
// Recursive function that calls helper methods to solve the problem
public boolean checkBoard(int col)  
{  
    // if n == col, we have placed correct queens.
    if (col == n)  
    {  
        //printBoard();  
        this.count++;
        return true;  
    }  
    /* Consider this column and try placing  
    this queen in all rows one by one */
    boolean flag = false;  
    for (int i = 0; i < n; i++)  
    {  
        /* Check if queen can be placed on  
        board[i][col] */
        if (checkValid(i, col) )  
        {  
            /* Place this queen in board[i][col] */
            board[i][col] = 1;  
  
            // return true if recursive function returns true 
            flag = checkBoard(col + 1) || flag;  
  
            /* If placing queen in board[i][col]  
            doesn't lead to a solution, then  
            remove queen from board[i][col] */
            this.board[i][col] = 0; // BACKTRACK  
        }  
    }  
    /* If queen can not be place in any row in  
        this column col then return false */
    return flag;  
}  
  
/* This function solves the n Queen problem.
It mainly uses checkBoard() to  
solve the problem.
Returns false if queens cannot be placed, 
Otherwise return true and prints the board.  
This function only prints ONE solution*/
public void solveNQ()  
{ 
    if (checkBoard(0) == false)  
    {  
        System.out.println("Solution does not exist");  
        return;
    }  
    return;
}  
  
// Driver code  
public static void main(String[] args) 
{ 
    Scanner kb = new Scanner(System.in);
    System.out.print("Enter the number of queens: ");
    int size = kb.nextInt(); 
    kb.close();
    GfG nq = new GfG(size);
    if (size <= 0) // base case for invalid size.
    {
        System.out.println("The number of valid arrangements is " + (nq.count));
        return;
    }
    nq.board = new int[size][size];
    nq.n = size;
    nq.k = 1;
    nq.solveNQ(); 
    System.out.println("The number of valid arrangements is " + (nq.count));
    } 
} 
