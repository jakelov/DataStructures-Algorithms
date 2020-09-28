import java.lang.Integer;

public class Prog {

	public static void main(String [] args) {
			if (args.length != 2) {
			System.out.println("Please execute: java Prog <n> <p>");
			System.out.println("n is the input size, and p is the program number.");
			System.exit(0);
		}

		int n = Integer.parseInt(args[0]);
		int p = Integer.parseInt(args[1]);

		switch(p) {
			case 1: prog1(n);
							break;
			case 2: prog2(n);
							break;
			case 3: prog3(n);
							break;
			case 4: prog4(n);
							break;
			default: System.out.println("Invalid program number. Only 1-4.");
		}
	}

	private static void prog1(int n) {
		// TODO: Code to generate n keys that all get hashed to the same index using hash1.
		for (int i = 0; i < n; i++)
		{
			// if i = 0, n = 10, we get 1 * 10 = 10, when modded by 10, is 0. 2 * 10 = 20, mod by 10 = 0. etc.
			System.out.println(n * i);
		}	
	}

	private static void prog2(int n) {
		// TODO: Code to generate n keys that all get hashed to the same index using hash2.	
		for (int i = 0; i < n; i++)
		{ 
			// since it is (n/n^2) * k, goes to 1/n * k, which is k/n (all floored), 
			// if k = 0, n = 10, 0/10 floored = 0, 1/10 floored = 1, etc. 
			// k is i, so if we iterate all the way to before n and floor it, we are in the same index 0
			System.out.println(i); 
		}	
	}
	
	private static void prog3(int n) 
	{
		// TODO: Code to generate n keys that all get hashed to the same index using hash3.
        Node[] low;
        int p = 128189;
        HashFunctions hs = new HashFunctions(n);
		
		if (n*n > p)
		{       
            low = new Node[p];
        for (int i = 0; i < p; i++)
         {
            low[i] = new Node(0, null); 
         }
            for(int i = p; i >= 0; i--)
            {
                int hashed = hs.hash3(i);
                low[hashed].setDigit(low[hashed].getDigit() + 1);
                Node newNode = new Node(i,low[hashed].getNext());
                low[hashed].setNext(newNode);
            }         
       }
       else 
       {
           low = new Node[n];
        for	(int i = 0; i < n; i++)
         {
            low[i] = new Node(0, null); 
         }
            for	(int i = n*n; i >= 0; i--)
            {
                int hashed =  hs.hash3(i);
                low[hashed].setDigit(low[hashed].getDigit() + 1);
                Node moo = new Node(i,low[hashed].getNext());
                low[hashed].setNext(moo);
            }
       }
        Node greatest = low[0];
        int index = 0;
        for (int i = 0; i < n; i++)
        {
           if(greatest.getDigit() < low[i].getDigit())
           {
            greatest = low[i];
            index = i;
           }
        }

        Node current = greatest.getNext();

        int count = 0;
        if (n*n > p)
        { 
           
            int x = 0;
            int bad = 0;
            while (x < n)
            {    
                int k = 0; 
                while (bad < n*n)
                { 
                    bad = current.getDigit() + (p * k);
                    System.out.println(bad); 
                    k++; 
                    x++;
                    if (x == n)
                    {
                        break;
                    }
                }
                if (current.getNext() != null) 
                    current = current.getNext();
                bad = 0;
            }
        }
        else
        {
			greatest = greatest.getNext();
            while (greatest.getNext() != null && count != n)
            {
                System.out.println(greatest.getDigit());
                greatest = greatest.getNext();
                count++;
            }
        }
	}

	private static void prog4(int n)
	{
		// TODO: Code to generate n keys that all get hashed to the same index using hash4.	
		Node [] ht = new Node[n];
		HashFunctions h = new HashFunctions(n);
		int [] c  = new int [n];
		int i = 0;
		int hash = 0;
		int MAX = n*n;
        for (i = 0; i < MAX; i++)
		{
			hash = h.hash4(i);
			ht[hash] = new Node(i, ht[hash]);
			c[hash] ++;
			if (c[hash] == n)
				break;
		}
		for (Node cur = ht[hash]; cur != null; cur = cur.getNext())
		{
			System.out.println(cur.getDigit());
		}
	}
}
