import java.util.Scanner;
public class Collatz {
    public long[] sol;
    // Constructor
    public Collatz(int size)
    {
        this.sol = new long [size];
    }
    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in); 
        long length = 0; 
        System.out.print("Enter the range: "); // ask for user input
        int a = kb.nextInt();
        int b = kb.nextInt();
        // all the bad cases where we don't want to check those weird bounds.
        if ((b < a) || (a <= 0 && b <= 0) || (a <= 1 && b <= 0) || (a <= 0 && b <= 1) || (a > 100000000 || b > 100000000)) 
            {
                System.out.println("Error occured: invalid input. Please submit two integers from 1 to 100000000, where the first number is smaller/equal to the second.");
                kb.close();
                return;
            }
        Collatz C = new Collatz(b); // create object
        kb.close();
        long bigLength = 0; // the greatest collatz length from a given n
        int greatestNum = a; // said given n from bigLength
        for (int i = a; i <= b; i++) 
        // this will iterate down the array, call collatzLength at every index, Store the lengths
        // and keep track of the largest n and the greatest collatzLength of that largest n
        {
            length = C.collatzLength(i); // First call to recursive function
            //System.out.println("\nCount is : " + length);
            if (length > bigLength) // if this iteration produced the largest collatz length, store i and the length.
            {
                bigLength = length;
                greatestNum = i;
            }
            //System.out.println("The collatz length of " + i + " is: " + length);
        }
        System.out.println("The number with the maximum Collatz length in the range: " + greatestNum);
        System.out.println("Its Collatz length: " + bigLength);
    }

    // Recursive method to find collatzLength of a given N.
    public long collatzLength(long n)
    {
        long length = 0;
        // boolean flag = true; // debug flag
        int switchVar;
        if (getLengthAt(n) != 0)  //System.out.print(n + " "); 
            return getLengthAt(n);
        // if it exists already, use it to not be redundant.
        else
        // if sol[n] is not initialized or default set to 0
        {
            switchVar = collHelper(n);
            //System.out.print(n + " ");
            switch (switchVar) {
                case (1): { return n; }
                case (2): { n = n/2; break; }
                case (3): { n = 3*n + 1; break; } 
            }
            length += collatzLength(n); // changed = to +=
            //setIndexLength(n, length);
            return length + 1;
        }   
    }
    // Case helper to make sure every recursive call only changes the number once.
    public int collHelper(long n)
    {
        int returnThis = 0;
        if (n == 1) { return returnThis = 1; }
        if (n % 2 == 0) { return returnThis = 2;  }
        if (n % 2 != 0) { return returnThis = 3;  } 
        return returnThis;
    }

    // Helper method to see if the indexLength is initialized, and returns what it's initialized too.
    public long getLengthAt(long index) {
        if (index >= sol.length)
        {
            return 0;
        }
        return sol[(int)index];
    }
}