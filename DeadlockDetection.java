import java.util.Scanner;

public class DeadlockDetection
{
    static boolean [] visited;
    static boolean [] currVisited;
    static int stackCount = 0;
    static int [] stack;
    static boolean [][] board; //process im on.
    static int [] resources;
    static int n; static int m;
    static boolean debug = false;


public static void loadData() {
    Scanner kb = new Scanner(System.in);
    n = kb.nextInt(); // assign processes
    m = kb.nextInt(); // assign resources
    board = new boolean [(n + 1)][(n + 1)]; // process requires process (row x col) (6x6)
    resources = new int [m];
    for (int i = 0; i < m; i++) { // for resources
        resources[i] = kb.nextInt(); 
    }
    for (int i = 1; i <= n; i++) { // <=
        int processSize = kb.nextInt();
        for (int j = 0; j < processSize; j++) {
            int resNum = kb.nextInt();
            int process = resources[resNum - 1];
            board[i][process] = true; // process i needs process x to finish
            // grid is finished.
        }
    }
    kb.close();
    stack = new int[n];
    visited = new boolean [n + 1];
    currVisited = new boolean [n + 1];
}


public static void main(String [] args) {
    loadData();
    boolean status = run();
    if (status) { System.out.println("Deadlock detected. No Process can finish executing."); return; }
    else { 
        System.out.println("All process can finish executing. The order of execution is given as follows.");
        printStack();
    }
}

private static boolean dfs(int process) { // process is the node number i, j is the arrow to whatever process is here.
                                         // process is i is the process currently getting checked, if 1 2 is true, jump to 2
    if (visited[process])
        return false;
    if (currVisited[process])
        return true;
    boolean flag = false;
    currVisited[process] = true;

    for (int ptr = 1; ptr <= n; ptr++) {
        if (ptr == process) {     
        continue;
        }
        if (board[process][ptr]) {         
            flag =  flag || dfs(ptr);
       }
    }
    currVisited[process] = false;
    visited[process] = true;
    if (flag == false) {
        stack[stackCount] = process;
        stackCount = stackCount + 1;
    }
    return flag;
}

// returns if deadlocked.
public static boolean run() {
    boolean deadlocked = false;
    for (int i = 1; i <= n; i++) {
        deadlocked = deadlocked || dfs(i);
        }
    return deadlocked;
    }


public static void printStack() {
        for (int i = 0; i < stackCount; i++)
        {
            System.out.println(stack[i]);
        }
    }
}