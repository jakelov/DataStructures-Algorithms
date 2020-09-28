import java.util.Scanner;
public class Jugs
{
    boolean debug = false;
    int ptr = 0; String [] history;
    int aMax; int bMax; int c; boolean[][] visited;
    public Jugs(int a, int b, int c) {
        this.aMax = a;
        this.bMax = b;
        this.c = c;
        this.visited = new boolean[a+1][b+1];
        this.history = new String[1000];
    }
    public void debugPrint(String text, Object... args) {
        if (debug) System.out.printf(text, args);
    }

    private boolean dfs(int a, int b)
    {
        if (a > this.aMax || a < 0) { debugPrint("Invalid Bounds\n"); return false; }
        if (b > this.bMax || b < 0) { debugPrint("Invalid Bounds\n"); return false; }
        if (a + b == this.c) { debugPrint("SOLUTION FOUND WITH A: %d + %d = %d\n", a, b, c); return true; }
        if (visited[a][b]) { debugPrint("VISITED ALREADY \n"); return false;  }
        // Else, we need to call recursively to find solutions.
        visited[a][b] = true;
        boolean valid = false;
        valid = dfs(aMax, b); // Fill A
        if (valid) { this.push(String.format("Fill Jug 1 [a = %d, b = %d]", aMax, b)); return true; }

        valid = dfs(a, bMax); // Fill B to Max
        if (valid) { this.push(String.format("Fill Jug 2 [a = %d, b = %d]", a, bMax)); return true; }

        valid = dfs(0, b); // Empty A or 1
        if (valid) { this.push(String.format("Empty Jug 1 [a = %d, b = %d]", 0, b)); return true; }

        valid =  dfs(a, 0); // Empty B or 2
        if (valid) { this.push(String.format("Empty Jug 2 [a = %d, b = %d]", a, 0)); return true; }
        int newA = a; int newB = b;
        while (newA > 0 && newB < bMax) { // Pour A -> B until constraints are reached
            newA--; newB++; 
        }
        valid = dfs(newA, newB);
        if (valid) { this.push(String.format("Pour Jug 1 -> Jug 2 [a = %d, b = %d]", newA, newB)); return true; }

        newA = a; newB = b;
        while (newB > 0 && newA < aMax) { // Pour B -> A until constraints are reached
            newA++; newB--;  
        }
        valid = dfs(newA, newB);
        if (valid) { this.push(String.format("Pour Jug 2 -> Jug 1 [a = %d, b = %d]", newA, newB)); return true; }

        return false;
    }

    public static void main(String [] args) {
        Scanner kb = new Scanner(System.in);
        int a; int b; int c;
        System.out.print("Enter A: "); a = kb.nextInt(); 
        System.out.print("Enter B: "); b = kb.nextInt(); 
        System.out.print("Enter C: "); c = kb.nextInt();
        kb.close();
        if (a < 0 || b < 0 || c < 0) return;
        Jugs J = new Jugs(a, b, c); // make object with user input
        boolean isValid = J.dfs(0, 0);
        if (isValid) {
        System.out.println("Yay! Found a solution.");
        while (J.isEmpty() == false) {
            System.out.println(J.pop()); 
        }
    }
        //J.debugPrint("\nCombo: " + "A: " + a + " + " + "B: " + b + " = C: " + c); }
        else System.out.println("Impossible!");
        

    }
    public void push(String pushThis)
    {
        history[ptr++] = pushThis;
        //ptr++;
    }
    public String pop()
    {
        //if (isEmpty()) return "";
        String returnThis = history[--ptr];
        //if (returnThis == null) returnThis = "";
        //ptr--;
        return returnThis;
    }
    public boolean isEmpty()
    {
        if (ptr == 0) return true;
        else return false;
    }
}